package pages;

import baseEntities.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutCompletePage extends BasePage {

    private final static String endpoint = "checkout-complete.html";

    @FindBy(className = "pony_express")
    public WebElement titlePicture;

    @FindBy(xpath = "//*[contains(text(), 'Your order has been dispatched')]")
    public WebElement completeText;

    public CheckOutCompletePage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }


    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return titlePicture.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
