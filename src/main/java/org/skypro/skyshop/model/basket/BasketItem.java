package org.skypro.skyshop.model.basket;

import lombok.Getter;
import org.skypro.skyshop.model.product.Product;

@Getter
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
