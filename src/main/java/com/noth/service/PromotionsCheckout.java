package com.noth.service;


import com.noth.repository.ProductRepository;
import com.noth.rule.RuleExecutor;

import java.math.BigDecimal;


public class PromotionsCheckout implements Checkout {

    private Basket basket;

    private RuleExecutor executor;

    public PromotionsCheckout(RuleExecutor ruleExecutor, ProductRepository productRepository) {
        this.basket = new PromotionsCheckoutBasket(productRepository);
        this.executor = ruleExecutor;
    }

    public void scan(String productItemCode) {
        // add item to basket , key on code ; value Product and count
        // lookup item  from db
        basket.addProductItem(productItemCode);
    }

    // abstract method to apply pre and post rules
    // Total current determines run order
    public BigDecimal total() {
        // Rule executor handles rule ordering
        return executor.calculateTotal(basket);
    }

}

