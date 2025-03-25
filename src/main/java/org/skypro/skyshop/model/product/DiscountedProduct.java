package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class DiscountedProduct extends Product implements Comparable<Searchable> {

    protected int basePrice;
    protected double discount;
    private final UUID id;

    public DiscountedProduct(UUID id, String productName, int basePrice, double discount) {
        super(id, productName);
        this.basePrice = basePrice;
        this.discount = discount;
        this.id = id;
        if (basePrice < 1) {
            throw new IllegalArgumentException("Указанная цена недействительна");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Указанная скидка неверна");
        }
    }

    @Override
    public int getPrice() {
        return (int) (basePrice - basePrice * discount/100);
    }

    @Override
    public String toString() {
        return getProduct() + ": " + getPrice() + " (" + discount + " %)";
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
