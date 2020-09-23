package com.noth.service;

import com.noth.model.BasketItem;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Basket describes all operation that be performed on it.
 * It also calculates it total based on items in the basket
 */
public interface Basket {
    void addProductItem(String code);

    BigDecimal calculateTotal();

    Map<String, BasketItem> getBasketItems();

    BigDecimal getTotal();

    void setTotal(BigDecimal value);

}
