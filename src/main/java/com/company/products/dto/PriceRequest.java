package com.company.products.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PriceRequest {

  private LocalDateTime applicationDate;

  private Long productId;

  private Long brandId;
}
