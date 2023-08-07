package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.service.abstracts.OrderService;

import javax.persistence.criteria.Order;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public DataResult<List<Order>> getOrder() {
        return null;
    }

    @Override
    public Result postOrder(Order order) {
        return null;
    }
}
