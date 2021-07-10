package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutOverviewPage extends BasePage {

    private final static String endpoint = "checkout-step-two.html";

    private static final By title_button_By = By.id("cancel");
    private static final By button_finish_By = By.id("finish");

    public CheckOutOverviewPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }


    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTitleButtonBy().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public WebElement getTitleButtonBy() { return driver.findElement(title_button_By); }
    public WebElement getButtonFinish() { return driver.findElement(button_finish_By); }

    public void setButtonFinish() { getButtonFinish().click();}


}
