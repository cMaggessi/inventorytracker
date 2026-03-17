package com.invtracker.inventorytracker.model;

import java.util.List;

public class InventorySummary {

    List<InventoryItem> invetory;
    List<InventoryItem> lowStock;
    List<Anomaly> anomalies;


    public InventorySummary() {
    }

    public InventorySummary(List<InventoryItem> invetory, List<InventoryItem> lowStock, List<Anomaly> anomalies) {
        this.invetory = invetory;
        this.lowStock = lowStock;
        this.anomalies = anomalies;
    }

    public List<InventoryItem> getInvetory() {
        return invetory;
    }

    public void setInvetory(List<InventoryItem> invetory) {
        this.invetory = invetory;
    }

    public List<InventoryItem> getLowStock() {
        return lowStock;
    }

    public void setLowStock(List<InventoryItem> lowStock) {
        this.lowStock = lowStock;
    }

    public List<Anomaly> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<Anomaly> anomalies) {
        this.anomalies = anomalies;
    }
}
