package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.core.utilities.results.SuccessResult;
import com.cke.marketapp.dto.request.TickRequest;
import com.cke.marketapp.dto.response.TickResponse;
import com.cke.marketapp.entities.Product;
import com.cke.marketapp.entities.Tick;
import com.cke.marketapp.repository.TickRepository;
import com.cke.marketapp.service.abstracts.TickService;
import com.cke.marketapp.util.TickMapperUtil;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TickService {

    private TickRepository tickRepository;
    private TickMapperUtil mapperUtil;

    @Override
    public DataResult<List<Tick>> getAllTicket() {
        List<TickResponse> tickResponses = mapperUtil.getAllTicks();
        return new SuccessDataResult<>(tickResponses,"listed all  tick");
    }

    @Override
    public DataResult<List<TickResponse>> getAllTicketByTicketId(Long ticketId) {
        // Veritabanından belirli bir ticketId'ye sahip tüm tickleri alın.
        List<TickResponse> tickResponses = mapperUtil.listTicksByTickId(ticketId);

        if (!tickResponses.isEmpty()) {
            return new SuccessDataResult<>(tickResponses, "Tickler bulundu.");
        } else {
            return null;
        }
    }

    @Override
    public DataResult<List<Tick>> getAllTicketByEmployeeId(Long employeeId) {
        List<TickResponse> tickResponses = mapperUtil.getTickByEmployeeId(employeeId);

        if (!tickResponses.isEmpty()) {
            return new SuccessDataResult<>(tickResponses, "Tickler bulundu.");
        } else {
            return null;
        }
    }

    @Override
    public DataResult<List<Tick>> getAllTicketByOrderId(Long orderId) {
        List<TickResponse> tickResponses = mapperUtil.getTickByOrderId(orderId);

        if (!tickResponses.isEmpty()) {
            return new SuccessDataResult<>(tickResponses, "Tickler bulundu.");
        } else {
            return null;
        }
    }

    @Override
    public DataResult<List<Tick>> getAllTicketByClientId(Long clientId) {
        List<TickResponse> tickResponses = mapperUtil.getTickByClientId(clientId);

        if (!tickResponses.isEmpty()) {
            return new SuccessDataResult<>(tickResponses, "Tickler bulundu.");
        } else {
            return null;
        }
    }

    @Override
    public Result addTicker(TickRequest tickRequest) {
        TickResponse tickResponse = mapperUtil.createTick(tickRequest);

        if (tickResponse != null) {
            return new SuccessResult("Tick başarıyla oluşturuldu.");
        } else {
            return new SuccessResult("Tick oluşturulurken bir hata oluştu.");
        }
    }

}
