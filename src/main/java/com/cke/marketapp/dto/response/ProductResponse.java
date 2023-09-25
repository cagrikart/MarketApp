package com.cke.marketapp.dto.response;

import lombok.Data;

@Data
public class ProductResponse {

    private String productName;
    private String barkodCode;

    private double price;
    private int quantity;
    private Long shopId;
}
