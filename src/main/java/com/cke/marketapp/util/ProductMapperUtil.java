package com.cke.marketapp.util;

import com.cke.marketapp.dto.ProductOfShopIdResponse;
import com.cke.marketapp.dto.ProductRequest;
import com.cke.marketapp.dto.ProductResponse;
import com.cke.marketapp.entities.Product;
import com.cke.marketapp.entities.Shop;
import com.cke.marketapp.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductMapperUtil {
    private ShopRepository shopRepository;

    public Product postProduct(ProductRequest request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setBarkodCode(request.getBarkodCode());
        Optional<Shop> shopOptional = shopRepository.findById(request.getShopId());
        if (shopOptional.isPresent()) {
            product.setShop(shopOptional.get());
        }
        return product;
    }
    public  ProductResponse listProduct(Product product) {
        ProductResponse response = new ProductResponse();
        response.setProductName(product.getProductName());
        response.setBarkodCode(product.getBarkodCode());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setShopId(product.getShop().getId());
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
