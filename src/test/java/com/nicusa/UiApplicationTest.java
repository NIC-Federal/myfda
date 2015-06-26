package com.nicusa;

import com.nicusa.domain.PersistenceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UiApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
@IntegrationTest("server.port:0")
public class UiApplicationTest {

	@Test
	public void contextLoads() {
	}

  @Value("${local.server.port}")
  private int port;

  private RestTemplate template = new TestRestTemplate();

  @Test
  public void homePageLoads() {
    ResponseEntity<String> response = template.getForEntity("http://localhost:"
            + port + "/", String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void resourceEndpointProtected() {
    ResponseEntity<String> response = template.getForEntity("http://localhost:"
            + port + "/resource", String.class);
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
  }

  @Test
  public void loginSucceeds() {
    RestTemplate template = new TestRestTemplate("user", "password");
    ResponseEntity<String> response = template.getForEntity("http://localhost:" + port
            + "/resource", String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}
