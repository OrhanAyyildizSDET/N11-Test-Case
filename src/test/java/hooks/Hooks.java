package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.ConfigReader;
import utils.Driver;

public class Hooks {

    //Here before every scenario user goes the url which settled in application.properties
    //Burada her senaryodan önce kullanıcı application.properties'de yer alan url'ye gider
    @Before(order = 1,value = "@login")
    public void navigateToHomePage(){
        Driver.getDriver().get(ConfigReader.getProperty("n11_base_url"));
    }

    //Here if scenario gives error it take the screenshot where it gives error
    //Burada senaryo hata verirse, hata verdiği yerde ekran görüntüsünü alır
    @After
    public void tearDown(Scenario scenario){

        if (scenario.isFailed()) {
            final byte[] screenshot=((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png","failed_screenshots");
        }

    }

    //We dont need to write Driver.close after every test case we need to add @close tag on the features
    //Her test caseden sonra Driver.close yazmamıza gerek yok, özelliklere @close tagı eklememiz yeterlidir.
    @After(value = "@close")
    public void closeBrowser(){

        Driver.closeDriver();

    }
}
