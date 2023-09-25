package com.cke.marketapp.repository;

import com.cke.marketapp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> getOrderByOrderDate(LocalDate orderDate);
}
