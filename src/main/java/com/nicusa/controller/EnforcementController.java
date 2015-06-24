package com.nicusa.controller;

import java.io.IOException;
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
import org.springframework.web.client.RestTemplate;

@RestController
public class EnforcementController {
    private static final Logger log = LoggerFactory.getLogger(EnforcementController.class);

    @Autowired
    @Value("${fda.drug.enforcement.url:https://api.fda.gov/drug/enforcement.json}")
    private String fdaDrugEnforcementUrl;

    RestTemplate rest = new RestTemplate();

    @RequestMapping("/enforcement")
    public String search (
        @RequestParam(value="unii", defaultValue="" ) String unii,
        @RequestParam(value="limit", defaultValue="10" ) int limit,
        @RequestParam(value="skip", defaultValue="0" ) int skip ) {
      if ( unii == null ) {
        unii = "";
      }

      String result = null;

      try {
        String query = String.format(
            this.fdaDrugEnforcementUrl + "?search=openfda.unii:%s&limit=%d&skip=%d",
            URLEncoder.encode( unii, StandardCharsets.UTF_8.name() ),
            limit,
            skip );
        result = rest.getForObject( query, String.class );
      } catch ( UnsupportedEncodingException uee ) {
        log.error( "Help, UTF 8 encoding fail!", uee );
      } catch ( IOException ioe ) {
        log.error( "Error accessing " + this.fdaDrugEnforcementUrl, ioe );
      }

      return result;
    }
}
