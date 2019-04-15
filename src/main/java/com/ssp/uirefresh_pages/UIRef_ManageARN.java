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

public class UIRef_ManageARN extends LoadableComponent <UIRef_ManageARN>{
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//a[@title='Create ARN Code']//span[text()='Create ARN Code']")
	WebElement btnCreateARNcode;
		
	@FindBy(xpath = "//*[@id='split-main-container']/div[4]/div/div/div[2]/div/div[1]/div[1]/div/custom-drop-down/select")
	WebElement selReferralType;
	
	@FindBy(xpath = "//*[@id='split-main-container']//custom-date-picker[@title='Issue Date']/div/input")
	WebElement txtIssueDate;
	
	@FindBy(xpath = "//*[@id='split-main-container']/div[4]/div/div/div[2]/div/div[2]/div[1]/div/input")
	WebElement txtARN;
	
	@FindBy(xpath = "//*[@id='split-main-container']//a[@title='Search']/span/span[text()='Search']")
	WebElement btnSearch;
		
	@FindBy(xpath = "//*[contains(@id,'DataTables_Table_')]/tbody/tr/td[6]")
	WebElement gridRow6PolicyURN;
	
	@FindBy(xpath = "//*[@id='split-main-container']/div[4]/div/div/div[5]/div/a/span/span")
	WebElement btnComplete;
		
	public UIRef_ManageARN(WebDriver driver, ExtentTest extentedReport) {
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
	
	public UIRef_CreateARNCode clickCreateARN (ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, btnCreateARNcode, "Unable to Click Create ARN Code");
			btnCreateARNcode.click();
			Log.pass("Clicked the btn CreateARNcode ", driver,extentedReport, true);
			WaitUtils.uiRefwaitForSpinner(driver);
			return new UIRef_CreateARNCode(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the Reinstate OK  : " + e);
		}		
	}
	
	public void clickbtnComplete (ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, btnComplete, "Unable to Click Complete on Manage ARN");
			btnComplete.click();
			Log.pass("Clicked the btn Complete on Manage ARN ", driver,extentedReport, true);
			WaitUtils.uiRefwaitForSpinner(driver);
		} catch (Exception e) {
			throw new Exception("Error while clicking of Manage ARN  : " + e);
		}		
	}

}
