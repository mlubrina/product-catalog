package com.company.products.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("LocalDateTime Converter Test")
public class LocalDateTimeConverterTest {

  @Test
  @DisplayName("Test Convert String to LocalDateTime")
  void convertStringToLocalDateTime() {
    LocalDateTimeConverter converter = new LocalDateTimeConverter();

    String source = "2023-06-13T12:34:56";

    LocalDateTime expectedDateTime = LocalDateTime.of(2023, 6, 13, 12, 34, 56);

    LocalDateTime convertedDateTime = converter.convert(source);

    assertEquals(expectedDateTime, convertedDateTime);
  }
}
