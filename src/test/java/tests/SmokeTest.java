package tests;

import baseEntities.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SmokeTest extends BaseTest {

    @Test
    public void positiveLoginTestFirstUser() {

        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage(driver, false);

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");

    }

    @Test
    public void positiveLoginTestSecondUser() {

        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("locked_out_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage(driver, false);

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");

    }

    @Test
    public void positiveLoginTestThirdUser() {

        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("problem_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage(driver, false);

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");

    }


    @Test
    public void negativeLoginTest() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("fjskf");
        loginPage.setPassword("1111");
        loginPage.clickLoginButton();

        //Assert.assertTrue(loginPage.getErrorLabel().isDisplayed());
        Assert.assertEquals(loginPage.getErrorLabel().getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void positiveAddProductsToCart() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.clickAddToCartButton();

        Assert.assertEquals(productsPage.getLabelCartNull().getText(), "1");
    }

    @Test
    public void positiveAddProductsToCart2() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.clickProductButton();

        SomeProductPage someProductPage = new SomeProductPage(driver, false);
        someProductPage.clickAddToCartButton();

        Assert.assertEquals(someProductPage.getLabelCartNull().getText(), "1");
    }

    @Test
    public void positiveDeleteProductsToCart() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.clickAddToCartButton();
        productsPage.clickAddToCartOtherProductButton();
        productsPage.clickRemoveToCartOtherProductButton();


        Assert.assertEquals(productsPage.getLabelCartNull().getText(), "1");
    }

    @Test
    public void positiveDeleteProductsToCart2() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.clickAddToCartOtherProductButton();
        productsPage.clickRemoveToCartOtherProductButton();

        Assert.assertThrows(org.openqa.selenium.NoSuchElementException.class, productsPage::getLabelCartNull);
    }

    @Test
    public void positiveChekOut() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage(driver, false);
        productsPage.clickAddToCartOtherProductButton();
        productsPage.clickCartButton();

        YourCartPage yourCartPage = new YourCartPage(driver, false);
        yourCartPage.clickCheckOutButton();

        YourInformationCheckOut yourInformationCheckOut = new YourInformationCheckOut(driver, false);
        yourInformationCheckOut.setUserFirstName("Nat");
        yourInformationCheckOut.setUserLastName("Leb");
        yourInformationCheckOut.setZipCode("1234");
        yourInformationCheckOut.clickContinueButton();

        CheckOutOverviewPage checkOutOverviewPage = new CheckOutOverviewPage(driver, false);
        checkOutOverviewPage.setButtonFinish();

        CheckOutCompletePage checkOutCompletePage = new CheckOutCompletePage(driver, false);
        Assert.assertEquals(checkOutCompletePage.getCompleteTextBy().getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }


}

