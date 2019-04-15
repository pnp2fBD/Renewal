package com.ssp.uxp_SSPages;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.WaitUtils;

/**
 * Card Details Page allows user to enter the Card Details for Making Annual Payment
 */
public class CardDetailsPageSS extends LoadableComponent<CardDetailsPageSS> {

  private final WebDriver driver;
  private ExtentTest extentedReport;
  private boolean isPageLoaded;
  private String cardNo;
  private String expMonth;
  private String expYear;  


  @FindBy(css = "div[class*='panel panel-heading timeoutPopup']")
  WebElement paymentHubHead;
  
  @FindBy(css = "input[name='expiryDate.expiryMonth']")
  WebElement inputExpiryMonth;

  @FindBy(css = "input[name='expiryDate.expiryYear']")
  WebElement inputExpiryYear;
  
  @FindBy(css = "input#cardNumber")
  WebElement txtCardNumber;

  @FindBy(css = "input#cardholderName")
  WebElement txtCardHolderAnnual;

  @FindBy(css = "select[name='expiryDate.expiryMonth']")
  WebElement selectExpiryMonth;

  @FindBy(css = "select[name='expiryDate.expiryYear']")
  WebElement selectExpiryYear;

  @FindBy(css = "input[name='securityCode']")
  WebElement txtVerification;

  @FindBy(css = "label[for='Continue'] span+input[alt='Continue']")
  WebElement btnContinue;

  @FindBy(css = "input#cardholderName")
  WebElement txtCardHoldName;

  @FindBy(css = "input[id*='submitButton']")
  WebElement btnBuy;

  @FindBy(css = "#address1")
  WebElement txtAddress1;

  @FindBy(css = "#address2")
  WebElement txtAddress2;

  @FindBy(css = "#town")
  WebElement txttown;

  @FindBy(css = "#postcode")
  WebElement txtPostCode;

  @FindBy(xpath = "//table//tr[td[span[contains(text(), 'Amount')]]]/td[2]/span")
  WebElement txtAmountToPay;

  @FindBy(css = "iframe[id='wp-cl-custom-html-iframe']")
  WebElement frame_sagepay;

  /**
   * 
   * Constructor class for Card Details Page Here we initializing the driver for page factory
   * objects. For ajax element waiting time has added while initialization
   * 
   * @param driver : Webdriver
   */
  public CardDetailsPageSS(WebDriver driver, ExtentTest report) {
    this.driver = driver;
    this.extentedReport = report;
    PageFactory.initElements(driver, this);
  }

  @Override
  protected void isLoaded() {

    if (!isPageLoaded) {
      Assert.fail();
    }

    if (isPageLoaded && !driver.getTitle().contains("Acceptance")) {
      Log.fail("SSP Card Details page did not open up. Site might be down.", driver,
          extentedReport);
    }
  }

  @Override
  protected void load() {

    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
    WaitUtils.waitForElement(driver, paymentHubHead);
  } 
  
  /**
   * To switch to card frame
   * @throws InterruptedException 
   */
  public void switchToCardDetailsFrame() throws InterruptedException{
    Thread.sleep(2000);
    driver.switchTo().frame(frame_sagepay);
    Thread.sleep(1000);
  }
  
  private void setCardNo(HashMap<String, String> testdata) throws Exception {
    this.cardNo = testdata.get("SSCardNo");
    this.expMonth = testdata.get("Month");
    this.expYear = testdata.get("Year");
      if (cardNo.equals("") || expMonth.equals("") || expYear.equals("")) {
        throw new Exception("Please enter values for fields - SSCardNo, Month & Year in testdata sheet");
      }  
  }
  
  /**
   * Enter card details
   * 
   * @param testdata
   * @param extentReport
   * @param Screenshot
   * @throws Exception
   * 
   */
  public void enterCardDetails(HashMap<String, String> testdata,
      boolean Screenshot) throws Exception {
    try {
      setCardNo(testdata);
      enterCardNumber(Screenshot);
      enterMonthYear(Screenshot);
      enterVerification(Screenshot);
      Log.message("Entered card details", driver, extentedReport,
          Screenshot);
    } catch (Exception e) {
      throw new Exception("Error while entering card details :" + e);
    }
  }
  
  /**
   * Enter Card number
   * 
   * @param testdata
   * @param extentReport
   * @param Screenshot
   * @throws Exception
   * 
   */
  public void enterCardNumber(boolean Screenshot) throws Exception {
    WaitUtils.waitForElement(driver, txtCardNumber);
    try {
      txtCardNumber.clear();
      txtCardNumber.sendKeys(cardNo);
      Log.message("Entered Card Number : " + cardNo, driver, extentedReport,
          Screenshot);
    } catch (Exception e) {
      throw new Exception("Error while entering Card number :" + e);
    }
    WaitUtils.waitForSpinner(driver);
  }

  /**
   * Select Expiry date
   * 
   * @param testdata
   * @param extentReport
   * @param Screenshot
   * @throws Exception
   * 
   */
  public void selectExpiry(HashMap<String, String> testdata,
      boolean Screenshot) throws Exception {
    try {
      selectMonth();
      selectYear(testdata);
    } catch (Exception e) {
      throw new Exception("Error while entering expiry details :" + e);
    }
    WaitUtils.waitForSpinner(driver);
  }

  /**
   * Select Month
   * 
   * @param extentReport
   * @throws Exception
   * 
   */
  public void selectMonth() throws Exception {
    try {
      Select selectmonth = new Select(selectExpiryMonth);
      selectmonth.selectByValue("02");
      Log.message("Selected Month : 2", driver, extentedReport);
    } catch (Exception e) {
      throw new Exception("Error while entering expiry month :" + e);
    }
  }

  /**
   * Select Year
   * 
   * @param extentReport
   * @param testdata
   * @throws Exception
   * 
   */
  public void selectYear(HashMap<String, String> testdata)
      throws Exception {
    try {
      Select selectyear = new Select(selectExpiryYear);
      selectyear.selectByValue(testdata.get("Year"));
      Log.message("Selected Year : " + testdata.get("Year"), driver, extentedReport);
    } catch (Exception e) {
      throw new Exception("Error while entering expiry year :" + e);
    }
  }
  
  /**
   * Enter month and year verification
   * 
   * @param extentReport
   * @param Screenshot
   * @throws Exception
   * 
   */
  public void enterMonthYear(boolean Screenshot) throws Exception {
    try {
      
      WaitUtils.waitForElementPresent(driver, inputExpiryMonth, "Expiry month input box is not displayed");
      inputExpiryMonth.clear();
      Thread.sleep(1000);
      inputExpiryMonth.sendKeys(expMonth);
      Log.message("Entered month : "+expMonth, driver, extentedReport, Screenshot);
      WaitUtils.waitForElementPresent(driver, inputExpiryYear, "Expiry month input box is not displayed");
      inputExpiryYear.clear();
      Thread.sleep(1000);
      inputExpiryYear.sendKeys(expYear);
      Log.message("Entered year : "+expYear, driver, extentedReport, Screenshot);

    } catch (Exception e) {
      throw new Exception("Error while entering CVV number :" + e);
    }
  }

  /**
   * Enter CVV verification
   * 
   * @param extentReport
   * @param Screenshot
   * @throws Exception
   * 
   */
  public void enterVerification(boolean Screenshot) throws Exception {
    try {
      txtVerification.clear();
      txtVerification.sendKeys("123");
      Log.message("Entered CVV : 123", driver, extentedReport, Screenshot);

    } catch (Exception e) {
      throw new Exception("Error while entering CVV number :" + e);
    }
  }

  /**
   * Enter Invalid Card Holder Name
   * 
   * @param extentReport
   * @param Screenshot
   * @throws Exception
   * 
   */
  public void enterInvalidName(
      boolean Screenshot) throws Exception {
    try {
      txtCardHoldName.clear();
      txtCardHoldName.sendKeys("ERROR");
      Log.message("Entered Card Holder Name : ERROR" , driver, extentedReport,
          Screenshot);
    } catch (Exception e) {
      throw new Exception("Error occured while entering card holders name :" + e);
    }

  }
  
  /**
   * Enter Card Holder Name
   * 
   * @param extentReport
   * @param Screenshot
   * @throws Exception
   * 
   */
  public void enterName(HashMap<String, String> testdata,
      boolean Screenshot) throws Exception {
    try {

      txtCardHoldName.sendKeys("First Name");
      Log.message("Entered Card Holder Name : " + testdata.get("First Name"), driver, extentedReport,
          Screenshot);
    } catch (Exception e) {
      throw new Exception("Error occured while entering card holders name :" + e);
    }

  }

  /**
   * Click Buy
   * 
   * @param extentReport
   * @param Screenshot
   * @throws Exception
   * 
   */
  public ConfirmationPage clickbuy(boolean Screenshot) throws Exception {
    try {
      WaitUtils.waitForElementPresent(driver, btnBuy, "Make payment button is not present");
      WaitUtils.waitForelementToBeClickable(driver, btnBuy, "Make payment button is not clickable");
      btnBuy.click();
      Log.message("Clicked on Buy button", driver, extentedReport, true);
      Thread.sleep(8000);
      return new ConfirmationPage(driver,extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Failed to click buy button on card details page :" + e);
    }
  }

}
