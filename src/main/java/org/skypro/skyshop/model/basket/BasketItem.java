package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public class BasketItem {

    private final Product products;
    private final int amount;

    public BasketItem(Product products, int amount) {
        this.products = products;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Product getProduct() {
        return products;
    }

}
