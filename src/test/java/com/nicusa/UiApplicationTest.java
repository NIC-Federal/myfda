package com.nicusa;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicusa.UiApplication;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
public class UiApplicationTest {

	@Test
	public void contextLoads() {
	}

  @Test
  public void testShortAutocomplete() {
    UiApplication uiApp = new UiApplication();
    uiApp.slurp = mock( HttpSlurper.class );
    String res = uiApp.autocomplete( "ab" );
    verify( uiApp.slurp, never() ).getData( anyString() );
  }

  @Test
  public void testRegularAutocomplete() {
    UiApplication uiApp = new UiApplication();
    final String res = "the result";
    uiApp.slurp = mock( HttpSlurper.class );
    when( uiApp.slurp.getData(anyString())).thenAnswer(
       new Answer<String>() {
         @Override
         public String answer(InvocationOnMock inv) throws Throwable {
           return res;
         }
       });
    assertThat( uiApp.autocomplete( "blah" ), is( res ));
  }

  @Test
  public void testSimpleSearch() {
    UiApplication uiApp = new UiApplication();
    final String res = "the result";
    uiApp.slurp = mock( HttpSlurper.class );
    when( uiApp.slurp.getData(anyString())).thenAnswer(
       new Answer<String>() {
         @Override
         public String answer(InvocationOnMock inv) throws Throwable {
           return res;
         }
       });
    assertThat( uiApp.autocomplete( "blah" ), is( res ));
  }

}
