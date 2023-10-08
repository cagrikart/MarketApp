package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.core.utilities.results.SuccessResult;
import com.cke.marketapp.entities.Payment;
import com.cke.marketapp.repository.PaymentRepository;
import com.cke.marketapp.service.abstracts.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    @Override
    public DataResult<List<Payment>> getAllPayment() {
        return new SuccessDataResult<List<Payment>>( this.paymentRepository.findAll());
    }

    @Override
    public Result addPayment(Payment payment) {
        this.paymentRepository.save(payment);
        return new SuccessResult("added payment");
    }
}
