package com.salesforce.cdp.queryservice.util;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SqlGeneratorTest {

  @Test
  public void testValidationFails() throws SQLException {
    String templateSql = "select test_column__c from test_table__c where test_column__c = ?";
    Map<Integer, Object> parameters = new HashMap<>();
    String strParam = "prefix\\'suffix";
    System.out.println(strParam);
    parameters.put(1, strParam);

    String query = SqlGenerator.createQuery(templateSql, parameters);
    Assertions.assertThat(query).isEqualTo("select test_column__c from test_table__c where test_column__c = 'prefix\\\\''suffix'");

  }

}