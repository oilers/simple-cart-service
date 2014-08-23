package org.oilers.cart.service;

import org.junit.Before;
import org.junit.Test;
import org.oilers.cart.model.Cart;
import org.oilers.cart.model.Product;
import org.oilers.cart.model.ProductType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;


public class CartServiceTest {

    private CartService cartService;

    @Before
    public void setUp(){
        cartService = new CartService();
    }

    @Test
    public void testSimpleCart() {
        Cart cart = cartService.getCart(getSimpleProductList(ProductType.BOOK, 12.49, false));
        assertEquals(0., cart.getTotalSalesTax().doubleValue());
        assertEquals(12.49, cart.getTotal().doubleValue());
        assertEquals(1, cart.getCartItems().size());

        cart = cartService.getCart(getSimpleProductList(ProductType.OTHER, 14.99, false));
        assertEquals(1.5, cart.getTotalSalesTax().doubleValue());
        assertEquals(16.49, cart.getTotal().doubleValue());
        assertEquals(1, cart.getCartItems().size());

        cart = cartService.getCart(getSimpleProductList(ProductType.FOOD, 11.25, true));
        assertEquals(.6, cart.getTotalSalesTax().doubleValue());
        assertEquals(11.85, cart.getTotal().doubleValue());
        assertEquals(1, cart.getCartItems().size());

        List<Product> duplicateList = new ArrayList<>();
        duplicateList.addAll(getSimpleProductList(ProductType.FOOD, 11.25, true));
        duplicateList.addAll(getSimpleProductList(ProductType.FOOD, 11.25, true));
        cart = cartService.getCart(duplicateList);
        assertEquals(1.2, cart.getTotalSalesTax().doubleValue());
        assertEquals(23.7, cart.getTotal().doubleValue());
        assertEquals(1, cart.getCartItems().size());
        assertEquals(2, cart.getCartItems().get(0).getQuantity());
    }

    @Test
    public void testCart1(){
        Cart cart = cartService.getCart(getProductsForCart1());
        assertEquals(1.5, cart.getTotalSalesTax().doubleValue());
        assertEquals(29.83, cart.getTotal().doubleValue());
        assertEquals(3, cart.getCartItems().size());
        assertEquals(12.49, cart.getCartItems().get(0).getTotalPrice().doubleValue());
        assertEquals(16.49, cart.getCartItems().get(1).getTotalPrice().doubleValue());
        assertEquals(0.85, cart.getCartItems().get(2).getTotalPrice().doubleValue());

    }

    @Test
    public void testCart2(){
        Cart cart = cartService.getCart(getProductsForCart2());
        assertEquals(7.65, cart.getTotalSalesTax().doubleValue());
        assertEquals(65.15, cart.getTotal().doubleValue());
        assertEquals(2, cart.getCartItems().size());
        assertEquals(10.5, cart.getCartItems().get(0).getTotalPrice().doubleValue());
        assertEquals(54.65, cart.getCartItems().get(1).getTotalPrice().doubleValue());
    }

    @Test
    public void testCart3(){
        Cart cart = cartService.getCart(getProductsForCart3());
        assertEquals(6.70, cart.getTotalSalesTax().doubleValue());
        assertEquals(74.68, cart.getTotal().doubleValue());
        assertEquals(4, cart.getCartItems().size());
        assertEquals(32.19, cart.getCartItems().get(0).getTotalPrice().doubleValue());
        assertEquals(20.89, cart.getCartItems().get(1).getTotalPrice().doubleValue());
        assertEquals(9.75, cart.getCartItems().get(2).getTotalPrice().doubleValue());
        assertEquals(11.85, cart.getCartItems().get(3).getTotalPrice().doubleValue());
    }

    private Collection<Product> getProductsForCart1() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("book", ProductType.BOOK, 12.49, false));
        products.add(new Product("music CD", ProductType.OTHER, 14.99, false));
        products.add(new Product("chocolate bar", ProductType.FOOD, 0.85, false));
        return products;
    }

    private Collection<Product> getProductsForCart2() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("imported box of chocolates", ProductType.FOOD, 10., true));
        products.add(new Product("imported bottle of perfume", ProductType.OTHER, 47.5, true));
        return products;
    }

    private Collection<Product> getProductsForCart3() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("imported bottle of perfume", ProductType.OTHER, 27.99, true));
        products.add(new Product("bottle of perfume", ProductType.OTHER, 18.99, false));
        products.add(new Product("packet of headache pills", ProductType.MEDICINE, 9.75, false));
        products.add(new Product("imported box of chocolates", ProductType.FOOD, 11.25, true));
        return products;
    }


    private Collection<Product> getSimpleProductList(ProductType productType, double price, boolean imported) {
        return Collections.singletonList(new Product("Test Product", productType, price, imported));
    }
}