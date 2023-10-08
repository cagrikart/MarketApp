package com.cke.marketapp.controller;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.entities.Client;
import com.cke.marketapp.service.abstracts.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/client")
@RestController
@AllArgsConstructor
public class ClientController {
    private ClientService clientService;

    @GetMapping("/getListClient")
    public DataResult<List<Client>> getListClient() {
        return clientService.getListClient();
    }

    @PostMapping("/addClient")
    public Result addClient(@RequestBody Client client) {
        return this.clientService.addClient(client);
    }
}
