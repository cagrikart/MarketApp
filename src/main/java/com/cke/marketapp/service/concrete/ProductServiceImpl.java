package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.ErrorResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.dto.request.ProductPriceUpdate;
import com.cke.marketapp.dto.request.ProductRequest;
import com.cke.marketapp.dto.request.ProductStockUpdate;
import com.cke.marketapp.dto.request.ProductUpdateRequest;
import com.cke.marketapp.dto.response.ProductOfShopIdResponse;
import com.cke.marketapp.dto.response.ProductResponse;
import com.cke.marketapp.entities.Product;
import com.cke.marketapp.entities.Department;
import com.cke.marketapp.repository.ProductRepository;
import com.cke.marketapp.repository.DepartmentRepository;
import com.cke.marketapp.service.abstracts.ProductService;
import com.cke.marketapp.util.ProductMapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private DepartmentRepository departmentRepository;
    private ProductMapperUtil mapperUtil;

    @Override
    public DataResult<List<ProductResponse>> getProducts() {
        List<Product> products = this.productRepository.findAll();
        List<ProductResponse> responses = products.stream()
                .map(product -> mapperUtil.listProduct(product))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(responses, "ürünler listelendi");
    }

    @Override
    public Result postProduct(ProductRequest request) {
        Product product = mapperUtil.postProduct(request);
        Product savedProduct = productRepository.save(product);
        ProductResponse response = mapperUtil.listProduct(savedProduct);
        return new SuccessDataResult<>(response,"added new product");
    }

    @Override
    public DataResult<List<ProductOfShopIdResponse>> getProductsByShopId(Long departmentId) {
        List<Product> products = productRepository.findByDepartmentId(departmentId);
        List<ProductOfShopIdResponse> productResponses = products.stream()
                .map(mapperUtil::listOfShopProduct)
                .collect(Collectors.toList());

        return new SuccessDataResult<>(productResponses, "Products found by shopId");
    }

    @Override
    public Result updateProduct(ProductUpdateRequest request, Long productId) {
        Optional<Product> productDb = this.productRepository.findById(productId);
        Optional<Department> shopDb = this.departmentRepository.findById(request.getShopId());
        Product product = null;

        if (productDb.isPresent() && shopDb.isPresent()) {
            product = productDb.get();
            product.setProductName(request.getProductName());
            product.setQuantity(request.getQuantity());
            product.setPrice(request.getPrice());
            product.setBarkodCode(request.getBarkodCode());
            product.setDepartment(shopDb.get());

            Product updateProduct = this.productRepository.save(product);
            ProductResponse response = mapperUtil.listProduct(updateProduct);

            return new SuccessDataResult<>(response, "product updated  ");
        } else {
            return new ErrorResult("product or shop not found ");
        }
    }
    @Override
    public Result updateProductPrice(ProductPriceUpdate request, Long productId) {
        Optional<Product> productDb = this.productRepository.findById(productId);
        Optional<Department> shopDb = this.departmentRepository.findById(request.getShopId());
        Product product = null;

        if (productDb.isPresent() && shopDb.isPresent()) {
            product = productDb.get();
            product.setPrice(request.getPrice());
            product.setDepartment(shopDb.get());

            Product updateProduct = this.productRepository.save(product);
            ProductResponse response = mapperUtil.listProduct(updateProduct);

            return new SuccessDataResult<>(response, "product updated price ");
        } else {
            return new ErrorResult("product or shop not found ");
        }
    }

    @Override
    public Result updateProductStock(ProductStockUpdate request, Long productId) {
        Optional<Product> productDb = this.productRepository.findById(productId);
        Optional<Department> shopDb = this.departmentRepository.findById(request.getShopId());
        Product product = null;

        if (productDb.isPresent() && shopDb.isPresent()) {
            product = productDb.get();
            product.setQuantity(request.getQuantity());
            product.setDepartment(shopDb.get());

            Product updateProduct = this.productRepository.save(product);
            ProductResponse response = mapperUtil.listProduct(updateProduct);

            return new SuccessDataResult<>(response, "product updated Stock ");
        } else {
            return new ErrorResult("product or shop not found ");
        }
    }

}
