package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

import java.util.Objects;

public abstract class Product implements Searchable {

    private final String name;

    public Product(String name) {
        Objects.requireNonNull(name, "Название продукта не может быть null");
        if (name.isBlank()) {throw new IllegalArgumentException("Название продукта не может быть пустой строкой");}
        this.name = name.trim();
    }

    @Override
    public String getSearchTerm() {
        return name.toLowerCase();
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Objects.equals(this.name, product.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return getName() + "\t\t" + getPrice();
    }
}
