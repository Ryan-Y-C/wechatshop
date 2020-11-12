package com.wechatshop.controller;

import com.wechatshop.api.OrderService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Reference(version = "${wechatshop.orderservice.version}", url = "${wechatshop.orderservice.url}")
    private OrderService orderService;

    @RequestMapping("/testRpc")
    public String testRpc() {
        orderService.placeOrder(1, 2);
        return "";
    }
}