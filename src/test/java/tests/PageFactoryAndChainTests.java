package tests;

import baseEntities.BaseTest;
import io.qameta.allure.*;
import models.User;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import steps.AddtoCartStep;
import steps.CheckOutStep;
import steps.LoginStep;

import javax.jws.soap.SOAPBinding;

public class PageFactoryAndChainTests extends BaseTest {

    //___________________________________________Логирование________________________________________________
    //1. Проверка на вход в систему стандарный пользователь
    @Link(value = "ссылка", url = "https://docs.qameta.io/allure/#_testng")
    @Link(name = "#_testng", type = "mylink")
    @TmsLink("12")
    @Issue("13")
    @Description("Тест на логирование аттрибутами,соответсвующими standard_user")
    @Test(description = "Позитивный тест на вход в систему №1")
    public void positiveLoginTestFirstUser() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(properties.getUsername(), properties.getPassword());

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    //2. Заваленный тест на locked_out_user
    @Description("Тест на логирование аттрибутами,соответсвующими locked_out_user")
    @Test(description = "Позитивный тест на вход в систему №2")
    public void positiveLoginTestSecondUser() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute("locked_out_user", "secret_sauce");

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    //3. Проверка на вход в систему problem_user

    @Description("Тест на логирование аттрибутами,соответсвующими problem_user")
    @Test(description = "Позитивный тест на вход в систему №3")
    public void positiveLoginTestThirdUser() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute("problem_user", "secret_sauce");

        Assert.assertEquals(productsPage.getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    //4. Проверка на ошибку при некорректном логине и пароле
    @Description("Тест на логирование некорректными атрибутами")
    @Test(description = "Негативный тест на вход в систему")
    public void negativeLoginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, true).loginWithIncorrectAttribute();

        Assert.assertEquals(loginPage.errorLabel.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    //___________________________________________Добавление товара в корзину________________________________________
    //5. Проверка на добавление товара в корзину с Products Page

    @Description("Тест на добавление товара в корзину со страницы Products Page")
    @Test(description = "Позитивный тест на добавление товара в корзину №1")
    public void positiveAddProductsToCart() {
        ProductsPage productsPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(properties.getUsername(), properties.getPassword())
                .addOrDeleteProduct("Sauce Labs Bolt T-Shirt", true);

        Assert.assertEquals(productsPage.labelCart.getText(), "1");
    }


    //6. Проверка на добавление в корзину со страницы одного товара
    @Description("Тест на добавление товара в корзину со страницы какого-либо товара")
    @Test(description = "Позитивный тест на добавление товара в корзину №2")
    public void positiveAddProductsToCart2() {
        SomeProductPage someProductPage = new LoginPage(driver, true)
                .loginWithCorrectAttribute(properties.getUsername(), properties.getPassword())
                .addToCartBySomeProductPage("Sauce Labs Onesie")
                .clickAddToCartButton();

        Assert.assertEquals(someProductPage.labelCart.getText(), "1");
    }


    //___________________________________________Удаление товара из корзины________________________________________

    //7. Проверка на удаление товара с карзины со страницы Products через кнопку Remove

    @Description("Позитивный тест на удаление товара с карзины со страницы Products через кнопку Remove")
    @Test(description = "Позитивный тест на удаление товара с карзины №1")
    public void positiveDeleteProductsToCart() {
        ProductsPage productsPage = new LoginStep(driver)
                .loginWithCorrectAttribute(properties.getUsername(), properties.getPassword())
                .addOrDeleteProduct("Sauce Labs Backpack", true)
                .addOrDeleteProduct("Sauce Labs Bolt T-Shirt", false);

        Assert.assertEquals(productsPage.labelCart.getText(), "1");
    }

    //8. Проверка на удаление товара с карзины со страницы Products через кнопку Remove другим способом
    @Description("Позитивный тест на удаление товара с карзины со страницы Products через кнопку Remove другим способом")
    @Test(description = "Позитивный тест на удаление товара с карзины №2", expectedExceptions = TimeoutException.class)
    public void positiveDeleteProductsToCart2() throws InterruptedException {
        ProductsPage productsPage = new LoginStep(driver)
                .loginWithCorrectAttribute(properties.getUsername(), properties.getPassword())
                .addOrDeleteProduct("Sauce Labs Backpack", false);

        waits.waitForVisibilityElement(productsPage.labelCart);
    }

    //___________________________________________Оплата________________________________________

    //9. Тест на оплату
    @Test(description = "Позитивный тест оплату")
    public void positiveChekOut() {
        CheckOutCompletePage checkOutCompletePage = new LoginStep(driver)
        .loginWithCorrectAttribute(properties.getUsername(), properties.getPassword())
                .addOrDeleteProduct("Sauce Labs Fleece Jacket",true)
                .clickCartButton()
                .clickCheckOutButton()
                .sendAttributeForCheckOut("Nat","Leb","1234")
                .clickContinueButton()
                .clickButtonFinish();

        Assert.assertEquals(checkOutCompletePage.completeText.getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }
}

