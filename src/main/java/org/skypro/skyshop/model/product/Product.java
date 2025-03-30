package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String productName;
    private final UUID id;

    public Product(UUID id, String productName) {
        this.productName = productName;
        this.id = id;
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Неверное имя продукта");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getProduct() {
        return productName;
    }

    @Override
    public String toString() {
        return getProduct() + ": " + getPrice();
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @JsonIgnore
    @Override
    public String searchTerm() {
        return getProduct();
    }

    @JsonIgnore
    @Override
    public String typeContent() {
        return "PRODUCT";
    }

    @Override
    public String searchableName() {
        return getProduct();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }
}
