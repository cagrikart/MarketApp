package com.cke.marketapp.controller;
import com.cke.marketapp.dto.OrderRequest;
import com.cke.marketapp.dto.OrderResponse;
import com.cke.marketapp.service.abstracts.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("/placeOrder")
    public OrderResponse placeOrder(@RequestBody OrderRequest requests) {
        return this.orderService.postOrder(requests);

    }
}
