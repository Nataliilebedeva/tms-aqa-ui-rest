package core;


import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserService {
    private ReadProperties properties = new ReadProperties();
    //в зависимости от типа драйвера setup создает экземпляр драйвера нужного типа и вернет его
    // private DriverManagerType driverManagerType; -  не нужна, используем сразу WebDriverManager и указываем тип браузера
    private WebDriver driver;

    //конструктор, который будет в зависимости от какой-то настройки
    // будет создавать экземпляр того или иного браузера

    public BrowserService() {
        switch (properties.getBrowser().toLowerCase()) {
            case "chrome":
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized", "incognito", "disable-gpu");
                chromeOptions.setHeadless(properties.getHeadless());

                //headless: запуск без пользовательского интерфейса
                //disable-gpu отключение аппаратного ускорения графического процессора

                //для того, чтобы options использовать, передаем параметр в объект
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(properties.getHeadless());
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "ie":
                WebDriverManager.getInstance(DriverManagerType.IEXPLORER).setup();
                driver = new InternetExplorerDriver();
                break;
//Если совпадений не выявлено, то управление передаётся КодуВыбораПоУмолчанию
            default:
                throw new AssertionError("Данный браузер не поддерживается.");
        }
    }

    //драйвер проинициализировали, но мы не можем его вернуть, поэтому создаем getDriver,
    // который будет возвращать экземляр WebDriver


    public WebDriver getDriver() {
        return driver;
    }
}
