package com.nicusa.controller;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class DrugControllerTest {

  @Test
  public void testShortAutocomplete() {
    DrugController drug = new DrugController();
    drug.rest = mock( RestTemplate.class );
    String res = drug.autocomplete( "ab" );
    verify( drug.rest, never() ).getForObject( anyString(), (Class<?>)any(Class.class) );
  }

  @Test
  public void testRegularAutocomplete() {
    DrugController drug = new DrugController();
    final String res = "[{\"value\":\"the result\"}]";
    drug.rest = mock( RestTemplate.class );
    when( drug.rest.getForObject(anyString(), (Class<?>)any(Class.class))).thenAnswer(
       new Answer<String>() {
         @Override
         public String answer(InvocationOnMock inv) throws Throwable {
           return res;
         }
       });
    assertThat( drug.autocomplete( "blah" ), is( "[\"the result\"]" ));
  }

  @Test
  public void testSimpleSearch() {
    DrugController drug = new DrugController();
    final String res = "[{\"value\":\"the result\"}]";
    drug.rest = mock( RestTemplate.class );
    when( drug.rest.getForObject(anyString(),(Class<?>)any(Class.class))).thenAnswer(
       new Answer<String>() {
         @Override
         public String answer(InvocationOnMock inv) throws Throwable {
           return res;
         }
       });
    assertThat( drug.autocomplete( "blah" ), is( "[\"the result\"]" ));
  }  
}
