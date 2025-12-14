package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    private static final String ADD_TO_CART_BUTTON_PATTERN =
            "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";
    private static final String DATA_TEST_PATTERN = "[data-test='%s']";
    By pageTitle = By.xpath("//*[@data-test='title']");
    By cartBadge = By.xpath("//*[@data-test='shopping-cart-badge']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Добавление товаров в корзину по названию товара")
    public ProductsPage addToCart(final String goodsName) {
        By addGoodsToCart = By.xpath(ADD_TO_CART_BUTTON_PATTERN.formatted(goodsName));
        driver.findElement(addGoodsToCart).click();
        return this;
    }

    @Step("Добавление товаров в корзину")
    public ProductsPage addToCart(final int goodsOrder) {
        driver.findElements(By.xpath(TEXT_LOCATOR_PATTERN.formatted("Add to cart"))).get(goodsOrder).click();
        return this;
    }

    public String checkGoodsQuantity() {
        return driver.findElement(cartBadge).getText();
    }

    public ProductsPage switchToCart() {
        driver.findElement(cartBadge).click();
        return this;
    }
}
