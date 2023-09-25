package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.dto.request.DepartmentRequest;
import com.cke.marketapp.entities.Department;

import java.util.List;

public interface DepartmentService {
    DataResult<List<Department>> getDepartmentList();

    Result createDepartments(List<DepartmentRequest> departmentRequests);

    DataResult<List<Department>> getDepartmentsByShopId(Long shopId);

}

