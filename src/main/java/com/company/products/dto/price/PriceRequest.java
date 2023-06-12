package com.company.products.dto.price;

import lombok.Data;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
public class PriceRequest {
    @FutureOrPresent()
    private LocalDateTime applicationDate;

    @Positive()
    private Long productId;

    @Positive()
    private Long brandId;
}
