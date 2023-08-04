package com.cke.marketapp.dto;

import lombok.Data;

@Data
public class ProductResponse {
    private Long productId;

    private String productName;
    private String barkodCode;

    private double price;
    private int quantity;
    private Long shopId;
}