package com.cke.marketapp.dto;

import com.cke.marketapp.entities.Shop;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class ProductRequest {

    private Long productId;

    private String productName;
    private String barkodCode;

    private double price;
    private int quantity;
    private Long shopId;
}
