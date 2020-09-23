package com.noth.repository;

import com.noth.model.BasketItem;
import com.noth.rule.Rule;
import com.noth.rule.PromotionsRule;
import com.noth.service.Basket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.noth.model.ProductCode.TravelCardHolder;


/**
 * LocalProductRepository provides an implementation of a Product Repo. In production these Products will be pulled from
 * another cached source (e.g. database). For this same exercise a local hardcode collection will be used.
 */
public class PromotionsRuleRepository implements RuleRepository {

    @Override
    public List<Rule> getPriceRules() {
        // In production this would call a store
        return List.of(getMatchTravelCardHolderRule());
    }

    @Override
    public List<Rule> getTotalRules() {
        // In production this would call a store
        return List.of(getTotalGreaterThan60Rule());
    }


    private Rule getTotalGreaterThan60Rule() {

        Predicate<Basket> matchTotalGreaterThan60Rule = basket -> (basket.getTotal().compareTo(BigDecimal.valueOf(60))) == 1;

        Function<Basket, BigDecimal> applyTotalGreaterThan60Rule = basket -> {
            final var discount = BigDecimal.valueOf(0.90);
            BigDecimal totalPostDiscount = basket.getTotal().multiply(discount);

            basket.setTotal(totalPostDiscount.setScale(2, RoundingMode.HALF_EVEN));

            return basket.getTotal();
        };

        return new PromotionsRule(matchTotalGreaterThan60Rule, applyTotalGreaterThan60Rule);
    }

    private Rule getMatchTravelCardHolderRule() {

        final var priceReduction = BigDecimal.valueOf(8.50);
        Predicate<Basket> matchTravelCardHolderRule = basket ->
                basket.getBasketItems().getOrDefault(TravelCardHolder.code, new BasketItem(null, 0)).getCount() > 1;

        Function<Basket, BigDecimal> applyTravelCardHoldersRule = basket -> {
            final BasketItem basketItem = basket.getBasketItems().get(TravelCardHolder.code);
            basketItem.getProduct().setPrice(priceReduction);

            return basketItem.calculateSubTotal();
        };

        return new PromotionsRule(matchTravelCardHolderRule, applyTravelCardHoldersRule);
    }

}
