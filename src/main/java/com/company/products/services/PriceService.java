package com.company.products.services;

import com.company.products.models.Price;
import com.company.products.repositories.PriceRepository;
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
