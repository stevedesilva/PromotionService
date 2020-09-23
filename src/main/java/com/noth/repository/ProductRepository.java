package com.noth.repository;

import com.noth.model.Product;

import java.util.Optional;

/**
 * ProductRepository represent a product catalogue which can be pulled from various implementations (e.g. cache, database, config, etc...)
 *
 */
public interface ProductRepository {
    /**
     * getProductItemByCode returns productItem object from underlying repository
     * Returns ProductItem
     * @param code
     * @return
     */
    Optional<Product> getProductItemByCode(String code);
}
