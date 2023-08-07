package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.entities.OrderDetails;

import javax.persistence.criteria.Order;
import java.util.List;

public interface OrderService {

    DataResult<List<Order>> getOrder();
    Result postOrder(Order order);

}
