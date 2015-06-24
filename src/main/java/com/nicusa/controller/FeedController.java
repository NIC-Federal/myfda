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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Calendar;


@RestController
public class FeedController {

  private static final Logger log = LoggerFactory.getLogger(FeedController.class);

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
      .append("30");
    return fromDt.toString();
  }

  private static String getDefaultToDate() {
    Calendar now = Calendar.getInstance();
    StringBuilder toDt = new StringBuilder().append(now.get(now.YEAR))
      .append((String.valueOf(now.get(now.MONTH) + 1).length() == 1) ? "0" + String.valueOf(now.get(now.MONTH) + 1) : String.valueOf(now.get(now.MONTH) + 1))
      .append("30");
    return toDt.toString();
  }

  @RequestMapping("/feed")
  @ResponseBody
  public String feed() {
    return "<h1>FDA Feeds</h1>";
  }

  @RequestMapping("/recalls")
  @ResponseBody
  public JsonNode getFDARecalls() {
    RestTemplate rest = new RestTemplate();
    JsonNode node = null;
    try {
      ObjectMapper mapper = new ObjectMapper();
      log.info(xml2JsonCnvrtrUrl + fdaRecallsRSSurl);
      node = mapper.readTree(rest.getForObject(xml2JsonCnvrtrUrl + fdaRecallsRSSurl, String.class));
    } catch (IOException e) {
      log.error("Error receiving FDA recalls", e);
      log.error(e.getStackTrace().toString());
    }

    return node;
  }

  @RequestMapping("/drug/recalls")
  @ResponseBody
  public JsonNode getDrugRecalls(@RequestParam(value = "limit", defaultValue = "5") int limit,
                                 @RequestParam(value = "skip", defaultValue = "0") int skip,
                                 @RequestParam(value = "fromDt", defaultValue = "") String fromDt,
                                 @RequestParam(value = "toDt", defaultValue = "") String toDt) {
    RestTemplate rest = new RestTemplate();
    JsonNode node = null;
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchDrugEnfrcmntUrl)
        .queryParam("search", getReportDateQuery(fromDt, toDt))
        .queryParam("limit", limit);
      if (skip > 0) builder.queryParam("skip", skip);
      ObjectMapper mapper = new ObjectMapper();
      String json = rest.getForObject(builder.build().toUri(), String.class);
      node = mapper.readTree(json);

    } catch (IOException e) {
      log.error("Error querying drug recalls", e);
      log.error(e.getStackTrace().toString());
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
