package org.skypro.skyshop.model.basket;

import lombok.Getter;
import org.skypro.skyshop.model.product.Product;
import java.util.List;

@Getter
public class UserBasket {

    private final List<BasketItem> items;
    private final int total;

    public UserBasket(List<BasketItem> items) {
        this.items = items;
        this.total = items.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getAmount())
                .sum();
    }

//    public List<BasketItem> getItems() {
//        return items;
//    }
//
//    public int getTotal() {
//        return total;
//    }
}
