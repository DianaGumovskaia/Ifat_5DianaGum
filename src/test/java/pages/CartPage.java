package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    By cartProducts = By.cssSelector(".inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getProductsNames() {
        List<WebElement> allProductsNames = driver.findElements(cartProducts);
        ArrayList<String> names = new ArrayList<>();
        for (WebElement products : allProductsNames) {
            names.add(products.getText());
        }
        return names;
    }

    public CartPage removeFromCart(final int cartsOrder) {
        driver.findElements(By.xpath(TEXT_LOCATOR_PATTERN.formatted("Remove"))).get(cartsOrder).click();
        return this;
    }
}
