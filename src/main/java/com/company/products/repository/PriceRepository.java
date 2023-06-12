package com.company.products.repository;

import com.company.products.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.brandId = :brandId AND p.productId = :productId " +
            "AND p.startDate <= :applicationDate AND p.endDate >= :applicationDate " +
            "AND p.priority = (SELECT MAX(p2.priority) FROM Price p2 " +
            "WHERE p2.brandId = :brandId AND p2.productId = :productId " +
            "AND p2.startDate <= :applicationDate AND p2.endDate >= :applicationDate)")
    Price findByBrandIdAndProductIdAndApplicationDate(Long brandId, Long productId, LocalDateTime applicationDate);



}
