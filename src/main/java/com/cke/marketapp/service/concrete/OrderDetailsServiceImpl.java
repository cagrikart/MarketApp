package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.core.utilities.results.SuccessResult;
import com.cke.marketapp.dto.OrderDetailsRequest;
import com.cke.marketapp.entities.Order;
import com.cke.marketapp.entities.OrderDetails;
import com.cke.marketapp.repository.OrderDetailsRepository;
import com.cke.marketapp.repository.OrderRepository;
import com.cke.marketapp.service.abstracts.OrderDetailsService;
import com.cke.marketapp.util.OrderDetailsMapperUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private OrderDetailsRepository orderDetailsRepository;
    private OrderDetailsMapperUtil mapperUtil;
    @Override
    public DataResult<List<OrderDetails>> getOrderDetails() {
        return new SuccessDataResult<List<OrderDetails>>(this.orderDetailsRepository.findAll());
    }

    @Override
    public DataResult<List<OrderDetails>> postOrderDetails(List<OrderDetailsRequest> orderDetailsRequest) {
        List<OrderDetails> orderDetails = mapperUtil.postOrderDetails(orderDetailsRequest);
        List<OrderDetails> savedOrderDetails = orderDetailsRepository.saveAll(orderDetails);
        return new SuccessDataResult<>(savedOrderDetails,"eklendi");
    }
}
