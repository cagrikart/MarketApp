package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.dto.OrderRequest;
import com.cke.marketapp.dto.OrderResponse;
import com.cke.marketapp.entities.Order;

public interface OrderService {

    //DataResult<List<Order>> getOrder();
    OrderResponse postOrder(OrderRequest request) ;
}
