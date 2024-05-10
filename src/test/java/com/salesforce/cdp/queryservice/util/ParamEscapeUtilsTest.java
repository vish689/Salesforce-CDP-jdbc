package com.salesforce.cdp.queryservice.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ParamEscapeUtilsTest {

  @Test
  public void testEscapeQuotes() {
    String sample = "that's a param";
    Assertions.assertThat(ParamEscapeUtils.getEscapedString(sample)).isEqualTo("'that''s a param'");
  }

  @Test
  public void testEscapeSlashes() {
    String sample = "that\\'s a param";
    Assertions.assertThat(ParamEscapeUtils.getEscapedString(sample)).isEqualTo("'that\\\\''s a param'");
  }

  @Test
  public void testEscapeDate() {
    Date sample = Date.valueOf(LocalDate.parse("2020-12-31"));
    Assertions.assertThat(ParamEscapeUtils.getEscapedString(sample)).isEqualTo("'2020-12-31'");
  }

  @Test
  public void testEscapeBigDecimal() {
    BigDecimal sample = new BigDecimal("3.0");
    Assertions.assertThat(ParamEscapeUtils.getEscapedString(sample)).isEqualTo("3.0");
  }

  @Test
  public  void test() {
    String input = "abc\\def";
    System.out.println(input);
    String output = input.replaceAll("\\\\", "\\\\\\\\");
    System.out.println(output);
  }

}