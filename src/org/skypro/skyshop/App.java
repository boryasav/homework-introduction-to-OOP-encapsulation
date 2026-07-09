package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
//import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        FixPriceProduct p1 = new FixPriceProduct("футболка базовая");
        SimpleProduct p2 = new SimpleProduct("шорты", 1990);
        DiscountedProduct p3 = new DiscountedProduct("майка", 2590, 10);
        SimpleProduct p4 = new SimpleProduct("брюки", 2590);
        FixPriceProduct p5 = new FixPriceProduct("футболка oversize");

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

        boolean SimpleProductInBasket = basket1.containsProduct("майка");
        boolean SimpleProductNotInBasket = basket2.containsProduct("футболка базовая");
        System.out.println("\nproductInBasket = " + SimpleProductInBasket + "\nproductNotInBasket = " + SimpleProductNotInBasket);

        basket1.clearBasket();
        System.out.println("\n* clear basket1 *");

        System.out.println("\nbasket 1:");
        basket1.printBasket();

        totalPriceBasket1 = basket1.getTotalPrice();
        System.out.println("\ntotalPriceBasket1 = " + totalPriceBasket1);

        System.out.println("\n" + basket1.containsProduct("футболка oversize"));
    }
}