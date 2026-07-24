package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {

    private final Map<String, List<Product>> basket;

    public ProductBasket() {
        basket = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product не может быть null");
        }

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

    public int getTotalPrice() {
        int sum = 0;
        for (Map.Entry<String, List<Product>> entry : basket.entrySet()) {
            List<Product> productsList = entry.getValue();
            for (Product product : productsList) {
                sum += product.getPrice();
            }
        }
        return sum;
    }

    public void printBasket() {
        System.out.println();

        if (basket.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        System.out.println(basket.values());

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + countSpecialProducts());
    }

    public int countSpecialProducts() {
        int counter = 0;
        for (Map.Entry<String, List<Product>> entry : basket.entrySet()) {
            List<Product> productList = entry.getValue();
            for (Product product : productList) {
                if (product.isSpecial())
                    counter++;
            }
        }
        return counter;
    }

    public boolean containsProduct(String productName) {
        return basket.containsKey(productName);
    }

    public void clearBasket() {
        basket.clear();
    }
}
