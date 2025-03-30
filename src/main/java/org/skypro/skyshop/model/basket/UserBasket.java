package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public class UserBasket {

    private final BasketItem basketItem;
    private final int total;

    public UserBasket(BasketItem basketItem) {
        this.basketItem = basketItem;
    }

}
