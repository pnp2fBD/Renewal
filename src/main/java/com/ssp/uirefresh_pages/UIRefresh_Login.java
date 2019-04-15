/**
 * 
 */
package com.ssp.uirefresh_pages;

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
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;
import com.ssp.uxp_pages.HomePage;
import org.openqa.selenium.support.ui.LoadableComponent;

public class UIRefresh_Login extends LoadableComponent<UIRefresh_Login> {

  private final WebDriver driver;
  private ExtentTest extentedReport;
  private String sspURL;
  private boolean isPageLoaded;
  public String spinner = "div.spinning-on-load-bg-table-active";
  public final String ERROR_MSG_LOGIN =
      "We do not recognise your details. Please re-enter your credentials";
  /**********************************************************************************************
   ********************************* WebElements of UIRefresh Login Page **********************************
   **********************************************************************************************/

  @FindBy(css = "#Username")
  WebElement txtUserName;

  @FindBy(css = "#Password")
  WebElement txtPassWord;

  @FindBy(css = "#userLogin > div.text-center > button")
  WebElement btnLogIn;

  public UIRefresh_Login(WebDriver driver, String url, ExtentTest report) {
    this(driver, report);
    sspURL = url;
  }

  /**
   * 
   * Constructor class for Login page Here we initializing the driver for page factory objects. For
   * ajax element waiting time has added while initialization
   * 
   * @param driver : Webdriver
   */
  public UIRefresh_Login(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentedReport = report;
    PageFactory.initElements(driver, this);
  }

  @Override
  protected void load() {
    isPageLoaded = true;
    driver.get(sspURL);
    WaitUtils.waitForPageLoad(driver);
  }

  @Override
  protected void isLoaded() throws Error {
    WaitUtils.waitForPageLoad(driver);
    if (!isPageLoaded) {
      Assert.fail();
    }
     if (isPageLoaded && !driver.getTitle().contains("SSP")) {
      Log.fail("SSP Login Page did not open up. Site might be down.", driver,extentedReport); }     
  }

  public UIRefresh_Dashboard loginToUIRefresh(String username, String password, boolean screenshot ,ExtentTest extentedReport) throws Exception {
    try {

      if (sspURL != null)
        Log.event("Launched UI Refresh site:: " + sspURL);
      
      Log.event("Logging in UI Refresh. : Entering credentials");
      enterUserName(username, extentedReport, screenshot);
      enterPassword(password, extentedReport, screenshot);
      clickBtnLogIn(extentedReport, screenshot);     
      return new UIRefresh_Dashboard(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while login to application : " + e);
    }
  }

  public void enterUserName(String userName, ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElement(driver, txtUserName);
      txtUserName.clear();
      txtUserName.sendKeys(userName);
      Log.message("Entered the UserName : " + userName, driver, extentedReport,screenshot);
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
  public void enterPassword(String pwd, ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      Log.event("Entering Password");
      WaitUtils.waitForElement(driver, txtPassWord);
      txtPassWord.clear();
      txtPassWord.sendKeys(pwd);
      Log.message("Entered the Password: " + pwd, driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while entering password : " + e);
    }
  }

  public void clickBtnLogIn(ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElementPresent(driver, btnLogIn, "Login button is not present");
      WaitUtils.waitForelementToBeClickable(driver, btnLogIn, "Login button is not clickable");
      GenericUtils.javaScriptExecutorToClick(driver, btnLogIn);
      WaitUtils.uiRefwaitForSpinner(driver);
      Log.message("Clicked LogIn button on login page ", driver, extentedReport,screenshot);     
    } catch (Exception e) {
      throw new Exception("Error while clicking 'Login' button: " + e);
    }
  }

}
