package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SomeProductPage extends BasePage {

    private final static String endpoint = "inventory-item.html?id=2";

    private final static By title_text_By = By.id("back-to-products");
    private final static By add_to_cart_button = By.xpath("//*[text() = 'Add to cart']");
    private final static By label_cart = By.className("shopping_cart_badge");

    public SomeProductPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }


    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTitleText().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    public WebElement getTitleText() { return driver.findElement(title_text_By);}
    public WebElement getAddToCartButton() { return driver.findElement(add_to_cart_button);}
    public WebElement getLabelCartNull() { return driver.findElement(label_cart);}


    public void clickAddToCartButton() { getAddToCartButton().click(); }
}
