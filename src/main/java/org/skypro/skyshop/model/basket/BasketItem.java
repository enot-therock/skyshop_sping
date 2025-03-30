package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class BasketItem {

    private final List<Product> product;
    private final int amount;

    public BasketItem(List<Product> product, int amount) {
        this.product = new ArrayList<>();
        this.amount = amount;
    }

}
