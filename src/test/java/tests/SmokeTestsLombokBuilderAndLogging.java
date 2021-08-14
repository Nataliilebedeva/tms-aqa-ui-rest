package tests;

import baseEntities.BaseTest;
import models.modelsForLombok.CustomerBuilder;
import models.modelsForLombok.UserLoginBuilder;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckOutCompletePage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.SomeProductPage;

public class SmokeTestsLombokBuilderAndLogging extends BaseTest {
    UserLoginBuilder userLoginBuilder = setupUserBuilderCorrect();
    UserLoginBuilder userLoginBuilderLocked = setupUserBuilderLocked();
    UserLoginBuilder userLoginBuilderProblem = setupUserBuilderProblem();
    CustomerBuilder customerBuilder = setupUserCustomer();

    //___________________________________________Логирование________________________________________________
    @Test
    public void positiveLoginTestFirstUser() {
        logger.info("Позитивный тест на вход в систему standard user начался...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword());

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
        logger.info("Позитивный тест на вход в систему standard user закончился.");
    }

    @Test
    public void positiveLoginTestSecondUser() {
        logger.info("Позитивный тест на вход в систему locked out user начался...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilderLocked.getLogin(), userLoginBuilderLocked.getPassword());

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
        logger.info("Позитивный тест на вход в систему locked out user закончился.");
    }

    @Test
    public void positiveLoginTestThirdUser() {
        logger.info("Позитивный тест на вход в систему problem user начался...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilderProblem.getLogin(), userLoginBuilderProblem.getPassword());

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
        logger.info("Позитивный тест на вход в систему problem user закончился.");
    }

    @Test
    public void negativeLoginTest() throws InterruptedException {
        logger.info("Негативный тест на вход в систему начался...");
        LoginPage loginPage = new LoginPage(driver, true).loginWithIncorrectAttribute();

        Assert.assertEquals(loginPage.errorLabel.getText(), "Epic sadface: Username and password do not match any user in this service");
        logger.info("Негативный тест на вход в систему закончился.");
    }

    //___________________________________________Добавление товара в корзину________________________________________
    // Проверка на добавление товара в корзину с Products Page
    @Test
    public void positiveAddProductsToCart() {
        logger.info("Позитивный тест на добавление товара в корзину со страницы Products page начался...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword())
                .addOrDeleteProduct("Sauce Labs Bolt T-Shirt", true);

        Assert.assertEquals(productsPage.labelCart.getText(), "1");
        logger.info("Позитивный тест на добавление товара в корзину со страницы Products page закончился.");
    }

    //Проверка на добавление в корзину со страницы одного товара
    @Test
    public void positiveAddProductsToCart2() {
        logger.info("Позитивный тест на добавление товара в корзину со страницы Some product page начался...");
        SomeProductPage someProductPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword())
                .addToCartBySomeProductPage("Sauce Labs Onesie")
                .clickAddToCartButton();

        Assert.assertEquals(someProductPage.labelCart.getText(), "1");
        logger.info("Позитивный тест на добавление товара в корзину со страницы Some product page закончился.");
    }

    //_______________________________________Удаление товара из корзины______________________________________
    //Проверка на удаление товара с карзины со страницы Products через кнопку Remove
    @Test
    public void positiveDeleteProductsToCart() {
        logger.info("Позитивный тест на удаление товара из корзины со страницы Products page начался...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword())
                .addOrDeleteProduct("Sauce Labs Backpack", true)
                .addOrDeleteProduct("Sauce Labs Bolt T-Shirt", false);

        Assert.assertEquals(productsPage.labelCart.getText(), "1");
        logger.info("Позитивный тест на удаление товара из корзины со страницы Products page закончился.");
    }

    // Проверка на удаление товара с карзины со страницы Products через кнопку Remove другим способом
    @Test(expectedExceptions = TimeoutException.class)
    public void positiveDeleteProductsToCart2() {
        logger.info("Позитивный тест на удаление товара из корзины со страницы Products page начался...");
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword())
                .addOrDeleteProduct("Sauce Labs Backpack", false);

        waits.waitForVisibilityElement(productsPage.labelCart);
        logger.info("Позитивный тест на удаление товара из корзины со страницы Products page закончился.");
    }

    //___________________________________________Оплата________________________________________
    @Test
    public void positiveChekOut() {
        logger.info("Позитивный тест оплату начался...");
        CheckOutCompletePage checkOutCompletePage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(userLoginBuilder.getLogin(), userLoginBuilder.getPassword())
                .addOrDeleteProduct("Sauce Labs Fleece Jacket", true)
                .clickCartButton()
                .clickCheckOutButton()
                .sendAttributeForCheckOut(customerBuilder.getFirstName(), customerBuilder.getLastName(), customerBuilder.getZipPostalCode())
                .clickContinueButton()
                .clickButtonFinish();

        Assert.assertEquals(checkOutCompletePage.completeText.getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        logger.info("Позитивный тест оплату закончился.");
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

