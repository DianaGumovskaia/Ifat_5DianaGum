package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {

    @Test
    public void checkGoodsInCart() {
        System.out.println("CartTest inc is running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.isPageLoaded("Products");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        productsPage.switchToCart();
        cartPage.isPageLoaded("Your Cart");
        assertEquals(cartPage.getProductsNames().size(), 2);
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Fleece Jacket"));
    }

    @Test
    public void checkRemoveBtn() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.isPageLoaded("Products");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        productsPage.switchToCart();
        cartPage.isPageLoaded("Your Cart");
        assertEquals(cartPage.getProductsNames().size(), 2);
        cartPage.removeFromCart(1);
        assertEquals(cartPage.getProductsNames().size(), 1);
    }
}
