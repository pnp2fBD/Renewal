  package com.ssp.uxp_SSPages;

import java.util.HashMap;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;

public class YourPolicyDetailsContactCover extends LoadableComponent<YourPolicyDetailsContactCover> {

  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  private ExtentTest extentedReport;

  @FindBy(xpath = "//h2[contains(text(),'Policy Details')]")
  WebElement policyDetailsHead;

  @FindBy(css = "input[name*='POLICYHOLDER_READONLY']")
  WebElement mainPolicyHolder;

  @FindBy(css = "input[name*='POLICYURN_READONLY']")
  WebElement policyNumber;

  @FindBy(css = "select[name*='COVERTYPE_READONLY']")
  WebElement coverType;

  @FindBy(css = "select[name*='BUILDINGSCONTINUOUSINSURANCE_READONLY']")
  WebElement buildingCI;

  @FindBy(css = "sselect[name*='CONTENTSCONTINUOUSINSURANCE_READONLY']")
  WebElement contentsCI;

  @FindBy(css = "select[name*='PREVIOUSCLAIMSNUMBER_READONLY']")
  WebElement noOfClaims;

  @FindBy(css = "button[title*='Next']")
  WebElement nextBtn;

  public YourPolicyDetailsContactCover(WebDriver driver, ExtentTest extentedReport) {
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
            .until(ExpectedConditions.visibilityOf(policyDetailsHead));
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {
    // TODO Auto-generated method stub
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

  /**
   * To validate policy details section
   * 
   * @param testData
   * @param policyURN
   * @param screenshot
   * @throws Exception
   */
  public void validatePolicyDetailsSection(HashMap<String, String> testData, String policyURN,
      boolean screenshot) throws Exception {

    boolean status = false;
    StringBuffer message = new StringBuffer("Failing on- ");
    String title = testData.get("Title");
    String firstName = testData.get("First Name");
    String lastName = testData.get("Last Name");

    String mainPolicyHolderName = title + " " + firstName + " " + lastName;

    try {
      if (!mainPolicyHolder.isEnabled()) {
        if (mainPolicyHolder.getAttribute("value").equals(mainPolicyHolderName)) {
          status = true;
        } else
          message.append("Policy holder name is not correct: ");
      } else
        message.append("Main policy holder textbox is enabled: ");

      if (policyNumber.getAttribute("value").equals(policyURN)) {
        status = true;
      } else
        message.append("Policy Number is not correct: ");

      Log.softAssertThat(status, "The policy details section is successfully validated",
          "The policy details section is not successfully validated: "+message, driver, extentedReport,
          screenshot);

    } catch (Exception e) {
      throw new Exception("Error while validating Policy detalis section. " + e);
    }
  }


  /**
   * To validate cover type section
   * 
   * @param testData
   * @param screenshot
   * @throws Exception
   */
  public void validateCoverTypeSection(HashMap<String, String> testData, boolean screenshot)
      throws Exception {

    boolean status = false;
    StringBuffer message = new StringBuffer("Failing on- ");
    String cover = testData.get("Cover");

    Select coverTypeDropDown = new Select(coverType);

    try {
      if (coverTypeDropDown.getFirstSelectedOption().getText().equals(cover)) {
        status = true;
      } else
        message.append("Incorrect Cover Type value in dropdown: ");

      Log.softAssertThat(status, "The cover type section is successfully validated",
          "The cover type section is not successfully validated- "+message, driver, extentedReport,
          screenshot);

    } catch (Exception e) {
      throw new Exception("Error while validating cover type section. " + e);
    }
  }

  /**
   * Click Next button
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public YourPolicyDetailsProperty clickNextBtn(boolean screenshot) throws Exception {

    try {
      WaitUtils.waitForElementPresent(driver, nextBtn, "Next button is not present.");
      WaitUtils.waitForelementToBeClickable(driver, nextBtn, "Next button is not clickable.");
      nextBtn.click();
      WaitUtils.waitForSpinner(driver);
      Log.message("User has clicked next button and navigated from YourPolicyDetailsContactCover.", driver, extentedReport,
          screenshot);
      return new YourPolicyDetailsProperty(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while clicking Next Button. "+e);
    }

  }

}
