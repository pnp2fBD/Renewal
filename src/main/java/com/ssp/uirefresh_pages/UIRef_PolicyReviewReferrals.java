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

public class UIRef_PolicyReviewReferrals extends LoadableComponent <UIRef_PolicyReviewReferrals>{
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	
	@FindBy(xpath = "//a[@title='Continue']//span[text()='Continue']")
	WebElement alertContinuebtn;
	
	@FindBy(css = "#authorisationNumber")
	WebElement txtAuthorizationNum;
	
	@FindBy(xpath = "//form[@name='reviewReferralVm.reviewReferralsForm']//a[@title='Authorise']//span[text()='AUTHORISE']")
	WebElement btnAuthorise;
	
	@FindBy(xpath = "//table[@id='example']/tbody/tr/td[3]")
	WebElement status;
	
	@FindBy(xpath = "//span[text()='Complete']")
	WebElement lnkComplete;
	
	@FindBy(xpath = "//*[@id='split-main-container']//a[@title='OK']/span/span[text()='OK']")
	WebElement btnOK;
		
	public UIRef_PolicyReviewReferrals(WebDriver driver, ExtentTest extentedReport) {
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
	
	public void ClickAlertContinuebtn(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, alertContinuebtn);
			alertContinuebtn.click();
			Log.pass("Clicked the alertContinuebtn in Claims prior to incpetion", driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while Clicking the alertContinuebtn in Claims prior to incpetion");
		}
	}
		
	public void enterAuthorizationNum(String ARNcode, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, txtAuthorizationNum, "Unable to click authorizationNum");
			txtAuthorizationNum.clear();
			txtAuthorizationNum.sendKeys(ARNcode);
			Log.pass("Entered the ARN Code for Policy Referrals is: " + ARNcode, driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while adding the ARN Code in Policy Referrals");
		}
	}
	
	
	public void ClickbtnAuthorise(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnAuthorise);
			btnAuthorise.click();
			Log.pass("Clicked the btnAuthorise for Policy Referrals", driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while Clicking the btnAuthorise for Policy Referrals");
		}
	}
	
	public void ClickOK(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnOK);
			btnOK.click();
			Log.pass("Clicked the btnOK for Policy Referrals", driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while Clicking the btnOK for Policy Referrals");
		}
	}
	
	public boolean validateARNStatus(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, status);
			int i=0;
			do {
				if (status.getAttribute("outerText").equals("")){
					btnAuthorise.click();
					Thread.sleep(3000);
				}else{
					break;
				}
				i++;
			} while (i <= 3);			
			if (status.getAttribute("outerText").equalsIgnoreCase("Authorised")){
				Log.pass("Policy Referral reason is Approved ", driver, extentedReport, true);
				return true;
			}else{
				Log.fail("Policy Referral reason is still not Approved ", driver, extentedReport, true);
				return false;
				}			
		} catch (Exception e) {
			throw new Exception("Error while verifying the Policy ARN");
		}
	}
	
	public UIRef_PolicyComplete NavigateToComplete(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkComplete);
			lnkComplete.click();
			Log.message("Clicked on the Complete Tab", driver, extentedReport);
			return new UIRef_PolicyComplete(driver, report);
		} catch (Exception e) {
			throw new Exception("Error while Click on the Complete Tab" + e);
		}
	}
}
