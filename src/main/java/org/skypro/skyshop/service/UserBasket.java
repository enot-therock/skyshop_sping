package org.skypro.skyshop.service;

import java.util.List;

public class UserBasket {

    private final List<BasketItem> basketItem;
    private final int total;

    public UserBasket(List<BasketItem> basketItem) {
        this.basketItem = basketItem;
    }

    int total = basketItem.stream()
                .mapToInt(p -> p.getAmount() * p.getProducts().getPrice())
                .sum();
}
