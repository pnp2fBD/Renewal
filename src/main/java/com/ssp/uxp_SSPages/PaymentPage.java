package com.ssp.uxp_SSPages;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import cucumber.stepdefination.runner.TestRunnerRenewals;

public class PaymentPage extends LoadableComponent<PaymentPage> {

  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  ExtentTest extentedReport;
  
  private String addCardErrorMessage;
  private String existPaymtPlan;
  private String existPaymtMthd;
  private String newPaymtPlan;
  private String newPaymtMthd;
  private String existBankAcct;
  private String existBankSortCode;
  private String newBankAcct;
  private String newBankSortCode;
  private String newBankAcctName;
  
  private void setExistPaymtPlanMthd(HashMap<String,String> testdata) throws Exception {
    this.existPaymtPlan = testdata.get("Payment Plan");
    this.existPaymtMthd = testdata.get("Payment Method");
      if(existPaymtPlan.equals("")||existPaymtMthd.equals("")){
        throw new Exception("Please enter 'Payment Plan' & 'Payment Method' in testdata sheet");
      }    
  }
  
  private void setExistBankDetails(HashMap<String,String> testdata) throws Exception {
    this.existBankAcct = testdata.get("Account Number");
    this.existBankSortCode = testdata.get("Sort Code");
      if(existBankAcct.equals("")||existBankSortCode.equals("")){
        throw new Exception("Please enter 'Account Number' & 'Sort Code' in testdata sheet");
      }    
  }
  
  private void setNewBankDetails(HashMap<String,String> testdata) throws Exception {
    this.newBankAcct = testdata.get("SSAccountNo");
    this.newBankSortCode = testdata.get("SSSortCode");
    this.newBankAcctName = testdata.get("SelfSeviceAccountName");
      if(newBankAcct.equals("")||newBankSortCode.equals("")||newBankAcctName.equals("")){
        throw new Exception("Please enter 'SSAccountNo' , 'SSSortCode' & 'SelfSeviceAccountName' in testdata sheet");
      }    
  }

  private void setNewPaymtPlanMthd(HashMap<String,String> testdata) throws Exception {
    this.newPaymtPlan = testdata.get("NewPaymentPlan");
    this.newPaymtMthd = testdata.get("NewPaymentMethod");
      if(newPaymtPlan.equals("")||newPaymtMthd.equals("")){
        throw new Exception("Please enter NewPaymentPlan & NewPaymentMethod in testdata sheet");
      }    
  }

  private void setAddCardErrorMessage(HashMap<String,String> testdata) throws Exception {
    this.addCardErrorMessage = testdata.get("AddCardErrMsg");
    String paymentMethod = testdata.get("Payment Method");
    if(paymentMethod.equalsIgnoreCase("Card")){
      if(addCardErrorMessage.equals("")){
        throw new Exception("Please enter value for AddCardErrMsg field in testdata sheet");
      }
    }
  }

  private String xpathBankDetailsPrefix = "//span[contains(text(),'"; 
  private String xpathBankDetailsSuffix = "')]";
  private String xpathSelectedBankAcctCheckBoxSuffix = "/ancestor::td/following-sibling::td//input[contains(@name,'ACCOUNTSELECTED')]";
  private String xpathSelectedCardPrefix = "//span[contains(text(),'";
  private String xpathSelectedCardSuffix = "')]/ancestor::td/following-sibling::td//input[contains(@name,'CARDDETAILS[1].ISROWSELECTED')]";
  private String xpathAddedBankSuffix = "')]/ancestor::tr//td[contains(@id,'C2__p4_QUE_BCAD84F421A74867452723_R')]//input";

  @FindBy(css = "iframe[id='wp-cl-custom-html-iframe']")
  WebElement frame_sagepay;
  
  @FindBy(css = "label[for*='QUE_EE2C7D9B8CC571FB1612284']")
  WebElement bankNameTextOnAddBankWindow;
  
  @FindBy(css = "h3[id*='C2__HEAD_01CAD4434CEC5950835273'] p")
  WebElement addCardErrorMsg;
  
  @FindBy(css = "#C2__TXT_3661A386F2BF3E14951119")
  WebElement paymentHeader;
  
  @FindBy(css = "label[for*='C2__QUE_E2BF6E7D321C01D51456374']")
  WebElement ddTandCCheckBox;
  
  @FindBy(css = "label[for*='C2__QUE_F7ECEE974F60E04B1504371']")
  List<WebElement> policyHolderOptionsDD;
  
  @FindBy(css = "label[for*='C2__QUE_F7ECEE974F60E04B1504371'] span")
  List<WebElement> policyHolderOptionsDDText;

  @FindBy(css = "#C2__HEAD_F7ECEE974F60E04B1500876")
  WebElement yourAccountDDMessage;
  
  @FindBy(css = "#C2__HEAD_70848E5D22D97546644779")
  WebElement yourAccountCardMessage;
  
  @FindBy(xpath = "//input[contains(@name,'PAYMENTPLAN')]/parent::label")
  List<WebElement> paymentPlanToSelect;
  
  @FindBy(css = "input[name*='PAYMENTPLAN']")
  List<WebElement> totalPaymentPlans;

  @FindBy(css = "div[id*='H_C2__QUE_3661A386F2BF3E14953402']")
  List<WebElement> paymentPlanName;

  @FindBy(css = "input[id*='96B66BDF8A5235B43213476']")
  List<WebElement> totalPaymentMethods;
  
  @FindBy(css = "label[for*='QUE_96B66BDF8A5235B43213476']")
  List<WebElement> paymentMethodToSelect;

  @FindBy(css = "label[for*='QUE_96B66BDF8A5235B43213476'] span")
  List<WebElement> paymentMethodName;

  @FindBy(css = "label[for*='70848E5D22D97546965865'] span")
  List<WebElement> policyHolderOptionsTextCard;
  
  @FindBy(css = "label[for*='70848E5D22D97546965865']")
  List<WebElement> policyHolderOptionsCard;

  @FindBy(css = "#C2__HEAD_70848E5D22D975462029376")
  WebElement policyHolderOptionsmessage;
  
  @FindBy(css = "button[title='Pay later']")
  WebElement payLaterButton;

  @FindBy(css = "button[title='Buy']")
  WebElement buyButton;

  @FindBy(css = "table[summary*='Previously Used Cards'] tbody tr")
  List<WebElement> totalNumberOfCardsUsed;

  @FindBy(css = "span[id*='C2__QUE_F8C0B01720158414427343']")
  List<WebElement> cardNumbers;
  
  @FindBy(css = "span[id*='C2__QUE_71C9ACD6B22EE44A577241']")
  List<WebElement> cardType;

  @FindBy(css = "input[id*='71C9ACD6B22EE44A808865_0']")
  WebElement selectCard;

  @FindBy(css = "button[title='Add new card']")
  WebElement addNewCardButton;

  @FindBy(css = "#C2__p4_BUT_3661A386F2BF3E141556762")
  WebElement backToYourQuote;

  @FindBy(css = "button[id*='RenewalSave']")
  WebElement saveRenewal;

  @FindBy(css = "input[name*='ISBANKACCOUNT']")
  List<WebElement> DDAccountConfirmationOptions;

  @FindBy(
      css = "div [class='radio radio-info radio-inline '] label[for*='C2__QUE_F7ECEE974F60E04B1504371_']")
  List<WebElement> DDAccountConfirmationOptionsList;

  @FindBy(css = "div[class*='TCConfirm']")
  WebElement directDebitAgreement;

  @FindBy(css = "div[id*='BUT_96B66BDF8A5235B44731444'] button[title='Use another bank account']")
  WebElement useAnotherBankAccountButton;

  @FindBy(css = "table[summary*='BankDetails'] tbody tr")
  List<WebElement> existingBankRecord;

  @FindBy(css = "button[id='C2__BUT_0E5DECEF635964D9466536']")
  WebElement addNewBankAccountButton;

  @FindBy(css = "span[id*='C2__QUE_EE2C7D9B8CC571FB2770385_R1']")
  WebElement existingSortCode;

  @FindBy(css = "span[id*='C2__QUE_EE2C7D9B8CC571FB2770391_R1']")
  WebElement existingAccountnumber;

  @FindBy(css = "span[id*='C2__QUE_EE2C7D9B8CC571FB2770389_R1']")
  WebElement existingAccountName;

  @FindBy(css = "input[name*='ACCOUNTNUMBER']")
  WebElement ddAccountNumber;

  @FindBy(css = "input[name*='ACCOUNTNAME']")
  WebElement ddAccountName;

  @FindBy(css = "input[name*='SORTCODE']")
  WebElement ddSortCode;

  @FindBy(css = "div[id*='BUT_7EB5E9A4B7ED0416563296'] button[title='Cancel']")
  WebElement cancelNewAccountDetails;

  @FindBy(css = "button[id*='DIRECT_DEBIT_SAVE']")
  WebElement saveNewAccountDetails;

  @FindBy(css = "#C2__TXT_C754A725E796A7AB4032783")
  WebElement paymentScreenHeader;

  @FindBy(css = "div[id*='TXT_7224C8E0646999EB619114']  div:nth-child(2)")
  WebElement paymentSuccessfullMessage;

  @FindBy(css = "div[id*='TXT_7224C8E0646999EB619114']  div:nth-child(3)")
  WebElement paymentThanksMessage;

  @FindBy(css = "div[id*='TXT_7224C8E0646999EB619114']  div:nth-child(4)")
  WebElement paymentPolicyURNMessage;

  @FindBy(css = "div[id*='TXT_7224C8E0646999EB619114']  div:nth-child(4) b")
  WebElement paymentPolicyURN;

  @FindBy(css = "button[value*='Back to Dashboard']")
  WebElement paymentScreenBackToDashBoardButton;

  @FindBy(css = "div[id*='TXT_7224C8E0646999EB619114']  div[id*='gif-success']")
  WebElement paymentDDSuccessImage;

  @FindBy(css = "div[id*='TXT_C754A725E796A7AB3990908'] * div:nth-child(1) b ")
  WebElement paymentUnsuccessfullMessage;

  @FindBy(css = "button[title='Back to Payment Screen']")
  WebElement backToPaymentPage;

  @FindBy(css = "li[class*='dropdown'] a[class*='dropdown-toggle']")
  WebElement welcomeUserDropdown;
  
  @FindBy(xpath = "//a[contains(text(),'Back to Dashboard')]")
  WebElement welcomeUserDropdownBackToDash;
  
  @FindBy(xpath = "//a[contains(text(),'Sign Out')]")
  WebElement welcomeUserDropdownSignOut;
  
  @FindBy(xpath = "//span[contains(text(),'Add bank account details')]/ancestor::div[contains(@class,'modal-popup-content clearfix')]")
  WebElement addBankDetSection;
  
  @FindBy(css = "div[id*='p4_QUE_70848E5D22D97546965865'] span[id*='ERRORMESSAGE']")
  WebElement policyHolderReqdFieldTxt;

  @FindBy(css = "div[id*='p4_QUE_9A49495A6377EE5B2345165'] span[id*='ERRORMESSAGE']")
  WebElement addCardReqdFieldTxt;
  
  @FindBy(css = "div[id*='p4_QUE_96B66BDF8A5235B43213476'] span[id*='ERRORMESSAGE']")
  WebElement selectPmtMthdReqdFieldTxt;
  
  @FindBy(xpath = "//input[contains(@name,'ISBANKACCOUNTEXIST')]/parent::div //span[contains(@id,'ERRORMESSAGE')]")
  WebElement addAcctReqdFieldTxt;
  
  @FindBy(css = "div[id*='p4_QUE_E2BF6E7D321C01D51456374'] span[id*='ERRORMESSAGE']")
  WebElement ddTCReqdFieldTxt;
  

  public PaymentPage(WebDriver driver, ExtentTest extentedReport) {
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }

  @Override
  protected void isLoaded() {
    if (!isPageLoaded) {
      Assert.fail();
    }
    if (isPageLoaded && !paymentHeader.isDisplayed()) {
      Log.fail("User is not navigated to Payment page", driver, extentedReport);
      Assert.fail();
    }
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }


  /**
   * Return to Dashboard from Payment page
   * 
   * @return
   * @throws Exception
   */
  public SelfServiceCustomerDashboard returnToDashBoard(boolean screenshot) throws Exception{
    try{
      Actions action = new Actions(driver);
      WaitUtils.waitForSpinner(driver);
      action.sendKeys(Keys.PAGE_UP).build().perform();
      WaitUtils.waitForElement(driver, welcomeUserDropdown);
      WaitUtils.waitForelementToBeClickable(driver, welcomeUserDropdown, "Welcome dropdown not clickable");
      welcomeUserDropdown.click();
      WaitUtils.waitForElement(driver, welcomeUserDropdownBackToDash);
      WaitUtils.waitForelementToBeClickable(driver, welcomeUserDropdownBackToDash, "Back to Dashboard not clickable");
      welcomeUserDropdownBackToDash.click();
      Log.message("User has clicked Back to dashboard button", driver, extentedReport, screenshot);
      WaitUtils.waitForSpinner(driver);
      return new SelfServiceCustomerDashboard(driver).get();
    }catch(Exception e){
      throw new Exception("Error on clicking back to dashboard button."+e);
      }   
  }
  
  /**
   * Signout of application from Payment page
   * 
   * @return
   * @throws Exception
   */
  public CustomerSelfService signout(boolean screenshot) throws Exception{
    try{
      Actions action = new Actions(driver);
      WaitUtils.waitForSpinner(driver);
      action.sendKeys(Keys.PAGE_UP).build().perform();
      WaitUtils.waitForElement(driver, welcomeUserDropdown);
      WaitUtils.waitForelementToBeClickable(driver, welcomeUserDropdown, "Welcome dropdown not clickable");
      welcomeUserDropdown.click();
      WaitUtils.waitForElement(driver, welcomeUserDropdownSignOut);
      WaitUtils.waitForelementToBeClickable(driver, welcomeUserDropdownSignOut, "Sign Out not clickable");
      welcomeUserDropdownSignOut.click();
      Log.message("User has clicked Sign Out button", driver, extentedReport, screenshot);
      WaitUtils.waitForSpinner(driver);
      return new CustomerSelfService(driver, TestRunnerRenewals.website_SS, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Error on clicking signout button."+e);
      }   
  }
  
  /**
   * To validate selected payment plan 
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @throws Exception
   */
  public void validateSelectedPaymentPlan(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      setExistPaymtPlanMthd(testdata);
      if (GenericUtils.validateSelectedRadioBtnSubString(totalPaymentPlans, paymentPlanName, existPaymtPlan,
          driver, extentedReport, screenshot)) {
        Log.pass("The correct Payment Plan is highlighted: " + existPaymtPlan, driver,
            extentedReport, screenshot);
      } else
        Log.fail("Incorrect Payment Plan is highlighted ", driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while validating the selected payment plan : " + e);
    }
  }

  /**
   * To validate selected payment method
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateSelectedPaymentMethod(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      if(existPaymtMthd.equals("Direct Debit")){
        existPaymtMthd = "Direct debit";
      }
      if (GenericUtils.validateSelectedRadioBtn(totalPaymentMethods, paymentMethodName,
          existPaymtMthd, driver, extentedReport, screenshot)) {
        Log.pass("The correct Payment Method is highlighted: " + existPaymtMthd, driver,
            extentedReport, screenshot);
      } else
        Log.fail("Incorrect Payment Method is highlighted ", driver, extentedReport, true);
    } catch (Exception e) {
      throw new Exception("Error while validating the selected payment method : " + e);
    }
  }
  
  /**
   * To make payment plan and method change in SS
   * 
   * @param testdata
   * @param screenshot
   * @throws Exception
   */
  public void makePaymentSS(HashMap<String, String> testdata, boolean screenshot) throws Exception{
    try{
      setNewPaymtPlanMthd(testdata);
      selectPaymentPlan(testdata, screenshot);
      selectPaymentMethod(testdata, screenshot);      
    } catch (Exception e) {
      throw new Exception("Error while making payment change in SS: " + e);
    }
  }
  
  /**
   * To select payment plan
   * 
   * @param testdata
   * @param screenshot
   * @throws Exception
   */
  public void selectPaymentPlan(HashMap<String, String> testdata, boolean screenshot) throws Exception {
    try {
      setNewPaymtPlanMthd(testdata);
      GenericUtils.selectRadioBtn(paymentPlanToSelect, paymentPlanName, newPaymtPlan, driver,
          extentedReport, screenshot);
      WaitUtils.waitForOnlySpinner(driver);
    } catch (Exception e) {
      throw new Exception("Error while selecting the Payment plan : " + e);
    }
  }

  /**
   * To select payment method
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void selectPaymentMethod(HashMap<String, String> testdata, boolean screenshot) throws Exception {
    try {
      if(newPaymtMthd.equals("Direct Debit")){
        newPaymtMthd = "Direct debit";      
        }
      GenericUtils.selectRadioBtn(paymentMethodToSelect, paymentMethodName, newPaymtMthd, driver,
          extentedReport, screenshot);
      WaitUtils.waitForOnlySpinner(driver);
    } catch (Exception e) {
      throw new Exception("Error while selecting the Payment method : " + e);
    }
  }

  /**
   * To select policy holder options
   * 
   * @param testdata
   * @param screenshot
   * @throws Exception
   */
  public void selectpolicyHolderEntryCard(HashMap<String, String> testdata, boolean screenshot) throws Exception {
    try {
      String expectedValue = testdata.get("PolicyHolderChoice");
      GenericUtils.selectRadioBtn(policyHolderOptionsCard, policyHolderOptionsTextCard, expectedValue, driver,
          extentedReport, screenshot);
      WaitUtils.waitForSpinner(driver);
    } catch (Exception e) {
      throw new Exception("Error while selecting the option 'Does the card belong to the policyholder?' " + e);
    }
  }

  public void validatePolicyHolderWithNoOptionSelected(HashMap<String, String> testdata,
      WebDriver driver, ExtentTest extentedReport) throws Exception {
    try {
      String expectedValue = testdata.get("PolicyHolderChoice");
      if (expectedValue.equalsIgnoreCase("No")) {
        if (policyHolderOptionsmessage.isDisplayed()) {
          if (GenericUtils.verifyWebElementStartWith(driver, policyHolderOptionsmessage,
              testdata.get("PolicyHolderChoiceNo"))) {
            Log.pass("Correct text is appearing", driver, extentedReport, true);
          } else {
            Log.fail("Incorrect text is appearing", driver, extentedReport, true);
          }
        } else
          Log.fail("The text is not appearing", driver, extentedReport, true);
        if (!buyButton.isDisplayed()) {
          Log.pass("Buy button is not appearing", driver, extentedReport, true);
        } else
          Log.fail("Buy button is appearing", driver, extentedReport, true);
      }
    } catch (Exception e) {
      throw new Exception(
          "Error while validating the Policy Holder With No Option Selected method " + e);
    }
  }

  /**
   * Validate existing card details
   *
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateExistingCardDetails(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      String cardAcntPymtMsg;
      String cardNo = testdata.get("Card Number");
      String cardNoLastFour = cardNo.substring(cardNo.length() - 4);
      String ssCardNo = "************" + cardNoLastFour;
      String xpath = xpathSelectedCardPrefix+ssCardNo+xpathSelectedCardSuffix;
      WebElement cardCheckBox = driver.findElement(By.xpath(xpath));
      WaitUtils.waitForElementPresent(driver, cardCheckBox, "Card check box is not present");

      for (WebElement card : cardNumbers) {
        if (card.getText().equals(ssCardNo)) {
          Log.pass("The Card Number " + card.getText() + " is displayed", driver,
              extentedReport, screenshot);
        } else
          Log.fail("The Card Number " + card.getText() + " is not displayed",
              driver, extentedReport, screenshot);
      }
     if(cardCheckBox.isSelected()){
       Log.pass("The existing card is selected by default", driver, extentedReport, screenshot);
     }else
       Log.fail("The existing card is not selected by default", driver, extentedReport, screenshot);
     
     String[] cardMessage = testdata.get("Card/DirectDebit_AccountMessage").split(",");
     cardAcntPymtMsg = cardMessage[0]+" "+ssCardNo+" "+cardMessage[1];
     
     if(yourAccountCardMessage.getText().equals(cardAcntPymtMsg)) {
       Log.pass("Card selected for payment message is displayed", driver, extentedReport, screenshot);
     }else
       Log.fail("Card selected for payment message is not displayed", driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while validating the existing card details : " + e);
    }
  }

  public void selectExistingCardForPayment(WebDriver driver, ExtentTest extentedReport)
      throws Exception {
    try {
      WaitUtils.waitForSpinner(driver);
      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Unable to Existing card Xheckbox"))
              .until(ExpectedConditions.elementToBeClickable(selectCard));
      GenericUtils.selectCheckBox(selectCard, driver, extentedReport, true);
    } catch (Exception e) {
      throw new Exception("Error while selecting the existing card checkbox " + e);
    }
  }

  /**
   * Add a new card
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public CardDetailsPageSS addNewCardForpayment(boolean screenshot) throws Exception {
    try {      
      WaitUtils.waitForElementPresent(driver, addNewCardButton, "Add New Card button is not displayed.");
      WaitUtils.waitForelementToBeClickable(driver, addNewCardButton, "Add New Card button is not clickable.");
      GenericUtils.clickButton(addNewCardButton, driver, extentedReport, screenshot);
      Log.message("Clicked Add a new card button on Payement page", driver, extentedReport, screenshot);
      WaitUtils.waitForSpinner(driver);
      return new CardDetailsPageSS(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while adding a new card " + e);
    }
  }
  
  /**
   * Add a new card when policy holder is not selected
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public PaymentPage addNewCardWithoutPolicyHolder(boolean screenshot) throws Exception {
    try {      
      WaitUtils.waitForElementPresent(driver, addNewCardButton, "Add New Card button is not displayed.");
      WaitUtils.waitForelementToBeClickable(driver, addNewCardButton, "Add New Card button is not clickable.");
      GenericUtils.clickButton(addNewCardButton, driver, extentedReport, screenshot);
      Log.message("Clicked Add a new card button on Payement page", driver, extentedReport, screenshot);
      WaitUtils.waitForSpinner(driver);
      return this;
    } catch (Exception e) {
      throw new Exception("Error while adding a new card " + e);
    }
  }
    
  /**
   * To validate add card error message
   * 
   * @param testdata
   * @param screenshot
   * @throws Exception
   */
  public void validateAddCardErrorMessage(HashMap<String,String> testdata,boolean screenshot) throws Exception{
    try {
      setAddCardErrorMessage(testdata);
      WaitUtils.waitForElementPresent(driver, addCardErrorMsg, "Add card error message is not displayed.");
      if(addCardErrorMsg.getText().equals(addCardErrorMessage)){
        Log.pass("Add card error message is displayed correctly", driver, extentedReport, screenshot);
      }else
        Log.fail("Add card error message is not displayed correctly", driver, extentedReport, screenshot);
      Log.message("Add card error message is displayed.", driver, extentedReport, screenshot);
      System.out.println("Message displayed: "+addCardErrorMsg.getText());
    } catch (Exception e) {
      throw new Exception("Error while validating add card error message" + e);
    }
  }
  
  /**
   * Click Buy button
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public ConfirmationPage clickOnPayLaterButton(boolean screenshot)
      throws Exception {
    try {
      WaitUtils.waitForElementPresent(driver, payLaterButton, "Pay later button is not displayed.");
      WaitUtils.waitForelementToBeClickable(driver, payLaterButton, "Pay later button is not clickable.");     
      GenericUtils.clickButton(payLaterButton, driver, extentedReport, true);
      Log.message("Clicked Pay later button on Payment page", driver, extentedReport, screenshot);
      WaitUtils.waitForOnlySpinner(driver);
      return new ConfirmationPage(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while clicking on Pay later button " + e);
    }
  }

  /**
   * Click Buy button
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public ConfirmationPage clickOnBuyButton(boolean screenshot)
      throws Exception {
    try {
      WaitUtils.waitForElementPresent(driver, buyButton, "Buy button is not displayed.");
      WaitUtils.waitForelementToBeClickable(driver, buyButton, "Buy button is not clickable.");     
      GenericUtils.clickButton(buyButton, driver, extentedReport, true);
      Log.message("Clicked Buy button on Payement page", driver, extentedReport, screenshot);
      WaitUtils.waitForSpinner(driver);
      return new ConfirmationPage(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while clicking on Buy button " + e);
    }
  }

  public YourQuotePageFromPP clickOnBackToYourQuoteButton(boolean screenshot)
      throws Exception {
    try {
      WaitUtils.waitForElementPresent(driver, backToYourQuote, "Back To Your Quote button is not displayed.");
      WaitUtils.waitForelementToBeClickable(driver, backToYourQuote, "Back To Your Quote button is not clickable.");
      GenericUtils.clickButton(backToYourQuote, driver, extentedReport, true);
      Log.message("Clicked Back To Your Quote button on Payement page", driver, extentedReport, screenshot);
      return new YourQuotePageFromPP(driver, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error while clicking on Back to your Quote button " + e);
    }
  }


  /**
   * Click Save Renewal button
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public PaymentPage saveRenewalPayment(boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElementPresent(driver, saveRenewal, "Save Renewal button is not displayed.");
      WaitUtils.waitForelementToBeClickable(driver, saveRenewal, "Save Renewal button is not clickable.");
      GenericUtils.clickButton(saveRenewal, driver, extentedReport, true);
      Log.message("Clicked Save Renewal button on Payement page", driver, extentedReport, screenshot);
      return this.get();
    } catch (Exception e) {
      throw new Exception("Error while clicking on Save Renewal button " + e);
    }
  }

  public void selectDDAccountConfirmation(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      String expectedValue = testdata.get("DDAccountConfirmation");
      GenericUtils.selectRadioBtn(DDAccountConfirmationOptionsList,
          DDAccountConfirmationOptionsList, expectedValue, driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while selecting the Direct Debit Account Confirmation " + e);
    }
  }

  public void validateErrorMessageWithNoDDOptionSelected(HashMap<String, String> testdata,
      WebDriver driver, ExtentTest extentedReport) throws Exception {
    try {
      String expectedValue = testdata.get("PolicyHolderChoice");
      if (expectedValue.equalsIgnoreCase("No")) {
        if (policyHolderOptionsmessage.isDisplayed()) {
          if (GenericUtils.verifyWebElementStartWith(driver, policyHolderOptionsmessage,
              testdata.get("PolicyHolderChoiceNo"))) {
            Log.pass("Correct text is appearing", driver, extentedReport, true);
          } else {
            Log.fail("Incorrect text is appearing", driver, extentedReport, true);
          }
        } else
          Log.fail("The text is not appearing", driver, extentedReport, true);
        if (!buyButton.isDisplayed()) {
          Log.pass("Buy button is not appearing", driver, extentedReport, true);
        } else
          Log.fail("Buy button is appearing", driver, extentedReport, true);
      } else
        Log.fail("No option was not selected: FAIL", driver, extentedReport, true);
    } catch (Exception e) {
      throw new Exception(
          "Error while validating the Policy Holder With No Option Selected method " + e);
    }
  }

  public void selectAnotherBankAccount(WebDriver driver, ExtentTest extentedReport)
      throws Exception {
    try {
      WaitUtils.waitForSpinner(driver);
      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Unable to Find Use Another account button"))
              .until(ExpectedConditions.elementToBeClickable(useAnotherBankAccountButton));
      GenericUtils.clickButton(useAnotherBankAccountButton, driver, extentedReport, true);
    } catch (Exception e) {
      throw new Exception("Error while checking Use Another account button " + e);
    }
  }

  /**
   * Validate existing Bank account details
   * 
   * @param testdata
   * @param accountName
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateExistingAccountDetails(HashMap<String, String> testdata, String accountName, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForSpinner(driver);
      setExistBankDetails(testdata);
      String xpath, ddAcntPymtMsg;      
      int tableSize = existingBankRecord.size();   
      
      if (tableSize>0) {
        xpath = xpathBankDetailsPrefix+existBankSortCode+xpathBankDetailsSuffix;
        WebElement  selectedSortCode= driver.findElement(By.xpath(xpath));
        
        xpath = xpathBankDetailsPrefix+existBankAcct+xpathBankDetailsSuffix;
        WebElement  selectedAccountNo= driver.findElement(By.xpath(xpath));
        
        xpath = xpathBankDetailsPrefix+accountName+xpathBankDetailsSuffix;
        WebElement  selectedAccountName= driver.findElement(By.xpath(xpath));
        
        xpath = xpathBankDetailsPrefix+accountName+xpathBankDetailsSuffix+xpathSelectedBankAcctCheckBoxSuffix;
        WebElement  selectedAccountCheckBox= driver.findElement(By.xpath(xpath));
        
        if (selectedAccountName.isDisplayed()) {
          Log.pass("Account Name details are matched: "+selectedAccountName.getText(), driver, extentedReport, screenshot);
        } else
          Log.fail("Account Name details didn't matched: "+selectedAccountName.getText(), driver, extentedReport, screenshot);
        if (selectedSortCode.isDisplayed()) {
          Log.pass("Sort code details are matched: "+selectedSortCode.getText(), driver, extentedReport, screenshot);
        } else
          Log.fail("Sort code details didn't matched: "+selectedSortCode.getText(), driver, extentedReport, screenshot);       
        if (selectedAccountNo.isDisplayed()) {
          Log.pass("Account details are matched: "+selectedAccountNo.getText(), driver, extentedReport, screenshot);
        } else
          Log.fail("Account details didn't matched: FAIL"+selectedAccountNo.getText(), driver, extentedReport, screenshot);
        if (selectedAccountCheckBox.isSelected()) {
          Log.pass("Account with bank account holder - "+selectedAccountName.getText()+" is checked by default", driver, extentedReport, screenshot);
        } else
          Log.fail("Account with bank account holder - "+selectedAccountName.getText()+" is not checked by default", driver, extentedReport, screenshot);
        
        String[] ddMessage = testdata.get("Card/DirectDebit_AccountMessage").split(",");
        ddAcntPymtMsg = ddMessage[0]+" "+selectedAccountNo.getText()+" "+ddMessage[1];
        
        if(yourAccountDDMessage.getText().equals(ddAcntPymtMsg)) {
          Log.pass("DD account selected for payment message is displayed", driver, extentedReport, screenshot);
        }else
          Log.fail("DD account selected for payment message is not displayed", driver, extentedReport, screenshot);        
      } else 
          throw new Exception("Bank account row is not present on the application");     
    } catch (Exception e) {
      throw new Exception("Error while verifying existing Account details " + e);
    }
  }

  /**
   * Accept DD T&C
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void acceptDDTandC(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      WaitUtils.waitForElementPresent(driver, ddTandCCheckBox, "DD T&C checkbox is not present");
      if(!ddTandCCheckBox.isSelected()){
        ddTandCCheckBox.click();
        WaitUtils.waitForSpinner(driver);
      }
      else
        Log.fail("DD T&C checkbox is already checked", driver, extentedReport, screenshot);
    }catch (Exception e) {
      throw new Exception("Error while checking T&C checkbox for DD " + e);
    }
  }
  
  /**
   * Select the option to confirm usage of Bank account
   * 
   * @param testdata
   * @param screenshot
   * @throws Exception
   */
  public void selectpolicyHolderEntryDD(HashMap<String, String> testdata, boolean screenshot) throws Exception {
    try {
      String expectedValue = testdata.get("PolicyHolderChoice");
      GenericUtils.selectRadioBtn(policyHolderOptionsDD, policyHolderOptionsDDText, expectedValue, driver,
          extentedReport, screenshot);
      WaitUtils.waitForSpinner(driver);
    } catch (Exception e) {
      throw new Exception("Error while selecting the option 'Does the card belong to the policyholder?' " + e);
    }
  }
  
  /**
   * Add a new bank account
   * 
   * @param driver
   * @param extentedReport
   * @throws Exception
   */
  public void clickOnAddNewBankAccountButton(WebDriver driver, ExtentTest extentedReport)
      throws Exception {
    try {
      WaitUtils.waitForSpinner(driver);
      WaitUtils.waitForElementPresent(driver, addNewBankAccountButton, "Add new bank account button is not present");
      WaitUtils.waitForelementToBeClickable(driver, addNewBankAccountButton, "Add new bank account button is not clickable");      
      GenericUtils.clickButton(addNewBankAccountButton, driver, extentedReport, true);
    } catch (Exception e) {
      throw new Exception("Error while clicking on Add a new account button " + e);
    }
  }

  /**
   * Add bank account details
   * 
   * @param testdata
   * @param action
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void addAccountDetailswindow(HashMap<String, String> testdata, String action, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      setNewBankDetails(testdata);    
      if(addBankDetSection.isDisplayed()){  
        GenericUtils.enterInTextbox(newBankSortCode, ddSortCode, driver,
            extentedReport, screenshot);
        GenericUtils.enterInTextbox(newBankAcct, ddAccountNumber, driver,
            extentedReport, screenshot);
        bankNameTextOnAddBankWindow.click();
        WaitUtils.waitForOnlySpinner(driver);
        GenericUtils.enterInTextbox(newBankAcctName, ddAccountName, driver,
            extentedReport, screenshot);      
        switch (action) {
          case "Save":
            WaitUtils.waitForElementPresent(driver, saveNewAccountDetails, "Save button on Add bank account details is not present");
            WaitUtils.waitForelementToBeClickable(driver, saveNewAccountDetails, "Save button on Add bank account details is not clickable");
            GenericUtils.clickButton(saveNewAccountDetails, driver, extentedReport, screenshot);
            Log.message("Clicked on Save button", driver, extentedReport, screenshot);
            WaitUtils.waitForOnlySpinner(driver);
            break;

          case "Cancel":
            WaitUtils.waitForElementPresent(driver, cancelNewAccountDetails, "Cancel button on Add bank account details is not present");
            WaitUtils.waitForelementToBeClickable(driver, cancelNewAccountDetails, "Cancel button on Add bank account details is not clickable");
            GenericUtils.clickButton(cancelNewAccountDetails, driver, extentedReport, screenshot);
            Log.message("Clicked on Save button", driver, extentedReport, screenshot);
            WaitUtils.waitForOnlySpinner(driver);
            break;
        }
      }else
        throw new Exception("Add bank details section is not displayed in SS");     
    } catch (Exception e) {
      throw new Exception("Error while clicking on Add Direct Debit account method " + e);
    }
  }
  
  /**
   * Validate added bank account is selected
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateAddedBankAcct(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception {
    String xpath,acctHolderName; 
    WebElement checkBox;
    try{
      acctHolderName = testdata.get("SelfSeviceAccountName");
      xpath = xpathBankDetailsPrefix + acctHolderName + xpathAddedBankSuffix;
      checkBox = driver.findElement(By.xpath(xpath));
      WaitUtils.waitForElementPresent(driver, checkBox, "The bank details checkbox is not present in row");
      if(checkBox.isSelected()){
        Log.pass("The added bank account is selected by default", driver, extentedReport, screenshot);
      }else{
        Log.fail("The added bank account is not selected by default", driver, extentedReport, screenshot);
        checkBox.click();
        WaitUtils.waitForOnlySpinner(driver);
        }
    } catch (Exception e) {
      throw new Exception("Error while validating added bank account " + e);
    }
  }
  
  /**
   * To de-select card
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void deSelectCard(HashMap<String,String> testdata, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      String cardNo = testdata.get("Card Number");
      String cardNoLastFour = cardNo.substring(cardNo.length() - 4);
      String ssCardNo = "************" + cardNoLastFour;
      String xpath = xpathSelectedCardPrefix+ssCardNo+xpathSelectedCardSuffix;
      WebElement cardCheckBox = driver.findElement(By.xpath(xpath));
      WaitUtils.waitForElementPresent(driver, cardCheckBox, "Card check box is not present");      
      if(cardCheckBox.isSelected()){
        cardCheckBox.click();
        WaitUtils.waitForOnlySpinner(driver);
        Log.message("Card no- "+cardNo+" is deselected", driver, extentedReport, screenshot);
      }
    }catch (Exception e) {
      throw new Exception("Error while de-selecting the card " + e);
    }
  }
  
  /**
   * To validate required field message for card payment 
   *
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception 
   */
  public void validateReqdFieldMsgforCardPymt(WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      WaitUtils.waitForElementPresent(driver, buyButton, "Buy button is not present");
      WaitUtils.waitForelementToBeClickable(driver, buyButton, "Buy button is not clickable");
      buyButton.click();
      if(policyHolderReqdFieldTxt.isDisplayed() && addCardReqdFieldTxt.isDisplayed()){
        Log.pass("Required field messages are displayed when card is not selected or a new card is not added", driver, extentedReport, screenshot);
      }
      else
        Log.fail("Required field messages are not displayed when card is not selected or a new card is not added", driver, extentedReport, screenshot);
    }catch (Exception e) {
      throw new Exception("Error while validating the card fields required field message " + e);
    }
  }
  
  /**
   * To validate required field message for selecting payment method
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception 
   */
  public void validateReqdFieldMsgforPmtPlanMthdChange(HashMap<String,String> testdata, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      WaitUtils.waitForElementPresent(driver, buyButton, "Buy button is not present");
      WaitUtils.waitForelementToBeClickable(driver, buyButton, "Buy button is not clickable");
      buyButton.click();
      if(selectPmtMthdReqdFieldTxt.isDisplayed()){
        Log.pass("Required field message is displayed when payment method is not selected", driver, extentedReport, screenshot);
      }
      else
        Log.fail("Required field message is not displayed when payment method is not selected", driver, extentedReport, screenshot);
      selectPaymentMethod(testdata, screenshot);
      buyButton.click();
      if(addAcctReqdFieldTxt.isDisplayed() && ddTCReqdFieldTxt.isDisplayed()){
        Log.pass("Required field message is displayed when bank account is not added and DD T&C are not accepted", driver, extentedReport, screenshot);
      }else
        Log.fail("Required field message is not displayed when bank account is not added and DD T&C are not accepted", driver, extentedReport, screenshot);
    }catch (Exception e) {
      throw new Exception("Error while validating the payment change fields required field message " + e);
    }
  }
  
}

