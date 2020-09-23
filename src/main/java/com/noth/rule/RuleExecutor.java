package com.noth.rule;

import com.noth.service.Basket;

import java.math.BigDecimal;
import java.util.List;

/**
 * RuleExecutor is responsible for running the Rules. Rules can either operate on the price (Pre total rules) or on the grand total(Post total rules).
 * There may be other variants in the future. For now this partition meets the use case, but can be extending in the future.
 */
public interface RuleExecutor {

    List<Rule> getPriceRules();

    List<Rule> getTotalRules();

    BigDecimal calculateTotal(Basket basket);

}
