package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {

    private final Map<String, List<Product>> basket;

    public ProductBasket() {
        basket = new HashMap<>();
    }

    public void add(Product product) {
        Objects.requireNonNull(product, "Передаваемый Product не может быть null");

        List<Product> productsList = basket.computeIfAbsent(product.getName(), k -> new ArrayList<>());
        productsList.add(product);
    }

    public List<Product> remove(String name) {
        List<Product> removedProducts = basket.remove(name);
        if (removedProducts == null) {
            removedProducts = Collections.emptyList();
        }
        return removedProducts;
    }

    public boolean containsProduct(String name) {
        return basket.containsKey(name);
    }

    public void clearBasket() {
        basket.clear();
    }

    public int getTotalPrice() {
        return basket.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public void printBasket() {
        printBasket("корзина");
    }

    public void printBasket(String basketName) {
        Objects.requireNonNull(basketName, "Имя корзины не может быть null");
        System.out.println("\n" + basketName.toUpperCase());

        if (basket.isEmpty()) {
            System.out.println("В корзине пусто");
        } else {
            basket.values().stream()
                    .flatMap(Collection::stream)
                    .forEach(System.out::println);
            System.out.println("Итого: " + getTotalPrice());
            System.out.println("Специальных товаров: " + getSpecialCount());
        }
    }

    private long getSpecialCount() {
        return basket.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }
}
