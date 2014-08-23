package org.oilers.cart.service;

import org.junit.Before;
import org.junit.Test;
import org.oilers.cart.model.Product;
import org.oilers.cart.model.ProductType;

import static junit.framework.Assert.assertEquals;


public class TaxServiceTest {

    private TaxService taxService;
    @Before
    public void setUp(){
        taxService = new TaxService();
    }

    @Test
    public void testTaxSimple() {
        assertEquals(0.,taxService.calculateTax(new Product("Test Product", ProductType.BOOK, 100., false)).doubleValue());
        assertEquals(0.,taxService.calculateTax(new Product("Test Product", ProductType.FOOD, 100., false)).doubleValue());
        assertEquals(0.,taxService.calculateTax(new Product("Test Product", ProductType.MEDICINE, 100., false)).doubleValue());
        assertEquals(10.,taxService.calculateTax(new Product("Test Product", ProductType.OTHER, 100., false)).doubleValue());
    }

    @Test
    public void testTaxSimpleImported() {
        assertEquals(5.,taxService.calculateTax(new Product("Test Product", ProductType.BOOK, 100., true)).doubleValue());
        assertEquals(5.,taxService.calculateTax(new Product("Test Product", ProductType.FOOD, 100., true)).doubleValue());
        assertEquals(5.,taxService.calculateTax(new Product("Test Product", ProductType.MEDICINE, 100., true)).doubleValue());
        assertEquals(15.,taxService.calculateTax(new Product("Test Product", ProductType.OTHER, 100., true)).doubleValue());
    }

    @Test
    public void testTaxRound() {
        assertEquals(.3,taxService.calculateTax(new Product("Test Product", ProductType.BOOK, 5.5, true)).doubleValue());
        assertEquals(.3,taxService.calculateTax(new Product("Test Product", ProductType.BOOK, 5.2, true)).doubleValue());
        assertEquals(1.5,taxService.calculateTax(new Product("Test Product", ProductType.OTHER, 14.99, false)).doubleValue());
        assertEquals(7.15,taxService.calculateTax(new Product("Test Product", ProductType.OTHER, 47.50, true)).doubleValue());
    }
}