package com.nicusa.util;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AutocompleteFilter {
  public List<String> filter(JsonNode jsonNode) {
    List<JsonNode> vals = jsonNode.findValues("value");
    List<String> rv = new LinkedList<String>();
    String last = null;

    Iterator<JsonNode> iter = vals.iterator();
    while (iter.hasNext() && rv.size() < 10) {
      String curr = iter.next().textValue();
      if (last == null || vals.size() <= 10 || !curr.startsWith(last)) {
        rv.add(curr);
      }

      // filter on identical first name of drug
      if (curr.indexOf(' ') != -1) {
        last = curr.substring(0, curr.indexOf(' '));
      } else {
        last = curr;
      }
      last += ' ';
    }

    // special case: all returned results start with the same name, don't
    // filter, just take the first 10
    if (rv.size() == 1 && vals.size() > 10) {
      iter = vals.iterator();
      iter.next();
      while (iter.hasNext() && rv.size() < 10) {
        rv.add(iter.next().textValue());
      }
    }
    return rv;
  }
}
