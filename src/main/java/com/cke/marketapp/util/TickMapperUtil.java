package com.cke.marketapp.util;

import com.cke.marketapp.dto.request.TickRequest;
import com.cke.marketapp.dto.response.TickResponse;
import com.cke.marketapp.entities.Order;
import com.cke.marketapp.entities.Tick;
import com.cke.marketapp.repository.ClientRepository;
import com.cke.marketapp.repository.EmployeeRepository;
import com.cke.marketapp.repository.OrderRepository;
import com.cke.marketapp.repository.TickRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class TickMapperUtil {

    private TickRepository tickRepository;
    private OrderRepository orderRepository;
    private EmployeeRepository employeeRepository;
    private ClientRepository clientRepository;

    public TickResponse createTick(TickRequest tickRequest) {
        // TickRequest nesnesini kullanarak yeni bir Tick nesnesi oluşturun.
        Tick tick = new Tick();
        tick.setOrder(orderRepository.findById(tickRequest.getOrderId()).orElse(null));
        tick.setEmployee(employeeRepository.findById(tickRequest.getEmployeeId()).orElse(null));
        tick.setClient(clientRepository.findById(tickRequest.getClientId()).orElse(null));

        // Yeni Tick'i veritabanına kaydedin.
        tick = tickRepository.save(tick);

        // Oluşturulan Tick'i TickResponse nesnesine dönüştürün.
        TickResponse tickResponse = new TickResponse();
        tickResponse.setTickId(tick.getId());
        tickResponse.setOrder(tick.getOrder().getId());
        tickResponse.setEmployee(tick.getEmployee().getId());
        tickResponse.setClient(tick.getClient().getId());

        return tickResponse;
    }

    public List<TickResponse> getAllTicks() {
        List<Tick> ticks = tickRepository.findAll(); // Veritabanından veya başka bir kaynaktan verileri alın.

        List<TickResponse> tickResponses = new ArrayList<>();

        for (Tick tick : ticks) {
            TickResponse tickResponse = new TickResponse();
            tickResponse.setTickId(tick.getId());
            tickResponse.setClient(tick.getClient() != null ? tick.getClient().getId() : null);
            tickResponse.setEmployee(tick.getEmployee() != null ? tick.getEmployee().getId() : null);
            tickResponse.setOrder(tick.getOrder() != null ? tick.getOrder().getId() : null);
            tickResponses.add(tickResponse);
        }

        return tickResponses;
    }

    public List<TickResponse> listTicksByTickId(Long tickId) {
        // Veritabanından belirli bir tickId'ye sahip Tick nesnesini alın.
        Optional<Tick> tickOptional = tickRepository.findById(tickId);
        // Tick nesnesi mevcutsa, TickResponse olarak dönüştürün.
        if (tickOptional.isPresent()) {
            Tick tick = tickOptional.get();
            TickResponse tickResponse = new TickResponse();
            tickResponse.setTickId(tick.getId());
            tickResponse.setOrder(tick.getOrder().getId());
            tickResponse.setEmployee(tick.getEmployee().getId());
            tickResponse.setClient(tick.getClient().getId());

            List<TickResponse> tickList = new ArrayList<>();
            tickList.add(tickResponse);

            return tickList;
        } else {
            return null;
        }
    }
    public List<TickResponse> getTickByEmployeeId(Long employeeId) {
        // Veritabanından belirli bir employeeId'ye sahip tüm Tickleri alın.
        List<Tick> ticks = tickRepository.getTickByEmployeeId(employeeId);
        List<TickResponse> tickResponses = new ArrayList<>();

        // Eğer sonuç boşsa, hemen boş bir liste döndürün.
        if (!ticks.isEmpty()) {
            for (Tick tick : ticks) {
                TickResponse tickResponse = new TickResponse();
                tickResponse.setTickId(tick.getId());
                tickResponse.setOrder(tick.getOrder() != null ? tick.getOrder().getId() : null);
                tickResponse.setEmployee(tick.getEmployee() != null ? tick.getEmployee().getId() : null);
                tickResponse.setClient(tick.getClient() != null ? tick.getClient().getId() : null);
                tickResponses.add(tickResponse);
            }
        }

        return tickResponses;
    }

    public List<TickResponse> getTickByOrderId(Long orderId) {
        List<Tick> ticks = tickRepository.getTickByOrderId(orderId);
        List<TickResponse> tickResponses = new ArrayList<>();

        if (!ticks.isEmpty()) {
            for (Tick tick : ticks) {
                TickResponse tickResponse = new TickResponse();
                tickResponse.setTickId(tick.getId());
                tickResponse.setOrder(tick.getOrder() != null ? tick.getOrder().getId() : null);
                tickResponse.setEmployee(tick.getEmployee() != null ? tick.getEmployee().getId() : null);
                tickResponse.setClient(tick.getClient() != null ? tick.getClient().getId() : null);
                tickResponses.add(tickResponse);
            }
        }

        return tickResponses;
    }
    public List<TickResponse> getTickByClientId(Long clientId) {
        List<Tick> ticks = tickRepository.getTickByClientId(clientId);
        List<TickResponse> tickResponses = new ArrayList<>();

        if (!ticks.isEmpty()) {
            for (Tick tick : ticks) {
                TickResponse tickResponse = new TickResponse();
                tickResponse.setTickId(tick.getId());
                tickResponse.setOrder(tick.getOrder() != null ? tick.getOrder().getId() : null);
                tickResponse.setEmployee(tick.getEmployee() != null ? tick.getEmployee().getId() : null);
                tickResponse.setClient(tick.getClient() != null ? tick.getClient().getId() : null);
                tickResponses.add(tickResponse);
            }
        }

        return tickResponses;
    }


}
