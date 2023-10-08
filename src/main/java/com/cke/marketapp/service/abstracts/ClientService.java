package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.entities.Client;
import com.cke.marketapp.entities.Employee;

import java.util.List;

public interface ClientService {
    DataResult<List<Client>> getListClient();
    Result addClient(Client client);

}
