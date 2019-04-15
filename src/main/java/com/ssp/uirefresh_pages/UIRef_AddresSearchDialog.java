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

public class UIRef_AddresSearchDialog extends LoadableComponent<UIRef_AddresSearchDialog> {
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	@FindBy(css = "#postCode")
	WebElement txtpostcode;

	@FindBy(css = "a[ng-click*='searchAddress'] span[title*='Search']")
	WebElement btnPostcodeSearch;
	
	@FindBy(css = "table[id*='DataTables_Table_']  tr:nth-child(1)  td:nth-child(3)  a")
	WebElement LnkaddressRetriveIcon;
	
	public UIRef_AddresSearchDialog(WebDriver driver, ExtentTest report) {
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
	
	public void enterPostCode(String inputVal, ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, txtpostcode, "Unable to enter postcode in address search dialog");
			txtpostcode.sendKeys(inputVal);
			Log.pass("Entered the postcode on address search dialog", driver,extentedReport, true);		
		} catch (Exception e) {
			Log.fail("Error while entering the postcode in address search dialog", driver,extentedReport, true);
			throw new Exception("Error while entering the postcode in address search dialog" + e);
		}		
	}
	
	public void clickBtnPostcodeSearch(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnPostcodeSearch);
			btnPostcodeSearch.click();
			Log.pass("Clicked the btnPostcodeSearch on address search dialog", driver,extentedReport, true);			
		} catch (Exception e) {
			Log.fail("Error while clicking the btnPostcodeSearch on address search dialog : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking the btnPostcodeSearch on address search dialog " + e);
		}		
	}
	
	public void clickLnkaddress1RetriveIcon(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, LnkaddressRetriveIcon);
			LnkaddressRetriveIcon.click();
			Log.pass("Clicked the LnkaddressRetriveIcon on address search dialog", driver,extentedReport, true);
			WaitUtils.waitUntilElementDisappear(driver, LnkaddressRetriveIcon);
		} catch (Exception e) {
			Log.fail("Error while clicking the the address1RetriveIcon on address search dialog : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking the address1RetriveIcon on address search dialog" + e);
		}		
	}
	


}
