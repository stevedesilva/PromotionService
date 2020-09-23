package com.noth.repository;

import com.noth.rule.Rule;

import java.util.List;

/**
 * RulesRepository represent a rules store which can be pulled from various implementations (e.g. cache, database, config, etc...)
 */
public interface RuleRepository {
    /**
     * getPriceRules returns rules which operate on the Product Items themselves, manipulating the price.
     * These rules should execute before the Total Rules and will be run in the order in which they are added.
     * @return List of Price rules
     */
    List<Rule>  getPriceRules();

    /**
     * getTotalRules returns rules which operate on the total calculation of all items in the basket. They should execute after Price rules, and will be run
     * in the order in which they are added.
     * @return List of Total Rules
     */
    List<Rule>  getTotalRules();
}
