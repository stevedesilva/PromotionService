package com.noth.rule;

import com.noth.repository.PromotionsRuleRepository;
import com.noth.repository.RuleRepository;
import com.noth.service.Basket;

import java.math.BigDecimal;
import java.util.List;

/**
 * RuleExecutor Implementation
 *  responsible for running the Rules.
 *  Rules can either operate on the price (Pre total rules) or on the grand total(Post total rules).
 * There may be other variants in the future. For now this partition meets the use case, but can be extending in the future.
 *
 */
public class PromotionsRuleExecutor implements RuleExecutor {
    private List<Rule> priceRules;
    private List<Rule> totalRules;

    private RuleRepository ruleRepository = new PromotionsRuleRepository();

    public PromotionsRuleExecutor() {
        this.priceRules = ruleRepository.getPriceRules();
        this.totalRules = ruleRepository.getTotalRules();
    }

    @Override
    public List<Rule> getPriceRules() {
        return priceRules;
    }


    @Override
    public List<Rule> getTotalRules() {
        return totalRules;
    }

    @Override
    public BigDecimal calculateTotal(Basket basket) {

        applyProductPriceRules(basket);

        // We calculate the total here since post rules could be empty
        basket.setTotal(basket.calculateTotal());

        applyPostTotalRules(basket);

        return basket.getTotal();
    }

    private void applyPostTotalRules(Basket basket) {
        for (Rule rule: getTotalRules()) {
            rule.applyRule(basket); //TODO change
        }
    }

    private void applyProductPriceRules(Basket basket) {
        for (Rule rule: getPriceRules()) {
            rule.applyRule(basket);
        }
    }

}
