package com.ssp.uxp_SSPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.support.StopWatch;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

public class CustomerSigninPage extends LoadableComponent<CustomerSigninPage> {

  private final WebDriver driver;
  private ExtentTest extentedReport;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  // Customer Sign in

  @FindBy(css = "[title='Customer Sign in']")
  WebElement titleAcceptance;
  @FindBy(css = "#C2__email_address")
  WebElement txtUserName;

  @FindBy(css = "#C2__password")
  WebElement txtPassWord;


  @FindBy(css = "#C2__sign_in")
  WebElement btnSignIn;

  public CustomerSigninPage(WebDriver driver, ExtentTest extentedReport) {
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }

  @Override
  protected void isLoaded() {

    if (!isPageLoaded) {
      Assert.fail();
    }
    if (!isPageLoaded && !driver.getTitle().contains("Customer Sign in")) {
      Log.fail("Customer Sign in  Page of Self Service did not open up. Site might be down.",
          driver, extentedReport);
    }
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

  /**
   * To enter user name
   * 
   * @param userName
   * @param extentedReport
   * @throws Exception
   * 
   */
  public void enterUserEmail(String userName, ExtentTest extentedReport) throws Exception {
    try {
      WaitUtils.waitForElement(driver, txtUserName);
      txtUserName.clear();
      txtUserName.sendKeys(userName);
      Log.message("Entered the UserName : " + userName, driver, extentedReport);
    } catch (Exception e) {
      throw new Exception("Error while entering Username : " + e);
    }
  }

  /**
   * To Enter password
   * 
   * @param pwd
   * @param extentedReport
   * @throws Exception
   * 
   */
  public void enterPassword(String pwd, ExtentTest extentedReport) throws Exception {
    try {
      WaitUtils.waitForElement(driver, txtPassWord);
      txtPassWord.clear();
      txtPassWord.sendKeys(pwd);
      Log.message("Entered the Password: " + pwd, driver, extentedReport);
    } catch (Exception e) {
      throw new Exception("Error while entering password : " + e);
    }
  }

  /**
   * Click signIn button on login page
   * 
   * @param extentedReport
   * @throws Exception
   * 
   */
  public void clickBtnSignIn() throws Exception {
    try {
      final long startTime = StopWatch.startTime();
      WaitUtils.waitForElement(driver, btnSignIn);
      GenericUtils.javaScriptExecutorToClick(driver, btnSignIn);
      // btnSignIn.click();
      WaitUtils.waitForSpinner(driver);
      Log.message("Clicked signIn button on login page ", driver, extentedReport);
      Log.event("Clicked signIn button on login page", StopWatch.elapsedTime(startTime));
    } catch (Exception e) {
      throw new Exception("Error while clicking signin button : " + e);
    }

  }

  /**
   * Login to SSP
   * 
   * @param username
   * @param password
   * @param screenshot
   * @return
   * @throws Exception
   */
  public SelfServiceCustomerDashboard loginToSSPCustomerSelfService(String username,
      String password, ExtentTest extentedReport, boolean screenshot) throws Exception {
    try { 
      enterUserEmail(username, extentedReport);
      enterPassword(password, extentedReport);
      Log.pass("User has successfully entered username and password", driver, extentedReport, screenshot);
      clickBtnSignIn();
      Log.message("User has input the user details and clicked sign in button", driver, extentedReport, screenshot);
      return new SelfServiceCustomerDashboard(driver).get();
    } catch (Exception e) {
      throw new Exception("Error while logging into the application : " + e);
    }
  }

}
