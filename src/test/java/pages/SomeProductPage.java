package pages;

import baseEntities.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SomeProductPage extends BasePage {

    private final static String endpoint = "inventory-item.html?id=2";

    @FindBy(id = "back-to-products")
    public WebElement titleText;

    @FindBy(xpath = "//*[text() = 'Add to cart']")
    public WebElement addToCartButton;

    @FindBy(className = "shopping_cart_badge")
    public WebElement labelCart;

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
            return titleText.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public SomeProductPage clickAddToCartButton() {
        logger.debug("Нажатие на кнопки ADD TO CARD со страницы сооветсвующего товара ");
        addToCartButton.click();
        return new SomeProductPage(driver,false);
    }
}
