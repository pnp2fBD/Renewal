package cucumber.stepdefination.runner;

import java.io.IOException;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.ssp.support.BaseCucumberTest;
import com.ssp.support.CucumberLog;
import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/html/", "json:target/json/cc.json","rerun:target/rerun.txt"},
    features = "src/main/java/resources", tags = "@NCD", monochrome = true, glue = "cucumber.stepdefination.runner")

public class TestRunnerNCD extends AbstractTestNGCucumberTests{

  BaseCucumberTest cucumberTest = new BaseCucumberTest();
  public static String website_SS;
  public static String website_EC;
  public static String website_UIRefresh;
  public static String browser;

  @BeforeSuite
  public void runBeforeMethod() {
    cucumberTest.beforeSuite();
  }

  @BeforeMethod
  public void init(ITestContext context) throws IOException {
    website_SS = System.getProperty("webSite_SS") != null ? System.getProperty("webSite_SS")
        : context.getCurrentXmlTest().getParameter("webSite_SS");

    System.out.println(
        "Website Self Service Link-: " + context.getCurrentXmlTest().getParameter("webSite_SS"));

    website_EC = System.getProperty("website_EC") != null ? System.getProperty("website_EC")
        : context.getCurrentXmlTest().getParameter("website_EC");

    System.out
        .println("Website EC Link-: " + context.getCurrentXmlTest().getParameter("website_EC"));

    website_UIRefresh =
        System.getProperty("website_UIRefresh") != null ? System.getProperty("website_UIRefresh")
            : context.getCurrentXmlTest().getParameter("website_UIRefresh");

    System.out.println("Website UI Refresh  Link -: "
        + context.getCurrentXmlTest().getParameter("website_UIRefresh"));
    
    browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
        .getParameter("browserName");
  }

  @After
  public void runAfterTest() {
    WebDriver driver = NcdStepDefination.getDriver();
    if (driver != null){
      driver.quit();
    }     
    CucumberLog.endTestCase(RenewalStepDefination.getExtentedReport());
  }


  @AfterSuite
  public void runAfterMethod() {
    cucumberTest.afterSuite();

  }
}
