package com.example.pivotpoints.model;

public class PivotResponse {

    StockPrice stockPrice;
    boolean isPivotHigh;
    boolean isPivotLow;
    String errorMessage;

    public PivotResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public StockPrice getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(StockPrice stockPrice) {
        this.stockPrice = stockPrice;
    }

    public boolean isPivotHigh() {
        return isPivotHigh;
    }

    public void setPivotHigh(boolean pivotHigh) {
        isPivotHigh = pivotHigh;
    }

    public boolean isPivotLow() {
        return isPivotLow;
    }

    public void setPivotLow(boolean pivotLow) {
        isPivotLow = pivotLow;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
