package com.noth.repository;


import com.noth.model.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static com.noth.model.ProductCode.*;

/**
 * LocalProductRepository provides an implementation of a Product Repo. In production these Products will be pulled from
 * another cached source (e.g. database). For this same exercise a local hardcode collection will be used.
 */
public class PromotionsProductRepository implements ProductRepository {
    private Map<String, Product> productCatalogue = setupData();

    private Map<String, Product> setupData() {

        return Map.of(
                TravelCardHolder.code, new Product(TravelCardHolder.code, "Travel Card Holder", BigDecimal.valueOf(9.25)),
                PersonalisedCufflinks.code, new Product(PersonalisedCufflinks.code, "Personalised cufflinks", BigDecimal.valueOf(45.00)),
                KidsTShirt.code, new Product(KidsTShirt.code, "Kids T-shirt", BigDecimal.valueOf(19.95)));
    }

    @Override
    public Optional<Product> getProductItemByCode(String code) {
        return Optional.ofNullable(productCatalogue.get(code));
    }
}
