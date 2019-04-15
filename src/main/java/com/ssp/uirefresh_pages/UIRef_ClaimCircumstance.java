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

public class UIRef_ClaimCircumstance extends LoadableComponent<UIRef_ClaimCircumstance> {
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;

	@FindBy(xpath = "//a[contains(text(),'Contents')]//parent::span//parent::td//following-sibling::td[@class='sorting_disabled']/div/a")
	WebElement lnkContentsdpdwn;
		
	@FindBy(xpath = "//a[contains(text(),'Contents')]//parent::span//parent::td//following-sibling::td[@class='sorting_disabled']/div//ul//li/a/span[text()='Create Generic Claim']")
	WebElement lnkCntCreateGenericClaim;

	@FindBy(xpath = "//a[contains(text(),'Buildings')]//parent::span//parent::td//following-sibling::td[@class='sorting_disabled']/div/a")
	WebElement lnkBuildingsdpdwn;
	
	@FindBy(xpath = "//a[contains(text(),'Buildings')]//parent::span//parent::td//following-sibling::td[@class='sorting_disabled']/div//ul//li/a/span[text()='Create Generic Claim']")
	WebElement lnkBldCreateGenericClaim;
	
	@FindBy(css = "select[name='typeOfClaim']")
	WebElement selTypeOfClaim;
	
	@FindBy(css = "#totalOutstanding")
	WebElement txtTotalOutstand;
			
	@FindBy(css = "#totalPaid")
	WebElement txtTotalPaid;
			
	@FindBy(css = "#totalRecovered")
	WebElement 	txtTotalRecovered;
					
	@FindBy(css = "a[title*='Save'] span[data-i18n*='save_button']")
	WebElement btnSave;
	
	@FindBy(css = "div[ng-class*='ApplyEstimates']")
	WebElement lnkApplyEstimates;
				

	public UIRef_ClaimCircumstance(WebDriver driver, ExtentTest extentedReport) {
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
	
	public void clickCntCreateGenericClaim(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkContentsdpdwn);
			lnkContentsdpdwn.click();
			WaitUtils.waitForElement(driver, lnkCntCreateGenericClaim);
			lnkCntCreateGenericClaim.click();
			Log.pass("Clicked the contents create generic claim link on claim circumstance page ", driver, extentedReport,true);
			WaitUtils.uiRefwaitForSpinner(driver);
		} catch (Exception e) {
			Log.fail("Error while clicking contents create generic claim link on claim circumstance page : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking contents create generic claim link on claim circumstance page " + e);
		}
	}
	
	public void clickBldCreateGenericClaim(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkBuildingsdpdwn);
			lnkBuildingsdpdwn.click();
			WaitUtils.waitForElement(driver, lnkBldCreateGenericClaim);
			lnkBldCreateGenericClaim.click();
			Log.pass("Clicked the building create generic claim link on claim circumstance page ", driver, extentedReport,true);
			WaitUtils.uiRefwaitForSpinner(driver);
		} catch (Exception e) {
			Log.fail("Error while clicking building create generic claim link on claim circumstance page : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking building create generic claim link on claim circumstance page " + e);
		}
	}
	
	public void selectClaimType(String inputVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, selTypeOfClaim, "Unable to select claim type on generic circumstance");
			Select select = new Select(selTypeOfClaim);
			select.selectByVisibleText(inputVal);
			Log.message("selected type of claim is  : " + inputVal, driver, extentedReport);			
		} catch (Exception e) {
			Log.fail("Error while the selection of type of claim on create generic circumstance page  : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while the selection of type of claim on create generic circumstance page " + e);
		}
	}
	//txtTotalOutstand;
	public void enterTotalOutstand(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, txtTotalOutstand);
			txtTotalOutstand.clear();
			txtTotalOutstand.sendKeys(inputVal);
			Log.message("Entered total outstanding is : " + inputVal, driver, extentedReport);	
		}catch(Exception e){
			Log.fail("Error while entring the total outstanding on create generic circumstance page  : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entring the total outstanding on create generic circumstance page: " + e);
		}
	}	
	//txtTotalPaid;
	public void enterTotalPaid(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, txtTotalPaid);
			txtTotalPaid.clear();
			txtTotalPaid.sendKeys(inputVal);
			Log.message("Entered total paid is : " + inputVal, driver, extentedReport);	
		}catch(Exception e){
			Log.fail("Error while entering the total paid on create generic circumstance  : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the total paid on create generic circumstance: " + e);
		}
	}
	//txtTotalRecovered;	
	public void enterTotalRecovered(String inputVal,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, txtTotalRecovered);
			txtTotalRecovered.clear();
			txtTotalRecovered.sendKeys(inputVal);
			Log.message("Entered total recovered is : " + inputVal, driver, extentedReport);	
		}catch(Exception e){
			Log.fail("Error while entring the total recovered on create generic circumstance  : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entring the total recovered on create generic circumstance: " + e);
		}
	}	
	public void clickBtnSave(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnSave);
			btnSave.click();
			Log.pass("Clicked the button save on create claim circumstance page ", driver, extentedReport,true);
			WaitUtils.uiRefwaitForSpinner(driver);
		} catch (Exception e) {
			Log.fail("Error while clicking button save on create generic claim circumstance page : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking button save on create generic claim circumstance page " + e);
		}
	}
	//lnkApplyEstimates
	public UIRef_ClaimApplyEstimates clicklnkApplyEstimates(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkApplyEstimates);
			lnkApplyEstimates.click();
			Log.pass("Clicked the link ApplyEstimates ", driver, extentedReport,true);
			WaitUtils.uiRefwaitForSpinner(driver);
			return new UIRef_ClaimApplyEstimates(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking the link ApplyEstimates : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking the link ApplyEstimates " + e);
		}
	}
}
