package runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/html-report.html",
        },
        tags = "@RegisterUser or @LoginUser or @ContactUs or @PlaceOrder or @DownloadInvoice",
        monochrome = false,
        publish = true,
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
