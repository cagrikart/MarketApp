package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.entities.Employee;
import com.cke.marketapp.repository.EmployeeRepository;
import com.cke.marketapp.service.abstracts.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public DataResult<List<Employee>> getListEmployee() {
        return new SuccessDataResult<List<Employee>>(this.employeeRepository.findAll(),"listed all Employee");
    }

    @Override
    public Result getEmployeeById(Long employeeId) {
        return new SuccessDataResult<>(this.employeeRepository.findById(employeeId));
    }
}
