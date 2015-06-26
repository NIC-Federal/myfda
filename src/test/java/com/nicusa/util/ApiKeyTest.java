package com.nicusa.util;

import org.springframework.web.util.UriComponentsBuilder;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class ApiKeyTest {

  @Test
  public void testNullApiKey ( ) {
    ApiKey key = new ApiKey();
    key.fdaApiKey = null;
    assertTrue( key.getFdaApiKey() == null );
    assertEquals( "", key.getFdaApiKeyQuery() );
    UriComponentsBuilder builder = mock( UriComponentsBuilder.class );
    key.addToUriComponentsBuilder( builder );
    verify(builder, never()).queryParam(anyString(),anyString());
  }

  @Test
  public void testApiKey () {
    ApiKey key = new ApiKey();
    key.fdaApiKey = "nope";
    assertEquals( "nope", key.getFdaApiKey() );
    assertEquals( "&api_key=nope", key.getFdaApiKeyQuery() );
  }

  @Test
  public void testApiKeyBuilder () {
    ApiKey key = new ApiKey();
    key.fdaApiKey = "unikitty";
    UriComponentsBuilder builder = mock( UriComponentsBuilder.class );
    key.addToUriComponentsBuilder( builder );
    verify(builder).queryParam( "api_key", "unikitty" );
  }

  @Test
  public void testApiKeyName () {
    ApiKey key = new ApiKey();
    assertEquals( "api_key", key.getFdaApiKeyName());
  }
}
