package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {
        System.out.println("ProdTest inc is running in thread: " + Thread.currentThread().getId());


        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.isPageLoaded("Products");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");
        productsPage.addToCart(2);
        assertEquals(productsPage.checkGoodsQuantity(), "2");
    }
}
