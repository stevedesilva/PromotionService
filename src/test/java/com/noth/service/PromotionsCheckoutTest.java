package com.noth.service;


import com.noth.repository.PromotionsProductRepository;
import com.noth.repository.ProductRepository;
import com.noth.rule.RuleExecutor;
import com.noth.rule.PromotionsRuleExecutor;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

import static com.noth.model.ProductCode.*;


class PromotionsCheckoutTest {

    private RuleExecutor ruleExecutor = new PromotionsRuleExecutor();
    private ProductRepository  productRepository = new PromotionsProductRepository();

    @Test
    public void total_whenBasketIsEmptyAndTotalCalled_shouldReturnZero() {
        Checkout checkout = new PromotionsCheckout(ruleExecutor,productRepository);
        BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual = checkout.total();
        assertEquals(expected, actual);
    }

    @Test
    public void total_whenBasketContainsNoPromotedProducts_shouldCalculateTotalWithoutApplyingDiscounts() {
        Checkout checkout = new PromotionsCheckout(ruleExecutor,productRepository);
        // product
        checkout.scan(TravelCardHolder.code);
        checkout.scan(KidsTShirt.code);
        checkout.scan(KidsTShirt.code);

        BigDecimal actual = checkout.total();

        BigDecimal expected = BigDecimal.valueOf(49.15);
        // 001 003 003
        assertEquals(expected, actual);
    }

    @Test
    public void total_whenBasketContainsTwoProductsWithCode001_shouldDiscountProduct() {
       // Basket: 001,003,001
        //Total price expected: £36.95
        Checkout checkout = new PromotionsCheckout(ruleExecutor,productRepository);
        // product
        checkout.scan(TravelCardHolder.code);
        checkout.scan(KidsTShirt.code);
        checkout.scan(TravelCardHolder.code);

        BigDecimal actual = checkout.total();

        BigDecimal expected = BigDecimal.valueOf(36.95);
        assertEquals(expected, actual);
    }

    @Test
    public void total_whenBasketTotalOver60_shouldApply10PercentDiscount() {
        // Basket: 001,002,003
        Checkout checkout = new PromotionsCheckout(ruleExecutor,productRepository);

        // product
        checkout.scan(TravelCardHolder.code);
        checkout.scan(PersonalisedCufflinks.code);
        checkout.scan(KidsTShirt.code);


        BigDecimal actual = checkout.total();

        BigDecimal expected = BigDecimal.valueOf(66.78);
        assertEquals(expected, actual);
    }

    @Test
    public void total_whenBasketTotalOver60_AndMultipleProductsWithCode001_shouldApplyDiscounts() {
//        Basket: 001,002,001,003
//        Total price expected: £73.76
        Checkout checkout = new PromotionsCheckout(ruleExecutor,productRepository);

        // product
        checkout.scan(TravelCardHolder.code);
        checkout.scan(PersonalisedCufflinks.code);
        checkout.scan(TravelCardHolder.code);
        checkout.scan(KidsTShirt.code);


        BigDecimal actual = checkout.total();

        BigDecimal expected = BigDecimal.valueOf(73.76);
        assertEquals(expected, actual);
    }
}