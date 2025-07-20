package com.nikky;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/place-order")
    public String placeOrder() {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            total += i;
        }

        if (total > 2) {
            return "Order Placed";
        }
        return "Failed";
    }
}
