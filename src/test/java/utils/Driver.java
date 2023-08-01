package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class Driver {
    private static WebDriver driver;
    private static final int timeout = 15;

    //I don't want to create and initialize the driver from others so ı declared Webdriver as private
    //Başkaları tarfından ve gereksiz yere instantiate edilmemesi için Webdriver private oldu.

    private Driver() {
    }

    //I initialize the driver to create a static method
    //Webdriver'i config properties'den hangi browser'i seçtiysem onunla atıyorum.
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser");
            if ("chrome".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if ("chrome-headless".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
            }
        }

        //Here we handle synchronization issue when pages loaded generally
        //Burada genel senkronizasyon hatalarını önlemek için belirli bir süre bekleme komutu giriyorum.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

        driver.manage().window().maximize();

        return driver;
    }

    public static void closeDriver() {
        //if the driver is pointing browser in application.properties file
        //driver null ise  browser ataması yapılıyor.
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
