package com.ssp.uxp_pages.UXP_BasicMotor;

import java.util.List;
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
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;

public class NewQuoteDriverPage extends LoadableComponent<NewQuoteDriverPage>{
  
  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  ExtentTest extentedReport;
  

  @FindBy(xpath = "//h1[text()='Driver']")
  private WebElement driverHeading;
  
  @FindBy(css = "span[id*='C2__ComponentPRD_ComponentPHD_ComponentDRV_Table__ComponentPRD_ComponentPHD_ComponentDRV_driverName_R']")
  private List<WebElement> driverNameListRecord;
  
  @FindBy(css = "button[title='Continue']")
  private WebElement continueBtn;
  
  @FindBy(css = "button[title='Back'][id='C2__BackCollection-ComponentPRD_ComponentPHD_ComponentDRV']")
  private WebElement backBtn;
  

  @Override
  protected void load() {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }
  @Override
  protected void isLoaded() throws Error {
    if (!isPageLoaded) {
      Assert.fail();
    }
    if (isPageLoaded && !driverHeading.isDisplayed()) {
      Log.fail("User is unable to navigate to New Quote Driver page", driver, extentedReport);
      Assert.fail("Correct page is not displayed");
    }
    uielement = new ElementLayer(driver);
    
  }
  
  public NewQuoteDriverPage(WebDriver driver, ExtentTest extentedReport){
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }
  
  /**
   * To validate driver record
   * 
   * @param driverName
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateDriverRecord(String driverName, WebDriver driver, ExtentTest extentedReport,
      boolean screenshot) throws Exception {
    boolean status = false;
    try {
      for (WebElement driverNameRecord : driverNameListRecord) {
        if (driverNameRecord.getText().trim().equals(driverName)) {
          status = true;
        }
      }
      Log.softAssertThat(status, "Driver record is displayed", "Driver record is not displayed",
          driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Unable to click continue on New Quote Driver page: " + e);
    }
  }
  
  /**
   * Click on continue
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public NewQuoteMedicalConditionsPage clickOnContinue(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnContinue method");
      WaitUtils.waitForElementPresent(driver, continueBtn, "Continue button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, continueBtn, "Continue button is not clickable");
      continueBtn.click();
      WaitUtils.waitForSpinner(driver);
      return new NewQuoteMedicalConditionsPage(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Unable to click continue on New Quote Driver page: "+e);
    }     
  }
  
  /**
   * Click on back
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public NewQuotePreviousLossesPage clickOnBack(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnBack method");
      WaitUtils.waitForElementPresent(driver, backBtn, "Back button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, backBtn, "Back button is not clickable");
      backBtn.click();
      WaitUtils.waitForSpinner(driver);
      return new NewQuotePreviousLossesPage(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Unable to click back on New Quote Driver page: "+e);
    }     
  }
  
}
