package com.cke.marketapp.controller;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.entities.Employee;
import com.cke.marketapp.service.abstracts.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {
    private EmployeeService employeeService;

    @GetMapping("/getListEmployee")
    public DataResult<List<Employee>> getListEmployee() {
        return this.employeeService.getListEmployee();
    }
    @GetMapping("/getEmployeeById")

    public Result getEmployeeById(Long employeeId) {
        return this.employeeService.getEmployeeById(employeeId);
    }
}
