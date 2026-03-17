package com.invtracker.inventorytracker.model;

public class InventoryItem {

    private String productId;
    private String productName;
    private int currentStock;


    public InventoryItem(String productId, String productName, int currentStock) {
        this.productId = productId;
        this.productName = productName;
        this.currentStock = currentStock;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

}