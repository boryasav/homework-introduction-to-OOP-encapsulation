package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;

import java.util.Arrays;

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

        SearchEngine searchEngine = new SearchEngine(100);
        searchEngine.add(p1);
        searchEngine.add(p2);
        searchEngine.add(p3);
        searchEngine.add(p4);
        searchEngine.add(p5);

        Article a1 = new Article("\nКакой трикотаж мы используем?".toUpperCase(),
                "Футер 3-х нитка — это плотный, теплый и формоустойчивый трикотаж.\n" +
                "Лицевая сторона гладкая и ровная, а изнаночная — объемная, с крупными вязаными петлями.\n" +
                p5 + "\n" +
                "состав: 80% хлопка и 20% лайкры.\n" +
                "Плотность 350 г/м2 — отлично сохраняет тепло, пропускает воздух, устойчив к деформации и пилингу (образовыванию катышков)\n");

        Article a2 = new Article("\nраспродажа в следующую среду".toUpperCase(),
                "Только один день, только одна среда\n" +
                        "Скидка 20% на весь низ из нашего каталога:\n" +
                        new DiscountedProduct(p2.getName(), p2.getPrice(), 20) + "\n" +
                        new DiscountedProduct(p4.getName(), p4.getPrice(), 20) + "\n" +
                        "Успейте сделать правильный выбор!\n");

        searchEngine.add(a1);
        searchEngine.add(a2);

        String searchTerm;

        searchTerm = "футболка";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":\n" + Arrays.toString(searchEngine.search(searchTerm)));

        searchTerm = "ФУ";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":\n" + Arrays.toString(searchEngine.search(searchTerm)));

        searchTerm = "ниЗ";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":\n" + Arrays.toString(searchEngine.search(searchTerm)));

        searchTerm = "шорты";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":\n" + Arrays.toString(searchEngine.search(searchTerm)));

        searchTerm = "майка";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":\n" + Arrays.toString(searchEngine.search(searchTerm)));

        searchTerm = "джинс";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":\n" + Arrays.toString(searchEngine.search(searchTerm)) + "\n");

        // создание новых продуктов с неправильными полями
        try {
            SimpleProduct p6 = new SimpleProduct("кепка", 0);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            DiscountedProduct p7 = new DiscountedProduct("    ", 500, 50);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            DiscountedProduct p8 = new DiscountedProduct("очки", 3990, 101);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        // метод поиска самого подходящего элемента
        try {
            searchTerm = "скидки";
            searchEngine.searchBestMatch(searchTerm);
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            searchTerm = "скидк";
            searchEngine.searchBestMatch(searchTerm);
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            searchTerm = "ФУТБОЛ";
            searchEngine.searchBestMatch(searchTerm);
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

    }
}