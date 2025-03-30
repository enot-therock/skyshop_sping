package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@SessionScope
public class ProductBasket {

    private final Map<UUID, Integer> products;

    public ProductBasket() {
        this.products = new HashMap<>();
    }

    public void addProductBasket(UUID id) {
        products.put(id, products.get(id));
    }

    public Map<UUID, Integer> allProducts() {
        return Collections.unmodifiableMap(products);
    }
}
