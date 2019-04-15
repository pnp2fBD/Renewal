/**
 * 
 */
package com.ssp.uirefresh_pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import com.ssp.support.Keyboard;
import com.ssp.support.Log;
import com.ssp.support.StopWatch;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

/**
 * @author jheelum.dutta
 *
 */
public class UIRefresh_System_User_Role extends LoadableComponent<UIRefresh_System_User_Role> {
  private final WebDriver driver;
  private ExtentTest extentedReport;
  private boolean isPageLoaded;
  public ElementLayer uielement;

  @FindBy(css = "#principalID")
  WebElement txt_Principal_Id;
  @FindBy(
      css = "#split-main-container > div:nth-child(4) > div > div > form > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(1) > div.col-md-5.col-xs-12.zero-space > div:nth-child(2) > div > custom-check-box > div > input")
  WebElement checkbox_ResetPassword;
  @FindBy(css = "#temporaryPassword")
  WebElement txt_Temporary_password;
  @FindBy(
      css = "#split-main-container > div:nth-child(4) > div > div > form > div:nth-child(2) > div:nth-child(3) > div > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div > custom-check-box > div > input")
  WebElement checkbox_user_mustchange;
  @FindBy(
      css = "#split-main-container > div:nth-child(4) > div > div > form > div:nth-child(2) > div.row > div > a:nth-child(1)")
  WebElement btn_save;
  @FindBy(
      css = "#split-main-container > div:nth-child(4) > div > div > form > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(3) > div > div > div > custom-drop-down > select")
  WebElement drop_country;
  @FindBy(
      css = "#split-main-container > div:nth-child(4) > div > div > form > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(2) > div.col-md-5.col-xs-12 > div > custom-drop-down > select")
  WebElement drop_Language;
  @FindBy(
      css = "#split-main-container > div:nth-child(4) > div > div > form > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(2) > div:nth-child(1) > div.col-md-6.col-xs-6.col-sm-6.zero-space > div > custom-date-picker > div > input")
  WebElement datePicker1; 
  @FindBy(css = "span[data-i18n*='system_user_role_label']>span")
  WebElement systemUserRole;

  /*
   * @FindBy(css
   * ="#split-main-container > div:nth-child(4) > div > div > form > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(2) > div:nth-child(1) > div.col-md-6.col-xs-6.col-sm-6.zero-space > div > custom-date-picker > div > a"
   * ); WebElement datePicker;
   */

  public UIRefresh_System_User_Role(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentedReport = report;
    PageFactory.initElements(driver, this);
  }


  @Override
  protected void load() {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

  @Override
  protected void isLoaded() throws Error {
    // TODO Auto-generated method stub
    if (!isPageLoaded) {
      Assert.fail();
    }
    if (isPageLoaded && !systemUserRole.isDisplayed()) {
      Log.fail("UI Refresh 'Edit System User Role' page did not open up. Site might be down.", driver, extentedReport);
    }
    uielement = new ElementLayer(driver);
  }

  public void enter_PrincipalID(String principalId, boolean screenshot) throws Exception {
    try {
      Log.event("Entered enter_PrincipalID method");
      WaitUtils.waitForElementPresent(driver, txt_Principal_Id, "Principal ID text box is not present");
      txt_Principal_Id.clear();
      txt_Principal_Id.sendKeys(principalId);
      Log.message("Entered the Principal Id: " + principalId, driver, extentedReport,screenshot);
    } catch (Exception e) {
      throw new Exception("Error while entering Principal ID: " + e);
    }
  }

  public void enter_TemporaryPassword(String password, boolean screenshot) throws Exception {
    try {
      Log.event("Entered enter_TemporaryPassword method");
      WaitUtils.waitForElementPresent(driver, txt_Temporary_password,"Password textbox is not present");
      txt_Temporary_password.clear();
      txt_Temporary_password.sendKeys(password);
      Log.message("Entered the Temporary password: " + password, driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while entering password : " + e);
    }
  }

  public void tickPasswordResetCheckbox(ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      Log.event("Entered tickPasswordResetCheckbox method");
      //WaitUtils.waitForElementPresent(driver, checkbox_ResetPassword, "checkbox_ResetPassword is not present");
      GenericUtils.scrollIntoView(driver, checkbox_ResetPassword);
      if (!checkbox_ResetPassword.isSelected()) {
        checkbox_ResetPassword.click();
        WaitUtils.waitForSpinner(driver);
        Log.message("Checked the 'Reset Password' checkbox", driver, extentedReport, screenshot);
      } else {
        Log.message("'Reset Password' checkbox is checked by default", driver, extentedReport, screenshot);
      }
    } catch (Exception e) {
      throw new Exception("Exception in checking the Reset Password checkbox" + e);
    }
  }


  public void tick_user_Must_change_Checkbox(ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      Log.event("Entered tick_user_Must_change_Checkbox method");
      GenericUtils.scrollIntoView(driver, checkbox_user_mustchange);
      //WaitUtils.waitForElementPresent(driver, checkbox_user_mustchange, "Check box - user_mustchange is not present");
      if (checkbox_user_mustchange.isSelected()) {
        checkbox_user_mustchange.click();
        WaitUtils.waitForSpinner(driver);
        Log.message("'User Must Change' checkbox is unchecked", driver, extentedReport,screenshot);
      } else {
        Log.message("'User Must Change' checkbox is checked", driver, extentedReport,screenshot);
      }
    } catch (Exception e) {
      throw new Exception("Exception in checking the user must change checkbox" + e);
    }
  }

  public UIRefrsh_EditPersonalContact clickBtnsave(ExtentTest extentedReport, WebDriver driver, boolean screenshot) throws Exception {
    try {
      Log.event("Entered clickBtnsave method");
      WaitUtils.waitForElementPresent(driver, btn_save, "Save button is not working");
      WaitUtils.waitForelementToBeClickable(driver, btn_save, "Save button is not clickable");
      GenericUtils.javaScriptExecutorToClick(driver, btn_save);
      WaitUtils.waitForSpinner(driver);
      Log.message("Clicked Save button on user role page ", driver, extentedReport, screenshot);
      return new UIRefrsh_EditPersonalContact(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while clicking Save button : " + e);
    }

  }

  public void select_Country(String Countrytxt, ExtentTest extentedReport) throws Exception {
    try {
      // role_Name_drop.click();

      (new WebDriverWait(driver, 300).pollingEvery(200, TimeUnit.MILLISECONDS)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Timed out to find 'Role' dropdown"))
              .until(ExpectedConditions.elementToBeClickable(drop_country));

      Select roledropdown = new Select(drop_country);
      roledropdown.selectByVisibleText(Countrytxt);
      Log.message("Country Selected : " + Countrytxt, extentedReport);
      WaitUtils.waitForSpinner(driver);
    } catch (Exception e) {
      throw new Exception("Unable to Select Country" + e);
    }
  }

  public void select_Language(String Countrytxt, ExtentTest extentedReport) throws Exception {
    try {
      // role_Name_drop.click();

      (new WebDriverWait(driver, 300).pollingEvery(200, TimeUnit.MILLISECONDS)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Timed out to find 'Role' dropdown"))
              .until(ExpectedConditions.elementToBeClickable(drop_Language));

      Select roledropdown = new Select(drop_Language);
      roledropdown.selectByVisibleText(Countrytxt);
      Log.message("Language Selected : " + Countrytxt, extentedReport);
      WaitUtils.waitForSpinner(driver);
    } catch (Exception e) {
      throw new Exception("Unable to Select Language" + e);
    }
  }


  public void enterDate(String days) throws Exception {
    try {


      SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yy");
      String effectDate = formDate.format(new Date());
      datePicker1.sendKeys(days);
      Thread.sleep(500);
      datePicker1.sendKeys(Keys.TAB);
      Log.event("Entered Effective Date");

      Log.message("Entered Effective Date");
    } catch (Exception e) {
      throw new Exception("Unable to Enter Quote Details : " + e);
    }
  }

}
