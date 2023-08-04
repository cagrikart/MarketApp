package com.cke.marketapp.service.abstracts;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.dto.ShopRequest;
import com.cke.marketapp.dto.ShopResponse;
import com.cke.marketapp.entities.Shop;

import java.util.List;

public interface ShopService {
    DataResult<List<Shop>> getShopList();

    Result postShop(List<ShopRequest> shopRequest);
}
