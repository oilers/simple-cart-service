package org.oilers.cart.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kodi on 8/22/2014.
 */
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public BigDecimal getTotalSalesTax() {
        BigDecimal salesTax = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
           salesTax = salesTax.add(cartItem.getTotalSalesTax());
        }
        return salesTax;
    }

    public BigDecimal getTotal(){
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            total = total.add(cartItem.getTotalPrice());
        }
        return total;
    }

    public void addItem(CartItem cartItem) {
        int existingIndex = cartItems.indexOf(cartItem);
        if(existingIndex > -1) {
           cartItems.get(existingIndex).setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItems.add(cartItem);
        }
    }
}
