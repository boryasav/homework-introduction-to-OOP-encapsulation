package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {

    private final Map<String, List<Product>> basket;

    public ProductBasket() {
        basket = new HashMap<>();
    }

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "Передаваемый Product не может быть null");

        String productName = product.getName();
        List<Product> productsList = basket.computeIfAbsent(productName, k -> new ArrayList<>());
        productsList.add(product);
    }

    public List<Product> removeProduct(String name) {
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
        int total = 0;
        for (List<Product> productList : basket.values()) {
            for (Product product : productList) {
                total += product.getPrice();
            }
        }
        return total;
    }

    private int countSpecialProducts() {
        int counter = 0;
        for (List<Product> productList : basket.values()) {
            for (Product product : productList) {
                if (product.isSpecial())
                    counter++;
            }
        }
        return counter;
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
            for (List<Product> productList : basket.values()) {
                for (Product product : productList) {
                    System.out.println(product);
                }
            }
            System.out.println("Итого: " + getTotalPrice());
            System.out.println("Специальных товаров: " + countSpecialProducts());
        }
    }
}
