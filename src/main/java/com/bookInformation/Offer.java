package com.bookInformation;

public class Offer {
    private int finskyOfferType;
    private Price listPrice;
    private Price retailPrice;

    // Getters and Setters
    public int getFinskyOfferType() {
        return finskyOfferType;
    }

    public void setFinskyOfferType(int finskyOfferType) {
        this.finskyOfferType = finskyOfferType;
    }

    public Price getListPrice() {
        return listPrice;
    }

    public void setListPrice(Price listPrice) {
        this.listPrice = listPrice;
    }

    public Price getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Price retailPrice) {
        this.retailPrice = retailPrice;
    }
}
