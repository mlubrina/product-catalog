package com.company.products.services;

import com.company.products.models.Price;
import com.company.products.repositories.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Price Service Test")
public class PriceServiceTest {

  @Mock
  private PriceRepository repository;

  private PriceService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    service = new PriceService(repository);
  }

  @Test
  @DisplayName("Test findPrice should Returns Price")
  void testFindPriceReturnsPrice() {
    // Mock data
    LocalDateTime applicationDate = LocalDateTime.of(2023, 6, 13, 0, 0);
    Long productId = 123L;
    Long brandId = 456L;
    Price expectedPrice = new Price();
    when(
        repository.findByBrandIdAndProductIdAndApplicationDate(brandId, productId, applicationDate))
        .thenReturn(expectedPrice);

    Price actualPrice = service.findPrice(applicationDate, productId, brandId);

    assertEquals(expectedPrice, actualPrice);
  }
}
