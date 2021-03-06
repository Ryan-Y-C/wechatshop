package com.wechatshop.controller;

import com.api.data.PageResponse;
import com.wechatshop.entity.ResponseData;
import com.wechatshop.generator.Shop;
import com.wechatshop.service.ShopService;
import com.wechatshop.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class ShopController {
    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shop")
    public PageResponse<Shop> getShop(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return shopService.getShopByUserId(UserContext.getCurrentUser().getId(), pageNum, pageSize);
    }

    @PostMapping("/shop")
    public ResponseData<Shop> createdShop(@RequestBody Shop shop, HttpServletResponse servletResponse) {
        clean(shop);
        servletResponse.setStatus(HttpServletResponse.SC_CREATED);
        return ResponseData.of(shopService.createdShop(shop, UserContext.getCurrentUser().getId()));
    }

    private void clean(Shop shop) {
        shop.setCreatedAt(new Date());
        shop.setUpdatedAt(new Date());
        shop.setOwnerUserId(null);
        shop.setOwnerUserId(1L);
    }

    @PatchMapping("/shop/{id}")
    public Object updateShop(@PathVariable("id") long id, @RequestBody Shop shop, HttpServletResponse response) {
        shop.setId(id);
            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseData.of(shopService.updateShop(shop, UserContext.getCurrentUser().getId()));

    }

    @DeleteMapping("/shop/{id}")
    public Object deleteShop(@PathVariable("id") long shopId, HttpServletResponse response) {
            return ResponseData.of(shopService.deleteShop(shopId, UserContext.getCurrentUser().getId()));

    }
}
