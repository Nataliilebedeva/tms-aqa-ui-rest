package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginStep extends BaseStep {


    public LoginStep(WebDriver driver) {
        super(driver);
    }

    @Step ("Вход в систему с корректными '{username}' и '{password}'")
    public ProductsPage loginWithCorrectAttribute(String username, String password){
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        return new ProductsPage(driver,true);
    }

    @Step ("Вход в систему с некорректными параметрами")
    public LoginPage loginWithIncorrectAttribute(){
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("11111");
        loginPage.setPassword("22222");
        loginPage.clickLoginButton();
        //
        return loginPage; //??
    }


}
