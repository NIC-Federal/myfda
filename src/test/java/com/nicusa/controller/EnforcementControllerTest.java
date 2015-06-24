package com.nicusa.controller;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EnforcementControllerTest {
  @Test
  public void testEnforcementSearch () {
    EnforcementController enforce = new EnforcementController();
    final String e = "[{\"value\":\"the result\"}]";
    enforce.rest = mock( RestTemplate.class );
    when( enforce.rest.getForObject(anyString(),(Class<?>)any(Class.class))).thenAnswer(
        new Answer<String>() {
          @Override
          public String answer(InvocationOnMock inv) throws Throwable {
            return e;
          }
        });
    assertThat( enforce.search( "blah", 10, 0 ), is( e ));
  }
}
