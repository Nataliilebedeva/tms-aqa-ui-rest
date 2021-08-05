package pages;

import baseEntities.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourInformationCheckOut extends BasePage {

    private final static String endpoint = "checkout-step-one.html";

    @FindBy(id = "cancel")
    public WebElement titleButton;

    @FindBy(id = "first-name")
    public WebElement firsNameInput;

    @FindBy(id = "last-name")
    public WebElement lastNameInput;

    @FindBy(id = "postal-code")
    public WebElement zipCodeInput;

    @FindBy(id = "continue")
    public WebElement continueButton;

    public YourInformationCheckOut(WebDriver driver, boolean openPageByURL) {
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

    public void setUserFirstName(String text) {
        firsNameInput.sendKeys(text);
    }

    public void setUserLastName(String text) {
        lastNameInput.sendKeys(text);
    }

    public void setZipCode(String text) {
        zipCodeInput.sendKeys(text);
    }

    public YourInformationCheckOut sendAttributeForCheckOut() {
        setUserFirstName("Nat");
        setUserLastName("Leb");
        setZipCode("1234");
        return new YourInformationCheckOut(driver, false);
    }

    public CheckOutOverviewPage clickContinueButton() {
        continueButton.click();
        return new CheckOutOverviewPage(driver, false);
    }
}
