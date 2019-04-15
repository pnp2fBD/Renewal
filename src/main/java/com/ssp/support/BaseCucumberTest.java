package com.ssp.support;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.testng.Reporter;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.NetworkMode;
import com.ssp.utils.DataUtils;
import com.ssp.utils.FileUtils;


public class BaseCucumberTest {

  public static ExtentReports extent;

  public void beforeSuite() {
    extent = new ExtentReports("target/surefire-reports/TestReport.html", true,
        DisplayOrder.OLDEST_FIRST, NetworkMode.ONLINE);
    Reporter.getCurrentTestResult().getTestContext().getSuite().setAttribute("policy_number", "");
  }

  /*
   * After suite will be responsible to close the report properly at the end You can have another
   * afterSuite as well in the derived class and this one will be called in the end making it the
   * last method to be called in test execution
   */
  public void afterSuite() {
    extent.flush();
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    File reportFolder = new File("test-reports/TestReports");
    File reportSourceFile = new File("target/surefire-reports/TestReport.html");
    File reportScreenshotSourceFile = new File("target/surefire-reports/ScreenShot");
    File reportScreenshotFile = new File("test-output/ScreenShot");
    try {
      reportFolder = FileUtils.createReportFolder(reportFolder, env);
    } catch (IOException e2) {
      e2.printStackTrace();
    }

    String reportDestFolder = reportFolder + File.separator + "TestReport.html";
    File reportDestFile = new File(reportDestFolder);
    String screenshotFolder = reportFolder + File.separator + "ScreenShot";
    File screenshotDestFolder = new File(screenshotFolder);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }

    try {
      FileUtils.copyFile(reportSourceFile, reportDestFile);
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      FileUtils.copyFolderDelExist(reportScreenshotFile, reportScreenshotSourceFile);
      FileUtils.copyFolder(reportScreenshotFile, screenshotDestFolder);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static ExtentTest addTestInfo(String testCaseId, String testDesc) {
    String browserwithos = null;
    String test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getName();
    System.out.println("test is - " + test);
    String browsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
        .getParameter("browser");
    System.out.println("Browser- " + browsername);
    String browserversion = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
        .getParameter("browser_version");
    System.out.println("browserversion- " + browserversion);
    String os = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
        .getParameter("os").substring(0, 1);
    System.out.println("OS Name - " + os);
    String osversion = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
        .getParameter("os_version");
    System.out.println("osversion - " + osversion);
    browserwithos = os + osversion + "_" + browsername + browserversion;
    System.out.println("browserwithos - " + browserwithos);
    return CucumberLog.testCaseInfo(testCaseId + " [" + test + "]",
        testCaseId + " - " + testDesc + " [" + browserwithos + "]", test);
  }

  public static HashMap<String, String> getTestData(String prefixClass, String testcaseId) {
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = prefixClass + env;
    return DataUtils.testDatabyID(testcaseId, className);
  }
  
  public static HashMap<String, String> getTestDataWithoutID(String prefixClass) {
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = prefixClass + env;
    return DataUtils.testDataNotbyID(className);
  }
  
  public static HashMap<String, HashMap<String,String>> getTestDataWithStringKeyValue(String prefixClass) {
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String className = prefixClass + env;
    return DataUtils.testDatabyStringKey(className);
  }
}

