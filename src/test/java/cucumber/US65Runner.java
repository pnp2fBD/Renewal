package cucumber;
/**
/* Author jheelum
*/

import java.io.IOException;
import org.junit.runner.RunWith;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.ssp.support.BaseCucumberTest;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/html/", "json:target/json/cc.json"},
   features = "src/test/java/resources",
   tags="@Renewal-userstory1", 
   monochrome = true,
   dryRun = false)
public class US65Runner {

}
