package com.ssp.uirefresh_pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;

public class UIRef_ContactBanking extends LoadableComponent<UIRef_ContactBanking> {
	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;
	private static String URN = null;

	@FindBy(css = "#bankAccounts > a")
	WebElement lnkBankAccounts;

	@FindBy(css = "a[ng-click^='showBankingForm'] span[data-i18n*='banking_label']")
	WebElement btnAddBankAccount;

	@FindBy(css = "custom-drop-down[ng-model^='contactBank'] select[name=country]")
	WebElement selBankCountry;

	@FindBy(css = "custom-drop-down[ng-model^='contactBank'] select[name=currencyIndicator]")
	WebElement selBankCurrency;

	@FindBy(css = "#bsbSortCode")
	WebElement txtBankCode;

	@FindBy(css = "custom-input-box[ng-model*='contactBank._name'] #name")
	WebElement txtBankAcctName;

	@FindBy(css = "custom-input-box[ng-model*='contactBank'] #reference")
	WebElement txtBankAcctNum;

	@FindBy(css = "custom-drop-down[ng-model^='contactBank'] select[name=accountType]")
	WebElement selBankAcctType;

	@FindBy(css = "a[title=Add][ng-click*='addContactBank'] span[data-i18n*='add_button']")
	WebElement btnBankAdd;

	@FindBy(css = "#creditCards > a")
	WebElement lnkCreditCards;

	@FindBy(css = "a[title='Add Card Details'] span[data-i18n*='add_card_details_label']")
	WebElement btnAddCardDetails;

	@FindBy(css = "custom-drop-down[ng-model='contactCredit.country'] select[name=country]")
	WebElement selCardCounty;

	@FindBy(css = "custom-input-box[ng-model='contactCredit.cardHolder'] #name")
	WebElement txtCardHolder;

	@FindBy(css = "custom-input-box[ng-model*='contactCredit.cardNumber'] #reference")
	WebElement txtCardNumber;

	@FindBy(css = "custom-drop-down[ng-model*='contactCredit.cardType'] select[name=cardType]")
	WebElement selCardType;

	@FindBy(css = "custom-input-box[ng-model*='contactCredit.expiryDate'] #expiryDate")
	WebElement txtCardExpiry;

	@FindBy(css = "a[title=Add][ng-click*='addCreditCardDetails'] span[data-i18n*='add_button']")
	WebElement btnCardAdd;

	@FindBy(css = "#example > tbody > tr")
	List<WebElement> rowBankAccts;
	
	@FindBy(css = "table[id^='DataTables_Table_'] tbody tr")
	List<WebElement> rowCards;
	
	@FindBy(css = "")
	WebElement btn;

	public UIRef_ContactBanking(WebDriver driver, ExtentTest report) {
		this.driver = driver;
		this.extentedReport = report;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		WaitUtils.waitForPageLoad(driver);
		if (!isPageLoaded) {
			Assert.fail();
		}
	}

	@Override
	protected void isLoaded() throws Error {
		isPageLoaded = true;
		// driver.get(sspURL);
		WaitUtils.waitForPageLoad(driver);
	}

	// lnkBankAccounts
	public void clickLnkBankAccounts(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkBankAccounts);
			lnkBankAccounts.click();
			Log.message("Clicked the Link Bank Account on Contact Banking ", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the bank account link	on Contact Banking : " + e);
		}
	}

	public void clickBtnAddBankAccount(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, btnAddBankAccount, "unable to click btnAddBankAccount");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", btnAddBankAccount);
			Log.message("Clicked the btnAddBankAccount on Contact Banking ", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the btnAddBankAccount	on Contact Banking : " + e);
		}
	}

	// selBankCountry
	public void selectBankCountry(String bankCountryVal, WebDriver driver, ExtentTest extentedReport) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, selBankCountry, "Unable to select Bank Country");
			Select roledropdown = new Select(selBankCountry);
			roledropdown.selectByVisibleText(bankCountryVal);
			Log.message("Select Bank Country is : " + bankCountryVal, extentedReport);
		} catch (Exception e) {
			Log.fail("Unable to select bank country" + e, driver, extentedReport, true);
		}
	}

	public void selectBankCurrency(String currencyVal, WebDriver driver, ExtentTest extentedReport) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, selBankCurrency, "Unable to select Bank Currency");
			Select roledropdown = new Select(selBankCurrency);
			roledropdown.selectByVisibleText(currencyVal);
			Log.message("Select Bank Currency is : " + currencyVal, extentedReport);
		} catch (Exception e) {
			Log.fail("Unable to select Bank Currency" + e, driver, extentedReport, true);
		}
	}

	public void enterBankCode(String bankCodeVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, txtBankCode);
			txtBankCode.sendKeys(bankCodeVal);
			Log.message("Entered the bankCode value is : " + bankCodeVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the bank Code  " + e);
		}
	}

	// txtBankAcctName
	public void enterBankAcctName(String bankAcctNameVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, txtBankAcctName);
			txtBankAcctName.sendKeys(bankAcctNameVal);
			Log.message("Entered the bankAcctName value is : " + bankAcctNameVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the bank Acct Name  " + e);
		}
	}

	// txtBankAcctNum
	public void enterbankAcctNum(String bankAcctNumVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, txtBankAcctNum);
			txtBankAcctNum.sendKeys(bankAcctNumVal);
			Log.message("Entered the bank Acct Num value is : " + bankAcctNumVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the bank Acct Num " + e);
		}
	}

	// selBankAcctType
	public void selectBankAcctType(String bankAcctTypeVal, WebDriver driver, ExtentTest extentedReport)
			throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, selBankAcctType, "Unable to select Bank Account Type");
			Select select = new Select(selBankAcctType);
			select.selectByVisibleText(bankAcctTypeVal);
			Log.message("Select Bank Acct Type Val is : " + bankAcctTypeVal, extentedReport);
		} catch (Exception e) {
			Log.fail("Unable to select Bank Acct Type" + e, driver, extentedReport, true);
		}
	}

	// btnBankAdd
	public void clickBtnBankAdd(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnBankAdd);
			btnBankAdd.click();
			Log.message("Clicked the btn Bank Add on Contact Banking ", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the btn BankAdd on Contact Banking : " + e);
		}
	}

	public Boolean verifyBankAccts(ExtentTest extentedReport, WebDriver driver) throws Exception {
		Boolean bankExist = false; 
		try {
			WaitUtils.uiRefwaitForSpinner(driver);
		    WaitUtils.waitForListElement(driver, rowBankAccts, 40);
			if(rowBankAccts.size() > 0 && !rowBankAccts.get(0).getText().isEmpty()){
				bankExist = true;
			}
			//System.out.println("Size of List is " + rowBankAccts.size());
			Log.softAssertThat(bankExist, "Bank account details already exist : " + rowBankAccts.get(0).getText(),
					"No bank account exist", driver, extentedReport, true);				
		} catch (Exception e) {			
			Log.fail("Error while verifying the bank account added " + e.getMessage(), driver, extentedReport, true);
		}
		return bankExist;						
	}

	// lnkCreditCards
	public void clickLnkCreditCards(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkCreditCards);
			lnkCreditCards.click();
			Log.message("Clicked the Link Credit Card on Contact Banking ", driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking the credit card link on contact banking " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while clicking the Credit Card link on Contact Banking : " + e);
		}
	}

	// btnAddCardDetails
	public void clickBtnAddCardDetails(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, btnAddCardDetails, "unable to click btnAddCardDetails");
			btnAddCardDetails.click();
			Log.message("Clicked the btnAddCardDetails on Contact Banking ", driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking the btnAddCardDetails on Contact Banking  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while clicking the btnAddCardDetails	on Contact Banking : " + e);
		}
	}

	// selCardCounty
	public void selectCardCounty(String cardVal, WebDriver driver, ExtentTest extentedReport) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, selCardCounty, "Unable to select Bank Country");
			Select roledropdown = new Select(selCardCounty);
			roledropdown.selectByVisibleText(cardVal);
			Log.message("Select Card Country is : " + cardVal, extentedReport);
		} catch (Exception e) {
			Log.fail("Unable to select Card country" + e.getMessage(), driver, extentedReport, true);
		}
	}

	// txtCardHolder
	public void enterCardHolder(String holderVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, txtCardHolder);
			txtCardHolder.sendKeys(holderVal);
			Log.message("Entered the card holder value is : " + holderVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the card holder  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while entering the card holder  " + e);
		}
	}

	// txtCardNumber
	public void enterCardNumber(String bankAcctNumVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, txtCardNumber);
			txtCardNumber.sendKeys(bankAcctNumVal);
			Log.message("Entered the card Num value is : " + bankAcctNumVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the card Num  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while entering the card Num " + e);
		}
	}

	// selCardType : WebElement
	public void selectCardType(String inputVal, WebDriver driver, ExtentTest extentedReport) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, selCardType, "Unable to select Card Type");
			Select roledropdown = new Select(selCardType);
			roledropdown.selectByVisibleText(inputVal);
			Log.message("Select card Type is : " + inputVal, extentedReport);
		} catch (Exception e) {
			Log.fail("Unable to select card type " + e.getMessage(), driver, extentedReport, true);
		}
	}
	// txtCardExpiry : WebElement
	public void enterCardExpiry(String inputVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, txtCardExpiry);
			txtCardExpiry.sendKeys(inputVal);
			Log.message("Entered the card expiry value is : " + inputVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the card expiry " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while entering the card expiry " + e);
		}
	}
	//btnCardAdd : WebElement
	public void clickBtnCardAdd(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnCardAdd);
			btnCardAdd.click();
			Log.message("Clicked the btnCardAdd on Contact Banking ", driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking the btnCardAdd on Contact Banking " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while clicking the btnCardAdd on Contact Banking : " + e);			
		}
	}	
	//rowCards
	public Boolean verifyCards(ExtentTest extentedReport, WebDriver driver) throws Exception {
		Boolean cardExist = false; 
		try {
			WaitUtils.waitForListElement(driver, rowCards, 60);
			if(rowCards.size() > 0 && !rowCards.get(0).getText().isEmpty()){
				cardExist = true;
			}	
			Log.softAssertThat(cardExist, "Card details exist: " + rowCards.get(0).getText(),
					"Card is not present", driver, extentedReport, true);			
		} catch (Exception e) {
			Log.fail("Error while verifying the added card details for Contact Banking " + e.getMessage(), driver, extentedReport, true);
		}
		return cardExist;
	}
}
