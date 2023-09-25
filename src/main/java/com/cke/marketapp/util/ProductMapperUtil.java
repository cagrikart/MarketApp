package com.cke.marketapp.util;

import com.cke.marketapp.dto.response.ProductOfShopIdResponse;
import com.cke.marketapp.dto.request.ProductRequest;
import com.cke.marketapp.dto.response.ProductResponse;
import com.cke.marketapp.entities.Product;
import com.cke.marketapp.entities.Department;
import com.cke.marketapp.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductMapperUtil {
    private DepartmentRepository departmentRepository;

    public Product postProduct(ProductRequest request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setBarkodCode(request.getBarkodCode());
        Optional<Department> shopOptional = departmentRepository.findById(request.getShopId());
        if (shopOptional.isPresent()) {
            product.setDepartment(shopOptional.get());
        }
        return product;
    }
    public  ProductResponse listProduct(Product product) {
        ProductResponse response = new ProductResponse();
        response.setProductName(product.getProductName());
        response.setBarkodCode(product.getBarkodCode());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setShopId(product.getDepartment().getId());
        return response;
    }

    public ProductOfShopIdResponse listOfShopProduct(Product product) {
        ProductOfShopIdResponse response = new ProductOfShopIdResponse();
        response.setProductName(product.getProductName());
        response.setBarkodCode(product.getBarkodCode());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        return response;
    }
}
