package org.oilers.cart.service;

import org.oilers.cart.model.Cart;
import org.oilers.cart.model.CartItem;
import org.oilers.cart.model.Product;

import java.util.Collection;

/**
 * Created by Kodi on 8/22/2014.
 */
public class CartService {

    private TaxService taxService = new TaxService();

    public Cart getCart(Collection<Product> products) {
        Cart cart = new Cart();
        for (Product product : products) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setSalesTax(taxService.calculateTax(product));
            cart.addItem(cartItem);
        }
        return cart;
    }
}
