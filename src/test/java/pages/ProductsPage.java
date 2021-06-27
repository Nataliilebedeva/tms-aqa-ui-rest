package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage extends BasePage {
    //1. Селекторы
    //BY переменные
    private final static By title_label_By = By.className("title");
    private final static By add_to_cart_button = By.id("add-to-cart-sauce-labs-backpack");
    private final static By label_cart = By.className("shopping_cart_badge");
    private final static By add_to_cart_button_other_product = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private final static By remove_to_cart_button_other_product = By.id("remove-sauce-labs-bolt-t-shirt");
    private final static By cart_button = By.className("shopping_cart_link");
    private final static By product_button = By.xpath("//*[contains(text(), 'One')]");
    private final static By product_sort_container = By.className("product_sort_container");

    public ProductsPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }


    @Override
    protected void openPage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    @Override
    public boolean isPageOpened() {
        try {
            return getTitleLabel().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    //3. Getter
    public WebElement getTitleLabel() { return driver.findElement(title_label_By);}
    //от этого элемента мы должны получить текст
    public String getTitleText() { return getTitleLabel().getText();}

    //геттер для кнопки добавить в корзину
    public WebElement getAddToCartButton() { return driver.findElement(add_to_cart_button);}

    //геттер для значка на корзине
    public WebElement getLabelCartNull() { return driver.findElement(label_cart);}

    //геттер добавления еще одного товара
    public WebElement getAddToCartButtonOtherProduct() { return driver.findElement(add_to_cart_button_other_product);}

    //геттер для удаления товара
    public WebElement getRemoveToCartButtonOtherProduct() { return driver.findElement(remove_to_cart_button_other_product);}

    //геттер для нажатия на кнопку корзина
    public WebElement getCartButton() { return driver.findElement(cart_button);}

    //геттер для одного товара
    public WebElement getProductButton() { return driver.findElement(product_button);}

    //геттер для поля сортировки
    public WebElement getProductSortContainer() { return driver.findElement(product_sort_container);}

    //4 атомарные методы
    public void clickAddToCartButton() {
        getAddToCartButton().click();
    }

    public void clickAddToCartOtherProductButton() {
        getAddToCartButtonOtherProduct().click();
    }

    public void clickRemoveToCartOtherProductButton() {
        getRemoveToCartButtonOtherProduct().click();
    }

    public void clickCartButton() {
        getCartButton().click();
    }

    public void clickProductButton() {
        getProductButton().click();
    }

    public void clickSortOption() {
        Select productSortContainer = new Select(getProductSortContainer());
        productSortContainer.selectByVisibleText("Price (low to high)");
    }

}
