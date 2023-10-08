package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.entities.Payment;

import java.util.List;

public interface PaymentService {
    DataResult<List<Payment>> getAllPayment();
    Result addPayment(Payment payment);
}
