package com.cke.marketapp.util;

import com.cke.marketapp.dto.ShopRequest;
import com.cke.marketapp.dto.ShopResponse;
import com.cke.marketapp.entities.Shop;

public class ShopMapperUtil {

    public static Shop postShop(ShopRequest request) {
        Shop shop = new Shop();
        shop.setShopName(request.getShopName());
        return  shop;
    }

    public static ShopResponse listShop(Shop shop ) {
        ShopResponse shopResponse = new ShopResponse();
        shopResponse.setShopName(shop.getShopName());
        return shopResponse;
    }
}
