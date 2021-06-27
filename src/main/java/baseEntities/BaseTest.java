package baseEntities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setupTest(){
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
    }

    @BeforeMethod
    public void setupMethod() {
        // WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDownMethod() {
        driver.quit();
    }
}
