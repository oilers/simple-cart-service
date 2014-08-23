package org.oilers.cart.model;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by Kodi on 8/22/2014.
 */
public class CartItem {

    private Product product;
    private BigDecimal salesTax;
    private int quantity = 1;

    public BigDecimal getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(BigDecimal salesTax) {
        this.salesTax = salesTax;
    }

    public BigDecimal getTotalSalesTax() {
        return salesTax.multiply(BigDecimal.valueOf(quantity));
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getTotalPrice() {
        return product.getPrice().add(salesTax).multiply(BigDecimal.valueOf(quantity));
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return product.equals(cartItem.product);

    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }

    public String toString(){
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        return quantity + " " + product.getName() + ": " + currencyInstance.format(getTotalPrice());
    }
}
