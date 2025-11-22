import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FirstTest {
    @Test
    public void checkZipCodeInput() {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        browser.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        browser.findElement(By.xpath("//input[@id='login-button']")).click();

        //boolean isErrorAppear = browser.findElement(By.xpath("//div[@class='error-message-container']")).isDisplayed();


        //assertTrue(isErrorAppear);
        //Thread.sleep(10000);
        //browser.quit();


    }
}
