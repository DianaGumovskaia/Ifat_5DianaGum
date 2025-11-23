import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class FirstTest {

    @Test
    public void checkAuth() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        browser.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        browser.findElement(By.xpath("//input[@id='login-button']")).click();
        browser.quit();
    }

    @Test
    public void checkErrorMessage() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        browser.findElement(By.xpath("//input[@id='password']")).sendKeys("12345");
        browser.findElement(By.xpath("//input[@id='login-button']")).click();

        boolean isErrorAppear = browser.findElement(By.xpath("//div[@class='error-message-container error']")).isDisplayed();
        assertTrue(isErrorAppear, "Сообщение об ошибке не отобразилось");

        String errorMessageText = browser.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        assertEquals(errorMessageText, "Epic sadface: Username and password do not match any user in this service");
        browser.quit();
    }

    @Test
    public void checkGoToPage() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        browser.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        browser.findElement(By.xpath("//input[@id='login-button']")).click();

        boolean isPageTitle = browser.findElement(By.xpath("//*[text()='Products']")).isDisplayed();
        assertTrue(isPageTitle, "Название страницы не отобразилось");
        browser.quit();
    }
    @Test
    public void checkErrorEmptyField() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.xpath("//input[@id='login-button']")).click();

        boolean isErrorAppear = browser.findElement(By.xpath("//div[@class='error-message-container error']")).isDisplayed();
        assertTrue(isErrorAppear, "Сообщение об ошибке не отобразилось");

        String errorMessageText = browser.findElement(By.xpath("//div[@class='error-message-container error']")).getText();
        assertEquals(errorMessageText, "Epic sadface: Username is required");
        browser.quit();
    }
}
