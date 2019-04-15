package com.ssp.uxp_SSPages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

public class YourPolicyDetailsHomeEmergency
    extends LoadableComponent<YourPolicyDetailsHomeEmergency> {

  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  private ExtentTest extentedReport;

  @FindBy(xpath = "//h2[contains(text(),'Home Emergency')]")
  WebElement heCoverHead;

  @FindBy(css = "button[title*='Back'][class*='action-button']")
  WebElement backBtn;

  @FindBy(css = "button[title*='Next']")
  WebElement nextBtn;
  
  @FindBy(css = "input[id*='ComponentHE_limit']")
  WebElement heCoverLmit;

  public YourPolicyDetailsHomeEmergency(WebDriver driver, ExtentTest extentedReport) {
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }

  @Override
  protected void isLoaded() {
    // TODO Auto-generated method stub
    if (!isPageLoaded) {
      Assert.fail();
    }
    (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class)
        .withMessage("Your Policy Details page did not open up."))
            .until(ExpectedConditions.visibilityOf(heCoverHead));
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {
    // TODO Auto-generated method stub
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }
  
  public void validateHECover(){
    
  }

  /**
   * To click Next button
   * 
   * @param screenshot
   * @throws Exception
   */
  public YourPolicyDetailsLegalExpense clickNextBtn(boolean screenshot) throws Exception {

    try {
      WaitUtils.waitForElementPresent(driver, nextBtn, "Next button is not present.");
      WaitUtils.waitForelementToBeClickable(driver, nextBtn, "Next button is not clickable.");
      GenericUtils.javaScriptExecutorToClick(driver, nextBtn);
      WaitUtils.waitForSpinner(driver);
      Log.message("User has clicked next button and navigated.", driver, extentedReport,
          screenshot);;
      return new YourPolicyDetailsLegalExpense(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while clicking Next Button. "+e);
    }
  }

  /**
   * To click Back button
   * 
   * @param screenshot
   * @throws Exception
   */
  public YourPolicyDetailsTechnologyCover clickBacktBtn(boolean screenshot) throws Exception {

    try {
      WaitUtils.waitForElementPresent(driver, backBtn, "Back button is not present.");
      GenericUtils.javaScriptExecutorToClick(driver, backBtn);
      WaitUtils.waitForSpinner(driver);
      Log.message("User has clicked back button and navigated.", driver, extentedReport,
          screenshot);
      return new YourPolicyDetailsTechnologyCover(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while clicking Back Button.");
    }

  }

}
