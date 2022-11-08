package baseEntities;

import core.BrowserService;
import core.ReadProperties;
import core.Waits;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import utils.Listener;

@Listeners(Listener.class)
public class BaseTest {
    protected  final Logger logger = LogManager.getLogger(this);
    public WebDriver driver;
    protected ReadProperties properties;
    protected Waits waits;

    //перед всеми тестами
    @BeforeTest
    public void setupTest(){

        properties = new ReadProperties();
    }

    //перед каждым тестом
    @BeforeMethod
    public void setupMethod() {
        driver = new BrowserService().getDriver();
        waits= new Waits(driver, properties.getTimeout());
    }

    @AfterMethod
    public void tearDownMethod() {
        driver.quit();
    }
}
