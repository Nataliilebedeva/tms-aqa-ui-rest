package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YourCartPage extends BasePage {

    private final static String endpoint = "cart.html";

    //1. Селекоторы
    private final static By title_button_By = By.id("continue-shopping");
    private final static By checkout_button = By.id("checkout");

    //2. Конструктор
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
            return getTitleButton().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

// Getter
    public WebElement getTitleButton() { return driver.findElement(title_button_By);}
    public WebElement getCheckOutButton() { return driver.findElement(checkout_button);}


    //Атомарные методы

    public void clickCheckOutButton() {
        getCheckOutButton().click();
    }
}
