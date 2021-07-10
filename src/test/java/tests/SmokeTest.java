package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import steps.AddtoCartStep;
import steps.CheckOutStep;
import steps.LoginStep;

public class SmokeTest extends BaseTest {


    //1. Проверка на вход в систему стандарный пользователь
    @Test
    public void positiveLoginTestFirstUser() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());

        Assert.assertEquals(new ProductsPage(driver, true).getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    //2. Заваленный тест на locked_out_user
    @Test
    public void positiveLoginTestSecondUser() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.login("locked_out_user", "secret_sauce");

        Assert.assertEquals(new ProductsPage(driver, true).getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    //3. Проверка на вход в систему problem_user
    @Test
    public void positiveLoginTestThirdUser() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.login("problem_user", "secret_sauce");

        //ProductsPage productsPage = new ProductsPage(driver, true);
        Assert.assertEquals(new ProductsPage(driver,true).getTitleText(), "PRODUCTS", "Страница Products не открылась");
    }

    //4. Проверка на ошибку при некорректном логине и пароле
    @Test
    public void negativeLoginTest() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.login("fjskf", "1111");

        //страницу надо заново инициализировать, где нужно передать драйвер
        //и false, потому что мы говорим, что страница уже открыта
        Assert.assertEquals(new LoginPage(driver,false).getErrorLabel().getText(), "Epic sadface: Username and password do not match any user in this service");
    }


    //5. Проверка на добавление товара в корзину с Products Page
    @Test
    public void positiveAddProductsToCart() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());

        AddtoCartStep addtoCartStep = new AddtoCartStep(driver);
        addtoCartStep.addToCartProduct("Sauce Labs Bolt T-Shirt");

        Assert.assertEquals(new ProductsPage(driver,true).getLabelCartNull().getText(), "1");
    }


    //6. Проверка на добавление в корзину со страницы одного товара
    @Test
    public void positiveAddProductsToCart2() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());

        AddtoCartStep addtoCartStep = new AddtoCartStep(driver);
        addtoCartStep.addToCartProductBySomeProductPage("Sauce Labs Onesie");

      Assert.assertEquals(new SomeProductPage(driver,true).getLabelCartNull().getText(), "1");
    }

    //7. Проверка на удаление товара с карзины со страницы Products через кнопку Remove
    @Test
    public void positiveDeleteProductsToCart() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());

        AddtoCartStep addtoCartStep = new AddtoCartStep(driver);
        addtoCartStep.addToCartProduct("Sauce Labs Bolt T-Shirt");
        addtoCartStep.addToCartProduct("Sauce Labs Backpack");
        addtoCartStep.addToCartProduct("Sauce Labs Backpack");
        Assert.assertEquals(new ProductsPage(driver, true).getLabelCartNull().getText(), "1");
    }

    //8. Проверка на удаление товара с карзины со страницы Products через кнопку Remove другим способом
    @Test
    public void positiveDeleteProductsToCart2() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());

        AddtoCartStep addtoCartStep = new AddtoCartStep(driver);
        addtoCartStep.addToCartProduct("Sauce Labs Backpack");
        addtoCartStep.addToCartProduct("Sauce Labs Backpack");

        Assert.assertThrows(org.openqa.selenium.NoSuchElementException.class, new ProductsPage(driver, true)::getLabelCartNull);
    }

    //9. Тест на оплату
    @Test
    public void positiveChekOut() {
        LoginStep loginStep =new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());

        CheckOutStep checkOutStep = new CheckOutStep(driver);
        checkOutStep.checkOut("Sauce Labs Fleece Jacket");

        Assert.assertEquals(new CheckOutCompletePage(driver,true).getCompleteTextBy().getText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");

    }


}

