package com.cke.marketapp.controller;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.dto.*;
import com.cke.marketapp.entities.Product;
import com.cke.marketapp.service.abstracts.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping("/getProduct")
    public DataResult<List<ProductResponse>> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/{shopId}")
    public DataResult<List<Product>> getProductsByShopId(@PathVariable Long shopId) {
        return new SuccessDataResult<>(productService.getProductsByShopId(shopId));
    }


    @PostMapping("/postProduct")
    public Result postProduct(@RequestBody ProductRequest request) {
        return this.productService.postProduct(request);
    }

    @PostMapping("/updateProduct")
    public Result updateProduct(@RequestBody ProductUpdateRequest request, @RequestParam Long productId) {
        return productService.updateProduct(request, productId);
    }

    @PostMapping("/updateProductPrice")

    public Result updateProductPrice(@RequestBody ProductPriceUpdate request, @RequestParam Long productId) {
        return productService.updateProductPrice(request, productId);

    }

    @PostMapping("/updateProductStock")

    public Result updateProductStock(@RequestBody ProductStockUpdate request, @RequestParam Long productId) {
        return productService.updateProductStock(request, productId);

    }

}
