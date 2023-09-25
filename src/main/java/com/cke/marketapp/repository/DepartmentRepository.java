package com.cke.marketapp.repository;


import com.cke.marketapp.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    List<Department> findByShopId(Long shopId);

}
