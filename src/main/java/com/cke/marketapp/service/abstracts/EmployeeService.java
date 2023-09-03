package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.entities.Employee;

import javax.xml.crypto.Data;
import java.util.List;

public interface EmployeeService {
    DataResult<List<Employee>> getListEmployee();
    Result getEmployeeById(Long employeeId);
}
