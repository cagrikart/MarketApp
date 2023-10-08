package com.cke.marketapp.controller;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.dto.request.TickRequest;
import com.cke.marketapp.dto.response.TickResponse;
import com.cke.marketapp.entities.Tick;
import com.cke.marketapp.repository.TickRepository;
import com.cke.marketapp.service.abstracts.TickService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticks")
@Api(value="tick", description="veresiye sistemi için endpointler")

@AllArgsConstructor
public class TickController {
    private TickService tickService;


    @GetMapping("/getAllTicket")

    public DataResult<List<Tick>> getAllTicket() {
        return tickService.getAllTicket();
    }

    @GetMapping("/getAllTicketByTicketId")

    public DataResult<List<TickResponse>> getAllTicketByTicketId(@RequestParam("ticketId") Long ticketId) {
        return tickService.getAllTicketByTicketId(ticketId);
    }

    @GetMapping("/getAllTicketByEmployeeId")
    public DataResult<List<Tick>> getAllTicketByEmployeeId(@RequestParam("employeeeId") Long employeeeId) {
        return tickService.getAllTicketByEmployeeId(employeeeId);
    }

    @GetMapping("/getAllTicketByOrderId")
    public DataResult<List<Tick>> getAllTicketByOrderId(@RequestParam("orderId") Long orderId) {
        return tickService.getAllTicketByOrderId(orderId);
    }

    @GetMapping("/getAllTicketByClientId")
    public DataResult<List<Tick>> getAllTicketByClientId(@RequestParam("clientId") Long clientId) {
        return tickService.getAllTicketByClientId(clientId);
    }

    @PostMapping("/addTicker")
    public Result addTicker(@RequestBody TickRequest tickRequest) {
        return tickService.addTicker(tickRequest);
    }
}