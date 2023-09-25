package com.cke.marketapp.controller;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.dto.request.DepartmentRequest;
import com.cke.marketapp.entities.Department;
import com.cke.marketapp.service.abstracts.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentService departmentService;


    @GetMapping("/getDepartmentList")
    public DataResult<List<Department>> getDepartmentList() {
        return this.departmentService.getDepartmentList();
    }

    @GetMapping("/shop/{shopId}")
    public DataResult<List<Department>> getDepartmentsByShopId(@PathVariable Long shopId) {
        return departmentService.getDepartmentsByShopId(shopId);
    }


    @PostMapping("/postDepartment")
    public Result postDepartment(@RequestBody List<DepartmentRequest> departmentRequest) {
        return this.departmentService.createDepartments(departmentRequest);
    }
}
