package org.oilers.cart.model;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by Kodi on 8/22/2014.
 */
public class Product {

    private String name;
    private ProductType type;
    private BigDecimal price;
    private boolean imported;

    public Product(String name, ProductType type, double price, boolean imported) {
        this.name = name;
        this.type = type;
        this.price = BigDecimal.valueOf(price);
        this.imported = imported;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isImported() {
        return imported;
    }

    public ProductType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return imported == product.imported && name.equals(product.name) && price.equals(product.price) && type == product.type;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + (imported ? 1 : 0);
        return result;
    }

    public String toString(){
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        return "1 " + (imported?"imported " :"") + name + " at " + currencyInstance.format(price);
    }
}
