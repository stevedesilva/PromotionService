package com.noth.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BasketItem models a unique product item in the basket.
 * It holds a record of the number of items and can calculate its total.
 */
public class BasketItem {
    private Product product;
    private Integer count;
    private BigDecimal subTotal;

    public BasketItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal calculateSubTotal() {
        if (count <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(count));
        subTotal = itemTotal.setScale(2, RoundingMode.HALF_EVEN);
        return subTotal;
    }
}


