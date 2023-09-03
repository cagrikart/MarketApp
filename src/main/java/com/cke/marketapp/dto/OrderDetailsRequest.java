package com.cke.marketapp.dto;

import com.cke.marketapp.entities.Order;
import com.cke.marketapp.entities.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class OrderDetailsRequest {

    private int quantity;
    private Long productId;
}
