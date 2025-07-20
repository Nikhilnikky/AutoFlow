package com.nikky.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String processOrder(String item, int quantity) {
        StringBuilder status = new StringBuilder();
        status.append("Processing order for: ").append(item).append(" x ").append(quantity);

        if (quantity > 5) {
            status.append(" - Bulk order discount applied.");
        } else {
            status.append(" - No discount.");
        }

        for (int i = 0; i < quantity; i++) {
            status.append(" | Item #").append(i + 1).append(" packed.");
        }

        return status.toString();
    }
}
