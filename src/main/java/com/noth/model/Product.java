package com.noth.model;

import java.math.BigDecimal;

/**
 * Product models a Product in the Product Catalogue
 * Used by the BasketItem class
 */
public class Product {
    private String code;
    private String name;
    private BigDecimal price;

    public Product(String code, String name, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}


