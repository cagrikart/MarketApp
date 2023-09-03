package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.dto.OrderDetailsRequest;
import com.cke.marketapp.dto.OrderRequest;
import com.cke.marketapp.dto.OrderResponse;
import com.cke.marketapp.entities.*;
import com.cke.marketapp.repository.EmployeeRepository;
import com.cke.marketapp.repository.OrderDetailsRepository;
import com.cke.marketapp.repository.OrderRepository;
import com.cke.marketapp.repository.ProductRepository;
import com.cke.marketapp.service.abstracts.OrderDetailsService;
import com.cke.marketapp.service.abstracts.OrderService;
import com.cke.marketapp.util.OrderMapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private EmployeeRepository employeeRepository;
    private ProductRepository productRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private OrderDetailsService orderDetailsService;
    private OrderRepository orderRepository;
    private OrderMapperUtil mapperUtil;



    public OrderResponse postOrder(OrderRequest request) {
        // Order nesnesini oluşturun ve veritabanına kaydedin
        Order order = mapperUtil.postOrder(request); // request nesnesini Order nesnesine dönüştürmek için bir mapper kullanıyorsanız
        Order savedOrder = orderRepository.save(order);

        // MapperUtil ile dönüşüm yaparak OrderResponse nesnesini oluşturun
        OrderResponse response = mapperUtil.listOrder(savedOrder);

        // Diğer gerekli bilgileri ekleyebilirsiniz

        return response;
    }









}
