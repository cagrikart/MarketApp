package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.core.utilities.results.SuccessResult;
import com.cke.marketapp.entities.Client;
import com.cke.marketapp.repository.ClientRepository;
import com.cke.marketapp.service.abstracts.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    @Override
    public DataResult<List<Client>> getListClient() {
        return new SuccessDataResult<List<Client>>(this.clientRepository.findAll());
    }

    @Override
    public Result addClient(Client client) {
        this.clientRepository.save(client);
        return new SuccessResult("added client");
    }
}
