import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class LoginTest extends BaseTest {

    @Test
    public void checkCorrectLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.isPageLoaded(), "Название страницы не отобразилось");
    }

    @Test
    public void checkIncorrectLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isMessageAppear(), "Сообщение об ошибке не отобразилось");
        assertEquals(loginPage.errorMessageText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void checkEmptyLogin() {
        loginPage.open();
        loginPage.loginBtnClick();

        assertTrue(loginPage.isMessageAppear(), "Сообщение об ошибке не отобразилось");
        assertEquals(loginPage.errorMessageText(), "Epic sadface: Username is required");
    }
}
