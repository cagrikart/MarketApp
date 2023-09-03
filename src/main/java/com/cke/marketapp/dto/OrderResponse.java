package com.cke.marketapp.dto;

import com.cke.marketapp.entities.OrderDetails;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderResponse {
    private Long employeeId;
    private List<OrderDetails> orderDetailsIds;
    private LocalDate orderDate;
    private double totalAmount;


}
