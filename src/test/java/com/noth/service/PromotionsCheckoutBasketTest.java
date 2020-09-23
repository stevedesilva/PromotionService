package com.noth.service;

import com.noth.repository.ProductRepository;
import com.noth.repository.PromotionsProductRepository;
import com.noth.rule.PromotionsRuleExecutor;
import com.noth.rule.RuleExecutor;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static com.noth.model.ProductCode.*;


class PromotionsCheckoutBasketTest {
    private RuleExecutor ruleExecutor = new PromotionsRuleExecutor();
    private ProductRepository productRepository = new PromotionsProductRepository();

    @Test
    public void total_whenBasketIsEmptyAndTotalCalled_shouldReturnZero() {
        Checkout checkout = new PromotionsCheckout(ruleExecutor, productRepository);
        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = checkout.total();
        assertEquals(expected, actual);
    }

    @Test
    void addProductItem_shouldAddItemToBasket() {
        Basket basket = new PromotionsCheckoutBasket(productRepository);
        basket.addProductItem(TravelCardHolder.code);
        basket.addProductItem(PersonalisedCufflinks.code);
        basket.addProductItem(KidsTShirt.code);

        assertEquals(3, basket.getBasketItems().size());
    }

    @Test
    void calculateTotal_shouldCalculateTotalWithoutApplyPromoRuleDiscounts() {
        Basket basket = new PromotionsCheckoutBasket(productRepository);
        basket.addProductItem(TravelCardHolder.code);
        basket.addProductItem(PersonalisedCufflinks.code);
        basket.addProductItem(KidsTShirt.code);
        basket.addProductItem(KidsTShirt.code);
        BigDecimal actual = basket.calculateTotal();

        BigDecimal expected = BigDecimal.valueOf(94.15);
        assertEquals(expected, actual);
    }


}