package com.cke.marketapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String barkodCode;
    private double purchasePrice;
    private double price;

    private double categoryId;
    private double stockGroupId;
    private String groupName;

    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

}