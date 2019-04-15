package com.ssp.uxp_SSPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.support.StopWatch;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;


public class CustomerSelfService extends LoadableComponent<CustomerSelfService> {

  private final WebDriver driver;
  private ExtentTest extentedReport;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  private String sspSelfServiceURL;

  @FindBy(css = "button[title*='Sign in']:not([disabled='disabled'])")
  WebElement btnsigninlocator;


  public CustomerSelfService(WebDriver driver, String url, ExtentTest report) {

    this.driver = driver;
    this.extentedReport = report;
    sspSelfServiceURL = url;
    PageFactory.initElements(driver, this);
  }

  @Override
  protected void isLoaded() {

    if (!isPageLoaded) {
      Assert.fail();
    }
    if (!isPageLoaded && !driver.getTitle().contains("Customer SelfService")) {
      Log.fail("SSP CustomerSelfService  Page did not open up. Site might be down.", driver,
          extentedReport);
    }
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {
    isPageLoaded = true;
    driver.get(sspSelfServiceURL);
    WaitUtils.waitForPageLoad(driver);
  }

  public boolean verifysignin() throws Exception {
    WaitUtils.waitForPageLoad(driver);
    if (!WaitUtils.waitForElement(driver, btnsigninlocator))

      throw new Exception("Sign in button is not visible!");
    return true;
  }

  /**
   * Click signIn button on login page
   * 
   * @param extentedReport
   * @param driver
   * @return
   * @throws Exception
   */
  public CustomerSigninPage clickBtnSignIn(ExtentTest extentedReport, WebDriver driver) throws Exception {
    try {
      final long startTime = StopWatch.startTime();
      verifysignin();
      WaitUtils.waitForPageLoad(driver);
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", btnsigninlocator);
      // btnSignIn.click();
      WaitUtils.waitForSpinner(driver);
      Log.message("Clicked signIn button on login page ", driver, extentedReport);
      Log.event("Clicked signIn button on login page", StopWatch.elapsedTime(startTime));
      return new CustomerSigninPage(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while clicking signin button : " + e);
    }

  }

}
