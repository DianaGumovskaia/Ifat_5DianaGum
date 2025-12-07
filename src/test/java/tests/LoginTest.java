package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class LoginTest extends BaseTest {

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {UserFactory.withLockedUserPermission(),
                        "Epic sadface: Sorry, this user has been locked out."},

                {UserFactory.emptyUser(),
                        "Epic sadface: Username is required"},

                {UserFactory.withUserNameOnly(""),
                        "Epic sadface: Username is required"},

                {UserFactory.withPasswordOnly(""),
                        "Epic sadface: Password is required"},

                {new User("locked_out_user", "hello1"),
                        "Epic sadface: Username and password do not match any user in this service"}

/*                {locked, password, "Epic sadface: Sorry, this user has been locked out."},
                {"", "", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"locked_out_user", "hello1", "Epic sadface: Username and password do not match any user in this service"}*/
        };
    }

    @Test(dataProvider = "loginData")
    public void checkIncorrectLogin(User user, String errorMsg) {
        System.out.println("LoginTest inc is running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isMessageAppear(), "Сообщение об ошибке не отобразилось");
        assertEquals(loginPage.errorMessageText(), errorMsg);
    }

    @Test(priority = 2, enabled = true, invocationCount = 1, alwaysRun = true)
    public void checkCorrectLogin() {
        System.out.println("LoginTest corr is running in thread: " + Thread.currentThread().getId());


        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.isPageLoaded("Products"), "Название страницы не отобразилось");
    }
}
