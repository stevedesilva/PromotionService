package com.noth.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * UserBasket models an users basket containing all checked out items.
 */
public class UserBasket {

    private BigDecimal basketTotal = BigDecimal.ZERO;
    private Map<String, BasketItem> checkoutBasket = new HashMap<>();

    public BigDecimal getBasketTotal() {
        return basketTotal;
    }

    public void setBasketTotal(BigDecimal basketTotal) {
        this.basketTotal = basketTotal;
    }

    public Map<String, BasketItem> getCheckoutBasket() {
        return checkoutBasket;
    }

}


