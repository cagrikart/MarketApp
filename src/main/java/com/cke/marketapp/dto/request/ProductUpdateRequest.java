package com.cke.marketapp.dto.request;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    private Long productId;

    private String productName;
    private String barkodCode;

    private double price;
    private int quantity;
    private Long shopId;
}
