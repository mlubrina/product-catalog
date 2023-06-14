package com.company.products.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Price Test")
public class PriceTest {

  @Test
  @DisplayName("Test should get and set OK")
  void gettersAndSetters() {
    Price price = new Price();

    Long id = 1L;
    Long brandId = 123L;
    LocalDateTime startDate = LocalDateTime.of(2023, 6, 13, 0, 0);
    LocalDateTime endDate = LocalDateTime.of(2023, 6, 14, 0, 0);
    int priceList = 1;
    Long productId = 456L;
    int priority = 2;
    double priceValue = 9.99;
    String currency = "USD";

    price.setId(id);
    price.setBrandId(brandId);
    price.setStartDate(startDate);
    price.setEndDate(endDate);
    price.setPriceList(priceList);
    price.setProductId(productId);
    price.setPriority(priority);
    price.setPrice(priceValue);
    price.setCurrency(currency);

    assertEquals(id, price.getId());
    assertEquals(brandId, price.getBrandId());
    assertEquals(startDate, price.getStartDate());
    assertEquals(endDate, price.getEndDate());
    assertEquals(priceList, price.getPriceList());
    assertEquals(productId, price.getProductId());
    assertEquals(priority, price.getPriority());
    assertEquals(priceValue, price.getPrice());
    assertEquals(currency, price.getCurrency());
  }
}
