package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    String user;
    String locked;
    String password;

    @Parameters({"browser"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("guest");
            //options.addArguments("--windows-size=1900,1000");
            //options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("safari")) {
            WebDriverManager.safaridriver().setup();
            driver = new SafariDriver();
        }
        //задать неявное ожидание по опр. времени
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);

        user = PropertyReader.getProperty("saucedemo.user");
        locked = PropertyReader.getProperty("saucedemo.locked");
        password = PropertyReader.getProperty("saucedemo.password");
    }

    @AfterMethod
    public void close() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
