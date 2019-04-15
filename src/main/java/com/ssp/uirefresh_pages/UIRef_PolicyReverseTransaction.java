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

public class UIRef_PolicyReverseTransaction extends LoadableComponent <UIRef_PolicyReverseTransaction>{
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
		
	@FindBy(xpath = "//h3[@class='modal-title']//span[text()='Reverse Transaction']")
	WebElement dialogLabel;
					
	@FindBy(xpath = "//select[@name='_rsn']")
	WebElement type;
	
	@FindBy(xpath = "//form[@name='policyAdjustmentVm.policyAdjustmentForm']//textarea")
	WebElement reason;
		
	@FindBy(xpath = "//form[@name='policyAdjustmentVm.policyAdjustmentForm']")
	WebElement abForm;
		
	@FindBy(xpath = "//form[@name='policyAdjustmentVm.policyAdjustmentForm']//a[@title='OK']")
	WebElement baOKBtn;

	public UIRef_PolicyReverseTransaction(WebDriver driver, ExtentTest extentedReport) {
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

		
	public void selectType(String typeVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.uiRefwaitForSpinner(driver);
			WaitUtils.waitForelementToBeClickable(driver, type, "Reverse Transaction Type dopdown element not clickable");
			Select select = new Select(type);
			select.selectByValue(typeVal);
			Log.message("Selected Type value in Reinstate is : " + typeVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Type in Reverse Transaction page " + e);
		}
	}
	
	public void enterReason(String reasonVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, reason, "Reverse Transaction reason element not clickable");
			reason.sendKeys(reasonVal);
			Log.message("Entered Reinstate reason value is : " + reasonVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the Reason in Reverse Transaction " + e);
		}
	}

	public UIRef_PolicySearch clickOKBtn (String policyURNval, ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, baOKBtn);
			baOKBtn.click();
			Log.pass("Reverse Transaction Successful of Policy URN " + policyURNval, driver,extentedReport, true);
			WaitUtils.uiRefwaitForSpinner(driver);
			return new UIRef_PolicySearch(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the Reverse Transaction OK  : " + e);
		}		
	}

}
