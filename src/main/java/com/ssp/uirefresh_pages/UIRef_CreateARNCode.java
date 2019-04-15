package com.ssp.uirefresh_pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

public class UIRef_CreateARNCode extends LoadableComponent<UIRef_CreateARNCode> {
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;

	@FindBy(xpath = "//*[contains(@id,'menu')]/form[@name='arnForm']/div[1]/div/div[1]/div[1]/div/input")
	WebElement txtARN;

	@FindBy(xpath = "//div[contains(@id,'menu')]/form[@name='arnForm']/div[2]/div/div/div[3]/div[2]/div/a[@class='eye_icon']/i")
	WebElement searchUWicon;

	@FindBy(xpath = "//form[@name='arnForm']//input[@name='urn']")
	WebElement txtUnderwriter;

	@FindBy(xpath = "//form[@name='arnForm']//select[@name='trn']")
	WebElement selTransaction;

	@FindBy(xpath = "//form[@name='arnForm']//select[@name='src']")
	WebElement selSource;

	@FindBy(xpath = "//form[@name='arnForm']/div[3]/div/div[2]/div[1]/div/div[1]/div/div[1]/input[@name='policyUrn']")
	WebElement txtPolicyURN;

	@FindBy(xpath = "//form[@name='arnForm']/div[5]/div/a[@title='Next']/span/span[text()='Next']")
	WebElement btnNext;
	
	@FindBy(xpath = "//div[contains(@id,'menu2')]/div[3]/div/a[@title='Previous']/span/span")
	WebElement btnPrevious;
	
	@FindBy(xpath = "//div[contains(@id,'menu2')]//a[@title='Save']//span[text()='Save']")
	WebElement btnSave;
		
	@FindBy(xpath = "//table[contains(@id,'DataTables_Table')]/tbody/tr/td[6]/a/i")
	WebElement referralApproveClick;

	@FindBy(xpath = "//table[contains(@id,'DataTables_Table')]/tbody/tr/td[7]/a/i")
	WebElement referralDeclineClick;
		
	public UIRef_CreateARNCode(WebDriver driver, ExtentTest extentedReport) {
		this.driver = driver;
		this.extentedReport = extentedReport;
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
		WaitUtils.waitForPageLoad(driver);
	}
		
	public String getARNcode(WebDriver driver, ExtentTest report) throws Exception {
		try{
			String ARNcodeVal;
			WaitUtils.waitForElement(driver, txtARN);
			ARNcodeVal = txtARN.getAttribute("value");
			Log.pass("ARN Code captured is : " + ARNcodeVal, driver, extentedReport,true);	
			return ARNcodeVal;
		}catch(Exception e){
			throw new Exception("Error while Fetching the ARN Code Val : " + e);
		}		
	}

	public UIRef_ContactSearchDialog clickUWsearchIcon(String underwriterVal, WebDriver driver, ExtentTest report)
			throws Exception {
		try {
			WaitUtils.uiRefwaitForSpinner(driver);
			WaitUtils.waitForelementToBeClickable(driver, searchUWicon, "Unable to click UW Search Icon");
			searchUWicon.click();
			Log.message("Underwriter Search Icon Clicked : ", driver, extentedReport);
			WaitUtils.uiRefwaitForSpinner(driver);
			return new UIRef_ContactSearchDialog(driver, report);
		} catch (Exception e) {
			throw new Exception("Error while the click of UW Search Icon" + e);
		}
	}

	public void selectTransaction(String TransVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.uiRefwaitForSpinner(driver);
			WaitUtils.waitForelementToBeClickable(driver, selTransaction, "Unable to select Transaction value");
			Select select = new Select(selTransaction);
			select.selectByValue(TransVal);
			Log.message("Selected UW value is : " + TransVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Transaction Val" + e);
		}
	}

	public void selectSource(String selSourceVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, selSource, "ARN Source dopdown element not clickable");
			Select select = new Select(selSource);
			select.selectByValue(selSourceVal);
			Log.message("Selected Source value is : " + selSourceVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Source Value " + e);
		}
	}
	public void enterPolicyURN(String policyURNval, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, txtPolicyURN, "unable to enter policy URN");
			txtPolicyURN.sendKeys(policyURNval);
			txtPolicyURN.sendKeys(Keys.TAB);
			Log.message("Entered  Policy URN is : " + policyURNval, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Policy URN val on ARN Page " + e);
		}
	}

	
	public void clickBtnNext(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, btnNext, "Unable to click button Next");
			btnNext.click();
			Log.message("Next button clicked on Create ARN code Page : ", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while the click of Next button clicked on Creat ARN code Page" + e);
		}
	}
	
	public void clickBtnSave(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, btnSave, "Unable to click button Save");
			btnSave.click();
			Log.message("Save button clicked on Create ARN code Page : ", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while the click of Save button clicked on Creat ARN code Page" + e);
		}
	}
		
	public void clickreferralApprove(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, referralApproveClick, "Unable to click referralApproveClick");
			referralApproveClick.click();
			Log.message("Referral Approved on Create ARN", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while the click of referralApproveClick clicked on Creat ARN code Page" + e);
		}
	}
}
