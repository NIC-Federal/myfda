package com.nicusa.util;

import com.google.common.util.concurrent.RateLimiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiKey {
  private static final Logger log = LoggerFactory.getLogger(ApiKey.class);

  // 4 requests per second per key
  private static final RateLimiter rateLimiter = RateLimiter.create( 4.0 );

  @Autowired
  @Value("${api.fda.key}")
  String fdaApiKey;

  public String getFdaApiKey () {
    rateLimiter.acquire(); // may wait
    return this.fdaApiKey;
  }

  public String getFdaApiKeyName () {
    return "api_key";
  }

  public String getFdaApiKeyQuery () {
    if ( this.fdaApiKey != null ) {
      return "&" + this.getFdaApiKeyName() + "=" + this.getFdaApiKey();
    } else {
      return "";
    }
  }

  public void addToUriComponentsBuilder (
      UriComponentsBuilder builder ) {
    if ( this.fdaApiKey != null ) {
      builder.queryParam( this.getFdaApiKeyName(), this.getFdaApiKey() );
    }
  }
}
