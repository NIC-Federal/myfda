package com.nicusa.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public enum NormalizeStateCode {
  ALABAMA("Alabama","AL","US-AL"),
  ALASKA("Alaska","AK","US-AK"),
  ARIZONA("Arizona","AZ","US-AZ"),
  ARKANSAS("Arkansas","AR","US-AR"),
  CALIFORNIA("California","CA","US-CA"),
  COLORADO("Colorado","CO","US-CO"),
  CONNECTICUT("Connecticut","CT","US-CT"),
  DELAWARE("Delaware","DE","US-DE"),
  DISTRICT_OF_COLUMBIA("of Columbia","DC","US-DC"),
  FLORIDA("Florida","FL","US-FL"),
  GEORGIA("Georgia","GA","US-GA"),
  HAWAII("Hawaii","HI","US-HI"),
  IDAHO("Idaho","ID","US-ID"),
  ILLINOIS("Illinois","IL","US-IL"),
  INDIANA("Indiana","IN","US-IN"),
  IOWA("Iowa","IA","US-IA"),
  KANSAS("Kansas","KS","US-KS"),
  KENTUCKY("Kentucky","KY","US-KY"),
  LOUISIANA("Louisiana","LA","US-LA"),
  MAINE("Maine","ME","US-ME"),
  MARYLAND("Maryland","MD","US-MD"),
  MASSACHUSETTS("Massachusetts","MA","US-MA"),
  MICHIGAN("Michigan","MI","US-MI"),
  MINNESOTA("Minnesota","MN","US-MN"),
  MISSISSIPPI("Mississippi","MS","US-MS"),
  MISSOURI("Missouri","MO","US-MO"),
  MONTANA("Montana","MT","US-MT"),
  NEBRASKA("Nebraska","NE","US-NE"),
  NEVADA("Nevada","NV","US-NV"),
  NEW_HAMPSHIRE("New Hampshire","NH","US-NH"),
  NEW_JERSEY("New Jersey","NJ","US-NJ"),
  NEW_MEXICO("New Mexico","NM","US-NM"),
  NEW_YORK("New York","NY","US-NY"),
  NORTH_CAROLINA("North Carolina","NC","US-NC"),
  NORTH_DAKOTA("North Dakota","ND","US-ND"),
  OHIO("Ohio","OH","US-OH"),
  OKLAHOMA("Oklahoma","OK","US-OK"),
  OREGON("Oregon","OR","US-OR"),
  PENNSYLVANIA("Pennsylvania","PA","US-PA"),
  RHODE_ISLAND("Rhode Island","RI","US-RI"),
  SOUTH_CAROLINA("South Carolina","SC","US-SC"),
  SOUTH_DAKOTA("South Dakota","SD","US-SD"),
  TENNESSEE("Tennessee","TN","US-TN"),
  TEXAS("Texas","TX","US-TX"),
  UTAH("Utah","UT","US-UT"),
  VERMONT("Vermont","VT","US-VT"),
  VIRGINIA("Virginia","VA","US-VA"),
  WASHINGTON("Washington","WA","US-WA"),
  WEST_VIRGINIA("West Virginia","WV","US-WV"),
  WISCONSIN("Wisconsin","WI","US-WI"),
  WYOMING("Wyoming","WY","US-WY"),
  PUERTO_RICO("Puerto Rico","PR","US-PR"),
  UNITED_STATES("United States", "US", "Nationwide");

  String unnabreviated;
  String ANSIabbreviation;
  String ISOabbreviation;

  NormalizeStateCode(String unnabreviated, String ANSIabbreviation, String ISOabbreviation) {
    this.unnabreviated = unnabreviated;
    this.ANSIabbreviation = ANSIabbreviation;
    this.ISOabbreviation = ISOabbreviation;
  }

  public String toString () {
    return this.ANSIabbreviation;
  }

  /**
   * check two terms against unabreviated.
   */
  private static NormalizeStateCode twoCheck ( String a, String b ) {
    String input = a + " " + b;
    for (NormalizeStateCode state : values()) {
      if (state.unnabreviated.equalsIgnoreCase(input)) {
        return state;
      }
    }
    return null;
  }

  /**
   * check one term against all state codes.
   */
  private static NormalizeStateCode oneCheck ( String input ) {
    for (NormalizeStateCode state : values()) {
      if (state.unnabreviated.equalsIgnoreCase(input)    ||
          state.ANSIabbreviation.equalsIgnoreCase(input) ||
          state.ISOabbreviation.equalsIgnoreCase(input)) {
        return state;
      }
    }
    return null;
  }

  public static String parseToJson ( String input ) {
    Set<NormalizeStateCode> rv = parse( input );
    StringBuilder sb = new StringBuilder();
    sb.append( '[' );
    Iterator<NormalizeStateCode> iter = rv.iterator();
    while ( iter.hasNext() ) {
      NormalizeStateCode sc = iter.next();
      sb.append( '"' );
      sb.append( sc.toString() );
      sb.append( '"' );
      if ( iter.hasNext() ) {
        sb.append( ", " );
      }
    }
    sb.append( ']' );
    return sb.toString();
  }

  /**
   * Parse string input to enum. String will contain a poorly normalized
   * list of state data and miscellaneous words to ignore.
   * @param input String to parse
   * @return A list of all states matched.
   */
  public static Set<NormalizeStateCode> parse(String input) {
    if (null == input) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    char last = ' ';
    for (int i = 0; i < input.length(); i++ ) {
      char c = input.charAt(i);
      if ( Character.isLetter( c )) {
        sb.append( c );
        last = c;
      } else if ( last != ' ' ) {
        sb.append( ' ' );
        last = ' ';
      }
    }

    String[] terms = sb.toString().trim().split( " " );

    Set<NormalizeStateCode> rv = new TreeSet<NormalizeStateCode>();

    for (int i = 0; i < terms.length; i++ ) {
      NormalizeStateCode cd = oneCheck( terms[i] );
      if ( cd != null ) {
        rv.add( cd );
      } else if ( i < terms.length - 1 ) {
        cd = twoCheck( terms[i], terms[i+1] );
        if ( cd != null ) {
          rv.add( cd );
        }
      }
    }

    // default to united states
    if ( rv.contains( NormalizeStateCode.UNITED_STATES )) {
      return Collections.emptySet();
    } else {
      return rv;
    }
  }
}
