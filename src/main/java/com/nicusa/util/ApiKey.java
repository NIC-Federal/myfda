package com.nicusa.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

public class ApiKey {

  @Autowired
  @Value("${api.fda.key}")
  String fdaApiKey;

  public String getFdaApiKey () {
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
