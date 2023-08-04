package com.cke.marketapp.entities;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;
    private String barkodCode;

    private double price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id") // Bu, ürün tablosunda mağaza ile ilişkilendiren sütunu belirtir.
    private Shop shop;


}