package com.ssp.uirefresh_pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.WaitUtils;

public class UIRef_ManageClaims extends LoadableComponent <UIRef_ManageClaims>{
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	@FindBy(css = "#claimUniqueRef")
	WebElement txtClaimNum;
		
	@FindBy(css = "button[title='Create']")
	WebElement btnCreate;
		
	@FindBy(css = "li[title='Create Claim']")
	WebElement lnkCreateClaim;
	
	@FindBy(css = "#contacts")
	WebElement txtContactSearch;
		
	@FindBy(css = "custom-input-box[ng-model*='searchClaimsVM.selectedContacts._nm'] input")
	WebElement txtContactName;
	
	@FindBy(css = "#policy")
	WebElement txtPolicySearch;
		
	@FindBy(css = "custom-input-box[ng-model*='searchClaimsVM.selectedPolicy.policyName'] input")
	WebElement txtPolicyName;
	
	@FindBy(css = "select[name='status']")
	WebElement selStatus;
		
	@FindBy(css = "a[ng-click^='searchClaimsVM.searchClaim'] span[data-i18n*='search_button']")
	WebElement btnSearch;
		
	@FindBy(css = "div[id^='DataTables_Table_'] table tbody tr:nth-child(1) td:nth-child(7)")
	WebElement tblRow1Status;
	
	@FindBy(css = "div[id^='DataTables_Table_'] table tbody tr:nth-child(1) td:nth-child(10) a")
	WebElement tblRow1Edit;
			
	public UIRef_ManageClaims(WebDriver driver, ExtentTest extentedReport) {
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
	//txtClaimNum
	public void enterClaimNum(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, txtClaimNum);
			txtClaimNum.clear();
			txtClaimNum.sendKeys(inputVal);
			txtClaimNum.sendKeys(Keys.TAB);
			Log.message("Entered the claim number : " + inputVal, driver, extentedReport);	
		}catch(Exception e){
			Log.fail("Error while entring the claim num on claim search page: " + e.getMessage(), driver, extentedReport,true);	
			throw new Exception("Error while entring the claim num on claim search page: " + e);
		}
	}	
	//WebElement btnCreate;
	public void clickBtnCreate(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnCreate);
			btnCreate.click();			
			Log.message("Clicked the btn Create on claim search page ", driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking button create on claim search: " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking button create on claim search: " + e);
		}		
	}	
	//WebElement lnkCreateClaim;
	public UIRef_CreateClaim clickBtnCreateClaim(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnCreate);
			btnCreate.click();	
			WaitUtils.waitForElement(driver, lnkCreateClaim);
			lnkCreateClaim.click();
			Log.message("Clicked the lnk CreateClaim on claim search page ", driver, extentedReport);
			WaitUtils.uiRefwaitForSpinner(driver);
			return new UIRef_CreateClaim(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking lnk CreateClaim on claim search: " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking lnk CreateClaim on claim search: " + e);
		}		
	}
	//WebElement txtContactSearch;
	public void enterContact(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, txtContactSearch);
			if(!(inputVal == null) ){
				txtContactSearch.clear();
				txtContactSearch.sendKeys(inputVal);
				txtContactSearch.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				Log.message("Entered the policy Number : " + inputVal, driver, extentedReport);
			}				
		}catch(Exception e){
			Log.fail("Error while entring the policyNum on claim search page: " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entring the policyNum on claim search page: " + e);
		}
	}	
	
	//WebElement txtContactName;
	
	
	//WebElement txtPolicySearch;
	public void enterpolicyNum(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, txtPolicySearch);
			txtPolicySearch.clear();
			txtPolicySearch.sendKeys(inputVal);
			txtPolicySearch.sendKeys(Keys.TAB);
			Log.message("Entered the contact URN : " + inputVal, driver, extentedReport);	
		}catch(Exception e){
			Log.fail("Error while entring the contact URN on claim search page: " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entring the contact URN on claim search page: " + e);
		}
	}		
	//WebElement txtPolicyName;
	
	
	//WebElement selStatus;
		
	
	//WebElement btnSearch;
	public void clickBtnSearch(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnSearch);
			btnSearch.click();			
			WaitUtils.uiRefwaitForSpinner(driver);
			Log.message("Clicked search button on claim search page ", driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking search button on claim search:  " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking search button on claim search: " + e);
		}		
	}	
	public void verifyClaimStatus(String inputVal, ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			String status = "";
			WaitUtils.waitForElement(driver, tblRow1Status);
			status = tblRow1Status.getText();	
			Log.softAssertThat(status.equalsIgnoreCase(inputVal), "Claim status verified as " + status, 
					"Claim status verification failed expected is " + inputVal + "however actual is" + status , driver, extentedReport, true);
		} catch (Exception e) {
			Log.fail("Error while verifying the claim status on claim search page is :" + e.getMessage(), driver, extentedReport);
		}		
	}
	//tblRow1Edit
	public UIRef_CreateClaim clicktblRow1Edit(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {			
			WaitUtils.waitForElement(driver, tblRow1Edit);
			tblRow1Edit.click();
			Log.message("Clicked the edit link in table row on claim search page ", driver, extentedReport);
			WaitUtils.uiRefwaitForSpinner(driver);
			return new UIRef_CreateClaim(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking edit link in table row on claim search:  " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking edit link in table row on claim search: " + e);
		}		
	}

}
