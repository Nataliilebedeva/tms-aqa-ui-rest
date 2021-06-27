package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YourInformationCheckOut extends BasePage {

    //Селекторы
    private final static By title_button_By = By.id("cancel");
    private final static By firs_name_Input_By = By.id("first-name");
    private final static By last_name_Input_By = By.id("last-name");
    private final static By zip_code_Input_By = By.id("postal-code");
    private final static By continue_Button_By = By.id("continue");


    public YourInformationCheckOut(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }


    @Override
    protected void openPage() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTitleButtonBy().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    //Геттеры
    public WebElement getTitleButtonBy() { return driver.findElement(title_button_By); }
    public WebElement getFirsName() {
        return driver.findElement(firs_name_Input_By);
    }
    public WebElement getLastName() {
        return driver.findElement(last_name_Input_By);
    }
    public WebElement getZipCode() { return driver.findElement(zip_code_Input_By);}
    public WebElement getContinueButton() { return driver.findElement(continue_Button_By);}

    //Атомарные методы

    public void setUserFirstName (String text) { getFirsName().sendKeys(text);}
    public void setUserLastName (String text) { getLastName().sendKeys(text);}
    public void setZipCode (String text) { getZipCode().sendKeys(text);}
    public void clickContinueButton () { getContinueButton().click();}


}
