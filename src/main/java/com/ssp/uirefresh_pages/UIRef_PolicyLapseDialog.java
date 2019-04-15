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
import com.ssp.utils.WaitUtils;

public class UIRef_PolicyLapseDialog extends LoadableComponent<UIRef_PolicyLapseDialog>{
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	
	@FindBy(xpath = "//h3[@class='modal-title']//span[text()='Lapse Quote']")
	WebElement dialogLabel;
				
	@FindBy(xpath = "//select[@name='_sts']")
	WebElement action;
	
	@FindBy(xpath = "//select[@name='_stsRsn']")
	WebElement reason;
	
	@FindBy(xpath = "//div[@class='modal-content']//a[@title='OK']")
	WebElement dialogCancelOkBtn;

	public UIRef_PolicyLapseDialog(WebDriver driver, ExtentTest extentedReport) {
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
	
	public void selectAction(String actionVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, action);
			Select select = new Select(action);
			select.selectByValue(actionVal);
			Log.message("Entered action value is : " + actionVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the action in Lapse Dailog " + e);
		}
	}	
	
	public void selectReason(String reasonVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, reason);
			Select select = new Select(reason);
			select.selectByValue(reasonVal);
			Log.message("Entered Reason Value is : " + reasonVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Reason Type in Lapse Dailog " + e);
		}
	}
	
	
	public void clickOKbtn(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, dialogCancelOkBtn);
			dialogCancelOkBtn.click();
			Log.pass("Clicked the OkBtn on Policy Lapse Dailog", driver,extentedReport, true);
			//return new UIRef_PolicyReviewPremium(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the OK Btn on Policy Lapse Dialog : " + e);
		}		
	}

}
