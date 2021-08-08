package tests;

import baseEntities.BaseTest;
import models.modelsForLombok.Customer;
import models.modelsForLombok.UserLogin;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOutCompletePage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.SomeProductPage;

public class SmokeTestsLombok extends BaseTest {
    UserLogin userCorrect = setupUserCorrect();
    UserLogin userLocked = setupUserLocked();
    UserLogin userProblem = setupUserProblem();
    Customer customer = setupCustomer();

    //___________________________________________Логирование________________________________________________
    @Test
    public void positiveLoginTestFirstUser() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttributeForValueObject(userCorrect);

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    @Test
    public void positiveLoginTestSecondUser() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttributeForValueObject(userLocked);

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    @Test
    public void positiveLoginTestThirdUser() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttributeForValueObject(userProblem);

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    @Test
    public void negativeLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, true).loginWithIncorrectAttribute();

        Assert.assertEquals(loginPage.errorLabel.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    //___________________________________________Добавление товара в корзину________________________________________
    // Проверка на добавление товара в корзину с Products Page
    @Test
    public void positiveAddProductsToCart() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttributeForValueObject(userCorrect)
                .addOrDeleteProduct("Sauce Labs Bolt T-Shirt", true);

        Assert.assertEquals(productsPage.labelCart.getText(), "1");
    }

    //Проверка на добавление в корзину со страницы одного товара
    @Test
    public void positiveAddProductsToCart2() {
        SomeProductPage someProductPage = new LoginPage(driver, true)
                .loginWithCorrectAttributeForValueObject(userCorrect)
                .addToCartBySomeProductPage("Sauce Labs Onesie")
                .clickAddToCartButton();

        Assert.assertEquals(someProductPage.labelCart.getText(), "1");
    }

    //_______________________________________Удаление товара из корзины______________________________________
    //Проверка на удаление товара с карзины со страницы Products через кнопку Remove
    @Test
    public void positiveDeleteProductsToCart() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttributeForValueObject(userCorrect)
                .addOrDeleteProduct("Sauce Labs Backpack", true)
                .addOrDeleteProduct("Sauce Labs Bolt T-Shirt", false);

        Assert.assertEquals(productsPage.labelCart.getText(), "1");
    }

    // Проверка на удаление товара с карзины со страницы Products через кнопку Remove другим способом
    @Test(expectedExceptions = TimeoutException.class)
    public void positiveDeleteProductsToCart2() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttributeForValueObject(userCorrect)
                .addOrDeleteProduct("Sauce Labs Backpack", false);

        waits.waitForVisibilityElement(productsPage.labelCart);
    }

    //___________________________________________Оплата________________________________________
    // Тест на оплату
    @Test(description = "Позитивный тест оплату")
    public void positiveChekOut() {
        CheckOutCompletePage checkOutCompletePage = new LoginPage(driver, true)
                .loginWithCorrectAttributeForValueObject(userCorrect)
                .addOrDeleteProduct("Sauce Labs Fleece Jacket", true)
                .clickCartButton()
                .clickCheckOutButton()
                .sendAttributeForCheckOutForBuilderForValueObject(customer)
                .clickContinueButton()
                .clickButtonFinish();

        Assert.assertEquals(checkOutCompletePage.completeText.getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    private UserLogin setupUserCorrect() {
        UserLogin userCorrect = new UserLogin();
        userCorrect.setLogin("standard_user");
        userCorrect.setPassword("secret_sauce");
        return userCorrect;
    }

    private UserLogin setupUserLocked() {
        UserLogin userLocked = new UserLogin();
        userLocked.setLogin("locked_out_user");
        userLocked.setPassword("secret_sauce");
        return userLocked;
    }

    private UserLogin setupUserProblem() {
        UserLogin userProblem = new UserLogin();
        userProblem.setLogin("problem_user");
        userProblem.setPassword("secret_sauce");
        return userProblem;
    }

    private Customer setupCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Nat");
        customer.setLastName("Leb");
        customer.setZipPostalCode("1111");
        return customer;
    }
}

