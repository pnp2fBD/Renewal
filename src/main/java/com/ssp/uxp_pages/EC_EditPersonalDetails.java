/**
 * 
 */
package com.ssp.uxp_pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.support.StopWatch;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

/**
 * @author jheelum.dutta
 *
 */
public class EC_EditPersonalDetails extends LoadableComponent<EC_EditPersonalDetails> {

  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  private ExtentTest extentedReport;


  /*
   * Webelements
   */

  @FindBy(css = "[title='Edit Personal Details']")
  WebElement titleAcceptance;

  @FindBy(css = "#C2__C1__Suspend-Checking_0")
  WebElement checkbox_GDPRReason;


  @FindBy(css = "#C2__C1__BUT_C6487216B270AC9D626037")
  WebElement btn_save;

  /**
   * 
   * Constructor class for EC_EditPersonalDetails Page Here we initializing the driver for page
   * factory objects. For ajax element waiting time has added while initialization
   * 
   * @param driver : Webdriver
   */
  public EC_EditPersonalDetails(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentedReport = report;
    PageFactory.initElements(driver, this);
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void isLoaded() {

    if (!isPageLoaded) {
      Assert.fail();
    }

    (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class).withMessage("SIAAS Home page did not open up."))
            .until(ExpectedConditions.visibilityOf(titleAcceptance));

    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {

    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);

  }

  public void Suspend_Contact_GDPR_Right_to_Restrict_Checkbox(ExtentTest extentedReport)
      throws Exception {
    try {
      GenericUtils.scrollIntoView(driver, checkbox_GDPRReason);
      if (!checkbox_GDPRReason.isSelected()) {
        checkbox_GDPRReason.click();
        WaitUtils.waitForSpinner(driver);
        Log.message("Checked the user must change checkbox", extentedReport);
      } else {
        Log.message("user must change   checkbox is  checked", extentedReport);
      }
    } catch (Exception e) {
      throw new Exception("Exception in checking the user must change checkbox" + e);
    }
  }

  // #C2__C1__BUT_C6487216B270AC9D626037

  public void clickBtnsave(ExtentTest extentedReport, WebDriver driver) throws Exception {
    try {
      final long startTime = StopWatch.startTime();

      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", btn_save);

      WaitUtils.waitForSpinner(driver);
      Log.message("Clicked Save button on user role page ", driver, extentedReport);
      Log.event("Clicked Save button on user role page", StopWatch.elapsedTime(startTime));
    } catch (Exception e) {
      throw new Exception("Error while clicking Save button : " + e);
    }

  }

}
