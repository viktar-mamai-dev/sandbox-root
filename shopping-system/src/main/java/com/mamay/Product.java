package com.mamay;

public class Product {
    private String productID;
    private String name;
    private String description;
    private double price;
    private ProductCategory category;
    private int availableItemCount;

    private Account seller;

    public int getAvailableCount() {
        return this.availableItemCount;
    }
    public boolean updatePrice(double newPrice) {
        // TODO
        return false;
    }
}
