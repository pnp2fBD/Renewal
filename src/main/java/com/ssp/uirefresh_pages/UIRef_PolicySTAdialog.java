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

public class UIRef_PolicySTAdialog extends LoadableComponent<UIRef_PolicySTAdialog>{
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	
	@FindBy(xpath = "//h3[@class='modal-title']//span[text()='Cancel Policy']")
	WebElement dialogLabel;
		
	@FindBy(xpath = "//input[@name='_effFrm']")
	WebElement staFromDate;
	
	@FindBy(xpath = "//input[@name='_effTo']")
	WebElement staToDate;
		
	@FindBy(xpath = "//select[@name='_rsn']")
	WebElement type;
	
	@FindBy(xpath = "//form[@name='policyAdjustmentVm.policyAdjustmentForm']//textarea")
	WebElement reason;
		
	@FindBy(xpath = "//form[@name='policyAdjustmentVm.policyAdjustmentForm']")
	WebElement staForm;
		
	@FindBy(xpath = "//form[@name='policyAdjustmentVm.policyAdjustmentForm']//a[@title='OK']")
	WebElement staOKBtn;

	public UIRef_PolicySTAdialog(WebDriver driver, ExtentTest extentedReport) {
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
	
	public void enterSTAFromDate(String fromDateVal,String dayInt, String yearInt, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, staFromDate);
			String strDate = GenericUtils.setDate(fromDateVal, staFromDate, Integer.parseInt(dayInt), Integer.parseInt(yearInt));
			Log.message("Entered the STA From Date value is : " + strDate, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the STA FromDate " + e);
		}
	}
	
	public void enterSTAToDate(String toDateVal,String dayInt, String yearInt, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, staToDate);
			String strDate = GenericUtils.setDate(toDateVal, staToDate, Integer.parseInt(dayInt), Integer.parseInt(yearInt));
			Log.message("Entered the STA To Date value is : " + strDate, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the STA ToDate " + e);
		}
	}
	
	public void selectType(String typeVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, type);
			Select select = new Select(type);
			select.selectByValue(typeVal);
			Log.message("Entered Type value in STA Dialog : " + typeVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Type in STA Dailog page " + e);
		}
	}
	
	public void enterReason(String reasonVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, reason);
			reason.sendKeys(reasonVal);
			Log.message("Entered STA reason value is : " + reasonVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the endorseReason in STA " + e);
		}
	}
	

	
	public UIRef_PolicyTree clickstaOKBtn (ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, staOKBtn);
			staOKBtn.click();
			Log.pass("Clicked the staOKBtn on STA Dailog", driver,extentedReport, true);
			return new UIRef_PolicyTree(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the staOKBtn on STA Dialog : " + e);
		}		
	}
}
