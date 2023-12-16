package com.example.inventoryinstrument.price.repository;

import com.example.inventoryinstrument.price.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}
