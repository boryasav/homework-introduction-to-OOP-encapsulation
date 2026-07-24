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

import java.util.List;

public class App {
    public static void main(String[] args) {
        FixPriceProduct tShirtBase = new FixPriceProduct("футболка базовая");
        FixPriceProduct tShirtOversize = new FixPriceProduct("футболка оверсайз");
        SimpleProduct shorts = new SimpleProduct("шорты", 1990);
        SimpleProduct pants = new SimpleProduct("брюки", 2590);
        DiscountedProduct tankTopDiscounted = new DiscountedProduct("майка", 2590, 10);

        ProductBasket basket1 = new ProductBasket();
        basket1.addProduct(tShirtBase);
        basket1.addProduct(tShirtBase);
        basket1.addProduct(tShirtOversize);
        basket1.addProduct(tShirtOversize);
        basket1.addProduct(tankTopDiscounted);
        basket1.addProduct(pants);

        ProductBasket basket2 = new ProductBasket();
        basket2.addProduct(shorts);
        basket2.addProduct(tankTopDiscounted);

        System.out.print("\nbasket 1:");
        basket1.printBasket();

        System.out.print("\nbasket 2:");
        basket2.printBasket();

        int totalPriceBasket1 = basket1.getTotalPrice();
        int totalPriceBasket2 = basket2.getTotalPrice();
        System.out.println("\ntotalPriceBasket1 = " + totalPriceBasket1 + "\ntotalPriceBasket2 = " + totalPriceBasket2);

        boolean SimpleProductInBasket = basket1.containsProduct("майка");
        boolean SimpleProductNotInBasket = basket2.containsProduct("футболка базовая");
        System.out.println("\nproductInBasket = " + SimpleProductInBasket + "\nproductNotInBasket = " + SimpleProductNotInBasket);

        System.out.print("\nОчищеная корзина basket 1:");
        basket1.clearBasket();
        basket1.printBasket();

        totalPriceBasket1 = basket1.getTotalPrice();
        System.out.println("\ntotalPriceBasket1 = " + totalPriceBasket1);

        System.out.println("\ncontainsProduct = " + basket1.containsProduct("футболка oversize"));


        SearchEngine searchEngine = new SearchEngine();
        searchEngine.add(tShirtBase);
        searchEngine.add(shorts);
        searchEngine.add(tankTopDiscounted);
        searchEngine.add(pants);
        searchEngine.add(tShirtOversize);

        Article a1 = new Article("трикотаж футболок",
                "Футер 3-х нитка — это тёплый, плотный и устойчивый к деформации и пилингу трикотаж. " +
                "Состав: 80% хлопка и 20% лайкры. Плотность 350 г/м2.");

        Article a2 = new Article("распродажа в следующую среду",
                "Только один день скидка 20% на весь низ из нашего каталога — " +
                        new DiscountedProduct(shorts.getName(), shorts.getPrice(), 20) + " и " +
                        new DiscountedProduct(pants.getName(), pants.getPrice(), 20) + ". " +
                        "Не упускайте выгоду!");

        searchEngine.add(a1);
        searchEngine.add(a2);


        System.out.println("\nМЕТОД ПОИСКА search()");
        String searchTerm;

        searchTerm = "ФУтб";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":\n" + (searchEngine.search(searchTerm)).values());

        searchTerm = "шорты";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":\n" + (searchEngine.search(searchTerm)).values());

        searchTerm = "майка";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":\n" + (searchEngine.search(searchTerm)).values());

        searchTerm = "джинс";
        System.out.println("\nРезультаты поиска по запросу " + searchTerm + ":\n" + (searchEngine.search(searchTerm)).values());


        System.out.println("\n\nСОЗДАНИЕ НОВЫХ ПРОДУКТОВ С НЕПРАВИЛЬНЫМИ ПОЛЯМИ");
        try {
            new SimpleProduct("кепка", 0);
        } catch (IllegalArgumentException exception) {
            System.out.println("\n" + exception.getMessage());
        }

        try {
            new DiscountedProduct("    ", 500, 50);
        } catch (IllegalArgumentException exception) {
            System.out.println("\n" + exception.getMessage());
        }

        try {
            new DiscountedProduct("очки", 3990, 101);
        } catch (IllegalArgumentException exception) {
            System.out.println("\n" + exception.getMessage());
        }


        System.out.println("\n\nМЕТОД ПОИСКА САМОГО ПОДХОДЯЩЕГО ЭЛЕМЕНТА searchBestMatch()");
        Searchable resultSearch;

        try {
            searchTerm = "скидки";
            resultSearch = searchEngine.searchBestMatch(searchTerm);
            System.out.println("\nРезультат поиска самого подходящего элемента по запросу \"" + searchTerm + "\":\n" + resultSearch);
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            searchTerm = "скидк";
            resultSearch = searchEngine.searchBestMatch(searchTerm);
            System.out.println("\nРезультат поиска самого подходящего элемента по запросу \"" + searchTerm + "\":\n" + resultSearch);
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

        try {
            searchTerm = "ФУТБОЛ";
            resultSearch = searchEngine.searchBestMatch(searchTerm);
            System.out.println("\nРезультат поиска самого подходящего элемента по запросу \"" + searchTerm + "\":\n" + resultSearch);
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\n\nМЕТОД УДАЛЕНИЯ ПРОДУКТОВ ИЗ КОРЗИНЫ removedProduct()");

        basket2.addProduct(new SimpleProduct(tankTopDiscounted.getName(), tankTopDiscounted.getBasePrice()));
        System.out.print("\nbasket 2:");
        basket2.printBasket();

        List<Product> removedProduct;

        removedProduct = basket2.removeProduct("майка");
        System.out.println("\nУдалённые продукты: " + removedProduct);

        System.out.print("\nbasket 2:");
        basket2.printBasket();

        removedProduct = basket2.removeProduct("джинсы");
        if (removedProduct.isEmpty()) System.out.println("\nСписок удалённых товаров пуст");

        System.out.print("\nbasket 2:");
        basket2.printBasket();

    }
}