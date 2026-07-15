package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {

    private final LinkedList<Product> basket;

    public ProductBasket() {
        basket = new LinkedList<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product не может быть null");
        }
        basket.add(product);
    }

    public List<Product> removeProduct(String name) {
        List<Product> removedProducts = new ArrayList<>();

        Iterator<Product> iterator = basket.iterator();

        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (Objects.equals(product.getName(), name)) {
                removedProducts.add(product);
                iterator.remove();
            }
        }
        return removedProducts;
    }

    public int getTotalPrice() {
        int sum = 0;
        for (Product product : basket) {
            sum += product.getPrice();
        }
        return sum;
    }

    public void printBasket() {
        System.out.println();

        if (basket.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        for (Product product : basket) {
            System.out.println(product);
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + counterSpecialProducts());
    }

    public int counterSpecialProducts() {
        int counter = 0;
        for (Product product : basket) {
            if (product.isSpecial())
                counter++;
        }
        return counter;
    }

    public boolean containsProduct(String productName) {
        for (Product product : basket) {
            if (Objects.equals(product.getName(), productName))
                return true;
        }
        return false;
    }

    public void clearBasket() {
        basket.clear();
    }
}
