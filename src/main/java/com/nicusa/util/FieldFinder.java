package com.nicusa.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FieldFinder {
    private static final Logger log = LoggerFactory.getLogger(FieldFinder.class);

    private String field;
    public FieldFinder ( String field ) {
      this.field = field;
    }

    public Set<String> find ( String json ) throws IOException {
      Set<String> uniis = new TreeSet<String>();
      ObjectMapper mapper = new ObjectMapper();
      JsonNode node = mapper.readTree( json );
      List<JsonNode> ulist = node.findValues( this.field );
      for ( JsonNode n : ulist ) {
        if ( n.isArray() ) {
          Iterator<JsonNode> iter = n.elements();
          while ( iter.hasNext() ) {
            uniis.add( iter.next().textValue().toUpperCase() );
          }
        } else {
          uniis.add( n.textValue().toUpperCase() );
        }
      }

      return uniis;
    }
}
