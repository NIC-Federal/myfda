package com.nicusa.util;

public class SpaceConverter {
  public String convert ( String val ) {
    if ( val == null ) {
      val = "";
    }

    StringBuilder sb = new StringBuilder();
    for ( int i = 0; i < val.length(); i++ ) {
      if ( val.charAt( i ) == ' ' ) {
        sb.append( '+' );
      } else {
        sb.append( val.charAt( i ));
      }
    }

    return sb.toString();
  }
}
