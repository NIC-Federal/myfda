package com.nicusa.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Calendar;


@RestController
public class FeedController {

  private static final Logger log = LoggerFactory.getLogger(FeedController.class);

  @Autowired
  @Value("${api.fda.key}")
  private String fdaApiKey;

  @Autowired
  @Value("${rss.fda.recalls.url:http://www.fda.gov/AboutFDA/ContactFDA/StayInformed/RSSFeeds/Recalls/rss.xml}")
  private String fdaRecallsRSSurl;

  @Autowired
  @Value("${gl.api.xml2json.url:http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=10&q=}")
  private String xml2JsonCnvrtrUrl;

  @Autowired
  @Value("${api.fda.drug.enfrcmnt.url:https://api.fda.gov/drug/enforcement.json?}")
  private String searchDrugEnfrcmntUrl;

  private static String getDefaultFromDate() {
    Calendar now = Calendar.getInstance();
    StringBuilder fromDt = new StringBuilder().append(now.get(now.YEAR))
      .append((String.valueOf(now.get(now.MONTH) - 1).length() == 1) ? "0" + String.valueOf(now.get(now.MONTH) - 1) : String.valueOf(now.get(now.MONTH) - 1))
      .append(getCurrentDay());
    return fromDt.toString();
  }

  private static String getDefaultToDate() {
    Calendar now = Calendar.getInstance();
    StringBuilder toDt = new StringBuilder().append(now.get(now.YEAR))
      .append((String.valueOf(now.get(now.MONTH) + 1).length() == 1) ? "0" + String.valueOf(now.get(now.MONTH) + 1) : String.valueOf(now.get(now.MONTH) + 1))
      .append(getCurrentDay());
    return toDt.toString();
  }

  private static String getCurrentDay(){
    Calendar now = Calendar.getInstance();
    return (String.valueOf(now.get(now.DAY_OF_MONTH)).length() == 1 ? "0"+String.valueOf(now.get(now.DAY_OF_MONTH)): String.valueOf(now.get(now.DAY_OF_MONTH)));
  }

  @RequestMapping("/recalls")
  @ResponseBody
  public JsonNode getFDARecalls() throws IOException {
    RestTemplate rest = new RestTemplate();
    JsonNode node = null;
    ObjectMapper mapper = new ObjectMapper();
    log.info(xml2JsonCnvrtrUrl + fdaRecallsRSSurl);
    node = mapper.readTree(rest.getForObject(xml2JsonCnvrtrUrl + fdaRecallsRSSurl, String.class));

    return node;
  }

  @RequestMapping("/drug/recalls")
  @ResponseBody
  public JsonNode getDrugRecalls(@RequestParam(value = "limit", defaultValue = "5") int limit,
                                 @RequestParam(value = "skip", defaultValue = "0") int skip,
                                 @RequestParam(value = "fromDt", defaultValue = "") String fromDt,
                                 @RequestParam(value = "toDt", defaultValue = "") String toDt) throws IOException {
    RestTemplate rest = new RestTemplate();
    JsonNode node = null;
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchDrugEnfrcmntUrl)
      .queryParam("search", getReportDateQuery(fromDt, toDt))
      .queryParam("limit", limit)
      .queryParam("api_key", this.fdaApiKey);
    if (skip > 0) builder.queryParam("skip", skip);
    ObjectMapper mapper = new ObjectMapper();
    String json = rest.getForObject(builder.build().toUri(), String.class);
    node = mapper.readTree(json);
    return node;
  }

  @RequestMapping("/drug/enforcements")
  @ResponseBody
  public JsonNode getDrugRecallsForUnii(
          @RequestParam(value = "unii", defaultValue = "") String unii,
          @RequestParam(value = "limit", defaultValue = "99") int limit,
          @RequestParam(value = "skip", defaultValue = "0") int skip) throws IOException{
    JsonNode node = null;
    RestTemplate rest = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    ObjectMapper mapper = new ObjectMapper();
    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchDrugEnfrcmntUrl)
              .queryParam("limit", limit)
              .queryParam("api_key", this.fdaApiKey);
    if (skip > 0) builder.queryParam("skip", skip);
    if(unii != null && unii != ""){
      builder.queryParam("search", "openfda.unii:"+unii);

    }else{
      //TODO:Get most recent recalls
    }
    try {
      String json = rest.getForObject(builder.build().toUri(), String.class);
      node = mapper.readTree(json);
      //TODO: Sort Desc & map description_pattern
    } catch (HttpClientErrorException ex) {
      if (ex.getStatusCode().value() == 404) {
        node = new ObjectMapper().readTree("{\"error\":{\"code\":\"NOT_FOUND\", \"message\":\"No matches found!\"}}");
      }else{
        throw ex;
      }

    }
    return node;
  }

  private String getReportDateQuery(String fromDt, String toDt) {
    Calendar now = Calendar.getInstance();
    StringBuilder reportDtquery = new StringBuilder()
      .append("report_date:[")
      .append((fromDt != null && fromDt.trim().length() == 8) ? fromDt : getDefaultFromDate())
      .append("+TO+")
      .append((toDt != null && toDt.trim().length() == 8) ? toDt : getDefaultToDate())
      .append("]");
    return reportDtquery.toString();
  }

}
