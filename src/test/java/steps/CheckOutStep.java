package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;

public class CheckOutStep extends BaseStep {

    public CheckOutStep(WebDriver driver) {
        super(driver);
    }

    @Step("Переход на страницу карзины You Cart")
    public void moveToYouCartPage () {
        ProductsPage productsPage = new ProductsPage(driver, true);
        // productsPage.addToCart(productName);
        productsPage.clickCartButton();
    }

    @Step("Переход на страницу Check Out и заполнение резквизитов для оплаты")
    public void moveToCheckOutPage () {
        YourCartPage yourCartPage = new YourCartPage(driver, true);
        yourCartPage.clickCheckOutButton();

        YourInformationCheckOut yourInformationCheckOut = new YourInformationCheckOut(driver, true);
        yourInformationCheckOut.setUserFirstName("Nat");
        yourInformationCheckOut.setUserLastName("Leb");
        yourInformationCheckOut.setZipCode("1234");

    }

    @Step("Переход на страницу Check Out:overview и завершение процесса оплаты")
    public void moveToCheckOverviewOutPageAndFinishCheckOut () {
        YourInformationCheckOut yourInformationCheckOut = new YourInformationCheckOut(driver, false);
        yourInformationCheckOut.clickContinueButton();
        CheckOutOverviewPage checkOutOverviewPage = new CheckOutOverviewPage(driver, false);
        checkOutOverviewPage.setButtonFinish();

    }



    }
