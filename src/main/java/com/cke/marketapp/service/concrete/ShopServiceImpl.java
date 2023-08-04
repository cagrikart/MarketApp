package com.cke.marketapp.service.concrete;

import com.cke.marketapp.core.utilities.results.*;
import com.cke.marketapp.dto.ShopRequest;
import com.cke.marketapp.dto.ShopResponse;
import com.cke.marketapp.entities.Shop;
import com.cke.marketapp.repository.ShopRepository;
import com.cke.marketapp.service.abstracts.ShopService;
import com.cke.marketapp.util.ShopMapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {
    private ShopRepository shopRepository;

    @Override
    public DataResult<List<Shop>> getShopList() {
        try {
            return new SuccessDataResult<List<Shop>>(this.shopRepository.findAll(),"listed shops");
        }
        catch (Exception e ) {
            return  new ErrorDataResult<>("kod errora düştü");
        }
    }

    @Override
    public Result postShop(List<ShopRequest> shopRequest) {
        List<Shop> shopList = shopRequest.stream().map(ShopMapperUtil::postShop).collect(Collectors.toList());
        this.shopRepository.saveAll(shopList);
        return new SuccessResult("added shop");
    }
}
