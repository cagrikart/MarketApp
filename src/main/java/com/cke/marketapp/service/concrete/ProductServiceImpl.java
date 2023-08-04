package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.*;
import com.cke.marketapp.dto.*;
import com.cke.marketapp.entities.Product;
import com.cke.marketapp.entities.Shop;
import com.cke.marketapp.repository.ProductRepository;
import com.cke.marketapp.repository.ShopRepository;
import com.cke.marketapp.service.abstracts.ProductService;
import com.cke.marketapp.util.ProductMapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ShopRepository shopRepository;
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
        return new SuccessDataResult<>(savedProduct, "eklnedi product");
    }

    @Override
    public DataResult<List<ProductOfShopIdResponse>> getProductsByShopId(Long shopId) {
        List<Product> products = productRepository.findByShopId(shopId);
        List<ProductOfShopIdResponse> productResponses = products.stream()
                .map(mapperUtil::listOfShopProduct)
                .collect(Collectors.toList());

        return new SuccessDataResult<>(productResponses, "Products found by shopId");
    }

    @Override
    public Result updateProduct(ProductUpdateRequest request, Long productId) {
        Optional<Product> productDb = this.productRepository.findById(productId);
        Optional<Shop> shopDb = this.shopRepository.findById(request.getShopId());
        Product product = null;

        if (productDb.isPresent() && shopDb.isPresent()) {
            product = productDb.get();
            product.setProductName(request.getProductName());
            product.setQuantity(request.getQuantity());
            product.setPrice(request.getPrice());
            product.setBarkodCode(request.getBarkodCode());
            product.setShop(shopDb.get());

            Product updateProduct = this.productRepository.save(product);
            return new SuccessDataResult<>(updateProduct, "Ürün güncellendi");
        } else {
            return new ErrorResult("Ürün veya dükkan bulunamadı");
        }
    }
    @Override
    public Result updateProductPrice(ProductPriceUpdate request, Long productId) {
        Optional<Product> productDb = this.productRepository.findById(productId);
        Optional<Shop> shopDb = this.shopRepository.findById(request.getShopId());
        Product product = null;

        if (productDb.isPresent() && shopDb.isPresent()) {
            product = productDb.get();
            product.setPrice(request.getPrice());
            product.setShop(shopDb.get());

            Product updateProduct = this.productRepository.save(product);
            return new SuccessDataResult<>(updateProduct, "Ürün güncellendi");
        } else {
            return new ErrorResult("Ürün veya dükkan bulunamadı");
        }
    }

    @Override
    public Result updateProductStock(ProductStockUpdate request, Long productId) {
        Optional<Product> productDb = this.productRepository.findById(productId);
        Optional<Shop> shopDb = this.shopRepository.findById(request.getShopId());
        Product product = null;

        if (productDb.isPresent() && shopDb.isPresent()) {
            product = productDb.get();
            product.setQuantity(request.getQuantity());
            product.setShop(shopDb.get());

            Product updateProduct = this.productRepository.save(product);
            return new SuccessDataResult<>(updateProduct, "Ürün güncellendi");
        } else {
            return new ErrorResult("Ürün veya dükkan bulunamadı");
        }
    }

}
