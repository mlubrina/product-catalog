package com.company.products.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceResponse {

  private Long productId;
  private Long brandId;
  private int priceList;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private double price;
}
