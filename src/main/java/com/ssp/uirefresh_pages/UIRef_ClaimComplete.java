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

public class UIRef_ClaimComplete extends LoadableComponent<UIRef_ClaimComplete> {
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;

	@FindBy(css = "custom-radio-button[label*=Notified] input")
	WebElement rdNotified;
	
	@FindBy(css = "custom-radio-button[label*=Open] input")
	WebElement rdOpen;
	
	@FindBy(css = "a[ng-click*='claimCompleteVm.complete'][ title='OK']")
	WebElement btnOK;

	public UIRef_ClaimComplete(WebDriver driver, ExtentTest extentedReport) {
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

	public void clickRdNotified(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, rdNotified);
			rdNotified.click();
			Log.message("selected the claim status notified  : ", driver, extentedReport, true);
		} catch (Exception e) {
			Log.fail("Error while the selection of claim claim status notified on claim complete page" + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while the selection of claim claim status notified on claim complete " + e);
		}
	}
	
	public void clickRdOpen(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, rdOpen);
			rdOpen.click();
			Log.message("selected the claim status open  : ", driver, extentedReport, true);
		} catch (Exception e) {
			Log.fail("Error while the selection of claim claim status open on claim complete page" + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while the selection of claim claim status open on claim complete page" + e);
		}
	}

	public void ClickBtnOK(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnOK);
			btnOK.click();
			Log.message("Clicked the btn OK on claim complte page ", driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while the clicking btn OK on claim complete page" + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while clicking btn OK claim complete page " + e);
		}
	}

}
