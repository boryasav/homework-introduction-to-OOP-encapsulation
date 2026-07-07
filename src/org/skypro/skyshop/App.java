package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        Product p1 = new Product("футболка базовая", 3190);
        Product p2 = new Product("шорты", 1990);
        Product p3 = new Product("майка", 2590);
        Product p4 = new Product("брюки", 2590);
        Product p5 = new Product("футболка oversize", 3490);

        ProductBasket basket1 = new ProductBasket();
        basket1.addProduct(p1);
        basket1.addProduct(p1);
        basket1.addProduct(p5);
        basket1.addProduct(p5);
        basket1.addProduct(p3);

        System.out.println("\n\"лишний\" addProduct (в basket1 нет свободного места):");
        basket1.addProduct(p4);

        ProductBasket basket2 = new ProductBasket();
        basket2.addProduct(p2);
        basket2.addProduct(p3);

        System.out.println("\nbasket 1:");
        basket1.printBasket();

        System.out.println("\nbasket 2:");
        basket2.printBasket();

        int totalPriceBasket1 = basket1.getTotalPrice();
        int totalPriceBasket2 = basket2.getTotalPrice();
        System.out.println("\ntotalPriceBasket1 = " + totalPriceBasket1 + "\ntotalPriceBasket2 = " + totalPriceBasket2);

        boolean productInBasket = basket1.containsProduct("майка");
        boolean productNotInBasket = basket2.containsProduct("футболка базовая");
        System.out.println("\nproductInBasket = " + productInBasket + "\nproductNotInBasket = " + productNotInBasket);

        basket1.clearBasket();
        System.out.println("\n* clear basket1 *");

        System.out.println("\nbasket 1:");
        basket1.printBasket();

        totalPriceBasket1 = basket1.getTotalPrice();
        System.out.println("\ntotalPriceBasket1 = " + totalPriceBasket1);

        System.out.println("\n" + basket1.containsProduct("футболка oversize"));
    }
}