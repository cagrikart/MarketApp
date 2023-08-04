package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.dto.*;
import com.cke.marketapp.entities.Product;

import java.util.List;

public interface ProductService {
    DataResult<List<ProductResponse>> getProducts();
    Result postProduct(ProductRequest request);

    DataResult<List<ProductOfShopIdResponse>> getProductsByShopId(Long shopId) ;

    Result updateProduct(ProductUpdateRequest request, Long productId);

    Result updateProductPrice(ProductPriceUpdate request, Long productId);
    Result updateProductStock(ProductStockUpdate request, Long productId);

}
