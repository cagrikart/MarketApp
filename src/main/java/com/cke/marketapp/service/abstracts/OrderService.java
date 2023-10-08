package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.dto.request.OrderRequest;
import com.cke.marketapp.dto.response.OrderDateResponse;
import com.cke.marketapp.dto.response.OrderResponse;
import com.cke.marketapp.entities.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {


    DataResult<List<OrderDateResponse>> dateOrder(LocalDate orderDate);
    OrderResponse postOrder(OrderRequest request);

    DataResult<List<OrderResponse>> orderWithPaymentId(Long paymentId);
    DataResult<List<Order> >getOrderByEmployeeId(Long employeeId);



}
