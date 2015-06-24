package com.nicusa.controller;

import com.nicusa.util.AutocompleteFilter;
import com.nicusa.util.FieldFinder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DrugController {
    private static final Logger log = LoggerFactory.getLogger(DrugController.class);

    @Autowired
    @Value("${fda.drug.label.url:https://api.fda.gov/drug/label.json}")
    private String fdaDrugLabelUrl;

    @Autowired
    @Value("${nlm.dailymed.autocomplete.url:https://dailymed.nlm.nih.gov/dailymed/autocomplete.cfm}")
    private String nlmDailymedAutocompleteUrl;

    RestTemplate rest = new RestTemplate();

    @RequestMapping("/drug")
    public String search(
        @RequestParam(value="name", defaultValue="") String name,
        @RequestParam(value="limit", defaultValue="10") int limit,
        @RequestParam(value="skip", defaultValue="0") int skip ) {
      if ( name == null ) {
        name = "";
      }

      String result = null;

      try {
        String query = String.format(
            this.fdaDrugLabelUrl + "?search=openfda.brand_name:%s&limit=%d&skip=%d",
            URLEncoder.encode( name, StandardCharsets.UTF_8.name() ),
            limit,
            skip );
        result = rest.getForObject( query, String.class );
        FieldFinder finder = new FieldFinder( "unii" );
        Set<String> uniis = finder.find( result );
      } catch ( UnsupportedEncodingException uee ) {
        log.error( "Help, UTF 8 encoding fail!", uee );
      } catch ( IOException ioe ) {
        log.error( "Error accessing " + this.fdaDrugLabelUrl, ioe );
      }
      return result;
    }

    @RequestMapping("/autocomplete")
    public String autocomplete(
        @RequestParam(value="name", defaultValue="") String name) {
      if ( name == null ) {
        name = "";
      }

      String result = "[{\"value\":\"" + name + "\"}]";
      if ( name.length() >= 3 ) {
        try {
          ObjectMapper mapper = new ObjectMapper();
          String query = String.format(
              this.nlmDailymedAutocompleteUrl + "?key=search&returntype=json&term=%s",
              URLEncoder.encode( name, StandardCharsets.UTF_8.name() ));
          JsonNode node = mapper.readTree(
              rest.getForObject( query, String.class ));
          AutocompleteFilter myFilter = new AutocompleteFilter();
          List<String> values = myFilter.filter( node );
          result = mapper.writeValueAsString( values );
        } catch ( UnsupportedEncodingException uee ) {
          log.error( "Help, UTF 8 encoding failed!", uee );
        } catch ( IOException ioe ) {
          log.error( "Error accessing " + this.nlmDailymedAutocompleteUrl, ioe );
        }
      }
      return result;
    }


}
