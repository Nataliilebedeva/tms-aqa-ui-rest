package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class ProductsPage extends BasePage {

    // private WebElement webElement;
    private final static String endpoint = "inventory.html";

    @FindBy(className = "title")
    public WebElement titleLabel;

    @FindBy(className = "shopping_cart_badge")
    public WebElement labelCart;

    @FindBy(className = "shopping_cart_link")
    public static WebElement cartButton;

    @FindBys({
            @FindBy(className = "inventory_item_name")
    })
    public List<WebElement> products;


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
            return titleLabel.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public String getTitleText() {
        return titleLabel.getText();
    }

    public WebElement returnWebElement(String productName) {
        boolean isFound = false;
        for (WebElement element : products) {
            String textValue = element.getText();
            if (textValue.equalsIgnoreCase(productName)) {
                // this.webElement = element;
                isFound = true;
                return element;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException("Опции с таким текстом нет");
        }
        return null;
    }

    //нажатие на название продукта и переход на описание продукта
    public SomeProductPage addToCartBySomeProductPage(String productName) {
        returnWebElement(productName).click();
        return new SomeProductPage(driver,false);
    }

    //нажатие на кнопку add to cart и добавление в карзину
    public void addToCart(String productName) {
        returnWebElement(productName).findElement(By.xpath("./ancestor::div[@class = 'inventory_item_description']//button")).click();
    }

    /***
     *
     * @param productName
     * @param addOrDelete: true - добавить, false - удалить
     * @return
     */
    public ProductsPage addOrDeleteProduct(String productName, Boolean addOrDelete) {
        if (addOrDelete == true) {
            addToCart(productName);
        } else {
            for (int i = 0; i < 2; i++) {
                addToCart(productName);
            }
        }
        return new ProductsPage(driver,false);
    }

    public YourCartPage clickCartButton() {
        cartButton.click();
        return new YourCartPage(driver, true);
    }


}