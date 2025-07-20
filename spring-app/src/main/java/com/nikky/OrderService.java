package com.nikky;

public class OrderService {
    public void placeOrder(String user, int amount) {
        if (amount > 1000) {
            System.out.println("High value order");
        } else {
            System.out.println("Normal order");
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("Processing step " + i);
        }
    }
}
