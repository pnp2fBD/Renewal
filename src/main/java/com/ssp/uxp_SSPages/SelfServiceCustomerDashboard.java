/**
 * 
 */
package com.ssp.uxp_SSPages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.ssp.support.StopWatch;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;
import cucumber.api.java.it.Date;
import cucumber.stepdefination.runner.TestRunnerRenewals;

/**
 * @author jheelum.dutta
 *
 */
public class SelfServiceCustomerDashboard extends LoadableComponent<SelfServiceCustomerDashboard> {

  private WebDriver driver;
  private boolean isPageLoaded;
  private WebElement renewalBtn;
  private WebElement viewRnwlDocBtn;
  private WebElement renewalDocSection;
  private WebElement renewalMessage;
  private List<WebElement> renewalDocList;
  private List<WebElement> renewalDocTxtList;
  Iterator<String> itr;
  public ElementLayer uielement;
  ExtentTest extentedReport;
  String tile = " #C2__row_TBL_20C9A55710F99F9293688";

  String renewPolicySectionPrefix = "//span[contains(text(),'";
  String renewPolicySectionSuffix =
      "')]/ancestor::div[contains(@onclick,'AUTOSELECTOR_TBL')][contains(@onclick,'renewal_item')]";
  String renewPolicyBtn = "//button[contains(@title,'Renew My Policy')]";
  String renewalMessageWithBoldTag =
      "//div[@class='col-md-11'][not(contains(@style,'display: none'))]//b";
  String renewalMessageWithNoBoldTag =
      "//div[@class='col-md-11 text-bold'][not(contains(@style,'display: none'))]//div[contains(@id,'C2__TXT_D837627EFA8E80AC3271462')]//div";
  String viewDocumentsBtn =
      "//button[contains(@title,'View Documents') and contains(text(),'View renewal')]";
  String documentCountList = "//div[contains(@class,'table_doc')]";
  String documentTextList = "//span[@class='tb-mob-answer-txt']";
  String renewalDocumentsSection = "//div[contains(@id,'C2__Documents1')]";
  String startDate =
      "//div[contains(text(),'Start date:')]/ancestor::div[contains(@class,'col-sm-5 col-xs-5 control-label text-bold noPadLft')]/following-sibling::div //span";
  String endDate =
      "//div[contains(text(),'End date:')]/ancestor::div[contains(@class,'col-sm-5 col-xs-5 control-label text-bold noPadLft')]/following-sibling::div //span";
  String lastYearPremium =
      "//div[contains(text(),'Last year�s premium:')]/ancestor::div[contains(@class,'col-sm-5 col-xs-5 control-label text-bold noPadLft')]/following-sibling::div //span";
  String renewalPremium =
      "//div[contains(text(),'Your renewal premium:')]/ancestor::div[contains(@class,'col-sm-5 col-xs-5 control-label text-bold noPadLft')]/following-sibling::div //span";
  String renewalPostExpiryMessageSuffix =
      "//div[contains(text(),'Your Household Standard policy is now due for renewal.')]";
  String renewalNoLongerAvailableMessageSuffix =
      "//div[contains(text(),'Your policy is now due for renewal. It is no longer possible to renew your Household Standard policy online. Please contact our Call Centre to renew your policy.')]";
  String renewalFutureDatedMessageSuffix =
      "//div[contains(text(),'Your Household Standard policy is due for renewal in 351 days.')]";

  String StartdatoldcssPath = "#C2__QUE_C1D6BAFD65139DF1198619_R";
  String enddatoldcssPath = "C2__QUE_C1D6BAFD65139DF1198619_R";
  String PreviouspremimumoldcssPath = "C2__QUE_C1D6BAFD65139DF1198621_R";
  String renewalPremiumoldcssPath = "C2__QUE_C1D6BAFD65139DF1198623_R";
  String renewmypolicyoldcssPath = "#C2__BUT_C1D6BAFD65139DF1214642_R";
  String viewdocumentoldcssPath = "#C2__BUT_1D33631E007828EF231943_R";
  String hideDocoldcssPath = "#C2__BUT_C1D6BAFD65139DF1226809_R1";

  @FindBy(xpath = "//h2[contains(text(),'Hello')]")
  WebElement welcomeGreeting;
  @FindBy(css = "[title='Customer Dashboard']")
  WebElement titleAcceptance;
  @FindBy(css = "#BUT_B3A80BADFCD28CFD90816_R1")
  WebElement btnConQuote;
  @FindBy(css = "#C2__QUE_B3A80BADFCD28CFD90822_R1")
  WebElement policyNumber;
  @FindBy(css = "#C2__QUE_B3A80BADFCD28CFD90828_R1")
  WebElement policyStartDate;
  @FindBy(css = "#C2__QUE_B3A80BADFCD28CFD90830_R1")
  WebElement policyEndtDate;
  @FindBy(
      css = "#TXT_991B8A1F6178C09242289 > div > div > div.col-md-11.col-sm-9.col-xs-10.padzero > p")
  WebElement warning_GDPR;
  @FindBy(css = "#C1__row_MNU_4B30B4C0CA2951D419304")
  WebElement user_info;
  @FindBy(css = "#C1__row_MNU_4B30B4C0CA2951D419304")
  WebElement notification_Alert;
  @FindBy(css = "#C2__p1_HEAD_20C9A55710F99F9293684 > div > div > span")
  WebElement notification_Top;
  @FindBy(css = "#C2__p1_HEAD_20C9A55710F99F9293684 > div > div > span")
  WebElement notification_Countdown;

  @FindBy(css = "#C2__QUE_C1D6BAFD65139DF1198617_R1")
  WebElement policyRenewalStartDate;
  @FindBy(css = "#C2__QUE_C1D6BAFD65139DF1198619_R1")
  WebElement policyRenewalEndtDate;
  @FindBy(css = "#C2__QUE_C1D6BAFD65139DF1198617_R1")
  WebElement policy_Previous_Premium;
  @FindBy(css = "#C2__QUE_C1D6BAFD65139DF1198619_R1")
  WebElement policy_Renewal_Premium;

  @FindBy(css = "#C2__BUT_C1D6BAFD65139DF1214642_R4")
  WebElement btnRenewMyPolicy;
  @FindBy(css = "#C2__BUT_1D33631E007828EF231943_R1")
  WebElement btnViewdocument;
  @FindBy(css = "#C2__BUT_C1D6BAFD65139DF1226809_R1")
  WebElement btnHidedocument;
  @FindBy(css = " #Renewal_Documents > div > span")
  WebElement renewaldocsec;
  @FindBy(css = "#C2__FMT_F19A6EAA43247F89573568_R1")
  WebElement notification_ShopAround;
  @FindBy(css = "C2__TXT_7487D9EEBEECEEEB405139_R1")
  WebElement notification_AutoRenew;
  @FindBy(css = "#C2__TXT_D837627EFA8E80AC3271462_R1")
  WebElement notification_Callcentre;
  @FindBy(css = "#C2__TXT_7487D9EEBEECEEEB405139_R1")
  WebElement notification_NotAutoRenew;
  int tileCount;
  @FindBy(css = "#C2__p1_HEAD_20C9A55710F99F9293693_R1 > div")
  WebElement notification_ExpireToday;
  @FindBy(css = "#C2__HEAD_8B2BA88D8A7370DC251764")
  WebElement annualPremium;
  @FindBy(css = "#C2__HEAD_82C3F84B8B5F77B74769")
  WebElement monthlyPremium;
  @FindBy(css = "#C2__HEAD_B44B5504A5EEA38B438117")
  WebElement monthlyPremiumText;
  @FindBy(css = "#C2__HEAD_B8490DFF52C31681484503")
  WebElement fortnightlyPremium;
  @FindBy(css = "#C2__HEAD_B8490DFF52C31681484505")
  WebElement fortnightlyPremiumText;
  @FindBy(css = "span[id*='C2__QUE_0F8C7DBAD34417D7206678']")
  List<WebElement> documentsSize;
  @FindBy(css = "span[id*='QUE_C1D6BAFD65139DF1198617']")
  List<WebElement> policyStartDates;
  @FindBy(css = "span[id*='QUE_C1D6BAFD65139DF1198621']")
  List<WebElement> previousPolicyPremium;
  @FindBy(css = "span[id*='QUE_C1D6BAFD65139DF1198623']")
  List<WebElement> renewalPolicyPremium;
  @FindBy(css = "li[class*='dropdown'] a")
  WebElement userAccountDropdown;
  @FindBy(css = "a[id*='SignOut']")
  WebElement signOutOption;
  @FindBy(xpath = "//div[contains(text(),'Warning: It is not currently')]")
  WebElement gdprMessage;



  public SelfServiceCustomerDashboard(WebDriver driver) {
    this.driver = driver;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }

  @Override
  protected void isLoaded() {

    if (!isPageLoaded) {
      Assert.fail();
    }
    (new WebDriverWait(driver, 180).pollingEvery(Duration.ofMillis(200))
        .ignoring(NoSuchElementException.class).withMessage("Self Service dashboard not opening."))
            .until(ExpectedConditions.visibilityOf(welcomeGreeting));
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

  /**
   * verify Sign in page is displayed
   * 
   * @param extentedReport
   * @throws Exception
   * 
   */

  public boolean verifySelfServiceCustomerDashboardPage() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, titleAcceptance))
      throw new Exception("Customer Dashboard is  not loaded");
    return true;
  }

  /**
   * Click on Continue button
   * 
   * @return Void
   * @throws Exception
   * 
   */
  public void clickonContinueQuotebtn(ExtentTest extentedReport) throws Exception {
    try {
      final long startTime = StopWatch.startTime();
      WaitUtils.waitForElement(driver, btnConQuote);
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", btnConQuote);
      // btnSignIn.click();
      WaitUtils.waitForSpinner(driver);
      Log.message("Clicked on Continue button on customer Dashboard ", driver, extentedReport);
      Log.event("Clicked on Continue buttonon customer Dashboard ",
          StopWatch.elapsedTime(startTime));
    } catch (Exception e) {
      throw new Exception("Error while clicking on Continue button : " + e);
    }


  }

  /**
   * Verify if Summary of Renewal - is displayed
   * 
   * @return Void
   * @throws Exception
   * 
   */
  public boolean verifyRenewalPolicyNumber(String policynumber) throws Exception {
    boolean flag = false;
    try {
      String policyno = policyNumber.getText();

      if (policyno.equals(policynumber)) {
        flag = true;
        Log.message("PolicyNumber Matched -- " + policyno, driver, extentedReport);
      } else {
        flag = false;
        Log.message("PolicyNumber Not Matched -- " + policyno, driver, extentedReport);
      }

    } catch (Exception e) {
      return flag;
    }
    return flag;


  }

  public void VerifyPolicyStartDate(String startdate, ExtentTest extentedReport) throws Exception {
    try {
      String startDate = policyStartDate.getText();
      if (startDate.equals(startdate)) {
        Log.message("Policy StartDate Matched--- " + startDate, driver, extentedReport);
      } else {
        Log.message("Policy StartDate Not Matched--- " + startDate, driver, extentedReport);
      }

    } catch (Exception e) {
      throw new Exception("Element not found : " + e);
    }


  }


  public void VerifyPolicyEndDate(String end_date, ExtentTest extentedReport) throws Exception {
    try {
      String endDate = policyEndtDate.getText();
      if (endDate.equals(end_date)) {
        Log.message("Policy End Date Matched--- " + endDate, driver, extentedReport);
      } else {
        Log.message("Policy End Date Not Matched-- " + endDate, driver, extentedReport);
      }

    } catch (Exception e) {
      throw new Exception("Element not found : " + e);
    }


  }



  public void VerifyPolicy_Warning_Message_Textmatched(String warningMessage,
      ExtentTest extentedReport) throws Exception {
    try {
      String warning_GDPRtext = warning_GDPR.getText();
      if (warning_GDPRtext.equals(warningMessage)) {
        Log.message("Warning message  Matched ", driver, extentedReport);
      } else {
        Log.message("Warning message Not Matched ", driver, extentedReport);
      }

    } catch (Exception e) {
      throw new Exception("Element not found : " + e);
    }

  }


  public void VerifyPolicy_Warning_Message_is_displayed(ExtentTest extentedReport)
      throws Exception {
    try {

      if (!warning_GDPR.isEnabled() && warning_GDPR.isDisplayed()) {
        Log.message("Warning message is displayed and Read only ", driver, extentedReport);
      } else {
        Log.message("Warning message is not displayed ", driver, extentedReport);
      }

    } catch (Exception e) {
      throw new Exception("Warning message not found : " + e);
    }
  }

  public void VerifyPolicy_username_is_displayed(String username, ExtentTest extentedReport)
      throws Exception {
    try {

      if (user_info.getText().equals(username)) {
        Log.message("Logged in sucessful ", driver, extentedReport);
      } else {
        Log.message("Warning message is not displayed ", driver, extentedReport);
      }

    } catch (Exception e) {
      throw new Exception("Element not found : " + e);
    }

  }

  // jheelum-- Renewal



  public boolean Verify_Renewal_TopNotification(String NotificationText) throws Exception {
    boolean flag = false;
    try {


      if (notification_Top.isDisplayed()) {
        if (notification_Top.getText().equals(NotificationText)) {
          flag = true;
          Log.message(notification_Top.getText() + "---Warning message text Matched : ", driver,
              extentedReport);
        } else {

          flag = false;
          Log.message(notification_Top.getText() + "----Warning message text Mis-matched: "
              + notification_Top, driver, extentedReport);
        }

      } else {
        Log.message("Warning message is not displayed ", driver, extentedReport);
      }

    } catch (Exception e) {

      return flag;

    }
    return flag;
  }

  public boolean Verify_Renewal_CoundownNotification(String NotificationText) throws Exception {
    boolean flag = false;
    try {


      if (notification_Countdown.isDisplayed()) {



        if (notification_Countdown.getText().equals(NotificationText)) {
          flag = true;
          Log.message("Warning message text Matched : " + NotificationText, driver, extentedReport);


        } else {
          flag = false;
          Log.fail("Warning message text Mis-matched: " + NotificationText, driver, extentedReport);
        }

      } else {
        flag = false;
        Log.message("Warning message is not displayed ", driver, extentedReport);

      }

    } catch (Exception e) {

      return flag;
    }
    return flag;

  }

  public boolean VerifyPolicyRenewalStartDate(String startdate) throws Exception {

    boolean flag = false;
    try {

      String startDate = policyRenewalStartDate.getText();
      if (startDate.equals(startdate)) {
        flag = true;
        Log.message("Policy StartDate Matched--- " + startDate, driver, extentedReport);
      } else {
        flag = false;
        Log.message("Policy StartDate Not Matched--- " + startDate, driver, extentedReport);
      }

    } catch (Exception e) {
      return flag;

    }
    return flag;


  }

  public WebElement verifyMultipleElementisindifferenttileisdispalyed(String oldcsspath,
      String newcsspath) throws Exception {
    WebElement ele = null;
    try {
      String newpath = oldcsspath.replace(oldcsspath, newcsspath);
      System.out.println(newpath);
      ele = driver.findElement(By.cssSelector(newpath));
      return ele;
    } catch (Exception e) {
      throw new Exception(ele + "notfound");
    }

  }


  public boolean VerifyPolicyRenewalStartDate(String startdate, String csspath, String newpath)
      throws Exception {

    boolean flag = false;
    try {

      WebElement csspathupdated =
          verifyMultipleElementisindifferenttileisdispalyed(csspath, newpath);

      String startDate = csspathupdated.getText();
      if (startDate.equals(startdate)) {
        flag = true;
        Log.message("Policy StartDate Matched--- " + startDate, driver, extentedReport);
      } else {

        flag = false;
        Log.message("Policy StartDate Not Matched--- " + startDate, driver, extentedReport);
      }

    } catch (Exception e) {
      return flag;

    }
    return flag;


  }

  public boolean VerifyPolicyRenewalEndDate(String end_date) throws Exception {
    boolean flag = false;
    try {
      String endDate = policyRenewalEndtDate.getText();
      if (endDate.equals(end_date)) {
        flag = true;
        Log.message("Policy End Date Matched--- " + endDate, driver, extentedReport);
        return flag;
      } else {
        flag = false;
        Log.message("Policy End Date Not Matched-- " + endDate, driver, extentedReport);
      }

    } catch (Exception e) {
      return flag;
    }
    return flag;


  }

  public boolean VerifyPolicyRenewalEndDate(String end_date, String csspath, String newpath)
      throws Exception {
    boolean flag = false;
    try {

      WebElement csspathupdated =
          verifyMultipleElementisindifferenttileisdispalyed(csspath, newpath);


      String endDate = csspathupdated.getText();
      if (endDate.equals(end_date)) {
        flag = true;
        Log.message("Policy End Date Matched--- " + endDate, driver, extentedReport);
        return flag;
      } else {
        flag = false;
        Log.message("Policy End Date Not Matched-- " + endDate, driver, extentedReport);
      }

    } catch (Exception e) {
      System.out.println();
      return flag;
    }
    return flag;


  }

  public boolean Verify_Policy_Prvious_Premium(String premium, String csspath, String newpath)
      throws Exception {

    boolean flag = false;
    try {
      WebElement csspathupdated =
          verifyMultipleElementisindifferenttileisdispalyed(csspath, newpath);

      String prev_premium = csspathupdated.getText();
      if (prev_premium.equals(premium)) {
        flag = true;
        Log.message("Policy Previous Premium Matched--- " + premium, driver, extentedReport);
      } else {
        flag = false;
        Log.message("Policy Previous Premium Not Matched-- " + premium, driver, extentedReport);
      }

    } catch (Exception e) {
      return flag;

    }
    return flag;


  }

  public boolean Verify_Policy_Prvious_Premium(String premium) throws Exception {

    boolean flag = false;
    try {


      String prev_premium = policy_Previous_Premium.getText();
      if (prev_premium.equals(premium)) {
        flag = true;
        Log.message("Policy Previous Premium Matched--- " + premium, driver, extentedReport);
      } else {
        flag = false;
        Log.message("Policy Previous Premium Not Matched-- " + premium, driver, extentedReport);
      }

    } catch (Exception e) {
      return flag;

    }
    return flag;


  }



  public boolean Verify_Policy_Renewal_Premium(String premium, String csspath, String newpath)
      throws Exception {
    boolean flag = false;
    try {

      WebElement csspathupdated =
          verifyMultipleElementisindifferenttileisdispalyed(csspath, newpath);


      String prev_premium = csspathupdated.getText();

      if (prev_premium.equals(premium)) {

        flag = true;
        Log.message("Policy Renewal Premium Matched--- " + premium, driver, extentedReport);
      } else {
        flag = false;
        Log.message("Policy Renewal Premium Not Matched-- " + premium, driver, extentedReport);
      }

    } catch (Exception e) {
      return flag;
    }
    return flag;


  }

  public boolean Verify_Policy_Renewal_Premium(String premium) throws Exception {
    boolean flag = false;
    try {
      String prev_premium = policy_Renewal_Premium.getText();
      if (prev_premium.equals(premium)) {

        flag = true;
        Log.message("Policy Renewal Premium Matched--- " + premium, driver, extentedReport);
      } else {
        flag = false;
        Log.message("Policy Renewal Premium Not Matched-- " + premium, driver, extentedReport);
      }

    } catch (Exception e) {
      return flag;
    }
    return flag;


  }


  public boolean Verify_Renew_my_policybutton_isdisplayed_and_isenabled(String csspath,
      String newpath) throws Exception {
    boolean flag = false;
    try {
      WebElement csspathupdated =
          verifyMultipleElementisindifferenttileisdispalyed(csspath, newpath);
      // System.out.println(csspathupdated);
      if (csspathupdated.isDisplayed()) {
        flag = true;
        Log.message("Renew My policy Button is Displayed " + btnRenewMyPolicy.getText(), driver,
            extentedReport);
      } else {
        flag = false;
        Log.fail(
            "Renew My policy Button is Displayed is not displayed  " + btnRenewMyPolicy.getText(),
            driver, extentedReport);
      }
    }

    catch (Exception e) {
      return flag;
    }
    return flag;
  }

  public boolean Verify_Renew_my_policybutton_isdisplayed_and_isenabled(WebDriver driver,
      ExtentTest extentedReport) throws Exception {
    boolean flag = false;
    try {
      if (btnRenewMyPolicy.isDisplayed()) {
        flag = true;
        Log.message("Renew My policy Button is Displayed " + btnRenewMyPolicy.getText(), driver,
            extentedReport);
      } else {
        flag = false;
        Log.fail(
            "Renew My policy Button is Displayed is not displayed  " + btnRenewMyPolicy.getText(),
            driver, extentedReport);
      }
    } catch (Exception e) {
      return flag;
    }
    return flag;
  }

  public boolean Verify_ViewDocumentbutton(String csspath, String newpath) throws Exception {
    boolean flag = false;
    try {
      WebElement csspathupdated =
          verifyMultipleElementisindifferenttileisdispalyed(csspath, newpath);
      if (csspathupdated.isDisplayed()) {
        flag = true;
        Log.message("Button Displayed :  " + btnViewdocument.getText(), driver, extentedReport);
      } else {
        flag = false;
        Log.fail("Button   not displayed  " + btnViewdocument.getText(), driver, extentedReport);
      }
    } catch (Exception e) {
      return flag;
    }
    return flag;
  }

  public boolean Verify_ViewDocumentbutton() throws Exception {
    boolean flag = false;
    try {
      if (btnViewdocument.isDisplayed()) {
        flag = true;
        Log.message("Button Displayed :  " + btnViewdocument.getText(), driver, extentedReport);
      } else {
        flag = false;
        Log.fail("Button   not displayed  " + btnViewdocument.getText(), driver, extentedReport);
      }
    } catch (Exception e) {
      return flag;
    }
    return flag;
  }

  public boolean Verify_HideDocumentbutton(String cssviewpath, String newviewpath,
      String csshidepath, String newhidewpath) throws Exception {
    boolean flag = false;
    try {
      WebElement viewbutton =
          verifyMultipleElementisindifferenttileisdispalyed(cssviewpath, newviewpath);
      viewbutton.click();
      WaitUtils.waitForSpinner(driver);
      WebElement hidebutton =
          verifyMultipleElementisindifferenttileisdispalyed(csshidepath, newhidewpath);
      if (hidebutton.isDisplayed()) {
        boolean flagDocsec = renewaldocsec.isDisplayed();
        if (flagDocsec) {
          flag = true;
          Log.message(renewaldocsec.getText() + " ------Section is displayed"
              + "Button Displayed -  " + "    and   " + btnHidedocument.getText(), driver,
              extentedReport);
        } else {
          Log.message("Renewal Document Section Not Displayed ");
        }
      } else {
        flag = false;
        Log.fail("Button Not Displayed   " + btnHidedocument.getText(), driver, extentedReport);
        Log.fail("Renewal Document  Section is not displayed", driver, extentedReport);
      }
      return flag;
    }

    catch (Exception e) {

      return flag;
    }

  }

  public boolean Verify_HideDocumentbutton() throws Exception {



    boolean flag = false;

    try {
      btnViewdocument.click();

      WaitUtils.waitForSpinner(driver);

      if (btnHidedocument.isDisplayed()) {

        boolean flagDocsec = renewaldocsec.isDisplayed();
        if (flagDocsec) {
          flag = true;

          Log.message(renewaldocsec.getText() + " ------Section is displayed"
              + "Button Displayed -  " + "    and   " + btnHidedocument.getText(), driver,
              extentedReport);

        } else {
          Log.message("Renewal Document Section Not Displayed ");
        }


      } else {
        flag = false;
        Log.fail("Button Not Displayed   " + btnHidedocument.getText(), driver, extentedReport);
        Log.fail("Renewal Document  Section is not displayed", driver, extentedReport);
      }
      return flag;
    }

    catch (Exception e) {

      return flag;
    }

  }

  public boolean ClickhideRenewalDocument() throws Exception {
    boolean flag = false;
    try {
      btnViewdocument.click();
      WaitUtils.waitForSpinner(driver);
      btnHidedocument.click();
      WaitUtils.waitForSpinner(driver);
      if (btnViewdocument.isDisplayed() && !renewaldocsec.isDisplayed()) {
        flag = true;
        Log.message(btnViewdocument.getText() + "---Button Displayed"
            + " and Renewal Document  Section is not displayed", driver, extentedReport);
      } else {
        flag = false;
        Log.fail("Button Not Displayed   " + btnHidedocument.getText(), driver, extentedReport);
        Log.fail("Renewal Document  Section is not displayed", driver, extentedReport);
      }
    } catch (Exception e) {
      return flag;
    }
    return flag;
  }

  public boolean validateRenewalDocument(WebDriver driver, ExtentTest extentedReport)
      throws Exception {
    boolean flag = false;
    WaitUtils.waitForSpinner(driver);
    try {
      btnViewdocument.click();
      WaitUtils.waitForSpinner(driver);
      System.out.println("Size is: " + documentsSize.size());
      if (documentsSize.size() == 1) {
        flag = true;
        Log.pass("Renewal Documents are appearing correctly", driver, extentedReport, true);
      } else {
        flag = false;
        Log.fail("Number of documents generated are more/less than expected", driver,
            extentedReport, true);
      }
    } catch (Exception e) {
      throw e;
      // return flag;
    }
    return flag;
  }

  /*
   * @author jheelum Multiple policy check US 51 - need to combine with TC Tc_32,TC_33,TC_34,TC_35
   * Need to get all the variable values from TC_032 and below variables would be global variable.
   * currently could not merge with because of bugzilla - 98260 Need to work on Error handeling if
   * any of the test case Fail
   */
  public int countTile() {
    try {
      // String tiledata=tile+"/div";
      List<?> al = driver.findElements(By.className("renewal-icon"));

      tileCount = al.size();

      String Mulstartdate1 = "12/10/2018";
      String Mulstartenddate = "11/10/2019";
      String Previous_Premium = "464.49";
      String Renewal_Premium = "464.49";

      for (int i = 1; i <= tileCount; i++) {


        String StartdatNewcssPath = "#C2__QUE_C1D6BAFD65139DF1198619_R" + i;
        String enddatNewcssPath = "C2__QUE_C1D6BAFD65139DF1198619_R" + i;
        String PreviouspremimumNewcssPath = "C2__QUE_C1D6BAFD65139DF1198621_R" + i;
        String renewalPremiumNewcssPath = "C2__QUE_C1D6BAFD65139DF1198623_R" + i;
        String renewmypolicynewcssPath = "#C2__BUT_C1D6BAFD65139DF1214642_R" + i;
        String viewdocumentnewcssPath = "#C2__BUT_1D33631E007828EF231943_R" + i;
        String hideDocnewcssPath = "#C2__BUT_C1D6BAFD65139DF1226809_R1" + i;

        try {

          VerifyPolicyRenewalStartDate(Mulstartdate1, StartdatoldcssPath, StartdatNewcssPath);

        } catch (Exception e) {
          throw new Exception("start date is not displayed ");
        }
        try {

          VerifyPolicyRenewalEndDate(Mulstartenddate, enddatoldcssPath, enddatNewcssPath);
        } catch (Exception e) {
          throw new Exception("start date is not displayed ");
        }

        try {
          Verify_Renew_my_policybutton_isdisplayed_and_isenabled(renewmypolicyoldcssPath,
              renewmypolicynewcssPath);
        } catch (Exception e) {
          throw new Exception("start date is not displayed ");
        }
        try {
          Verify_Policy_Prvious_Premium(Previous_Premium, PreviouspremimumoldcssPath,
              PreviouspremimumNewcssPath);
        } catch (Exception e) {
          throw new Exception("start date is not displayed ");
        }
        try {


          Verify_Policy_Renewal_Premium(Renewal_Premium, renewalPremiumoldcssPath,
              renewalPremiumNewcssPath);
        } catch (Exception e) {
          throw new Exception("start date is not displayed ");
        }

        try {
          Verify_ViewDocumentbutton(viewdocumentoldcssPath, viewdocumentnewcssPath);

        } catch (Exception e) {
          throw new Exception("start date is not displayed ");
        }
        try {
          Verify_HideDocumentbutton(viewdocumentoldcssPath, viewdocumentnewcssPath,
              hideDocoldcssPath, hideDocnewcssPath);

        } catch (Exception e) {
          throw new Exception("start date is not displayed ");
        }

      }

      return tileCount;

    } catch (Exception e) {
      return tileCount;
    }

  }

  public boolean validate_Multiple_PolicyIs_Displayed(List<String> Dates,
      List<String> previousPremium, List<String> newPremium, WebDriver driver,
      ExtentTest extentedReport) throws Exception {
    boolean flag = false;
    try {
      itr = Dates.iterator();
      while (itr.hasNext()) {
        System.out.println("Dates are: " + itr.next());
      }
      List<?> al = driver.findElements(By.className("renewal-icon"));
      tileCount = al.size();
      for (int i = 0; i < tileCount; i++) {
        String Mulstartdate = Dates.get(i);
        String lastyearPremium = previousPremium.get(i);
        String renewalPremium = newPremium.get(i);

        List<WebElement> sDates = policyStartDates;
        List<WebElement> oldPremium = previousPolicyPremium;
        List<WebElement> rPremium = renewalPolicyPremium;

        String tempDate = sDates.get(i).getText();
        String tempOldPremium = oldPremium.get(i).getText();
        String tempRenewalPremium = rPremium.get(i).getText();

        if (Mulstartdate.equals(tempDate)) {
          Log.pass("Correct Start Date is appearing: " + tempDate, driver, extentedReport, true);
        } else
          Log.fail("Incorrect Start Date is appearing: " + tempDate, driver, extentedReport, true);

        if (lastyearPremium.equals(tempOldPremium)) {
          Log.pass("Correct Previous year Premium is appearing: " + tempOldPremium, driver,
              extentedReport, true);
        } else
          Log.fail("Incorrect Previous year Premium is appearing: " + tempOldPremium, driver,
              extentedReport, true);

        if (renewalPremium.equals(tempRenewalPremium)) {
          Log.pass("Correct Renewal Premium is appearing: " + tempRenewalPremium, driver,
              extentedReport, true);
        } else
          Log.fail("InCorrect Renewal Premium is appearing: " + tempRenewalPremium, driver,
              extentedReport, true);
        // String Mulstartenddate = "11/10/2019";
        // String Previous_Premium = "464.49";
        // String Renewal_Premium = "464.49";
        /*
         * String StartdatNewcssPath = "#C2__QUE_C1D6BAFD65139DF1198619_R" + i; String
         * enddatNewcssPath = "C2__QUE_C1D6BAFD65139DF1198619_R" + i; String
         * PreviouspremimumNewcssPath = "C2__QUE_C1D6BAFD65139DF1198621_R" + i; String
         * renewalPremiumNewcssPath = "C2__QUE_C1D6BAFD65139DF1198623_R" + i; String
         * renewmypolicynewcssPath = "#C2__BUT_C1D6BAFD65139DF1214642_R" + i; String
         * viewdocumentnewcssPath = "#C2__BUT_1D33631E007828EF231943_R" + i; String
         * hideDocnewcssPath = "#C2__BUT_C1D6BAFD65139DF1226809_R1" + i;
         * 
         * try {
         * 
         * VerifyPolicyRenewalStartDate(Mulstartdate, StartdatoldcssPath, StartdatNewcssPath);
         * 
         * } catch (Exception e) { throw new Exception("start date is not displayed "); } try {
         * 
         * VerifyPolicyRenewalEndDate(Mulstartenddate, enddatoldcssPath, enddatNewcssPath); } catch
         * (Exception e) { throw new Exception("start date is not displayed "); }
         * 
         * try { Verify_Renew_my_policybutton_isdisplayed_and_isenabled(renewmypolicyoldcssPath,
         * renewmypolicynewcssPath); } catch (Exception e) { throw new
         * Exception("start date is not displayed "); } try {
         * Verify_Policy_Prvious_Premium(Previous_Premium, PreviouspremimumoldcssPath,
         * PreviouspremimumNewcssPath); } catch (Exception e) { throw new
         * Exception("start date is not displayed "); } try {
         * 
         * 
         * Verify_Policy_Renewal_Premium(Renewal_Premium, renewalPremiumoldcssPath,
         * renewalPremiumNewcssPath); } catch (Exception e) { throw new
         * Exception("start date is not displayed "); }
         * 
         * try { Verify_ViewDocumentbutton(viewdocumentoldcssPath, viewdocumentnewcssPath);
         * 
         * } catch (Exception e) { throw new Exception("start date is not displayed "); } try {
         * Verify_HideDocumentbutton(viewdocumentoldcssPath, viewdocumentnewcssPath,
         * hideDocoldcssPath, hideDocnewcssPath);
         * 
         * } catch (Exception e) { throw new Exception("start date is not displayed "); }
         * 
         * }
         */
        // tileCount = countTile();
        if (tileCount >= 1) {
          Log.message("Number of Tiles---------" + tileCount);
          flag = true;
        } else {
          Log.message("Number of Tiles" + tileCount);
          flag = false;
        }

      }
    } catch (Exception e) {
      flag = false;
      System.out.println("number of tile " + tileCount);
      return flag;
    }
    return flag;

  }


  public boolean validate_shopAround_Notification(String NotificationText) throws Exception {

    boolean flag = false;


    try {
      if (notification_ShopAround.isDisplayed()) {


        if (notification_ShopAround.getText().equals(NotificationText)) {
          flag = true;
          Log.message("Warning message text Matched : " + NotificationText, driver, extentedReport);


        } else {
          flag = false;
          Log.fail("Warning message text Mis-matched: " + NotificationText, driver, extentedReport);
        }

      } else {
        flag = false;
        Log.message("Warning message is not displayed ", driver, extentedReport);

      }

    } catch (Exception e) {

      return flag;
    }
    return flag;

  }



  public boolean validate_AutoRenew_Notification(String NotificationText) throws Exception {
    boolean flag = false;


    try {
      if (notification_AutoRenew.isDisplayed()) {


        if (notification_AutoRenew.getText().equals(NotificationText)) {
          flag = true;
          Log.message("Warning message text Matched : " + NotificationText, driver, extentedReport);


        } else {
          flag = false;
          Log.fail("Warning message text Mis-matched: " + NotificationText, driver, extentedReport);
        }
      } else {
        flag = false;
        Log.message("Warning message is not displayed ", driver, extentedReport);

      }

    } catch (Exception e) {

      return flag;
    }
    return flag;
  }


  public boolean validate_notAutoRenew_Notification(String NotificationText) throws Exception {
    boolean flag = false;


    try {
      if (notification_NotAutoRenew.isDisplayed()) {


        if (notification_NotAutoRenew.getText().equals(NotificationText)) {
          flag = true;
          Log.message("Warning message text Matched : " + NotificationText, driver, extentedReport);


        } else {
          flag = false;
          Log.fail("Warning message text Mis-matched: " + NotificationText, driver, extentedReport);
        }
      } else {
        flag = false;
        Log.message("Warning message is not displayed ", driver, extentedReport);

      }

    } catch (Exception e) {

      return flag;
    }
    return flag;
  }



  public boolean Verify_CallCentreNotification(String NotificationText) throws Exception {
    boolean flag = false;
    try {



      if (notification_Callcentre.isDisplayed()) {



        if (notification_Callcentre.getText().equals(NotificationText)) {
          flag = true;
          Log.message("Warning message text Matched : " + NotificationText, driver, extentedReport);


        } else {
          flag = false;
          Log.fail("Warning message text Mis-matched: " + NotificationText, driver, extentedReport);
        }

      } else {
        flag = false;
        Log.message("Warning message is not displayed ", driver, extentedReport);

      }

    } catch (Exception e) {

      return flag;
    }
    return flag;

  }

  public boolean validate_policyExpairingToday_Notification(String NotificationText)
      throws Exception {
    boolean flag = false;


    try {
      // need to add a check if policy Expiry date is todays date
      if (notification_ExpireToday.isDisplayed()) {


        if (notification_ExpireToday.getText().equals(NotificationText)) {
          flag = true;
          Log.message("Warning message text Matched : " + NotificationText, driver, extentedReport);


        } else {
          flag = false;
          Log.fail("Warning message text Mis-matched: " + NotificationText, driver, extentedReport);
        }
      } else {
        flag = false;
        Log.message("Warning message is not displayed ", driver, extentedReport);

      }

    } catch (Exception e) {

      return flag;
    }
    return flag;
  }


  public boolean compareDate() throws ParseException {

    boolean flag = false;

    String startDate = policyRenewalStartDate.getText();
    System.out.println("start date" + startDate);
    System.out.println("start date" + startDate);

    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");


    // Now format the date
    Date date2 = (Date) dateFormat.parse(startDate);

    // Date date1=(Date) new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
    // Print the Date
    System.out.println(date2);
    flag = true;
    return flag;

  }

  /**
   * Renewal Section Validation
   * 
   * @param policyNo
   * @param test
   * @param screenshot
   * @throws Exception
   */
  public boolean validateRenewalPolicySection(Entry<String, HashMap<String, String>> test,
      boolean screenshot) throws Exception {

    String policyNo = test.getKey();

    Map<String, String> policyRenewalData = test.getValue();
    boolean status = true;
    StringBuffer message = new StringBuffer("Failing on -");

    String btnXpath =
        renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix + renewPolicyBtn;
    renewalBtn = driver.findElement(By.xpath(btnXpath));

    btnXpath = renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix + viewDocumentsBtn;
    viewRnwlDocBtn = driver.findElement(By.xpath(btnXpath));

    String StartDate = policyRenewalData.get("InceptionDate");
    String EndDate = policyRenewalData.get("ExpiryDate");
    String LastPrem = policyRenewalData.get("PolicyPremium");
    String RenPrem = policyRenewalData.get("RenewalPremium");

    String startDateXpath =
        renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix + startDate;
    String endDateXpath = renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix + endDate;
    String lastPremXpath =
        renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix + lastYearPremium;
    String renewalPremXpath =
        renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix + renewalPremium;
    try {
      WebElement elemenStartDate = driver.findElement(By.xpath(startDateXpath));
      WebElement elementEndDate = driver.findElement(By.xpath(endDateXpath));
      WebElement elementLastPrem = driver.findElement(By.xpath(lastPremXpath));
      WebElement elementRenPrem = driver.findElement(By.xpath(renewalPremXpath));

      if (!GenericUtils.verifyWebElementTextEquals(elemenStartDate, StartDate)) {
        status = false;
        message.append("Start Date: ");
      }
      if (!GenericUtils.verifyWebElementTextEquals(elementEndDate, EndDate)) {
        status = false;
        message.append("End Date: ");
      }
      if (!GenericUtils.verifyWebElementTextEquals(elementLastPrem, LastPrem)) {
        status = false;
        message.append("Last Year Premium: ");
      }
      if (!GenericUtils.verifyWebElementTextEquals(elementRenPrem, RenPrem)) {
        status = false;
        message.append("Renewal Premium: ");
      }
      if (!renewalBtn.isDisplayed()) {
        status = false;
        message.append("Renewal my policy button is not present on dashboard: ");
      }
      if (!viewRnwlDocBtn.isDisplayed()) {
        status = false;
        message.append("View renewal documents button is not present on dashboard");
      }

      return status;
    } catch (Exception e) {
      throw new Exception(
          "Error while validating Renewal Policy section for policy number " + policyNo);
    }
  }

  /**
   * To validate renewal documents section
   * 
   * @param test
   * @param screenshot
   * @throws Exception
   */
  public void validateRenewalDocuments(Entry<String, HashMap<String, String>> test,
      ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {

      // Entry<String, HashMap<String, String>> test
      String policyNo = test.getKey();
      String btnXpath =
          renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix + viewDocumentsBtn;
      viewRnwlDocBtn = driver.findElement(By.xpath(btnXpath));
      int counter = 0;

      WaitUtils.waitForelementToBeClickable(driver, viewRnwlDocBtn,
          "View renewal documents button is not appearing");
      viewRnwlDocBtn.click();
      WaitUtils.waitForSpinner(driver);

      btnXpath =
          renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix + renewalDocumentsSection;
      renewalDocSection = driver.findElement(By.xpath(btnXpath));
      if (renewalDocSection.isDisplayed()) {
        btnXpath = renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix
            + renewalDocumentsSection + documentCountList;
        renewalDocList = driver.findElements(By.xpath(btnXpath));
        if (renewalDocList.size() == 1) {
          btnXpath = renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix
              + renewalDocumentsSection + documentCountList + documentTextList;
          renewalDocTxtList = driver.findElements(By.xpath(btnXpath));
          for (WebElement txtList : renewalDocTxtList) {
            String docName = txtList.getText().trim();
            if (docName.contains("PDF")) {
              Log.pass("The renewal document are generated sucessfully with PDF type for URN: "
                  + policyNo, driver, extentedReport, screenshot);
              break;
            } else
              counter++;
          }
          if (counter > 1) {
            Log.fail("The renewal document type is not PDF for URN: " + policyNo, driver,
                extentedReport, screenshot);
          }
        } else
          Log.fail("The renewal document list size is not equal to one for URN: " + policyNo,
              driver, extentedReport, screenshot);
      } else
        Log.fail("The documents section table is not displayed on dashboard for URN: " + policyNo,
            driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while validating renewal policy documents ");
    }
  }

  /**
   * Click Renew My policy button
   * 
   * @param policyNo
   * @param screenshot
   * @return
   * @throws Exception
   */
  public YourQuotePageFromPP clickRenewMyPolicy(String policyNo, ExtentTest extentedReport,
      boolean screenshot) throws Exception {
    try {

      String btnXpath =
          renewPolicySectionPrefix + policyNo + renewPolicySectionSuffix + renewPolicyBtn;
      renewalBtn = driver.findElement(By.xpath(btnXpath));

      WaitUtils.waitForElementPresent(driver, renewalBtn,
          "Renew my policy button is not displayed.");
      WaitUtils.waitForelementToBeClickable(driver, renewalBtn,
          "Renew my policy button is not clickable.");
      GenericUtils.javaScriptExecutorToClick(driver, renewalBtn);
      Log.message("Clicked Renew my policy button successfully.", driver, extentedReport);
      WaitUtils.waitForOnlySpinner(driver);
      return new YourQuotePageFromPP(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while Clicking on Renew My Policy button : " + e);
    }
  }


  /**
   * Validate renewal home message when renewal is possible in SS
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateRenewalHomeMessagePossibleRenw(String policyURN, String messageExpected,
      WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
    boolean status = false;
    StringBuffer message = new StringBuffer("Issue: ");
    try {
      String btnXpath = renewPolicySectionPrefix + policyURN + renewPolicySectionSuffix
          + renewalMessageWithBoldTag;
      renewalMessage = driver.findElement(By.xpath(btnXpath));

      if (renewalMessage.getText().trim().equals(messageExpected)) {
        status = true;
      } else
        message.append("The message is not as expected");

      Log.softAssertThat(status, "Renewal message displayed is as expected",
          "Renewal message validation failed. " + message, driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while validating renewal home message : " + e);
    }
  }

  /**
   * Validate renewal home message when renewal is not possible in SS
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateRenewalHomeMessageNotPossibleRenw(String policyURN, String messageExpected,
      WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
    boolean status = false;
    StringBuffer message = new StringBuffer("Issue: ");
    try {
      String btnXpath = renewPolicySectionPrefix + policyURN + renewPolicySectionSuffix
          + renewalMessageWithNoBoldTag;
      renewalMessage = driver.findElement(By.xpath(btnXpath));
      btnXpath = renewPolicySectionPrefix + policyURN + renewPolicySectionSuffix + renewPolicyBtn;
      renewalBtn = driver.findElement(By.xpath(btnXpath));

      if (renewalMessage.getText().trim().equals(messageExpected)) {
        status = true;
      } else
        message.append("The message is not as expected");
      if (!renewalBtn.isDisplayed()) {
        status = true;
      } else
        message.append("Renew my policy button is displayed");
      Log.softAssertThat(status, "Renewal message displayed is as expected",
          "Renewal message validation failed. " + message, driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while validating renewal home message : " + e);
    }
  }

  /**
   * Validate renewal home message when renewal completed in SS for future dated policy
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateRenewalHomeMessageafterRenewalFutureDate(String policyURN,
      String messageExpectedPrefix, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
      throws Exception {
    boolean status = false;
    StringBuffer message = new StringBuffer("Issue: ");
    try {
      String btnXpath = renewPolicySectionPrefix + policyURN + renewPolicySectionSuffix
          + renewalMessageWithBoldTag;
      renewalMessage = driver.findElement(By.xpath(btnXpath));
      String startDateXpath =
          renewPolicySectionPrefix + policyURN + renewPolicySectionSuffix + startDate;
      WebElement elemenStartDate = driver.findElement(By.xpath(startDateXpath));

      String dateVal = elemenStartDate.getText().trim();
      String messageExpected = messageExpectedPrefix + " " + dateVal;
      System.out.println(messageExpected);
      if (renewalMessage.getText().trim().equals(messageExpected)) {
        status = true;
      } else
        message.append("The future dated message after policy is renewed is not as expected");
      Log.softAssertThat(status, "Renewal message displayed is as expected",
          "Renewal message validation failed. " + message, driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while validating renewal home message : " + e);
    }
  }

  /**
   * Validate renewal status and message on success
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateRenewalSuccess(WebDriver driver, ExtentTest extentedReport,
      boolean screenshot) throws Exception {
    boolean status = false;
    try {
      if (!renewalBtn.isDisplayed()) {
        status = true;
      }
      Log.softAssertThat(status, "Renewal was successful", "Renewal was unsucessful", driver,
          extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while Clicking on Renew My Policy button : " + e);
    }
  }

  /**
   * To sign out from the application
   * 
   * @return
   * @throws Exception
   */
  public CustomerSelfService signOut() throws Exception {
    try {
      WaitUtils.waitForElementPresent(driver, userAccountDropdown,
          "User name dropdown not present.");
      WaitUtils.waitForelementToBeClickable(driver, userAccountDropdown, "User ");
      GenericUtils.javaScriptExecutorToClick(driver, userAccountDropdown);
      WaitUtils.waitForElementPresent(driver, signOutOption, "Sign out option is not present");
      signOutOption.click();
      WaitUtils.waitForSpinner(driver);
      return new CustomerSelfService(driver, TestRunnerRenewals.website_SS, extentedReport);
    } catch (Exception e) {
      throw new Exception("Error while signing out.");
    }
  }

  /**
   * To validate warning message when user is suspended with GDPR
   * 
   * @param driver
   * @param extentedReport
   * @param gdprWarningMessage
   * @param screenshot
   * @throws Exception
   */
  public void validateWarningMessageForSuspendedUser(WebDriver driver, ExtentTest extentedReport,
      String gdprWarningMessage, boolean screenshot) throws Exception {
    boolean status = false;
    try {
      String message = gdprMessage.getText();
      if (gdprMessage.isDisplayed()) {
        if (message.equals(gdprWarningMessage)) {
          status = true;
          Log.pass("GDPR Warning message is dispayed correctly", driver, extentedReport,
              screenshot);
        }
      }
      Log.softAssertThat(status, "Renewal functionality is working correctly",
          "Renewal functionality is not working", driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while Clicking on Renew My Policy button : " + e);
    }
  }

  private void initRenewalButn(String policyURN) {
    String btnXpath =
        renewPolicySectionPrefix + policyURN + renewPolicySectionSuffix + renewPolicyBtn;
    renewalBtn = driver.findElement(By.xpath(btnXpath));
  }

  /**
   * Validate the renewal button is not appearing when user is suspended with GDPR
   * 
   * @param driver
   * @param extentedReport
   * @param policyURN
   * @param screenshot
   * @throws Exception
   */
  public void validateRenewalButtonForSuspendedUser(WebDriver driver, ExtentTest extentedReport,
      String policyURN, boolean screenshot) throws Exception {
    try {
      initRenewalButn(policyURN);
      if (renewalBtn.isDisplayed()) {
        Log.fail("Renewal button is appearing for Suspended user", driver, extentedReport,
            screenshot);
      } else
        Log.pass("Renewal button is not appearing for Suspended user", driver, extentedReport,
            screenshot);
    } catch (NoSuchElementException e) {
      throw new Exception("Error while verifying Renewal Button for suspended user : " + e);
    }
  }

}
