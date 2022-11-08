package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginStep extends BaseStep {


    public LoginStep(WebDriver driver) {
        super(driver);
    }

    @Step ("Вход в систему с корректными '{username}' и '{password}'")
    public void loginWithCorrectAttribute(String username, String password){
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
    }

    @Step ("Вход в систему с некорректными параметрами")
    public void loginWithIncorrectAttribute(){
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("11111");
        loginPage.setPassword("22222");
        loginPage.clickLoginButton();
    }


}
