package com.noth.rule;


import com.noth.service.Basket;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Rule model has logic which check the predicate and then apply an action if matched
 */
public class PromotionsRule implements Rule {
    private Predicate<Basket> matched;

    private Function<Basket,BigDecimal> action;

    public PromotionsRule(Predicate<Basket> matched, Function<Basket, BigDecimal> action) {
        this.matched = matched;
        this.action = action;
    }

    public BigDecimal applyRule(Basket basket) {
        BigDecimal result = BigDecimal.ZERO;
        if (matched.test(basket)) {
            result = action.apply(basket);
        }
        return result;
    }

}