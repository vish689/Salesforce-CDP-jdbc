package com.salesforce.cdp.queryservice.util;

import java.sql.Date;
import java.sql.Timestamp;

public class ParamEscapeUtils {

  private static String[][] ESCAPES = new String[][] {
      {"'", "''"},
      {"\\\\", "\\\\\\\\"}
  };

  public static String getEscapedString(Object parameter) {
    if (parameter instanceof String) {
      return quoteString(escapeString(parameter.toString()));
    } else if (parameter instanceof Date) {
      return quoteString(escapeString(parameter.toString()));
    } else if (parameter instanceof Timestamp) {
      return quoteString(escapeString(parameter.toString()));
    } else {
      return escapeString(parameter.toString());
    }
  }

  private static String escapeString(String parameter) {
    for (String[] escape: ESCAPES) {
      parameter = parameter.replaceAll(escape[0], escape[1]);
    }
    return parameter;
  }

  private static String quoteString(String parameter) {
    return String.format("'%s'", parameter);
  }

}
