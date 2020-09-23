package com.noth.rule;

import com.noth.service.Basket;

import java.math.BigDecimal;

/**
 * IRule will be used to model a rule. We can plug in other rules if necessary.
 *
 */
public interface Rule {
    /**
     * applyRule performs the action associated with the rule. Will only run if predicate is met.
     * @param basket
     * @return Result of rule application
     */
    BigDecimal applyRule(Basket basket);
}
