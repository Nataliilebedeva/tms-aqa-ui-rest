package steps;

import baseEntities.BaseStep;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.*;

public class CheckOutStep extends BaseStep {

    public CheckOutStep(WebDriver driver) {
        super(driver);
    }

    public void checkOut (String productName) {
        ProductsPage productsPage = new ProductsPage(driver,true);
        productsPage.addToCart(productName);
        productsPage.clickCartButton();

        new YourCartPage(driver,true).clickCheckOutButton();

        YourInformationCheckOut yourInformationCheckOut = new YourInformationCheckOut(driver, true);
        yourInformationCheckOut.setUserFirstName("Nat");
        yourInformationCheckOut.setUserLastName("Leb");
        yourInformationCheckOut.setZipCode("1234");
        yourInformationCheckOut.clickContinueButton();

        CheckOutOverviewPage checkOutOverviewPage = new CheckOutOverviewPage(driver, false);
        checkOutOverviewPage.setButtonFinish();

    }
    }
