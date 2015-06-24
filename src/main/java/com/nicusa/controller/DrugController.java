package com.nicusa.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicusa.assembler.DrugAssembler;
import com.nicusa.domain.Drug;
import com.nicusa.resource.DrugResource;
import com.nicusa.util.AutocompleteFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class DrugController {
  private static final Logger log = LoggerFactory.getLogger(DrugController.class);
  RestTemplate rest = new RestTemplate();
  @Autowired
  @Value("${fda.drug.label.url:https://api.fda.gov/drug/label.json}")
  private String fdaDrugLabelUrl;
  @Autowired
  @Value("${nlm.dailymed.autocomplete.url:https://dailymed.nlm.nih.gov/dailymed/autocomplete.cfm}")
  private String nlmDailymedAutocompleteUrl;
  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private DrugAssembler drugAssembler;

  @ResponseBody
  @RequestMapping(value = "/drug/{id}", method = RequestMethod.GET, produces = "application/hal+json")
  public ResponseEntity<DrugResource> getDrug(@PathVariable("id") Long id) {
    Drug drug = entityManager.find(Drug.class, id);
    if (drug == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(drugAssembler.toResource(drug), HttpStatus.OK);
  }

  @RequestMapping("/drug")
  public String search(
    @RequestParam(value = "name", defaultValue = "") String name,
    @RequestParam(value = "limit", defaultValue = "10") int limit,
    @RequestParam(value = "skip", defaultValue = "0") int skip) {
    if (name == null) {
      name = "";
    }

    String result = null;

    try {
      String query = String.format(
        this.fdaDrugLabelUrl + "?search=openfda.brand_name:%s&limit=%d&skip=%d",
        URLEncoder.encode(name, StandardCharsets.UTF_8.name()),
        limit,
        skip);
      result = rest.getForObject(query, String.class);
    } catch (UnsupportedEncodingException uee) {
      log.error("Help, UTF 8 encoding fail!", uee);
    }
    return result;
  }

  @RequestMapping("/autocomplete")
  public String autocomplete(
    @RequestParam(value = "name", defaultValue = "") String name) {
    if (name == null) {
      name = "";
    }

    String result = "[{\"value\":\"" + name + "\"}]";
    if (name.length() >= 3) {
      try {
        ObjectMapper mapper = new ObjectMapper();
        String query = String.format(
          this.nlmDailymedAutocompleteUrl + "?key=search&returntype=json&term=%s",
          URLEncoder.encode(name, StandardCharsets.UTF_8.name()));
        JsonNode node = mapper.readTree(
          rest.getForObject(query, String.class));
        AutocompleteFilter myFilter = new AutocompleteFilter();
        List<String> values = myFilter.filter(node);
        result = mapper.writeValueAsString(values);
      } catch (UnsupportedEncodingException uee) {
        log.error("Help, UTF 8 encoding failed!", uee);
      } catch (IOException ioe) {
        log.error("Error accessing " + this.nlmDailymedAutocompleteUrl, ioe);
      }
    }
    return result;
  }

}
