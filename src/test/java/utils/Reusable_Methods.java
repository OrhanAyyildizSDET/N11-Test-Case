package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Reusable_Methods {

    public static WebElement waitForVisibility(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static String getScreenshot(String name) throws IOException {

        // naming the screenshot with the current date to avoid duplication
        //aldığımız screenshot zaman eklenerek isimlendirilir.
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        // full path to the screenshot location
        //screenshoot nereye kaydedileceği string olarak tanımlanır.
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);

        // save the screenshot to the path given
        //Aldığımız screenshoot'ı kaydediyoruz
        FileUtils.copyFile(source, finalDestination);
        return target;
    }

    //Here we take screenshoot as byte type and we can directly use it
    //burada screenshoot'ı byte olarak return yapıyoruz ve direk olarak kullanabiliyoruz.
    public static byte[] getByteScreenshot(){
        try {
            File src = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(src);
            return fileContent;
        }catch (Exception e){
            return null;
        }
    }
}
