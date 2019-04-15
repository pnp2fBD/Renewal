package com.ssp.uirefresh_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.WaitUtils;

public class UIRef_ContactSearchDialog extends LoadableComponent<UIRef_ContactSearchDialog> {
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	@FindBy(xpath = "//input[@name='name']")
	WebElement modalName;
	
	@FindBy(xpath = "//div[@class='modal-content']//a[@title='Search']//span[text()='Search']")
	WebElement modalSearchBtn;
		
	@FindBy(xpath = "//tr[1]//a[@title='Retrieve']")
	WebElement modalRetreiveRow1;
	
	@FindBy(xpath = "//div[@class='modal-content']")
	WebElement contactModalWin;
	
	public UIRef_ContactSearchDialog(WebDriver driver, ExtentTest report) {
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
		WaitUtils.waitForPageLoad(driver);
	}
	
	public void enterContactName(String contactNameVal, ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, modalName);
			modalName.sendKeys(contactNameVal);
			Log.pass("Entered the Contact Name on Contact Search Dialog", driver,extentedReport, true);		
		} catch (Exception e) {
			throw new Exception("Error while entering the Contact Name on Contact Search Dialog" + e);
		}		
	}
	
	public void clickModalSearchbtn(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, modalSearchBtn);
			modalSearchBtn.click();
			Log.pass("Clicked the modalSearchBtn on Contact Search Dialog", driver,extentedReport, true);
			
		} catch (Exception e) {
			throw new Exception("Error while clicking the SearchBtn on Contact Search Dialog " + e);
		}		
	}
	
	public void clickmodalRetreiveRow1(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, modalRetreiveRow1);
			modalRetreiveRow1.click();
			Log.pass("Clicked the modalRetreiveRow1 on Contact Search Dialog", driver,extentedReport, true);
			WaitUtils.waitUntilElementDisappear(driver, contactModalWin);
		} catch (Exception e) {
			throw new Exception("Error while clicking the Retreive Row 1 contact on Contact Search Dialog" + e);
		}		
	}
	

}
