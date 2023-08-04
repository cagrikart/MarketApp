package com.cke.marketapp.controller;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.dto.ShopRequest;
import com.cke.marketapp.entities.Shop;
import com.cke.marketapp.service.abstracts.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
@AllArgsConstructor
public class ShopController {
    private ShopService shopService;


    @GetMapping("/getShop")
    public DataResult<List<Shop>> getShopList() {
        return this.shopService.getShopList();
    }

    @PostMapping("/postShop")
    public Result postShop(@RequestBody List<ShopRequest> shopRequest) {
        return this.shopService.postShop(shopRequest);
    }
}
