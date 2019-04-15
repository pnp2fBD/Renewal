package com.ssp.uirefresh_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;

public class UIRef_PolicyRenewal  extends LoadableComponent<UIRef_PolicyRenewal>{
	private final WebDriver driver ;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;
	
		
	@FindBy(xpath = "//a[@title='Continue']//span[text()='Continue']")
	WebElement renewalContinueBtn;
	
	public UIRef_PolicyRenewal(WebDriver driver, ExtentTest report) {
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
	
	public void ClickRenewalContinueBtn(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, renewalContinueBtn);
			renewalContinueBtn.click();
			Log.message("Clicked the Continue Button for Renewal", driver, extentedReport);
			
		}catch(Exception e){
			throw new Exception("Error while Click of the Continue Button for Renewal" + e );
		}
	}
	
	public UIRef_PolicyReviewPremium NavigateToReviewPremium(WebDriver driver, ExtentTest report) throws Exception{
		try{
			Log.message("NavigateToReviewPremium", driver, extentedReport);	
			return new UIRef_PolicyReviewPremium(driver, report);
		}catch(Exception e){
			throw new Exception("Error while NavigateToReviewPremium" + e );
		}
	}

}
