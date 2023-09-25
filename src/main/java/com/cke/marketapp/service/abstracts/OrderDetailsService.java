package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.dto.request.OrderDetailsRequest;
import com.cke.marketapp.entities.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
    DataResult<List<OrderDetails>> getOrderDetails();
     DataResult<List<OrderDetails>> postOrderDetails(List<OrderDetailsRequest> requests) ;

}
