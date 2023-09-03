package com.cke.marketapp.controller;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.core.utilities.results.SuccessResult;
import com.cke.marketapp.dto.OrderDetailsRequest;
import com.cke.marketapp.entities.OrderDetails;
import com.cke.marketapp.service.abstracts.OrderDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderdetails")
@AllArgsConstructor
public class OrderDetailsController {

    private OrderDetailsService orderDetailsService;
    @GetMapping("/getOrderDetails")
    public DataResult<List<OrderDetails>> getOrderDetails() {
        return new SuccessDataResult<>(this.orderDetailsService.getOrderDetails());
    }

    @PostMapping("/postOrderDetails")
    public Result postOrderDetails(@RequestBody List<OrderDetailsRequest>  orderDetailsRequest) {
        return new SuccessDataResult<>(this.orderDetailsService.postOrderDetails(orderDetailsRequest));
    }
}
