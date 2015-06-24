package com.nicusa.controller;

import com.nicusa.util.HttpSlurper;

import com.nicusa.assembler.DrugAssembler;
import com.nicusa.domain.Drug;
import com.nicusa.resource.DrugResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import javax.persistence.EntityManager;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DrugControllerTest {

  @Mock
  private EntityManager entityManager;

  @Mock
  private DrugAssembler drugAssembler;

  @InjectMocks
  private DrugController drugController;

  @Test
  public void testGetDrugFound() {
    Drug persistedDrug = new Drug();
    persistedDrug.setId(1L);
    DrugResource drugResource = new DrugResource();
    when(entityManager.find(Drug.class, 1L)).thenReturn(persistedDrug);
    when(drugAssembler.toResource(persistedDrug)).thenReturn(drugResource);
    ResponseEntity<DrugResource> drugResourceResponseEntity = drugController.getDrug(1L);
    assertThat(HttpStatus.OK, is(drugResourceResponseEntity.getStatusCode()));
    assertThat(drugResourceResponseEntity.getBody(), is(drugResource));
  }


  @Test
  public void testGetDrugNotFound() {
    when(entityManager.find(Drug.class, 1L)).thenReturn(null);
    ResponseEntity<DrugResource> drugResourceResponseEntity = drugController.getDrug(1L);
    assertThat(HttpStatus.NOT_FOUND, is(drugResourceResponseEntity.getStatusCode()));
    assertThat(drugResourceResponseEntity.getBody(), is(nullValue()));
  }


  @Test
  public void testShortAutocomplete() throws IOException {
    DrugController drug = new DrugController();
    drug.rest = mock(RestTemplate.class);
    String res = drug.autocomplete("ab");
    verify(drug.rest, never()).getForObject(anyString(), (Class<?>) any(Class.class));
  }

  @Test
  public void testRegularAutocomplete() throws IOException {
    DrugController drug = new DrugController();
    final String res = "[{\"value\":\"the result\"}]";
    drug.rest = mock(RestTemplate.class);
    when(drug.rest.getForObject(anyString(), (Class<?>) any(Class.class))).thenAnswer(
    new Answer<String>() {
      @Override
      public String answer(InvocationOnMock inv) throws Throwable {
        return res;
      }
    });
    assertThat(drug.autocomplete("blah"), is("[\"the result\"]"));
  }

  @Test
  public void testGetUniisByName () throws IOException {
    DrugController drug = new DrugController();
    final String res = "[{\"term\":\"abcdefg\"}]";
    drug.rest = mock( RestTemplate.class );
    when( drug.rest.getForObject(anyString(),(Class<?>)any(Class.class))).thenAnswer(
    new Answer<String>() {
      @Override
      public String answer(InvocationOnMock inv) throws Throwable {
        return res;
      }
    });
    Set<String> uniis = drug.getUniisByName( "blah" );
    assertEquals( uniis.size(), 1 );
    assertTrue( uniis.contains( "abcdefg" ));
  }

  @Test
  public void testGetBrandNamesByNameAndUniis () throws IOException {
    DrugController drug = new DrugController();
    final String s = "{\"meta\":{\"disclaimer\":\"openFDA is a beta research project and not for clinical use. While we make every effort to ensure that data is accurate, you should assume all results are unvalidated.\",\"license\":\"http://open.fda.gov/license\",\"last_updated\":\"2015-05-31\"},\"results\":[{\"term\":\"ADVIL PM\",\"count\":2}]}";
    drug.slurp = mock( HttpSlurper.class );
    when( drug.slurp.getData(anyString())).thenAnswer(
    new Answer<String>() {
      @Override
      public String answer(InvocationOnMock inv) throws Throwable {
        return s;
      }
    });
    Set<String> uniis = new HashSet<String>();
    uniis.add("8GTS82S83M");
    uniis.add("WK2XYI10QM");
    Map<String,Set<String>> result = drug.getBrandNamesByNameAndUniis( "blah", uniis );
    assertTrue( result.contains("ADVIL PM") );
  }

  @Test
  public void testGetBrandNamesByNameAndUnii () throws IOException {
    DrugController drug = new DrugController();
    final String s = "{\"meta\":{\"disclaimer\":\"openFDA is a beta research project and not for clinical use. While we make every effort to ensure that data is accurate, you should assume all results are unvalidated.\",\"license\":\"http://open.fda.gov/license\",\"last_updated\":\"2015-05-31\"},\"results\":[{\"term\":\"ADVIL PM\",\"count\":2}]}";
    drug.slurp = mock( HttpSlurper.class );
    when( drug.slurp.getData(anyString())).thenAnswer(
    new Answer<String>() {
      @Override
      public String answer(InvocationOnMock inv) throws Throwable {
        return s;
      }
    });

    Set<String> result = drug.getBrandNamesByNameAndUnii( "blah", "8GTS82S83M" );
    assertTrue( result.contains("ADVIL PM") );
  }

  @Test
  public void testGetGenericNameByUnii () throws IOException {
    DrugController drug = new DrugController();
    final String s = "{\"meta\":{\"disclaimer\":\"openFDA is a beta research project and not for clinical use. While we make every effort to ensure that data is accurate, you should assume all results are unvalidated.\",\"license\":\"http://open.fda.gov/license\",\"last_updated\":\"2015-05-31\"},\"results\":[{\"term\":\"DIPHENHYDRAMINE HYDROCHLORIDE\",\"count\":245}]}";
    drug.rest = mock( RestTemplate.class );
    when( drug.rest.getForObject(anyString(),(Class<?>)any(Class.class))).thenAnswer(
    new Answer<String>() {
      @Override
      public String answer(InvocationOnMock inv) throws Throwable {
        return s;
      }
    });
    String result = drug.getGenericNameByUnii( "blah" );
    assertTrue( result.contains("HYDROCHLORIDE") );
  }

  @Test
  public void testGetRxcuiByBrandName () throws IOException {
    DrugController drug = new DrugController();
    final String s = "{\"idGroup\":{\"name\":\"advil\",\"rxnormId\":[\"153010\"]}}";
    drug.slurp = mock( HttpSlurper.class );
    when( drug.slurp.getData(anyString())).thenAnswer(
    new Answer<String>() {
      @Override
      public String answer(InvocationOnMock inv) throws Throwable {
        return s;
      }
    });
    Long resultRxcuis = drug.getRxcuiByBrandName( "blah" );
    assertTrue( resultRxcuis != null );
  }

  @Test
  public void testGetActiveIngrediantsByRxcui () throws IOException {
    DrugController drug = new DrugController();
    final String s = "{\"relatedGroup\":{\"rxcui\":\"643061\",\"rela\":[\"tradename_of\",\"has_precise_ingredient\"],\"conceptGroup\":[{\"tty\":\"IN\",\"conceptProperties\":[{\"rxcui\":\"3498\",\"name\":\"Diphenhydramine\",\"synonym\":\"\",\"tty\":\"IN\",\"language\":\"ENG\",\""
    +"suppress\":\"N\",\"umlscui\":\"C0012522\"},{\"rxcui\":\"5640\",\"name\":\"Ibuprofen\",\"synonym\":\"\",\"tty\":\"IN\",\"language\":\"ENG\",\"suppress\":\"N\",\"umlscui\":\"C0020740\"}]},{\"tty\":\"PIN\",\"conceptProperties\":[{\"rxcui\":\"1362\",\"name\":\"Diphenhydramine Hydrochloride\""
    +",\"synonym\":\"\",\"tty\":\"PIN\",\"language\":\"ENG\",\"suppress\":\"N\",\"umlscui\":\"C0004963\"},{\"rxcui\":\"82004\",\"name\":\"Diphenhydramine Citrate\",\"synonym\":\"\",\"tty\":\"PIN\",\"language\":\"ENG\",\"suppress\":\"N\",\"umlscui\":\"C0282144\"}]}]}}";
    drug.slurp = mock( HttpSlurper.class );
    when( drug.slurp.getData(anyString())).thenAnswer(
    new Answer<String>() {
      @Override
      public String answer(InvocationOnMock inv) throws Throwable {
        return s;
      }
    });
    Set<String> result = drug.getActiveIngredientsByRxcui( new Long(643061) );
    assertTrue( result.contains( "Ibuprofen" ) );
  }

  @Test
  public void testSimpleSearch() throws IOException {
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
    // assertThat( drug.search( "blah", 10, 0 ), is( res ));
    //TODO FIX THIS
  }
}
