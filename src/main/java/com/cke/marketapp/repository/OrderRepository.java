package com.cke.marketapp.repository;

import com.cke.marketapp.entities.Order;
import com.cke.marketapp.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
