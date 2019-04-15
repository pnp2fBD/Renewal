package com.ssp.uirefresh_pages;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

public class UIRef_CreateClaim extends LoadableComponent <UIRef_CreateClaim>{
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	@FindBy(css = "input[name*=dateofLoss]")
	WebElement txtDOL;
		
	@FindBy(css = "#RecordDetailsTimePicker")
	WebElement txtTOL;
		
	@FindBy(css = "input[name='clmHndlr']")
	WebElement txtHandlerUnit;
	
	@FindBy(css = "a[data-i18n-attr*='next_button'][ng-click*='recordDetailsVM.tab.narrative']")
	WebElement btnNotificationNext;
		
	@FindBy(css = "textarea[name='narrative']")
	WebElement txtNarrative;
	
	@FindBy(css = "textarea[name='description']")
	WebElement txtDescription;
		
	@FindBy(css = "custom-drop-down[ng-model='recordDetailsVM._ct'] select[name='causeOfLoss']")
	WebElement selClaimType;
	
	//@FindBy(css = "select[name='status']")
	//WebElement selStatus;
		
	@FindBy(css = "custom-drop-down[ng-model='recordDetailsVM._col'] select[name='causeOfLoss']")
	WebElement selCauseofLoss;
	
	@FindBy(css = "div[ ng-include*='./app/components/manageClaims/narrative.htm'] a[title='OK'][data-i18n-attr*='ok_button']")
	WebElement btnNarrativeOK;
	
	@FindBy(css = "p[ng-click*='Complete'] span[data-i18n*='complete_label']")
	WebElement lnkComplete;
	
	@FindBy(css = "#split-main-container > div.widgets-breadcrumb.ng-scope > ul > li:nth-child(4) > span.urn-breadcrumb.ng-binding.ng-scope")
	WebElement claimURN;
	
	@FindBy(css = "div[ng-class*=RecordDetails] p[ng-click*='RecordDetails']")
	WebElement lnkRecordDetails;
	
	@FindBy(css = "div[ng-class*='Circumstance'] span[data-i18n*='create_circumstance_label']")
	WebElement lnkCreateCircumstance;
	
	@FindBy(css = "div[ng-class*=WorkWithParties] p[ng-click*='WorkWithParties']")
	WebElement lnkWorkWithParties;
		
	@FindBy(css = "div[ng-class*=ViewAccount] p[ng-click*='ViewAccount']")
	WebElement lnkViewAccount;
		
	@FindBy(css = "div[ng-class*='ApplyEstimates'] p[ng-click*='ApplyEstimates']")
	WebElement lnkApplyEstimates;				
	
	@FindBy(css = "div[ng-class*=Information] p[ng-click*='Information']")
	WebElement lnkInformation;
	
	@FindBy(css = "div[ng-class*=MakePayment] p[ng-click*='MakePayment']")
	WebElement lnkMakePayments;
	
	@FindBy(css = "div[ng-class*=AllocateRecoveries] p[ng-click*='AllocateRecoveries']")
	WebElement lnkAllocateRecoveries;
						
	public UIRef_CreateClaim(WebDriver driver, ExtentTest extentedReport) {
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
	//WebElement txtDOL;
	public void enterClaimDOL(String fromDateVal,String dayInt,String yearInt,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForelementToBeClickable(driver, txtDOL, "Unable to enter date of loss");
			String strDate = GenericUtils.setDate(fromDateVal, txtDOL, Integer.parseInt(dayInt), Integer.parseInt(yearInt));
			//txtDOL.sendKeys(inputVal);
			Log.message("Entered the claim DOL : " + strDate, driver, extentedReport);	
		}catch(Exception e){
			throw new Exception("Error while entring the claim DOL on create claim page: " + e);
		}
	}
	//WebElement txtTOL;
	public void enterClaimTOL(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, txtTOL);
			txtTOL.sendKeys(inputVal);
			Log.message("Entered the claim TOL : " + inputVal, driver, extentedReport);	
		}catch(Exception e){
			throw new Exception("Error while entring the claim TOL on create claim page: " + e);
		}
	}	
	//WebElement txtHandlerUnit;
	public void enterClaimHander(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, txtHandlerUnit);
			txtHandlerUnit.sendKeys(inputVal);
			Log.message("Entered claim hander is : " + inputVal, driver, extentedReport);	
		}catch(Exception e){
			throw new Exception("Error while entring the claim handler on create claim page: " + e);
		}
	}
	// WebElement btnNotificationNext;
	public void ClickBtnNotificationNext(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnNotificationNext);
			btnNotificationNext.click();
			Log.message("Clicked the btn Notification Next on create claim page ", driver, extentedReport,true);
		} catch (Exception e) {
			throw new Exception("Error while clicking button Notification Next on create claim page " + e);
		}
	}
	//WebElement txtNarrative;
	public void enterNarrative(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, txtNarrative);
			txtNarrative.sendKeys(inputVal);
			Log.message("Entered txtNarrative is : " + inputVal, driver, extentedReport);	
		}catch(Exception e){
			throw new Exception("Error while entring the Narrative on create claim page: " + e);
		}
	}	
	//WebElement txtDescription;
	public void enterClaimDescription(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, txtDescription);
			txtDescription.sendKeys(inputVal);
			Log.message("Entered claim description is : " + inputVal, driver, extentedReport);	
		}catch(Exception e){
			throw new Exception("Error while entring the claim description on create claim page: " + e);
		}
	}	
	//WebElement selClaimType;
	public void selectClaimType(String inputVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, selClaimType);
			Select select = new Select(selClaimType);
			select.selectByVisibleText(inputVal);
			Log.message("selected claim type is  : " + inputVal, driver, extentedReport);			
		} catch (Exception e) {
			throw new Exception("Error while the selection of claim type on create claim page " + e);
		}
	}	
	//WebElement selCauseofLoss;
	public void selectCauseofLoss(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, selCauseofLoss);
			Select select = new Select(selCauseofLoss);
			select.selectByVisibleText(inputVal);
			Log.message("selected claim causeofLoss is  : " + inputVal, driver, extentedReport);			
		} catch (Exception e) {
			throw new Exception("Error while the selection of claim causeofLoss on create claim page " + e);
		}
	}	
	//WebElement btnNarrativeOK;
	public void ClickBtnNarrativeOK(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnNarrativeOK);
			btnNarrativeOK.click();
			Log.message("Clicked the btn OK narrative tab on create claim page ", driver, extentedReport,true);
			WaitUtils.uiRefwaitForSpinner(driver);
		} catch (Exception e) {
			throw new Exception("Error while clicking btn OK narrative tab on create claim page " + e);
		}
	}	
	//WebElement lnkComplete;
	public UIRef_ClaimComplete ClickLnkComplete(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkComplete);
			lnkComplete.click();
			Log.message("Clicked the Link Complete on create claim page ", driver, extentedReport,true);
			return new UIRef_ClaimComplete(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking Link Complete on create claim page " + e);
		}
	}
	public String getClaimURN(ExtentTest extentedReport, WebDriver driver) {
		String cURN = "";
		try {
			WaitUtils.waitForelementToBeClickable(driver, claimURN, "Unable to find claim URN");
			cURN = claimURN.getText();
			cURN = cURN.replaceAll("[()]", "");
			Log.message("Claim URN captured is: " + cURN, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Unable to capture claim urn on create claim page", driver, extentedReport, true);
		}
		return cURN;
	}
	//WebElement lnkCreateCircumstance;
		public UIRef_ClaimCircumstance clickLinkCreateCircumstance(ExtentTest extentedReport, WebDriver driver) throws Exception {
			try {
				WaitUtils.waitForElement(driver, lnkCreateCircumstance);
				lnkCreateCircumstance.click();
				Log.message("Clicked the Link Create Circumstance on create claim page ", driver, extentedReport,true);
				return new UIRef_ClaimCircumstance(driver, extentedReport);
			} catch (Exception e) {
				Log.fail("Clicked the Link Create Circumstance on create claim page " + e.getMessage(), driver, extentedReport,true);
				throw new Exception("Error while clicking Link Create Circumstance on create claim page " + e);
			}
		}
}
