package com.cke.marketapp.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDateResponse {
    private LocalDate orderDate;
    private double totalAmount;


}