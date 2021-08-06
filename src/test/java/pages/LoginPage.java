package pages;

import baseEntities.BasePage;
import models.CustomerBuilder;
import models.UserBuilder;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(tagName = "h3")
    public WebElement errorLabel;

    public LoginPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }

    @Override
    protected void openPage() {
        driver.get(properties.getURL());
    }

    @Override
    public boolean isPageOpened() {
        try {
            return loginButton.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void setUsername(String text) {
        usernameInput.sendKeys(text);
    }

    public void setPassword(String text) {
        passwordInput.sendKeys(text);
    }


    public void clickLoginButton() {
        loginButton.click();
    }

    public ProductsPage loginWithCorrectAttribute(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
        return new ProductsPage(driver, true);
    }

    public LoginPage loginWithIncorrectAttribute() throws InterruptedException {
        setUsername("11111");
        setPassword("22222");
        clickLoginButton();
        Thread.sleep(1000);
        return new LoginPage(driver,false);
    }

    //дублирующий метод создавала просто для проверки работы билдера
    public ProductsPage loginWithCorrectAttributeForBuilder(UserBuilder userBuilder) {
        setUsername(userBuilder.getLogin());
        setPassword(userBuilder.getPassword());
        clickLoginButton();
        return new ProductsPage(driver, true);
    }
}

