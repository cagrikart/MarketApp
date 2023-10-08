package com.cke.marketapp.repository;

import com.cke.marketapp.entities.Tick;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TickRepository extends JpaRepository<Tick,Long> {
    List<Tick> getTickByEmployeeId(Long employeeId);
    List<Tick> getTickByOrderId(Long orderId);
    List<Tick> getTickByClientId(Long clientId);

}
