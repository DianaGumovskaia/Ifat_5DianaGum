package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    By userField = By.xpath("//input[@id='user-name']");
    By passwordField = By.xpath("//input[@id='password']");
    By loginBtn = By.xpath("//input[@id='login-button']");
    By error = By.xpath("//div[@class='error-message-container error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(final String userName, final String passwordName) {
        driver.findElement(userField).sendKeys(userName);
        driver.findElement(passwordField).sendKeys(passwordName);
        driver.findElement(loginBtn).click();
    }

    public boolean isMessageAppear() {
        return driver.findElement(error).isDisplayed();
    }

    public String errorMessageText() {
        return driver.findElement(error).getText();
    }

    public void loginBtnClick() {
        driver.findElement(loginBtn).click();
    }
}
