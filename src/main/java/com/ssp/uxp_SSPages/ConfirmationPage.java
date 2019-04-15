package com.ssp.uxp_SSPages;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.NoSuchElementException;
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
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

public class ConfirmationPage extends LoadableComponent<ConfirmationPage>{
  
  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  private String screenType;
  ExtentTest extentedReport;
   
 /* @FindBy(css = "#C2__TXT_8A3A5CE79D52CE56550183") 
  WebElement paymentScreenHeaderInvoice;
  
  @FindBy(css = "#C2__TXT_C754A725E796A7AB4032783")
  WebElement paymentScreenHeader;*/
  
  @FindBy(xpath = "//h4[contains(text(),'Confirmation of payment')]")
  List<WebElement> paymentScreenHeader;

  @FindBy(xpath = "//div[contains(@id,'FMT_E909EEA4713F3E8C1612235')]//div[contains(@class,'container container-table')][not(contains(@style,'display: none'))]"
      + "//div[@class='panel-body clearfix']//div[@class='row']/div[not(contains(@style,'hidden'))]/div[not(contains(@style,'display: none'))]//div[contains(text(),'is successful')]")
  WebElement paymentSuccessfullMessage;

  @FindBy(xpath = "//div[contains(@id,'FMT_E909EEA4713F3E8C1612235')]//div[contains(@class,'container container-table')][not(contains(@style,'display: none'))]"
      + "//div[@class='panel-body clearfix']//div[@class='row']/div[not(contains(@style,'hidden'))]/div[not(contains(@style,'display: none'))]//div[contains(text(),'Thank')]")
  WebElement paymentThanksMessage;

  @FindBy(xpath = "//div[contains(@id,'FMT_E909EEA4713F3E8C1612235')]//div[contains(@class,'container container-table')][not(contains(@style,'display: none'))]"
      + "//div[@class='panel-body clearfix']//div[@class='row']/div[not(contains(@style,'hidden'))]/div[not(contains(@style,'display: none'))]//div[contains(text(),'Your policy')]")
  WebElement paymentPolicyURNMessage;

  @FindBy(xpath = "//div[contains(@id,'FMT_E909EEA4713F3E8C1612235')]//div[contains(@class,'container container-table')][not(contains(@style,'display: none'))]"
      + "//div[@class='panel-body clearfix']//div[@class='row']/div[not(contains(@style,'hidden'))]/div[not(contains(@style,'display: none'))]//div[contains(text(),'Your policy')]//b")
  WebElement paymentPolicyURN;
  
  @FindBy(xpath = "//div[contains(@id,'FMT_E909EEA4713F3E8C1612235')]//div[contains(@class,'container container-table')][not(contains(@style,'display: none'))]"
      + "//div[@class='panel-body clearfix']//div[@class='row']/div[not(contains(@style,'hidden'))]/div[not(contains(@style,'display: none'))]//div[contains(text(),'reference')]")
  WebElement paymentRefMessage;
  
  @FindBy(xpath = "//div[contains(@id,'FMT_E909EEA4713F3E8C1612235')]//div[contains(@class,'container container-table')][not(contains(@style,'display: none'))]"
      + "//div[@class='panel-body clearfix']//div[@class='row']/div[not(contains(@style,'hidden'))]/div[not(contains(@style,'display: none'))]//div[contains(text(),'reference')]//b")
  WebElement paymentRefNo;

  @FindBy(css = "button[value*='Back to dashboard']")
  WebElement paymentScreenBackToDashBoardButton;

  @FindBy(xpath = "//div[contains(@id,'FMT_E909EEA4713F3E8C1612235')]//div[contains(@class,'container container-table')][not(contains(@style,'display: none'))]"
      + "//div[@class='panel-body clearfix']//div[@class='row']/div[not(contains(@style,'hidden'))]/div[not(contains(@style,'display: none'))] //img")
  WebElement paymentDDSuccessImage;
  
  @FindBy(xpath = "//div[contains(@id,'FMT_E909EEA4713F3E8C1612235')]//div[contains(@class,'container container-table')][not(contains(@style,'display: none'))]"
      + "//div[@class='panel-body clearfix']//div[@class='row']/div[not(contains(@style,'hidden'))]/div[not(contains(@style,'display: none'))]//div[contains(text(),'total premium')]")
  WebElement payLaterMessage;

  @FindBy(css = "div[id*='TXT_C754A725E796A7AB3990908'] * div:nth-child(1) b ")
  WebElement paymentUnsuccessfullMessage;
  
  @FindBy(css = "button[title='Back to Payment Screen']")
  WebElement backToPaymentPage;

  
  public ConfirmationPage (WebDriver driver, ExtentTest extentedReport) {
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }

  @Override
  protected void load() {
    // TODO Auto-generated method stub
    if (!isPageLoaded) {
      Assert.fail();
    }
    for(int i=0;i<paymentScreenHeader.size();i++){
      try{
        if(paymentScreenHeader.get(i).isDisplayed()){
          Log.pass("Confirmation page is loaded successfully", driver, extentedReport, true);
        }
        else
          Log.fail("Payment header is not displayed", driver, extentedReport, true);
      }catch(NoSuchElementException e){
        if(i<paymentScreenHeader.size()-1){
          continue;
        }else
          Log.fail("Confirmation page did not open up", driver, extentedReport, true);
          Assert.fail();
      }      
    }
    //WaitUtils.waitForElementPresent(driver, paymentScreenHeader, "Confirmation page did not open up");
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void isLoaded() throws Error {
    // TODO Auto-generated method stub
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

  /**
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param policyURN
   * @throws Exception
   */
  public void validatePaymentStatus(HashMap<String,String> testdata, HashMap<String,String> policyDetails, Properties testDataSS, boolean paymentType, WebDriver driver,
      ExtentTest extentedReport, String policyURN, boolean screenshot) throws Exception {
    String paymentMethod,renewalAmount,payLaterPmtMsg,paymentSucessMsg,paymentThanksMsg,paymentPolicyNoMsg,paymentRefNoMsg,
    paymtSuccMsg,policyURNMessage,urnMessage,thanksMessage,payLaterMessageOnScreen,policyReferenceMessage,refNo,compRefMessage;
    try {
      
      paymentSucessMsg = testDataSS.getProperty("paymentSucessMsg");
      paymentThanksMsg = testDataSS.getProperty("paymentThanksMsg");
      paymentPolicyNoMsg = testDataSS.getProperty("paymentPolicyNoMsg");
      paymentRefNoMsg =  testDataSS.getProperty("paymentRefNoMsg");
      renewalAmount = policyDetails.get("RenewalPremium");
      payLaterPmtMsg = testDataSS.getProperty("payLaterMessagePrefix")+""+renewalAmount+" "+testDataSS.getProperty("payLaterMessageSuffix");      
      if(!paymentType){
        paymentMethod = testdata.get("Payment Method");
      }else  
        paymentMethod = testdata.get("NewPaymentMethod");
     
      // Verify payment success image 
      if (paymentDDSuccessImage.isDisplayed())
        Log.pass("Payment success image is appearing", driver, extentedReport, screenshot);
      else
        Log.fail("Payment success image is not appearing", driver, extentedReport, screenshot);
      
      // Verify payment success message
      if (!paymentMethod.equalsIgnoreCase("Invoice")) {
        paymtSuccMsg = paymentSuccessfullMessage.getText().trim();
        if (paymtSuccMsg.equals(paymentSucessMsg)) {
          Log.pass(
              "Payment successful message is appearing. Actual displayed message: "+paymtSuccMsg+" ,Expected message: "+paymentSucessMsg,
              driver, extentedReport, screenshot);
        } else
          Log.fail(
              "Payment successful message is not appearing. Actual displayed message: "+paymtSuccMsg+" ,Expected message: "+paymentSucessMsg,
              driver, extentedReport, screenshot);
      }
      
      // Verify payment policy URN message
      policyURNMessage = paymentPolicyURNMessage.getText().trim();
      urnMessage = paymentPolicyNoMsg + " "+policyURN;
      System.out.println("----------------------"+urnMessage+"-----------------------");
    
      if (policyURNMessage.equalsIgnoreCase(urnMessage)) {
        Log.pass("Correct payment message is appearing. Actual displayed message: "+policyURNMessage+" ,Expected message: "+urnMessage, driver,
            extentedReport, screenshot);
      } else
        Log.fail("Incorrect payment message is appearing. Actual displayed message: "+policyURNMessage+" ,Expected message: "+urnMessage, driver,
            extentedReport, screenshot);
      
      // Verify payment success thanks message
      thanksMessage = paymentThanksMessage.getText().trim();
      if (thanksMessage.equalsIgnoreCase(paymentThanksMsg)) {
        Log.pass("Payment Successful message is appearing. Actual displayed message: "+thanksMessage+" ,Expected message: "+paymentThanksMsg,
            driver, extentedReport, screenshot);
      } else
        Log.fail("Payment Successful message is not appearing. Actual displayed message: "+thanksMessage+" ,Expected message: "+paymentThanksMsg,
            driver, extentedReport, screenshot);
      
      // Verify pay later message
      if(paymentMethod.equalsIgnoreCase("Invoice")){
        payLaterMessageOnScreen = payLaterMessage.getText().trim();
        System.out.println("----------------------"+payLaterMessageOnScreen+"-----------------------");
        if(payLaterMessageOnScreen.contains(payLaterPmtMsg)){
          Log.pass("Pay later message is appearing. Actual displayed message: "+payLaterMessageOnScreen+" ,Expected message without date: "+payLaterPmtMsg,
              driver, extentedReport, screenshot);
        }
        else
          Log.fail("Pay later message is not appearing. Actual displayed message: "+payLaterMessageOnScreen+" ,Expected message without date: "+payLaterPmtMsg,
              driver, extentedReport, screenshot);
      }

      // Verify payment reference message
      if(paymentMethod.equalsIgnoreCase("Card")){
        policyReferenceMessage = paymentRefMessage.getText().trim();
        refNo = paymentRefNo.getText().trim();
        compRefMessage = paymentRefNoMsg+ " "+refNo;      
        System.out.println("----------------------"+compRefMessage+"-----------------------");

        if (policyReferenceMessage.equalsIgnoreCase(compRefMessage)) {
          Log.pass("Correct payment reference message is appearing. Actual displayed message: "+policyReferenceMessage+" ,Expected message: "+compRefMessage, driver,
              extentedReport, screenshot);
        } else
          Log.fail("Incorrect payment reference message is appearing. Actual displayed message: "+policyReferenceMessage+" ,Expected message: "+compRefMessage, driver,
              extentedReport, screenshot);
      }    
    } catch (Exception e) {
      throw new Exception("Error while validating the Payment Confirmation Screen " + e);
    }
  }
  
  /**
   * @param driver
   * @param extentedReport
   * @throws Exception
   */
  public SelfServiceCustomerDashboard clickOnBackToYourDashboardButtonSSPayment(WebDriver driver, ExtentTest extentedReport, boolean screenshot)
      throws Exception {
    try {
      Log.event("Entered method clickOnBackToYourDashboardButtonSSPayment");
      WaitUtils.waitForElementPresent(driver, paymentScreenBackToDashBoardButton, "'Back to dashboard' button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, paymentScreenBackToDashBoardButton, "'Back to dashboard' button is not clickable");
      GenericUtils.clickButton(paymentScreenBackToDashBoardButton, driver, extentedReport, true);
      Log.message("Clicked on 'Back to dashboard' button", driver, extentedReport, screenshot);
      return new SelfServiceCustomerDashboard(driver).get();
    } catch (Exception e) {
      throw new Exception("Error while clicking on Back to Dashboard button from Payment Screen " + e);
    }
  }

  /**
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param policyURN
   * @throws Exception
   */
  public void validateUnsuccessfullPaymentScreen(Properties testdataSS, WebDriver driver,
      ExtentTest extentedReport) throws Exception {
    try {
      WaitUtils.waitForSpinner(driver);
      if ((paymentUnsuccessfullMessage.getText())
          .equalsIgnoreCase(testdataSS.getProperty("paymentUnsucessfull"))) {
        Log.pass("Payment Unsuccessful message is appearing: " + paymentUnsuccessfullMessage.getText(),
            driver, extentedReport, true);
      } else
        Log.fail(
            "Payment Successful message is not appearing: " + paymentUnsuccessfullMessage.getText(),
            driver, extentedReport, true);
      }
      catch (Exception e) {
        throw new Exception("Error while validating the Payment Failure Screen " + e);
      }
    }
  
  /**
   * @param driver
   * @param extentedReport
   * @throws Exception
   */
  public PaymentPage clickOnBackToPaymentButton(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForSpinner(driver);
      WaitUtils.waitForElementPresent(driver, backToPaymentPage, "'Back to payment' button is not present");
      WaitUtils.waitForelementToBeClickable(driver, backToPaymentPage, "'Back to payment' button is not clickable");
      GenericUtils.javaScriptExecutorToClick(driver, backToPaymentPage);
      WaitUtils.waitForOnlySpinner(driver);
      //GenericUtils.clickButton(backToPaymentPage, driver, extentedReport, true);
      Log.message("Clicked on 'Back to payment' button", driver, extentedReport, screenshot);
      return new PaymentPage(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while clicking on back to payment screen button " + e);
    }
  }
}
