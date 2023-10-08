package com.cke.marketapp.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderRequest {

    private Long employeeId;

    private LocalDate orderDate;

    private Long paymentId;

    private List<OrderDetailsRequest> orderDetailsList = new ArrayList<>();

}
