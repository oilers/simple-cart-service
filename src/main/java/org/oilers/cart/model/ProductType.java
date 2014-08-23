package org.oilers.cart.model;

/**
 * Created by Kodi on 8/22/2014.
 */
public enum ProductType {
    BOOK,
    FOOD,
    MEDICINE,
    OTHER(true);

    private final boolean taxable;

    public boolean isTaxable() {
        return taxable;
    }
    private ProductType(boolean taxable) {
        this.taxable = taxable;
    }

    private ProductType() {
        this.taxable = false;
    }
}
