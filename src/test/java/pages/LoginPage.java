package pages;

import baseEntities.BasePage;
import models.sauseDemo.UserBuilder;
import models.modelsForLombok.UserLogin;
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
        logger.info("Выполнение Step(Method) Корректное логирование началось");
        logger.debug(String.format("Заполнение формы Login параметром %s", username));
        setUsername(username);
        logger.debug(String.format("Заполнение формы Password параметром %s", password));
        setPassword(password);
        logger.debug("Нажатие кнопки LOGIN");
        clickLoginButton();
        return new ProductsPage(driver, true);
    }

    public LoginPage loginWithIncorrectAttribute() throws InterruptedException {
        logger.info("Выполнение Step(Method) Некорректное логирование началось...");
        logger.debug("Заполнение форм на странице Login произвольными (некорректными) параметрами");
        setUsername("11111");
        setPassword("22222");
        logger.debug("Нажатие кнопки LOGIN");
        clickLoginButton();
        return new LoginPage(driver,false);
    }

    //дублирующий метод создавала просто для проверки работы билдера
    public ProductsPage loginWithCorrectAttributeForBuilder(UserBuilder userBuilder) {
        setUsername(userBuilder.getLogin());
        setPassword(userBuilder.getPassword());
        clickLoginButton();
        return new ProductsPage(driver, true);
    }

    //дублирующий метод создавала просто для ValueObject
    public ProductsPage loginWithCorrectAttributeForValueObject(UserLogin userLogin) {
        setUsername(userLogin.getLogin());
        setPassword(userLogin.getPassword());
        clickLoginButton();
        return new ProductsPage(driver, true);
    }

}

