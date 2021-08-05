package steps;

import baseEntities.BaseStep;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import pages.ProductsPage;
import pages.SomeProductPage;

public class AddtoCartStep extends BaseStep {

    public AddtoCartStep(WebDriver driver) {
        super(driver);
    }

    @Step("Добавление товара '{productName}' со главной страницы с товарами Product Page")
    public ProductsPage addToCartProduct(String productName) {
        ProductsPage productsPage = new ProductsPage(driver, true);
        productsPage.addToCart(productName);
        return productsPage;
    }

    @Step("Добавление товара '{productName}' со страницы товара '{productName}'")
    public SomeProductPage addToCartProductBySomeProductPage(String productName) {
        ProductsPage productsPage = new ProductsPage(driver, true);
        productsPage.addToCartBySomeProductPage(productName);
        SomeProductPage someProductPage = new SomeProductPage(driver, true);
        someProductPage.clickAddToCartButton();
        return someProductPage;

    }

    public void addOrDeleteProduct(String productName, Boolean addOrDelete) {
        ProductsPage productsPage = new ProductsPage(driver, true);
        if (addOrDelete == true) {
            productsPage.addToCart(productName);
        } else {
            for (int i = 0; i < 2; i++) {
                productsPage.addToCart(productName);
            }
        }
    }
}
