package com.cke.marketapp.dto.request;

import lombok.Data;

@Data
public class ProductStockUpdate {
    private Long productId;
    private int quantity;
    private Long shopId;
}
