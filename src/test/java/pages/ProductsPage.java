package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    //переменная для ссылки
    private final static String endpoint = "inventory.html";

    private final static By title_label_By = By.className("title");
    private final static By label_cart = By.className("shopping_cart_badge");

    private final static By cart_button = By.className("shopping_cart_link");

    private final static String product_addToCart_button = "//*[text() = 'replace']/ancestor::div[@class = 'inventory_item_description']//button";

    private final static String some_product_page_button = "//*[text() = 'replace']";


    public ProductsPage(WebDriver driver, boolean openPageByURL) {
        super(driver, openPageByURL);
    }


    @Override
    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
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
    public String getTitleText() { return getTitleLabel().getText();}

    //геттер для значка на корзине
    public WebElement getLabelCartNull() { return driver.findElement(label_cart);}

    //геттер для нажатия на кнопку корзина
    public WebElement getCartButton() { return driver.findElement(cart_button);}

    //геттер для кнопки add to cart
    public WebElement getProductAddToCartButton (String productName) {
        return driver.findElement(By.xpath(product_addToCart_button.replace("replace",productName)));
    }

    //геттер для нажатия на название продукта
    public WebElement getSomeProductPageButton (String productName) {
        return driver.findElement(By.xpath(some_product_page_button.replace("replace",productName)));
    }


    //4 атомарные методы

    //нажатие на кнопку add to cart и добавление в карзину
    public void addToCart(String productName) { getProductAddToCartButton (productName).click(); }

    //нажатие на название продукта и переход на описание продукта
    public void addToCartBySomeProductPage(String productName) {
        getSomeProductPageButton(productName).click();
    }

    public void clickCartButton() {
        getCartButton().click();
    }




}
