package com.cke.marketapp.util;

import com.cke.marketapp.dto.request.DepartmentRequest;
import com.cke.marketapp.dto.response.DepartmentResponse;
import com.cke.marketapp.entities.Department;
import com.cke.marketapp.entities.Shop;
import com.cke.marketapp.repository.DepartmentRepository;
import com.cke.marketapp.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DepartmentMapperUtil {

    private  ShopRepository shopRepository;
    private  DepartmentRepository departmentRepository;
    public Department createDepartment(DepartmentRequest request) {
        // Null değer kontrolü ekleyin
        if (request == null || request.getShopId() == null || request.getDepartmentName() == null) {
            throw new IllegalArgumentException("Invalid DepartmentRequest");
        }

        // Shop'u bul
        Shop shop = this.shopRepository.findById(request.getShopId())
                .orElseThrow(() -> new NullPointerException("Shop not found"));

        // Yeni bir departman oluşturun ve bilgileri ayarlayın
        Department department = new Department();
        department.setDepartmentName(request.getDepartmentName());
        department.setShop(shop);

        // Departmanı kaydedin ve geri döndürün
        return departmentRepository.save(department);
    }
    public  DepartmentResponse listShop(Department department) {
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setShopName(department.getDepartmentName());
        return departmentResponse;
    }
}
