package com.company.products.controllers;


import com.company.products.dto.PriceRequest;
import com.company.products.dto.PriceResponse;
import com.company.products.exceptions.ValidationException;
import com.company.products.models.Price;
import com.company.products.services.PriceService;
import com.company.products.utils.ValidationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/prices")
public class PriceController {

  private final PriceService service;

  public PriceController(PriceService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<PriceResponse> getPrice(PriceRequest request) throws ValidationException {
    ValidationUtils.validatePriceRequest(request);

    Optional<Price> optionalPrice = Optional.ofNullable(
        service.findPrice(request.getApplicationDate(),
            request.getProductId(), request.getBrandId()));

    return optionalPrice
        .map(this::convertToPriceResponse)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  private PriceResponse convertToPriceResponse(Price price) {
    return PriceResponse.builder()
        .productId(price.getProductId())
        .brandId(price.getBrandId())
        .priceList(price.getPriceList())
        .startDate(price.getStartDate())
        .endDate(price.getEndDate())
        .price(price.getPrice())
        .build();
  }
}
