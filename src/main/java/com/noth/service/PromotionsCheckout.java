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
        basket.addProductItem(productItemCode);
    }

    public BigDecimal total() {
        // Rule executor handles rule ordering
        return executor.calculateTotal(basket);
    }

}

