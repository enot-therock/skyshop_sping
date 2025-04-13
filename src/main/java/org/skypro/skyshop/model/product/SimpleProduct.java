package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class SimpleProduct extends Product implements Comparable<Searchable> {

    protected int price;

    public SimpleProduct(UUID id, String productName, int price) {
        super(id, productName);
        this.price = price;
        if (price < 1) {
            throw new IllegalArgumentException("Указанная цена недействительна");
        }
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return getProduct() + ": " + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public int compareTo(Searchable o) {
        return this.searchableName().compareTo(o.searchableName());
    }
}
