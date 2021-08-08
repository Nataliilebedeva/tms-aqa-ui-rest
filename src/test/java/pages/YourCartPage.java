package pages;

import baseEntities.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourCartPage extends BasePage {

    private final static String endpoint = "cart.html";

    @FindBy(id = "continue-shopping")
    public WebElement titleButton;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    public YourCartPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }

    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return titleButton.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public YourInformationCheckOut clickCheckOutButton() {
        logger.debug("Нажатие на кнопки CHECKOUT");
        checkoutButton.click();
        return new YourInformationCheckOut(driver,true);
    }
}
