import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"locked_out_user", "hello1", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(description = "Проверка корректного логина", priority = 1, dataProvider = "loginData")
    public void checkIncorrectLogin(String user, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isMessageAppear(), "Сообщение об ошибке не отобразилось");
        assertEquals(loginPage.errorMessageText(), errorMsg);
    }

    @Test(priority = 2, enabled = true, invocationCount = 1, alwaysRun = true)
    public void checkCorrectLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(productsPage.isPageLoaded("Products"), "Название страницы не отобразилось");
    }
}
