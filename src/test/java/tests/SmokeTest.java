package tests;

import baseEntities.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import steps.AddtoCartStep;
import steps.CheckOutStep;
import steps.LoginStep;

public class SmokeTest extends BaseTest {


    //1. Проверка на вход в систему стандарный пользователь

    @Link(value = "ссылка", url = "https://docs.qameta.io/allure/#_testng")
    @Link(name = "#_testng",type = "mylink")
    @TmsLink("12")
    @Issue("13")
    @Description("Тест на логирование аттрибутами,соответсвующими standard_user")
    @Test (description="Позитивный тест на вход в систему №1")
    public void positiveLoginTestFirstUser() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.loginWithCorrectAttribute(properties.getUsername(), properties.getPassword());

        Assert.assertEquals(new ProductsPage(driver, true).getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    //2. Заваленный тест на locked_out_user
    @Link(value = "ссылка", url = "https://docs.qameta.io/allure/#_testng")
    @Link(name = "#_testng",type = "mylink")
    @TmsLink("12")
    @Issue("13")
    @Description("Тест на логирование аттрибутами,соответсвующими locked_out_user")
    @Test (description="Позитивный тест на вход в систему №2")
    public void positiveLoginTestSecondUser() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.loginWithCorrectAttribute("locked_out_user", "secret_sauce");

        Assert.assertEquals(new ProductsPage(driver, true).getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    //3. Проверка на вход в систему problem_user
    @Link(value = "ссылка", url = "https://docs.qameta.io/allure/#_testng")
    @Link(name = "#_testng",type = "mylink")
    @TmsLink("12")
    @Issue("13")
    @Description("Тест на логирование аттрибутами,соответсвующими problem_user")
    @Test (description="Позитивный тест на вход в систему №3")
    public void positiveLoginTestThirdUser() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.loginWithCorrectAttribute("problem_user", "secret_sauce");

        Assert.assertEquals(new ProductsPage(driver,true).getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    //4. Проверка на ошибку при некорректном логине и пароле
    @Link(value = "ссылка", url = "https://docs.qameta.io/allure/#_testng")
    @Link(name = "#_testng",type = "mylink")
    @TmsLink("12")
    @Issue("13")
    @Description("Тест на логирование некорректными атрибутами")
    @Test (description="Негативный тест на вход в систему")
    public void negativeLoginTest() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.loginWithIncorrectAttribute();

        Assert.assertEquals(new LoginPage(driver,false).getErrorLabel().getText(), "Epic sadface: Username and password do not match any user in this service");
    }


    //5. Проверка на добавление товара в корзину с Products Page
    @Link(value = "ссылка", url = "https://docs.qameta.io/allure/#_testng")
    @Link(name = "#_testng",type = "mylink")
    @TmsLink("12")
    @Issue("13")
    @Description("Тест на добавление товара в корзину со страницы Products Page")
    @Test (description="Позитивный тест на добавление товара в корзину №1")
    public void positiveAddProductsToCart() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.loginWithCorrectAttribute(properties.getUsername(), properties.getPassword());

        AddtoCartStep addtoCartStep = new AddtoCartStep(driver);
        addtoCartStep.addToCartProduct("Sauce Labs Bolt T-Shirt");

        Assert.assertEquals(new ProductsPage(driver,true).getLabelCartNull().getText(), "1");
    }


    //6. Проверка на добавление в корзину со страницы одного товара
    @Link(value = "ссылка", url = "https://docs.qameta.io/allure/#_testng")
    @Link(name = "#_testng",type = "mylink")
    @TmsLink("12")
    @Issue("13")
    @Description("Тест на добавление товара в корзину со страницы какого-либо товара")
    @Test (description="Позитивный тест на добавление товара в корзину №2")
    public void positiveAddProductsToCart2() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.loginWithCorrectAttribute(properties.getUsername(), properties.getPassword());

        AddtoCartStep addtoCartStep = new AddtoCartStep(driver);
        addtoCartStep.addToCartProductBySomeProductPage("Sauce Labs Onesie");

      Assert.assertEquals(new SomeProductPage(driver,true).getLabelCartNull().getText(), "1");
    }

    //7. Проверка на удаление товара с карзины со страницы Products через кнопку Remove
    @Link(value = "ссылка", url = "https://docs.qameta.io/allure/#_testng")
    @Link(name = "#_testng",type = "mylink")
    @TmsLink("12")
    @Issue("13")
    @Description("Позитивный тест на удаление товара с карзины со страницы Products через кнопку Remove")
    @Test (description="Позитивный тест на удаление товара с карзины №1")
    public void positiveDeleteProductsToCart() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.loginWithCorrectAttribute(properties.getUsername(), properties.getPassword());

        AddtoCartStep addtoCartStep = new AddtoCartStep(driver);
        addtoCartStep.addToCartProduct("Sauce Labs Bolt T-Shirt");
        addtoCartStep.addToCartProduct("Sauce Labs Backpack");
        addtoCartStep.addToCartProduct("Sauce Labs Backpack");
        Assert.assertEquals(new ProductsPage(driver, true).getLabelCartNull().getText(), "1");
    }

    //8. Проверка на удаление товара с карзины со страницы Products через кнопку Remove другим способом
    @Link(value = "ссылка", url = "https://docs.qameta.io/allure/#_testng")
    @Link(name = "#_testng",type = "mylink")
    @TmsLink("12")
    @Issue("13")
    @Description("Позитивный тест на удаление товара с карзины со страницы Products через кнопку Remove другим способом")
    @Test(description="Позитивный тест на удаление товара с карзины №2")
    public void positiveDeleteProductsToCart2() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.loginWithCorrectAttribute(properties.getUsername(), properties.getPassword());

        AddtoCartStep addtoCartStep = new AddtoCartStep(driver);
        addtoCartStep.addToCartProduct("Sauce Labs Backpack");
        addtoCartStep.addToCartProduct("Sauce Labs Backpack");

        Assert.assertThrows(org.openqa.selenium.NoSuchElementException.class, new ProductsPage(driver, true)::getLabelCartNull);
    }

    //9. Тест на оплату
    @Link(value = "ссылка", url = "https://docs.qameta.io/allure/#_testng")
    @Link(name = "#_testng",type = "mylink")
    @TmsLink("12")
    @Issue("13")
    @Test (description="Позитивный тест оплату")
    public void positiveChekOut() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.loginWithCorrectAttribute(properties.getUsername(), properties.getPassword());

        AddtoCartStep addtoCartStep = new AddtoCartStep(driver);
        addtoCartStep.addToCartProduct("Sauce Labs Fleece Jacket");
        CheckOutStep checkOutStep = new CheckOutStep(driver);
        checkOutStep.moveToYouCartPage();
        checkOutStep.moveToCheckOutPage();
        checkOutStep.moveToCheckOverviewOutPageAndFinishCheckOut();

        Assert.assertEquals(new CheckOutCompletePage(driver,true).getCompleteTextBy().getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }
}

