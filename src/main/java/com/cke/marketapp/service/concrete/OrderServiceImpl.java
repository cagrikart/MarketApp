package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.ErrorDataResult;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.dto.request.OrderRequest;
import com.cke.marketapp.dto.response.OrderDateResponse;
import com.cke.marketapp.dto.response.OrderResponse;
import com.cke.marketapp.entities.*;
import com.cke.marketapp.repository.OrderRepository;
import com.cke.marketapp.service.abstracts.OrderService;
import com.cke.marketapp.util.OrderMapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderMapperUtil mapperUtil;

    public OrderResponse postOrder(OrderRequest request) {
        OrderResponse response = mapperUtil.postOrder(request); // Doğrudan OrderResponse döndüğünü varsayalım
        return response;
    }

    @Override
    public DataResult<List<OrderResponse>> orderWithPaymentId(Long paymentId) {
        List<OrderResponse> orderResponses =mapperUtil.orderWithPaymentId(paymentId);

        return new SuccessDataResult<>(orderResponses,"listed order by payment id ");
    }
    @Override
    public DataResult<List<OrderDateResponse>> dateOrder(LocalDate orderDate) {
        OrderDateResponse orderResponse;
        orderResponse = mapperUtil.listDateOrder(orderDate);
        return new SuccessDataResult<>(orderResponse, "Listed orders for the given date");
    }

    @Override
    public DataResult<List<Order>> getOrderByEmployeeId(Long employeeId) {
        List<OrderResponse> orderResponses =mapperUtil.getOrderByEmployeeId(employeeId);
        return new SuccessDataResult<>(orderResponses, "listed order by employee id ");
    }



}

