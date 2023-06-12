package com.company.products.service;

import com.company.products.model.Price;
import com.company.products.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService {

    private final PriceRepository repository;

    @Autowired
    public PriceService(PriceRepository repository) {
        this.repository = repository;
    }

    public Price findPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return repository.findByBrandIdAndProductIdAndApplicationDate(brandId, productId, applicationDate);
    }
}
