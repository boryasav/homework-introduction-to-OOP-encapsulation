package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {

    private final int basePrice;
    private final int discountPercentage;

    public DiscountedProduct(String name, int basePrice, int discountPercentage) {
        super(name);

        if (basePrice < 1) {
            throw new IllegalArgumentException("Цена должна быть строго больше 0");
        }
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть целым числом в диапазоне от 0 до 100 включительно");
        }
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public int getPrice() {
        double result = basePrice * (1 - ((double) discountPercentage / 100));
        return (int) result;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + discountPercentage + "%)";
    }
}
