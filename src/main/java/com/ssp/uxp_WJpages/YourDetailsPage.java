/**
 * 
 */
package com.ssp.uxp_WJpages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;


/**
 * @author jheelum.dutta
 *
 */
public class YourDetailsPage extends LoadableComponent<YourDetailsPage> {

  private final WebDriver driver;
  private ExtentTest extentedReport;
  private boolean isPageLoaded;
  public ElementLayer uielement;

  @FindBy(css = "[title='Your Details']")
  WebElement titleAcceptance;
  @FindBy(css = "#QUE_9547744E5D4FFB161738")
  WebElement houseNumberLocator;
  @FindBy(css = "#QUE_9547744E5D4FFB161740")
  WebElement postcodeLocator;



  /**
   * 
   * Constructor class for Card Details Page Here we initializing the driver for page factory
   * objects. For ajax element waiting time has added while initialization
   * 
   * @param driver : Webdriver
   */
  public YourDetailsPage(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentedReport = report;
    PageFactory.initElements(driver, this);
  }

  @Override
  protected void isLoaded() {

    if (!isPageLoaded) {
      Assert.fail();
    }

    if (isPageLoaded && !driver.getTitle().contains("Billing Adjustment")) {
      Log.fail("SSP Billing Adjustment Page did not open up. Site might be down.", driver,
          extentedReport);
    }
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {

    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);

    (new WebDriverWait(driver, 20).pollingEvery(200, TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
        .withMessage("Buyquote page not loaded properly"))
            .until(ExpectedConditions.visibilityOf(titleAcceptance));
  }



  public void enter_House_number(String houseNumbertxt, ExtentTest extentedRepor) throws Exception {
    try {
      WaitUtils.waitForElementPresent(driver, houseNumberLocator,
          " House Number  textbox is not found");

      houseNumberLocator.clear();
      houseNumberLocator.sendKeys(houseNumbertxt);
      Log.message("Value entered in Best Quote :" + houseNumbertxt, extentedReport);
    }

    catch (Exception e) {
      throw new Exception("Unable to Select Payment Plan" + e);
    }

  }

  public void enter_PostCode(String postCodetxt, ExtentTest extentedRepor) throws Exception {
    try {
      WaitUtils.waitForElementPresent(driver, postcodeLocator,
          " House Number  textbox is not found");

      postcodeLocator.clear();
      postcodeLocator.sendKeys(postCodetxt);
      Log.message("Value entered in Best Quote :" + postCodetxt, extentedReport);
    }

    catch (Exception e) {
      throw new Exception("Unable to Select Payment Plan" + e);
    }

  }



}
