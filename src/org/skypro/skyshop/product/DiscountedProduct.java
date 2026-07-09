package org.skypro.skyshop.product;

public class DiscountedProduct extends Product{

    private final int basePrice;
    private final int discountPercentage;

    public DiscountedProduct(String name, int basePrice, int discountPercentage) {
        super(name);
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public int getPrice() {
        double result = basePrice * ( 1 - ( (double) discountPercentage / 100));
        return (int) result;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " ("+discountPercentage+"%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
