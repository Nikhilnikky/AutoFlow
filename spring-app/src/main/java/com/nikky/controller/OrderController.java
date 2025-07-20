package com.nikky.controller;

import com.nikky.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(@RequestParam String item, @RequestParam int quantity) {
        return orderService.processOrder(item, quantity);
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Order service is running!";
    }
}
