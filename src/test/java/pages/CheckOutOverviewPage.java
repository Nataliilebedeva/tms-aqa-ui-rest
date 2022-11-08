package pages;

import baseEntities.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutOverviewPage extends BasePage {

    private final static String endpoint = "checkout-step-two.html";

    @FindBy(id = "cancel")
    public WebElement titleButton;

    @FindBy(id = "finish")
    public WebElement buttonFinish;

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
            return titleButton.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public CheckOutCompletePage clickButtonFinish() {
        logger.debug("Нажатие на кнопку FINISH");
        buttonFinish.click();
        return new CheckOutCompletePage(driver,true);
    }


}
