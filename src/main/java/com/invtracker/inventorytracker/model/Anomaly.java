package com.invtracker.inventorytracker.model;

public class Anomaly {
    private int row;
    private String error;


    public Anomaly() {
    }

    public Anomaly(int row, String error) {
        this.row = row;
        this.error = error;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
