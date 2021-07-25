package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.ProductsPage;
import pages.SomeProductPage;

public class AddtoCartStep extends BaseStep {

    public AddtoCartStep(WebDriver driver) {
        super(driver);
    }

    @Step("Добавление товара '{productName}' со главной страницы с товарами Product Page")
    public void addToCartProduct(String productName) {
        ProductsPage productsPage = new ProductsPage(driver,true);
        productsPage.addToCart(productName);
    }

    @Step("Добавление товара '{productName}' со страницы товара '{productName}'")
    public void addToCartProductBySomeProductPage(String productName) {
        ProductsPage productsPage = new ProductsPage(driver,true);
        productsPage.addToCartBySomeProductPage(productName);
        SomeProductPage someProductPage = new SomeProductPage(driver, true);
        someProductPage.clickAddToCartButton();

    }
}
