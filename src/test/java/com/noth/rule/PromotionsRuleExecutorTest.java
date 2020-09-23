package com.noth.rule;

import com.noth.repository.ProductRepository;
import com.noth.repository.PromotionsProductRepository;
import com.noth.service.Basket;
import com.noth.service.PromotionsCheckoutBasket;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.noth.model.ProductCode.*;
import static org.junit.jupiter.api.Assertions.*;

class PromotionsRuleExecutorTest {

    private RuleExecutor ruleExecutor = new PromotionsRuleExecutor();
    private ProductRepository productRepository = new PromotionsProductRepository();

    @Test
    void getPriceRules() {
        assertEquals(1,ruleExecutor.getPriceRules().size());
    }

    @Test
    void getTotalRules() {
        assertEquals(1,ruleExecutor.getTotalRules().size());
    }

    @Test
    void calculateTotal_shouldApplyOver60Discount() {
        Basket basket  = new PromotionsCheckoutBasket(productRepository);
        basket.addProductItem(TravelCardHolder.code);
        basket.addProductItem(PersonalisedCufflinks.code);
        basket.addProductItem(KidsTShirt.code);

        var actual = ruleExecutor.calculateTotal(basket);
        var expected = BigDecimal.valueOf(66.78);

        assertEquals(expected,actual);

    }

    @Test
    void calculateTotal_shouldApplyTravelCardHolderDiscount() {
        Basket basket  = new PromotionsCheckoutBasket(productRepository);
        basket.addProductItem(TravelCardHolder.code);
        basket.addProductItem(PersonalisedCufflinks.code);
        basket.addProductItem(TravelCardHolder.code);
        basket.addProductItem(TravelCardHolder.code);


        var actual = ruleExecutor.calculateTotal(basket);
        var expected = BigDecimal.valueOf(63.45);

        assertEquals(expected,actual);

    }

    @Test
    void calculateTotal_whenNoRulesMatch_shouldApplyNoDiscount() {
        Basket basket  = new PromotionsCheckoutBasket(productRepository);
        basket.addProductItem(KidsTShirt.code);
        basket.addProductItem(KidsTShirt.code);
        basket.addProductItem(KidsTShirt.code);

        var actual = ruleExecutor.calculateTotal(basket);
        var expected = BigDecimal.valueOf(59.85);

        assertEquals(expected,actual);

    }
}