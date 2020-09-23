package com.noth.model;

public enum ProductCode {
    TravelCardHolder("001"),
    PersonalisedCufflinks("002"),
    KidsTShirt("003");

    public final String code;

    ProductCode(String code) {
        this.code = code;
    }
}

