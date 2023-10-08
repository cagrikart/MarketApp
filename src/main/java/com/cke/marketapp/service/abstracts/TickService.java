package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.dto.request.TickRequest;
import com.cke.marketapp.dto.response.TickResponse;
import com.cke.marketapp.entities.Tick;

import java.util.List;

public interface TickService {
    DataResult<List<Tick>> getAllTicket();

    DataResult<List<TickResponse>> getAllTicketByTicketId(Long ticketId);

    DataResult<List<Tick>> getAllTicketByEmployeeId(Long employeeId);
    DataResult<List<Tick>> getAllTicketByOrderId(Long orderId);

    DataResult<List<Tick>> getAllTicketByClientId(Long clientId);
    Result addTicker(TickRequest tickRequest);
}
