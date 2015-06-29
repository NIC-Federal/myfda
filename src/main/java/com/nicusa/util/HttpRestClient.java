package com.nicusa.util;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class HttpRestClient {
  private final Logger log = LoggerFactory.getLogger(HttpRestClient.class);
  
  RestTemplate rest = new RestTemplate();

  public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
    log.info("Making a requst to:{}", url.toString());
    for (int i=0; i<20; i++){
      try {
        return rest.getForObject(url, responseType);
      } catch (HttpClientErrorException ex) {
        if( ex.getStatusCode() != HttpStatus.TOO_MANY_REQUESTS ){
          throw ex;
        }
        log.info("Got 429. Times:{}", i);
      }
    }
    return rest.getForObject(url, responseType);
  }


  public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) throws RestClientException {
    log.info("Making a requst to:{}", url);
    for (int i=0; i<20; i++){
      try {
        return rest.getForObject(url, responseType, urlVariables);
      } catch (HttpClientErrorException ex) {
        if( ex.getStatusCode() != HttpStatus.TOO_MANY_REQUESTS ){
          throw ex;
        }
        log.info("Got 429. Times:{}", i);
      }
    }
    return rest.getForObject(url, responseType, urlVariables);
  }
}
