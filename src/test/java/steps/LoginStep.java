package steps;

import baseEntities.BaseStep;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginStep extends BaseStep {

//конструктор получит драйвер из наших тестов
    public LoginStep(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password){
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

    }
}
