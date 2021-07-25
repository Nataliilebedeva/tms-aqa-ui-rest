package baseEntities;

import core.BrowserService;
import core.ReadProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import utils.Listener;

@Listeners(Listener.class)
public class BaseTest {
    public WebDriver driver;
    protected ReadProperties properties;

    //перед всеми тестами
    @BeforeTest
    public void setupTest(){
      //  WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        //дублирование кода
        properties = new ReadProperties();
    }

    //перед каждым тестом
    @BeforeMethod
    public void setupMethod() {
        // new и перед каждым методом, потому что будем рассматривать параллельизацию методов
        driver = new BrowserService().getDriver();
    }

    @AfterMethod
    public void tearDownMethod() {
        driver.quit();
    }
}
