package com.company.products.controllers;

import com.company.products.dto.PriceRequest;
import com.company.products.models.Price;
import com.company.products.services.PriceService;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Price Controller Test")
class PriceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PriceService priceService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Test should get Price ValidRequest and Returns Price Response")
  void testShouldGetPriceValidRequestAndReturnsPriceResponse() throws Exception {
    PriceRequest request = new PriceRequest();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    LocalDateTime applicationDate = LocalDateTime.now();
    String formattedDate = applicationDate.format(formatter);
    request.setApplicationDate(LocalDateTime.parse(formattedDate, formatter));
    request.setProductId(123L);
    request.setBrandId(456L);

    Price price = new Price();
    price.setProductId(request.getProductId());
    price.setBrandId(request.getBrandId());
    price.setPriceList(1);
    price.setStartDate(LocalDateTime.now().minusDays(1));
    price.setEndDate(LocalDateTime.now().plusDays(1));
    price.setPrice(9.99);

    when(priceService.findPrice(any(LocalDateTime.class), any(Long.class), any(Long.class)))
        .thenReturn(price);

    mockMvc.perform(MockMvcRequestBuilders.get("/prices")
            .param("applicationDate", request.getApplicationDate().toString())
            .param("productId", request.getProductId().toString())
            .param("brandId", request.getBrandId().toString())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(price.getProductId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(price.getBrandId()))
        .andReturn();
  }
}
