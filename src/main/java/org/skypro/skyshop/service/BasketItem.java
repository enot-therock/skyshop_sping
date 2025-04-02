package org.skypro.skyshop.service;

import org.skypro.skyshop.model.product.Product;

import java.util.List;
import java.util.stream.Collectors;

public class BasketItem {

    private final List<Product> products;
    private final int amount;

    public BasketItem(List<Product> products, int amount) {
        this.products = products;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public List<Product> getProducts() {
        return products;
    }

}
