package com.cke.marketapp.repository;

import com.cke.marketapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByDepartmentId(Long deparmentId);

}
