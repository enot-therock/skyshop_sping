package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class FixPriceProduct extends Product implements Comparable<Searchable> {

    protected final int FIX_PRICE = 99;

    public FixPriceProduct(UUID id, String productName) {
        super(id, productName);
    }

    @Override
    public int getPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return getProduct() + ": " + getPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public int compareTo(Searchable o) {
        return this.searchableName().compareTo(o.searchableName());
    }
}
