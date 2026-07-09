package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Arrays;
import java.util.Objects;

public class ProductBasket {

    private static final int BASKET_SIZE = 5;

    private final Product[] basket;

    // одновременно и индекс ближайшей свободной ячейки массива (куда будет "помещаться" новый продукт в корзине)
    // и фактическое кол-во продуктов в корзине (без учёта null)
    private int size;

    public ProductBasket() {
        basket = new Product[BASKET_SIZE];
    }

    public void addProduct(Product product) {
        if (size >= BASKET_SIZE)
            System.out.println("Невозможно добавить продукт");
        else {
            basket[size] = product;
            size++;
        }
    }

    public int getTotalPrice() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += basket[i].getPrice();
        }
        return sum;
    }

    public void printBasket() {
        if (size == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println(basket[i]);
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + counterSpecialProducts());
    }

    public int counterSpecialProducts() {
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (basket[i].isSpecial())
                counter++;
        }
        return counter;
    }

    public boolean containsProduct(String productName) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(basket[i].getName(), productName))
                return true;
        }
        return false;
    }

    public void clearBasket() {
        Arrays.fill(basket, null);  // Intellij IDEA сама мне предложила использовать метод класса Arrays
        size = 0;
    }
}
