package com.cke.marketapp.controller;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.entities.Payment;
import com.cke.marketapp.service.abstracts.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {
    private PaymentService paymentService;
    @GetMapping("/getAllPayment")
    public DataResult<List<Payment>> getAllPayment() {
        return this.paymentService.getAllPayment();
    }
    @PostMapping("/addPayment")
    public Result addPayment(@RequestBody Payment payment) {
        return this.paymentService.addPayment(payment);
    }
}
