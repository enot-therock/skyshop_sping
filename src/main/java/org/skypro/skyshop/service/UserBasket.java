package org.skypro.skyshop.service;

import org.skypro.skyshop.model.product.Product;

import java.util.List;
import java.util.stream.IntStream;

public class UserBasket {

    private final List<BasketItem> basketItem;
    private final int total;

    public UserBasket(List<BasketItem> basketItem) {
        this.basketItem = basketItem;
        this.total = basketItem.stream()
                .map(BasketItem::getProducts)
                .mapToInt(Product::getPrice)
                .sum();
    }
}
