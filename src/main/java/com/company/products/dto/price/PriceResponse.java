package com.company.products.dto.price;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PriceResponse {
    private Long productId;
    private Long brandId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
}
