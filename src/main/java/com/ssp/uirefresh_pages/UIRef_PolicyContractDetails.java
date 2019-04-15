package com.ssp.uirefresh_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;

public class UIRef_PolicyContractDetails extends LoadableComponent<UIRef_PolicyContractDetails>{
	
	private WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;
	
	@FindBy(xpath="//div[@class='input-groups policy_insurer policy_insurer_woscrl']/select")
	WebElement txtinsured;
	
	public UIRef_PolicyContractDetails(WebDriver driver, ExtentTest report) {
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

}
