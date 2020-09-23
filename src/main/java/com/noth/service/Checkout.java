package com.noth.service;

import java.math.BigDecimal;

/**
 * Models the checkout interface.
 * Note I have taken the liberty to use BigDecimal instead of double for precision reasons.
 */
public interface Checkout {
    /**
     * scan will look up item from the attached repository adding it to the users basket
     * @param productItemCode
     */
    void scan(String productItemCode);


    /**
     * Returning total applying relevant rules for items in the users basket.
     * Returns BigDecimal instead of double for precision reasons.
     * @return
     */
    BigDecimal total();
}
