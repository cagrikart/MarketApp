package com.cke.marketapp.dto;

import lombok.Data;

@Data
public class ProductOfShopIdResponse {

    private String productName;
    private String barkodCode;

    private double price;
    private int quantity;
}
