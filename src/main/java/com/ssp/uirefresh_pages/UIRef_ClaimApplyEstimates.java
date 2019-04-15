package com.ssp.uirefresh_pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.WaitUtils;

public class UIRef_ClaimApplyEstimates extends LoadableComponent<UIRef_ClaimApplyEstimates> {
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;

	@FindBy(css = "select[name='ClaimCircumstance']")
	WebElement selCircumstance;
		
	@FindBy(css = "#paymentTable > thead > tr:nth-child(1) > th:nth-child(6) > div > button[title=Add]")
	WebElement btnPaymentAdd;

	@FindBy(css = "#paymentTable > thead > tr:nth-child(1) > th:nth-child(6) > div > ul > li > a")
	WebElement lnkPayUnAuthTranCover;
	
	@FindBy(css = "#paymentTable #edit")
	WebElement lnkPayUnAuthTranCoverEdit;
	
	//@FindBy(css = "#paymentTable > tbody > tr:nth-child(1) > td:nth-child(4)")
	@FindBy(css = "#amount")
	WebElement txtPaymentRevReserveVal;
	
	@FindBy(css = "#recoveryTable > thead > tr:nth-child(1) > th:nth-child(6) > div > button[title=Add]")
	WebElement btnRecoveryAdd;

	@FindBy(css = "#recoveryTable > thead > tr:nth-child(1) > th:nth-child(6) > div > ul > li > a")
	WebElement lnkRecUnAuthTranCover;
	
	@FindBy(css = "#recoveryTable #edit")
	WebElement lnkRecUnAuthTranCoverEdit;
	
	//@FindBy(css = "#recoveryTable > tbody > tr:nth-child(1) > td:nth-child(4)")
	@FindBy(css = "#amount")
	WebElement txtRecoveryRevReserveVal;
					
	@FindBy(css = "a[title='Save Estimation']")
	WebElement btnOK;
	
	@FindBy(css = "p[ng-click*='Complete'] span[data-i18n*='complete_label']")
	WebElement lnkComplete;
				

	public UIRef_ClaimApplyEstimates(WebDriver driver, ExtentTest extentedReport) {
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
	
	public void clickAddPaymentCategory(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnPaymentAdd);
			btnPaymentAdd.click();
			Log.pass("Clicked the payment add button cateory on claim apply estimate page ", driver, extentedReport);
			WaitUtils.waitForElement(driver, lnkPayUnAuthTranCover);
			lnkPayUnAuthTranCover.click();
			Log.pass("Clicked the payment unauthorised transaction cover on claim apply estimate page ", driver, extentedReport,true);
		} catch (Exception e) {
			Log.fail("Error while clicking the payment unauthorised transaction cover on claim apply estimate page  " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking the payment unauthorised transaction cover on claim apply estimate page " + e);
		}
	}
	
	public void clickAddRecoveryCategory(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, btnRecoveryAdd, "unable to click recovery add button cateory");
			btnRecoveryAdd.click();
			Log.pass("Clicked the recovery add button cateory on claim apply estimate page ", driver, extentedReport);
			WaitUtils.waitForElement(driver, lnkRecUnAuthTranCover);
			lnkRecUnAuthTranCover.click();
			Log.pass("Clicked the recovery unauthorised transaction cover on claim apply estimate page ", driver, extentedReport,true);
			//WaitUtils.uiRefwaitForSpinner(driver);
		} catch (Exception e) {
			Log.fail("Error while clicking recovery unauthorised transaction cover on claim apply estimate page " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while clicking recovery unauthorised transaction cover on claim apply estimate page " + e);
		}
	}
	
	public void enterPaymentRevReserve(String inputVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkPayUnAuthTranCoverEdit);
			//System.out.println(txtPaymentRevReserveVal.getText());
			lnkPayUnAuthTranCoverEdit.click();
			Log.message("Clicked to edit the the payment category in claim apply estimates  : ", driver, extentedReport);
//			WaitUtils.waitForelementToBeClickable(driver, txtPaymentRevReserveVal, "Unable to click");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
//			executor.executeScript("arguments[0].click();", btn_Edit);
//			executor.executeScript("document.getElementById('amount').value='100'") ;
			//executor.executeScript("arguments[0].value = " + inputVal;, txtPaymentRevReserveVal);   
			txtPaymentRevReserveVal.clear();
			//executor.executeScript("document.getElementByCss('#paymentTable > tbody > tr:nth-child(1) > td:nth-child(4)').value = 100 ") ;
//			System.out.println(txtPaymentRevReserveVal.getText());
			/*Actions actions = new Actions(driver);
			actions.moveToElement(txtPaymentRevReserveVal);
			actions.click();
			actions.sendKeys(inputVal);
			actions.perform();
			executor.executeScript("arguments[0].value='100';", txtPaymentRevReserveVal);*/
			txtPaymentRevReserveVal.sendKeys(inputVal);
			txtPaymentRevReserveVal.sendKeys(Keys.TAB);
			Log.message("Entered the payment revised reserved value in claim apply estimates  is : " + inputVal, driver, extentedReport);			
		} catch (Exception e) {
			Log.fail("Error while the entering the payment revised reserved value in claim apply estimates " + e.getMessage(), driver, report, true);
			throw new Exception("Error while the entering the payment revised reserved value in claim apply estimates " + e);
		}
	}
	
	public void enterRecoveryRevReserve(String inputVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, lnkRecUnAuthTranCoverEdit, "unable to enter recovery category edit link");
			lnkRecUnAuthTranCoverEdit.click();
			Log.message("clicked the recovery category edit link in claim apply estimates" , driver, extentedReport);
			WaitUtils.waitForelementToBeClickable(driver, txtRecoveryRevReserveVal, "Unable to click");
			txtRecoveryRevReserveVal.sendKeys(inputVal);
			txtRecoveryRevReserveVal.sendKeys(Keys.TAB);
			Log.message("entered the recovery revised reserved value in claim apply estimates page is : " + inputVal, driver, extentedReport);			
		} catch (Exception e) {
			Log.fail("Error while the entering the recovery revised reserved value in claim apply estimates " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while the entering the recovery revised reserved value in claim apply estimates " + e);
		}
	}	
	public void clickBtnOK(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnOK);
			btnOK.click();
			Log.pass("Clicked the button OK on claim apply estimates ", driver, extentedReport,true);
			WaitUtils.uiRefwaitForSpinner(driver);
		} catch (Exception e) {
			Log.fail("Error while clicking button ok on claim apply estimates " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while clicking button ok on claim apply estimates " + e);
		}
	}
	public UIRef_ClaimComplete clicklnkComplete(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkComplete);
			lnkComplete.click();
			Log.pass("Clicked the link claim Complete ", driver, extentedReport,true);
			WaitUtils.uiRefwaitForSpinner(driver);
			return new UIRef_ClaimComplete(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking the link claim complete " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while clicking the link claim complete " + e);
		}
	}


}
