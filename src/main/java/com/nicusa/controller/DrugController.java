package com.nicusa.controller;

import com.nicusa.HttpSlurper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DrugController {
    private static final Logger log = LoggerFactory.getLogger(DrugController.class);

    @Autowired
    @Value("${fda.drug.label.url}")
    private String fdaDrugLabelUrl;

    @Autowired
    @Value("${nlm.dailymed.autocomplete.url}")
    private String nlmDailymedAutocompleteUrl;

    HttpSlurper slurp = new HttpSlurper();

    @RequestMapping("/drug")
    public String search(
        @RequestParam(value="name", defaultValue="") String name,
        @RequestParam(value="limit", defaultValue="10") int limit,
        @RequestParam(value="skip", defaultValue="0") int skip ) {
      if ( name == null ) {
        name = "";
      }

      try {
        String query = String.format(
            this.fdaDrugLabelUrl + "?search=openfda.brand_name:%s&limit=%d&skip=%d",
            URLEncoder.encode( name, StandardCharsets.UTF_8.name() ),
            limit,
            skip );
        return slurp.getData( query );
      } catch ( UnsupportedEncodingException uee ) {
        log.error( "Help, UTF 8 encoding fail!", uee );
        throw new RuntimeException( uee );
      }
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
          String query = String.format(
              this.nlmDailymedAutocompleteUrl + "?key=search&returntype=json&term=%s",
              URLEncoder.encode( name, StandardCharsets.UTF_8.name() ));
          result = slurp.getData( query );
        } catch ( UnsupportedEncodingException uee ) {
          log.error( "Help, UTF 8 encoding failed!", uee );
        }
      }
      return result;
    }


}
