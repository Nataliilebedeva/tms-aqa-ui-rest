package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutCompletePage extends BasePage {
    private static final By title_picture_By = By.className("pony_express");
    private static final By complete_text_By = By.xpath("//*[contains(text(), 'Your order has been dispatched')]");

    public CheckOutCompletePage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }


    @Override
    protected void openPage() {
        driver.get("https://www.saucedemo.com/checkout-complete.html");
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTitlePictureBy().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public WebElement getTitlePictureBy() { return driver.findElement(title_picture_By); }
    public WebElement getCompleteTextBy() { return driver.findElement(complete_text_By); }

}
