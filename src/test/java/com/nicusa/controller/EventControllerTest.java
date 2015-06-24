package com.nicusa.controller;

import com.nicusa.util.HttpSlurper;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Set;

public class EventControllerTest {

  @Test
  public void testEventByUnii () {
    EventController event = new EventController();
    event.rest = mock( RestTemplate.class );
    final String r = "{\"term\":\"ANXIETY\"}";
    when( event.rest.getForObject(anyString(),(Class<?>)any(Class.class))).thenAnswer(
      new Answer<String>() {
        @Override
        public String answer(InvocationOnMock inv) throws Throwable {
          return r;
        }
      });

    final String s = "{\"meta\":{\"disclaimer\":\"openFDA is a beta research project and not for clinical use. While we make every effort to ensure that data is accurate, you should assume all results are unvalidated.\",\"license\":\"http://open.fda.gov/license\",\"last_updated\":\"2015-01-21\"},\"results\":[{\"term\":1,\"count\":578},{\"term\":2,\"count\":342}]}";
    event.slurp = mock( HttpSlurper.class );
    when( event.slurp.getData(anyString())).thenAnswer(
        new Answer<String>() {
          @Override
          public String answer(InvocationOnMock inv) throws Throwable {
            return s;
          }
        });
    String json = event.search( "blah", 10, 0 );
    assertTrue( json.contains( "342" ));
    assertTrue( json.contains( "ANXIETY" ));
    assertTrue( json.contains( "578" ));
  }

  @Test
  public void testEventTerms () {
    EventController event = new EventController();
    event.rest = mock( RestTemplate.class );
    final String res = "{\"meta\":{\"disclaimer\":\"openFDA is a beta research project and not for clinical use. While we make every effort to ensure that data is accurate, you should assume all results are unvalidated.\",\"license\":\"http://open.fda.gov/license\",\"last_updated\": \"2015-01-21\"},\"results\":[{\"term\":\"PAIN\",\"count\": 584  },{\"term\": \"DIARRHOEA\",\"count\":543},{\"term\": \"NAUSEA\",\"count\": 481},{\"term\":\"ANXIETY\",\"count\": 417}]}";
    when( event.rest.getForObject(anyString(),(Class<?>)any(Class.class))).thenAnswer(
      new Answer<String>() {
        @Override
        public String answer(InvocationOnMock inv) throws Throwable {
          return res;
        }
      });
    Set<String> terms = event.getEventTerms( "blah" );
    assertTrue( terms.contains( "PAIN" ));
    assertTrue( terms.contains( "DIARRHOEA" ));
    assertTrue( terms.contains( "ANXIETY" ));
  }
}
