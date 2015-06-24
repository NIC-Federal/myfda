package com.nicusa.controller;

import com.nicusa.UiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aveerapaneni on 6/23/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class FeedControllerIT {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    @Value("${rss.fda.recalls.url}")
    private String fdaRecallsRSSurl;

    @Autowired
    @Value("${gl.api.xml2json.url}")
    private String xml2JsonCnvrtrUrl;

    @Test
    public void testFdaFOODRecalls(){
        RestTemplate template = new TestRestTemplate("user", "password");
        ResponseEntity<String> response = template
                .getForEntity("http://localhost:" + port + "/recalls", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains(fdaRecallsRSSurl));
    }

    @Test
    public void testDRUGRecalls(){
      RestTemplate template = new TestRestTemplate("user", "password");
      ResponseEntity<String> response = template
        .getForEntity("http://localhost:" + port + "/drug/recalls", String.class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertTrue(response.getBody().contains("http://open.fda.gov/license"));
    }

}
