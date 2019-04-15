package com.ssp.uirefresh_pages;

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

public class UIRef_PolicyCancelDialog extends LoadableComponent<UIRef_PolicyCancelDialog>{
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	
	@FindBy(xpath = "//h3[@class='modal-title']//span[text()='Cancel Policy']")
	WebElement dialogLabel;
		
	@FindBy(xpath = "//input[@name='_effFrm']")
	WebElement cancelEffectiveDate;
	
	@FindBy(xpath = "//select[@name='_rsn']")
	WebElement reasonType;
	
	@FindBy(xpath = "//select[@name='_ty']")
	WebElement returnPremiumType;
		
	@FindBy(xpath = "//input[@id='businessOutcomeContact']")
	WebElement businessPlacedWith;
		
	@FindBy(xpath = "//select[@name='_act']")
	WebElement action;
	
	@FindBy(xpath = "//div[@class='modal-content']//a[@title='OK']")
	WebElement dialogCancelOkBtn;

	public UIRef_PolicyCancelDialog(WebDriver driver, ExtentTest extentedReport) {
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
	
	public void enterCancelEffectiveDate(String cancelEffectiveDateVal,String dayInt, String yearInt, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, cancelEffectiveDate);
			String strDate = GenericUtils.setDate(cancelEffectiveDateVal, cancelEffectiveDate, Integer.parseInt(dayInt), Integer.parseInt(yearInt));
			Log.message("Entered the Cancel Effective Date value is : " + strDate, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Cancel Effective Date on Cancel Dailog " + e);
		}
	}
	
	
	public void verifyCancelDialogLable (WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, dialogLabel);
			Log.assertThatExtentReport(dialogLabel.getAttribute("Value").equalsIgnoreCase("CANCEL POLICY"), "Cancel Dialog Matched", "Cancel Dialog not Matched", driver, report);			
		}catch(Exception e){
			throw new Exception("Error in verification of Cancel Dialog Label : " + e);
		}
	}
	
	public void selectAction(String actionVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, action);
			Select select = new Select(action);
			select.selectByValue(actionVal);
			Log.message("Entered action value is : " + actionVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the action in Cancel Dailog page " + e);
		}
	}
	
	public void selectreturnPremiumType(String returnPremiumTypeVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, returnPremiumType);
			Select select = new Select(returnPremiumType);
			select.selectByValue(returnPremiumTypeVal);
			Log.message("Entered returnPremiumType value is : " + returnPremiumTypeVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the returnPremiumType in Cancel Dailog page " + e);
		}
	}
	
	public void selectReasonType(String reasonVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, reasonType);
			Select select = new Select(reasonType);
			select.selectByValue(reasonVal);
			Log.message("Entered Reason Type Value is : " + reasonVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Reason Type in Cancel Dailog page " + e);
		}
	}
	
	
	public UIRef_PolicyReviewPremium clickCancelDialogOK(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, dialogCancelOkBtn);
			dialogCancelOkBtn.click();
			Log.pass("Clicked the OkBtn on Policy Cancel Dailog", driver,extentedReport, true);
			return new UIRef_PolicyReviewPremium(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the dialogCancelOkBtn on Policy Cancel Dialog : " + e);
		}		
	}
}
