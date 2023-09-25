package com.cke.marketapp.repository;

import com.cke.marketapp.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,Long> {
}
