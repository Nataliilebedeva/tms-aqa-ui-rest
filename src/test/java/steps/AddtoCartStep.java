package steps;

import baseEntities.BaseStep;
import org.openqa.selenium.WebDriver;
import pages.ProductsPage;
import pages.SomeProductPage;

public class AddtoCartStep extends BaseStep {

    public AddtoCartStep(WebDriver driver) {
        super(driver);
    }

    public void addToCartProduct(String productName) {
        ProductsPage productsPage = new ProductsPage(driver,true);
        productsPage.addToCart(productName);
    }

    public void addToCartProductBySomeProductPage(String productName) {
        ProductsPage productsPage = new ProductsPage(driver,true);
        productsPage.addToCartBySomeProductPage(productName);
        SomeProductPage someProductPage = new SomeProductPage(driver, true);
        someProductPage.clickAddToCartButton();

    }
}
