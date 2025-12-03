package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    By userField = By.xpath("//input[@id='user-name']");
    By passwordField = By.xpath("//input[@id='password']");
    By loginBtn = By.xpath("//input[@id='login-button']");
    By error = By.xpath("//div[@class='error-message-container error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(final String userName, final String passwordName) {
        enterLoginName(userName);
        driver.findElement(passwordField).sendKeys(passwordName);
        driver.findElement(loginBtn).click();
    }

    public void enterLoginName(final String userName) {
        driver.findElement(userField).sendKeys(userName);
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
