package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/default-cucumber-reports.html",  //cucumber reprots oluşturulan klasör
                "rerun:target/failedRerun.txt"
        },
        features = "./src/test/resources/features",
        glue = {"stepDefinitions","hooks"},
        tags = "@n11Favorite",
        dryRun = false
)
public class Runner {
}