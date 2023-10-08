package com.cke.marketapp.dto.request;


import lombok.Data;


@Data
public class TickRequest {

    private Long tickId;

    private Long orderId;


    private Long employeeId;

    private Long clientId;
}
