package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import static enums.TitleNaming.PRODUCTS;
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

/*                {locked, password, "Epic sadface: Sorry, this user has been locked out.},
                {"", "", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"locked_out_user", "hello1", "Epic sadface: Username and password do not match any user in this service"}*/
        };
    }

    @Epic("Создание лида")
    @Feature("Создание с карточки клиента")
    @Story("Пангинация")
    @TmsLink("DianaGumovskaia?tab=repositories")
    @Owner("https://t.me/di_gumovskaya")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("Ifat_5DianaGum")
    @Test(description = "Проверка некорректного логина", dataProvider = "loginData")
    public void checkIncorrectLogin(User user, String errorMsg) {
        System.out.println("LoginTest inc is running in thread: " + Thread.currentThread().getId());

        loginPage
                .open()
                .login(user);

        //AllureUtils.takeScreenshot(driver);
        assertTrue(loginPage.isMessageAppear(), "Сообщение об ошибке не отобразилось");
        assertEquals(loginPage.errorMessageText(), errorMsg);
    }

    @Test(description = "Проверка корректного логина", priority = 2, enabled = true, invocationCount = 1, alwaysRun = true)
    public void checkCorrectLogin() {
        System.out.println("LoginTest corr is running in thread: " + Thread.currentThread().getId());

        loginPage
                .open()
                .login(withAdminPermission());

        assertTrue(productsPage.isPageLoaded(PRODUCTS.getDisplayName()), "Название страницы не отобразилось");
    }
}
