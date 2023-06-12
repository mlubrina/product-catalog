package com.company.products.controller;

import com.company.products.dto.price.PriceRequest;
import com.company.products.dto.price.PriceResponse;
import com.company.products.model.Price;
import com.company.products.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService service;

    @Autowired
    public PriceController(PriceService service) {
        this.service = service;
    }

    @GetMapping
    public PriceResponse getPrice(@Valid PriceRequest request) {

        Price price = service.findPrice(request.getApplicationDate(), request.getProductId(), request.getBrandId());

        PriceResponse priceResponse = convertToPriceResponse(price);

        return priceResponse;
    }

    private PriceResponse convertToPriceResponse(Price price) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setProductId(price.getProductId());
        priceResponse.setBrandId(price.getBrandId());
        priceResponse.setPriceList(price.getPriceList());
        priceResponse.setStartDate(price.getStartDate());
        priceResponse.setEndDate(price.getEndDate());
        priceResponse.setPrice(price.getPrice());
        return priceResponse;
    }
}
