package com.cke.marketapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate orderDate;

    private double totalAmount;

    // Siparişin birden fazla detayı olabileceği için ilişkiyi OneToMany olarak belirliyoruz.
    // CascadeType.ALL, bir sipariş silindiğinde ilişkili detayların da silinmesini sağlar.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetailsList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    public void addOrderDetails(OrderDetails orderDetails) {
        this.orderDetailsList.add(orderDetails);
    }
}
