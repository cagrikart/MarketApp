package com.cke.marketapp.util;

import com.cke.marketapp.dto.OrderDetailsRequest;
import com.cke.marketapp.dto.OrderRequest;
import com.cke.marketapp.dto.OrderResponse;
import com.cke.marketapp.entities.Employee;
import com.cke.marketapp.entities.Order;
import com.cke.marketapp.entities.OrderDetails;
import com.cke.marketapp.repository.EmployeeRepository;
import com.cke.marketapp.repository.OrderRepository;
import com.cke.marketapp.service.abstracts.OrderDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderMapperUtil {
    private EmployeeRepository employeeRepository;
    private OrderDetailsService orderDetailsService;
    private OrderRepository orderRepository;

    public OrderResponse listOrder(Order order) {
        OrderResponse response = new OrderResponse();
        response.setEmployeeId(order.getEmployee().getId());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());
        response.setOrderDetailsIds(order.getOrderDetailsList());
        return response;
    }

    public Order postOrder(OrderRequest request) {
        OrderResponse response = new OrderResponse();

        // OrderDetailsRequest listesini alın
        List<OrderDetailsRequest> detailsRequests = request.getOrderDetailsList();
        Optional<Employee> employeeOptional = employeeRepository.findById(request.getEmployeeId());

        // OrderDetails servisi aracılığıyla sipariş detaylarını ekleyin
        List<OrderDetails> orderDetailsList = orderDetailsService.postOrderDetails(detailsRequests).getData();

        // Yeni bir sipariş oluşturun
        Order order = new Order();
        if (employeeOptional.isPresent()) {
            order.setEmployee(employeeOptional.get());
        }
        order.setOrderDate(LocalDate.now()); // Sipariş tarihi
        // OrderDetails listesini siparişe ekleyin
        order.setOrderDetailsList(orderDetailsList);

        // Toplam miktarı hesapla ve ayarla
        double totalAmount = orderDetailsList.stream().mapToDouble(OrderDetails::getTotalPrice).sum();
        order.setTotalAmount(totalAmount);

        // Siparişi kaydet
        order = orderRepository.save(order);

        // OrderResponse nesnesini doldurun
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());
        response.setEmployeeId(order.getEmployee().getId());
        response.setOrderDetailsIds(orderDetailsList);
        return order;
    }
}
