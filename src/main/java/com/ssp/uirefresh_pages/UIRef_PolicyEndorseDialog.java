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

public class UIRef_PolicyEndorseDialog extends LoadableComponent<UIRef_PolicyEndorseDialog>{
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//form[@name='policyAdjustmentVm.policyAdjustmentForm']")
	WebElement endorseForm;
	
	@FindBy(xpath = "//h3[@class='modal-title']//span[text()='Endorse Policy']")
	WebElement endorsedialogLabel;
		
	@FindBy(xpath = "//input[@name='_effFrm']")
	WebElement endorseCancelEffectiveDate;
	
	@FindBy(xpath = "//select[@name='rsnCd']")
	WebElement endrorseType;
	
	@FindBy(xpath = "//form[@name='policyAdjustmentVm.policyAdjustmentForm']//textarea")
	WebElement endorseReason;
		
	@FindBy(xpath = "//form[@name='policyAdjustmentVm.policyAdjustmentForm']//a[@title='OK']//span[text()='OK']")
	WebElement endorseOKbtn;

	public UIRef_PolicyEndorseDialog(WebDriver driver, ExtentTest extentedReport) {
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
	
	public void enterEndorseCancelEffectiveDate(String endorseCancelEffectiveDateVal,String dayInt, String yearInt, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, endorseCancelEffectiveDate);
			String strDate = GenericUtils.setDate(endorseCancelEffectiveDateVal, endorseCancelEffectiveDate, Integer.parseInt(dayInt), Integer.parseInt(yearInt));
			Log.message("Entered the Endorse Effective Date value is : " + strDate, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Endorse Effective Date on Endorse Dailog " + e);
		}
	}
	
	
	public void verifyEndorseDialogLable (WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, endorsedialogLabel);
			Log.assertThatExtentReport(endorsedialogLabel.getAttribute("Value").equalsIgnoreCase("Endorse Policy"), "Endorse Policy Dialog label Matched", "Endorse Policy Dialog label not Matched", driver, report);			
		}catch(Exception e){
			throw new Exception("Error in verification of Endorse Policy Dialog Label : " + e);
		}
	}
	
	public void selectEndorseType(String endrorseTypeVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, endrorseType);
			Select select = new Select(endrorseType);
			select.selectByValue(endrorseTypeVal);
			Log.message("Entered endrorseType value is : " + endrorseTypeVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the endrorse Type in Endorse Dailog page " + e);
		}
	}
	
	public void enterReason(String endorseReasonVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, endorseReason);
			endorseReason.sendKeys(endorseReasonVal);
			Log.message("Entered endorseReason value is : " + endorseReasonVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the endorseReason in Endorse Dailog page " + e);
		}
	}
	
	/*
	public void selectReasonType(String reasonVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, reasonType);
			Select select = new Select(reasonType);
			select.selectByValue(reasonVal);
			Log.message("Entered Reason Type Value is : " + reasonVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Reason Type in Cancel Dailog page " + e);
		}
	}*/
	
	
	public UIRef_PolicyTree clickEndorseDialogOK(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, endorseOKbtn);
			endorseOKbtn.click();
			Log.pass("Clicked the endorseOKbtn on Policy Endorse Dailog", driver,extentedReport, true);
			return new UIRef_PolicyTree(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the endorseOKbtn on Policy Endorse Dialog : " + e);
		}		
	}
}
