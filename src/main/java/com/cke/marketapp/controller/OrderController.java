package com.cke.marketapp.controller;
import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.dto.request.OrderRequest;
import com.cke.marketapp.dto.response.OrderDateResponse;
import com.cke.marketapp.dto.response.OrderResponse;
import com.cke.marketapp.entities.Order;
import com.cke.marketapp.service.abstracts.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("/postOrder")
    public OrderResponse postOrder(@RequestBody OrderRequest requests) {
        return this.orderService.postOrder(requests);

    }

    @GetMapping("/ordersWithDate")
    public DataResult<List<OrderDateResponse>> ordersWithDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderDate) {
        return this.orderService.dateOrder(orderDate);
    }

    @GetMapping("/orderWithPaymentId")
    public DataResult<List<OrderResponse>> orderWithPaymentId(@RequestParam("paymentId") Long paymentId) {
        return this.orderService.orderWithPaymentId(paymentId);
    }

    @GetMapping("/getOrderByEmployeeId")
    public DataResult<List<Order>> getOrderByEmployeeId(@RequestParam("employeeId") Long employeeId) {
        return this.orderService.getOrderByEmployeeId(employeeId);
    }
}
