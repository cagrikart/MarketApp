package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.*;
import com.cke.marketapp.dto.request.DepartmentRequest;
import com.cke.marketapp.entities.Department;
import com.cke.marketapp.repository.DepartmentRepository;
import com.cke.marketapp.service.abstracts.DepartmentService;
import com.cke.marketapp.util.DepartmentMapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private DepartmentMapperUtil mapperUtil;

    @Override
    public DataResult<List<Department>> getDepartmentList() {
        try {
            return new SuccessDataResult<List<Department>>(this.departmentRepository.findAll(),"listed shops");
        }
        catch (Exception e ) {
            return  new ErrorDataResult<>("not listed ");
        }
    }
    @Override
    public DataResult<List<Department>> getDepartmentsByShopId(Long shopId) {
        return new SuccessDataResult<List<Department>>(departmentRepository.findByShopId(shopId),"shop id ye göre filtrelendi ");
    }
    @Override
    public Result createDepartments(List<DepartmentRequest> departmentRequests) {
        // Null değer kontrolü ekleyin
        if (departmentRequests == null || departmentRequests.isEmpty()) {
            throw new IllegalArgumentException("DepartmentRequests list is empty");
        }

        // Departmanları oluşturun
        List<Department> departments = departmentRequests.stream()
                .map(mapperUtil::createDepartment)
                .collect(Collectors.toList());

        // Departmanları kaydedin
        departmentRepository.saveAll(departments);

        return new SuccessResult("Departments added successfully");
    }
}
