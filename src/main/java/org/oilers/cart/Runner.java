package org.oilers.cart;

import org.oilers.cart.model.Cart;
import org.oilers.cart.model.CartItem;
import org.oilers.cart.model.Product;
import org.oilers.cart.model.ProductType;
import org.oilers.cart.service.CartService;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kodi on 8/23/2014.
 */
public class Runner {


    public static void main(String[] args) {
        CartService cartService = new CartService();
        List<Product> products = getProductsForCart1();
        printCartCrap(products, cartService.getCart(products));
        products = getProductsForCart2();
        printCartCrap(products, cartService.getCart(products));
        products = getProductsForCart3();
        printCartCrap(products, cartService.getCart(products));


    }

    public static String currencyFormat(BigDecimal ammount) {
        return NumberFormat.getCurrencyInstance().format(ammount);
    }

    private static void printCartCrap(List<Product> products, Cart cart) {
        printItems(products);
        System.out.println();
        printReciept(cart);
        System.out.println();
    }

    private static void printReciept(Cart cart) {
        System.out.println("Reciept:");
        for (CartItem cartItem : cart.getCartItems()) {
            System.out.println(cartItem);
        }
        System.out.println("Sales Tax: " + currencyFormat(cart.getTotalSalesTax()));
        System.out.println("Total: " + currencyFormat(cart.getTotal()));
    }

    private static void printItems(List<Product> products) {
        System.out.println("Items:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static List<Product> getProductsForCart1() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("book", ProductType.BOOK, 12.49, false));
        products.add(new Product("music CD", ProductType.OTHER, 14.99, false));
        products.add(new Product("chocolate bar", ProductType.FOOD, 0.85, false));
        return products;
    }

    private static List<Product> getProductsForCart2() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("imported box of chocolates", ProductType.FOOD, 10., true));
        products.add(new Product("imported bottle of perfume", ProductType.OTHER, 47.5, true));
        return products;
    }

    private static List<Product> getProductsForCart3() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("imported bottle of perfume", ProductType.OTHER, 27.99, true));
        products.add(new Product("bottle of perfume", ProductType.OTHER, 18.99, false));
        products.add(new Product("packet of headache pills", ProductType.MEDICINE, 9.75, false));
        products.add(new Product("imported box of chocolates", ProductType.FOOD, 11.25, true));
        return products;
    }
}
