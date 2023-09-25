package com.cke.marketapp.dto.request;

import lombok.Data;

@Data
public class ProductPriceUpdate {
    private Long productId;
    private double price;
    private Long shopId;
}
