package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {
    public static void main(String[] args) {
        System.out.println("\nСОЗДАНИЕ НОВЫХ ПРОДУКТОВ С НЕПРАВИЛЬНЫМИ ПОЛЯМИ");
        try {
            new SimpleProduct("кепка", 0);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        try {
            new DiscountedProduct("    ", 500, 50);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
        try {
            new DiscountedProduct("очки", 3990, 101);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        Product tShirtBase = new FixPriceProduct("Футболка БАЗА");
        Product tShirtOversize = new FixPriceProduct("Футболка ОВЕРСАЙЗ");
        Product tShirtLinen = new FixPriceProduct("Футболка ЛЁН");
        Product shortsLinen = new SimpleProduct("Шорты ЛЁН", 1990);
        Product shortsSport = new SimpleProduct("Шорты СПОРТ", 2689);
        Product pantsLinen = new SimpleProduct("Штаны ЛЁН", 2590);
        Product tankTopBase = new SimpleProduct("Майка БАЗА", 2390);
        Product tankTopBaseDiscounted = new DiscountedProduct(tankTopBase.getName(), tankTopBase.getPrice(), 15);

        ProductBasket basket1 = new ProductBasket();
        basket1.addProduct(tShirtBase);
        basket1.addProduct(tShirtBase);
        basket1.addProduct(tShirtOversize);
        basket1.addProduct(tShirtOversize);
        basket1.addProduct(shortsSport);
        basket1.addProduct(shortsLinen);
        basket1.addProduct(tankTopBaseDiscounted);

        ProductBasket basket2 = new ProductBasket();
        basket2.addProduct(tShirtLinen);
        basket2.addProduct(tShirtBase);
        basket2.addProduct(tShirtBase);
        basket2.addProduct(shortsLinen);
        basket2.addProduct(tankTopBaseDiscounted);
        basket2.addProduct(tankTopBase);

        basket1.printBasket("корзина 1");
        basket2.printBasket("корзина 2");

        String nameProduct = tShirtOversize.getName();
        boolean basket1ContainsProduct = basket1.containsProduct(nameProduct);
        boolean basket2ContainsProduct = basket2.containsProduct(nameProduct);
        System.out.println("\nbasket1ContainsProduct " + nameProduct + " = " + basket1ContainsProduct +
                "\nbasket2ContainsProduct " + nameProduct + " = " + basket2ContainsProduct);

        basket1.clearBasket();
        basket1.printBasket("очищенная корзина 1");

        basket1ContainsProduct = basket1.containsProduct(nameProduct);
        System.out.println("\nПосле чистки Корзины 1 basket1ContainsProduct " + nameProduct + " = " + basket1ContainsProduct);


        Article article1 = new Article("трикотаж футболок",
                "Футер 3-х нитка — это тёплый, плотный и устойчивый к деформации трикотаж.\n" +
                "Состав: 80% хлопка и 20% лайкры. Плотность 350 г/м2.");

        Article article2 = new Article("распродажа в следующую среду",
                "Только один день скидка 20% на весь низ из нашего каталога:\n" +
                        new DiscountedProduct(shortsLinen.getName(), shortsLinen.getPrice(), 20) + "\n" +
                        new DiscountedProduct(shortsSport.getName(), shortsSport.getPrice(), 20) + "\n" +
                        new DiscountedProduct(pantsLinen.getName(), pantsLinen.getPrice(), 20) + "\n" +
                        "Не упускайте выгоду!");


        SearchEngine searchEngine = new SearchEngine();
        searchEngine.add(tShirtBase);
        searchEngine.add(tShirtLinen);
        searchEngine.add(tShirtOversize);
        searchEngine.add(tankTopBase);
        searchEngine.add(tankTopBaseDiscounted);
        searchEngine.add(shortsLinen);
        searchEngine.add(shortsSport);
        searchEngine.add(pantsLinen);
        searchEngine.add(article1);
        searchEngine.add(article2);


        System.out.println("\n\nМЕТОД УДАЛЕНИЯ ПРОДУКТОВ ИЗ КОРЗИНЫ removedProduct()");
        String productName;
        basket2.printBasket("корзина 2 до удаления");

        productName = tankTopBase.getName();
        System.out.println("\nУдалённые товары по названию \"" + productName + "\": " + basket2.removeProduct(productName));

        productName = shortsSport.getName();
        if (basket2.removeProduct(productName).isEmpty())
            System.out.println("\nСписок удалённых товаров по названию \"" + productName + "\" пуст");

        basket2.printBasket("корзина 2 после удаления");


        System.out.println("\n\nМЕТОД ПОИСКА САМОГО ПОДХОДЯЩЕГО ЭЛЕМЕНТА searchBestMatch()");
        Searchable resultSearch;
        String searchTerm;
        try {
            searchTerm = "футболк";
            resultSearch = searchEngine.searchBestMatch(searchTerm);
            System.out.println("\nРезультат поиска самого подходящего элемента по запросу \"" + searchTerm + "\":\n" + resultSearch);
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
        try {
            searchTerm = "бри";
            resultSearch = searchEngine.searchBestMatch(searchTerm);
            System.out.println("\nРезультат поиска самого подходящего элемента по запросу \"" + searchTerm + "\":\n" + resultSearch);
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
        try {
            searchTerm = "ш";
            resultSearch = searchEngine.searchBestMatch(searchTerm);
            System.out.println("\nРезультат поиска самого подходящего элемента по запросу \"" + searchTerm + "\":\n" + resultSearch);
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\n\nМЕТОД ПОИСКА search()");
        int num;

        searchTerm = "ФУтб";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":");
        num = 1;
        for (Searchable searchable : searchEngine.search(searchTerm)) {
            System.out.println((num++) + ") " + searchable);
        }

        searchTerm = "шорты";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":");
        num = 1;
        for (Searchable searchable : searchEngine.search(searchTerm)) {
            System.out.println((num++) + ") " + searchable);
        }

        searchTerm = "майка";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":");
        num = 1;
        for (Searchable searchable : searchEngine.search(searchTerm)) {
            System.out.println((num++) + ") " + searchable);
        }

        searchTerm = "джинс";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":");
        num = 1;
        for (Searchable searchable : searchEngine.search(searchTerm)) {
            System.out.println((num++) + ") " + searchable);
        }

    }
}