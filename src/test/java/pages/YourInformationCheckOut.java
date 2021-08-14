package pages;

import baseEntities.BasePage;
import models.sauseDemo.CustomerBuilder;
import models.modelsForLombok.Customer;
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

    public YourInformationCheckOut sendAttributeForCheckOut(String firstName, String lastName, String zipPostalCode) {
        logger.info("Выполнение Step(Method) Заполнение полей для оплаты началось...");
        logger.debug("Заполнение поля First name");
        setUserFirstName(firstName);
        logger.debug("Заполнение поля Last name");
        setUserLastName(lastName);
        logger.debug("Заполнение поля Zip code");
        setZipCode(zipPostalCode);
        return new YourInformationCheckOut(driver, false);
    }

    //дублирующий метод создавала просто для проверки работы билдера
    public YourInformationCheckOut sendAttributeForCheckOutForBuilder(CustomerBuilder customerBuilder) {
        setUserFirstName(customerBuilder.getFirstName());
        setUserLastName(customerBuilder.getLastName());
        setZipCode(customerBuilder.getZipPostalCode());
        return new YourInformationCheckOut(driver, false);
    }

    //дублирующий метод создавала просто для Value Object
    public YourInformationCheckOut sendAttributeForCheckOutForBuilderForValueObject(Customer customer) {
        setUserFirstName(customer.getFirstName());
        setUserLastName(customer.getLastName());
        setZipCode(customer.getZipPostalCode());
        return new YourInformationCheckOut(driver, false);
    }

    public CheckOutOverviewPage clickContinueButton() {
        logger.debug("Нажатие на кнопку CONTINUE");
        continueButton.click();
        return new CheckOutOverviewPage(driver, true);
    }
}
