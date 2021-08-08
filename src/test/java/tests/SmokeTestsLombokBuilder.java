package tests;

import baseEntities.BaseTest;
import models.UserBuilder;
import models.modelsForLombok.Customer;
import models.modelsForLombok.CustomerBuilder;
import models.modelsForLombok.UserLogin;
import models.modelsForLombok.UserLoginBuilder;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOutCompletePage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.SomeProductPage;

public class SmokeTestsLombokBuilder extends BaseTest {
    UserLoginBuilder userLoginBuilder = setupUserBuilderCorrect();
    UserLoginBuilder userLoginBuilderLocked = setupUserBuilderLocked();
    UserLoginBuilder userLoginBuilderProblem = setupUserBuilderProblem();
    CustomerBuilder customerBuilder = setupUserCustomer();

    //___________________________________________Логирование________________________________________________
    @Test
    public void positiveLoginTestFirstUser() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword());

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    @Test
    public void positiveLoginTestSecondUser() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilderLocked.getLogin(), userLoginBuilderLocked.getPassword());

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    @Test
    public void positiveLoginTestThirdUser() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilderProblem.getLogin(), userLoginBuilderProblem.getPassword());

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
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword())
                .addOrDeleteProduct("Sauce Labs Bolt T-Shirt", true);

        Assert.assertEquals(productsPage.labelCart.getText(), "1");
    }

    //Проверка на добавление в корзину со страницы одного товара
    @Test
    public void positiveAddProductsToCart2() {
        SomeProductPage someProductPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword())
                .addToCartBySomeProductPage("Sauce Labs Onesie")
                .clickAddToCartButton();

        Assert.assertEquals(someProductPage.labelCart.getText(), "1");
    }

    //_______________________________________Удаление товара из корзины______________________________________
    //Проверка на удаление товара с карзины со страницы Products через кнопку Remove
    @Test
    public void positiveDeleteProductsToCart() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword())
                .addOrDeleteProduct("Sauce Labs Backpack", true)
                .addOrDeleteProduct("Sauce Labs Bolt T-Shirt", false);

        Assert.assertEquals(productsPage.labelCart.getText(), "1");
    }

    // Проверка на удаление товара с карзины со страницы Products через кнопку Remove другим способом
    @Test(expectedExceptions = TimeoutException.class)
    public void positiveDeleteProductsToCart2() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword())
                .addOrDeleteProduct("Sauce Labs Backpack", false);

        waits.waitForVisibilityElement(productsPage.labelCart);
    }

    //___________________________________________Оплата________________________________________
    @Test
    public void positiveChekOut() {
        CheckOutCompletePage checkOutCompletePage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword())
                .addOrDeleteProduct("Sauce Labs Fleece Jacket", true)
                .clickCartButton()
                .clickCheckOutButton()
                .sendAttributeForCheckOut(customerBuilder.getFirstName(), customerBuilder.getLastName(), customerBuilder.getZipPostalCode())
                .clickContinueButton()
                .clickButtonFinish();

        Assert.assertEquals(checkOutCompletePage.completeText.getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    private Customer setupCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Nat");
        customer.setLastName("Leb");
        customer.setZipPostalCode("1111");
        return customer;
    }

    private UserLoginBuilder setupUserBuilderCorrect() {
        UserLoginBuilder userLoginBuilder = UserLoginBuilder.builder()
                .login("standard_user")
                .password("secret_sauce")
                .build();
        return userLoginBuilder;
    }

    private UserLoginBuilder setupUserBuilderLocked() {
        UserLoginBuilder userLoginBuilderLocked = UserLoginBuilder.builder()
                .login("locked_out_user")
                .password("secret_sauce")
                .build();
        return userLoginBuilderLocked;
    }

    private UserLoginBuilder setupUserBuilderProblem() {
        UserLoginBuilder userLoginBuilderProblem = UserLoginBuilder.builder()
                .login("problem_user")
                .password("secret_sauce")
                .build();
        return userLoginBuilderProblem;
    }

    private CustomerBuilder setupUserCustomer() {
        CustomerBuilder customerBuilder = CustomerBuilder.builder()
                .firstName("Nat")
                .lastName("Leb")
                .zipPostalCode("1111")
                .build();
        return customerBuilder;
    }
}

