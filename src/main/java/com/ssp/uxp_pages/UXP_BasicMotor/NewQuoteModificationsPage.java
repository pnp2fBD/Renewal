package com.ssp.uxp_pages.UXP_BasicMotor;

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

public class NewQuoteModificationsPage extends LoadableComponent<NewQuoteModificationsPage>{
  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  ExtentTest extentedReport;
  
  @FindBy(xpath = "//h1[text()='Modifications']")
  private WebElement modificationsHeading;
  
  @FindBy(css = "button[title='Continue']")
  private WebElement continueBtn;
  
  @FindBy(css = "button[title='Back'][id='C2__Back-ComponentPRD_ComponentPHD_ComponentVEH_ComponentMOD']")
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
    if (isPageLoaded && !modificationsHeading.isDisplayed()) {
      Log.fail("User is unable to navigate to New Quote Modifications page", driver, extentedReport);
      Assert.fail();
    }
    uielement = new ElementLayer(driver);
    
  }
  
  public NewQuoteModificationsPage(WebDriver driver, ExtentTest extentedReport){
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
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
  public NewQuoteDriverDetailsPage clickOnContinue(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnContinue method");
      WaitUtils.waitForElementPresent(driver, continueBtn, "Continue button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, continueBtn, "Continue button is not clickable");
      continueBtn.click();
      return new NewQuoteDriverDetailsPage(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Unable to click continue on New Quote Modifications page: "+e);
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
  public NewQuoteSecurityPage clickOnBack(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnBack method");
      WaitUtils.waitForElementPresent(driver, backBtn, "Back button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, backBtn, "Back button is not clickable");
      backBtn.click();
      return new NewQuoteSecurityPage(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Unable to click back on New Quote Modifications page: "+e);
    }     
  }

}
