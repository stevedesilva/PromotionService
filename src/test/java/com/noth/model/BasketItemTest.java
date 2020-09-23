package com.noth.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BasketItemTest {

    @Test
    void calculateSubTotal_whenEmptyBasketItem_shouldZeroTotal() {

        Product p1 = new Product("100", "Product 1", BigDecimal.valueOf(10.99));
        BasketItem basketItem = new BasketItem(p1,0);
        var actual = basketItem.calculateSubTotal();
        var expected = BigDecimal.ZERO;
        assertEquals(expected,actual);
    }

    @Test
    void calculateSubTotal_whenMoreOneBasketItem_shouldReturnSingleItemTotal() {

        Product p1 = new Product("100", "Product 1", BigDecimal.valueOf(10.99));
        BasketItem basketItem = new BasketItem(p1,1);
        var actual = basketItem.calculateSubTotal();
        var expected = BigDecimal.valueOf(10.99);
        assertEquals(expected,actual);
    }

    @Test
    void calculateSubTotal_whenMoreThanOneBasketItem_shouldReturnSumOfAllItems() {

        Product p1 = new Product("100", "Product 1", BigDecimal.valueOf(10.99));
        BasketItem basketItem = new BasketItem(p1,5);
        var actual = basketItem.calculateSubTotal();
        var expected = BigDecimal.valueOf(54.95);
        assertEquals(expected,actual);
    }
}