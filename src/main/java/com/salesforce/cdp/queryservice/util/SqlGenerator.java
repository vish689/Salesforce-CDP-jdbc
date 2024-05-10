package com.salesforce.cdp.queryservice.util;

import static com.salesforce.cdp.queryservice.util.ParamEscapeUtils.getEscapedString;

import java.sql.SQLException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlGenerator {

  public static String createQuery(String sql, Map<Integer, Object> parameters)
      throws SQLException {
    String sqlQuery = sql;
    int parameterIndex = 0;
    while(sqlQuery.contains("?")) {
      if (parameterIndex == parameters.size()) {
        log.error("Not enough parameters to replace placeholders in query.");
        throw new SQLException("Not enough parameters");
      }
      Object parameter = parameters.get(++parameterIndex);
      sqlQuery = sqlQuery.replaceFirst("\\?", parameter == null ? "null" : getEscapedString(parameter));
    }
    return sqlQuery;
  }
}
