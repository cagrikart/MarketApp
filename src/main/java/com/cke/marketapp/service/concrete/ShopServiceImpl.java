package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.DataResult;
import com.cke.marketapp.core.utilities.results.Result;
import com.cke.marketapp.core.utilities.results.SuccessDataResult;
import com.cke.marketapp.entities.Shop;
import com.cke.marketapp.repository.ShopRepository;
import com.cke.marketapp.service.abstracts.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {
    private ShopRepository shopRepository;
    @Override
    public DataResult<List<Shop>> getShopList() {
        return new SuccessDataResult<List<Shop>>( this.shopRepository.findAll(),"listed shop");
    }

    @Override
    public Result postShop(Shop shop) {
        return new SuccessDataResult<>(this.shopRepository.save(shop),"added shop");
    }
}
