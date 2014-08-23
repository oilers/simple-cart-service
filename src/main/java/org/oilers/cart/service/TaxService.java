package org.oilers.cart.service;

import org.oilers.cart.model.Product;

import java.math.BigDecimal;

/**
 * Created by Kodi on 8/22/2014.
 */
public class TaxService {

    public static final BigDecimal MULTIPLIER = BigDecimal.valueOf(20);

    public BigDecimal calculateTax(Product product) {
        return round(getTaxRate(product).multiply(product.getPrice()));
    }

    private BigDecimal round(BigDecimal taxPrice) {
        double taxPriceDouble = taxPrice.doubleValue();
        BigDecimal roundedTaxPrice = BigDecimal.valueOf(taxPrice.multiply(MULTIPLIER).add(BigDecimal.valueOf(.5)).longValue()).divide(MULTIPLIER);
        if(taxPrice.compareTo(roundedTaxPrice) >= 1 ){
            roundedTaxPrice = roundedTaxPrice.add(BigDecimal.valueOf(.05));
        }
        return roundedTaxPrice;
    }

    private BigDecimal getTaxRate(Product product) {
        BigDecimal taxRate = BigDecimal.ZERO;
        if(product.getType().isTaxable()) {
            taxRate = taxRate.add(BigDecimal.valueOf(.1));
        }
        if(product.isImported()) {
            taxRate = taxRate.add(BigDecimal.valueOf(.05));
        }
        return taxRate;
    }
}
