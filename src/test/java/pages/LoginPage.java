package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    By userField = By.xpath("//input[@id='user-name']");
    By passwordField = By.xpath("//input[@id='password']");
    By loginBtn = By.xpath("//input[@id='login-button']");
    By error = By.xpath("//div[@class='error-message-container error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Авторизация под кредами пользователя: логин = {user.email}, пароль = {*****}")
    public LoginPage login(User user) {
        enterLoginName(user.getEmail());
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(loginBtn).click();
        return this;
    }

    @Step("Вводим логин")
    public LoginPage enterLoginName(final String userName) {
        driver.findElement(userField).sendKeys(userName);
        return this;
    }

    @Step("Отображение сообщения об ошибке")
    public boolean isMessageAppear() {
        return driver.findElement(error).isDisplayed();
    }

    @Step("Получаем текст из сообщения об ошибке")
    public String errorMessageText() {
        return driver.findElement(error).getText();
    }
}
