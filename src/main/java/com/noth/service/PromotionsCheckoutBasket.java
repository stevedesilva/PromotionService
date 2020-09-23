package com.noth.service;

import com.noth.model.BasketItem;
import com.noth.model.Product;
import com.noth.model.UserBasket;
import com.noth.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class PromotionsCheckoutBasket implements Basket {

    public static final int ONE_ITEM = 1;
    private ProductRepository productRepository;

    private UserBasket checkoutBasket = new UserBasket();

    public PromotionsCheckoutBasket(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProductItem(String code) {

        if (checkoutBasket.getCheckoutBasket().containsKey(code)) {
            BasketItem basketItem = checkoutBasket.getCheckoutBasket().get(code);
            basketItem.setCount(basketItem.getCount() + ONE_ITEM);
        }
        else {
            // Add to basket
            final Optional<Product> productItem = productRepository.getProductItemByCode(code);
            productItem.ifPresent(item -> checkoutBasket.getCheckoutBasket().put(code,new BasketItem(item, ONE_ITEM))); // magic number
            // else exception?
        }
    }

    @Override
    public BigDecimal calculateTotal() {

        BigDecimal total = BigDecimal.ZERO;
        for (String code: checkoutBasket.getCheckoutBasket().keySet()) {

            BasketItem basketItem = checkoutBasket.getCheckoutBasket().get(code);
            BigDecimal subtotal = basketItem.calculateSubTotal();
            total = total.add(subtotal);
        }
        return total;
    }

    @Override
    public Map<String, BasketItem> getBasketItems() {
        return this.checkoutBasket.getCheckoutBasket();
    }

    @Override
    public BigDecimal getTotal() {
        return this.checkoutBasket.getBasketTotal();
    }

    @Override
    public void setTotal(BigDecimal value) {
         this.checkoutBasket.setBasketTotal(value);
    }


}
