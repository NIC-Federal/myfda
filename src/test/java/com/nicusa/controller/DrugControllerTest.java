package com.nicusa.controller;

import com.nicusa.HttpSlurper;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class DrugControllerTest {

  @Test
  public void testShortAutocomplete() {
    DrugController drug = new DrugController();
    drug.slurp = mock( HttpSlurper.class );
    String res = drug.autocomplete( "ab" );
    verify( drug.slurp, never() ).getData( anyString() );
  }

  @Test
  public void testRegularAutocomplete() {
    DrugController drug = new DrugController();
    final String res = "the result";
    drug.slurp = mock( HttpSlurper.class );
    when( drug.slurp.getData(anyString())).thenAnswer(
       new Answer<String>() {
         @Override
         public String answer(InvocationOnMock inv) throws Throwable {
           return res;
         }
       });
    assertThat( drug.autocomplete( "blah" ), is( res ));
  }

  @Test
  public void testSimpleSearch() {
    DrugController drug = new DrugController();
    final String res = "the result";
    drug.slurp = mock( HttpSlurper.class );
    when( drug.slurp.getData(anyString())).thenAnswer(
       new Answer<String>() {
         @Override
         public String answer(InvocationOnMock inv) throws Throwable {
           return res;
         }
       });
    assertThat( drug.autocomplete( "blah" ), is( res ));
  }  
}
