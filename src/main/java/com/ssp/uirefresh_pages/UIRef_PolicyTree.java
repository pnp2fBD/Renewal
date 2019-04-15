package com.ssp.uirefresh_pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.support.StopWatch;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;

/**
 * @author Sunil Juneja
 * @Date - 25-Oct-2018
 */

public class UIRef_PolicyTree extends LoadableComponent<UIRef_PolicyTree> {

	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;
	
	@FindBy(css="#split-main-container > div:nth-child(4) > div > div > div:nth-child(2) > div:nth-child(3) > div.dataTableContainer.table-responsive > table > tbody:nth-child(2) > tr > td:nth-child(5) > div > a")
	WebElement editIcon;
	
	@FindBy(css="#a[class='table_icons premium_icon dropdown-toggle']")
	List<WebElement> editIconinPolicyTree;
	
	@FindBy(xpath="//span[text()='Policy Extension']//parent::a//parent::span//parent::td//following-sibling::td//a[@class='table_icons premium_icon dropdown-toggle']")
	WebElement addPropertyInPolicyExtn; 
		
	@FindBy(xpath="//span[text()='Property']//parent::a//parent::span//parent::td//following-sibling::td//a[@class='table_icons premium_icon dropdown-toggle']")
	WebElement dropOptionsInPropertyRow; 
		
	//@FindBy(xpath="//span[contains(text(),'Policy Extension')]")
	@FindBy(css="#split-main-container > div:nth-child(4) > div > div > div:nth-child(2) > div:nth-child(3) > div.dataTableContainer.table-responsive > table > tbody:nth-child(2) > tr > td:nth-child(5) > div > ul > li:nth-child(10) > a")
	WebElement  contextMenuAddPolicyExtension;
	
	//@FindBy(xpath="//span[text()='Add Property']")
	@FindBy(xpath="//span[text()='Policy Extension']//parent::a//parent::span//parent::td//following-sibling::td//div[contains(@class,'dropdown dropdown-position open')]//li//span[text()='Property']")
	WebElement  addProperty;
		
	@FindBy(xpath="//span[text()='Add Legal Expenses']")
	List<WebElement> addLegalExpenses2;
	
	@FindBy(xpath="//a[@title='Save']//span[text()='Save']")
	WebElement  addlegalExpensesSaveBtn;
	
	@FindBy(xpath="//span[text()='Add Home Emergency']")
	List<WebElement> addHomeEmergency3;
	
	@FindBy(xpath="//a[@title='Save']//span[text()='Save']")
	List<WebElement>  addHomeEmergencySaveBtn1;
		
	@FindBy(xpath="//span[text()='Add Interested Parties']")
	WebElement  addInterestedParties24;
	
	@FindBy(xpath="//span[text()='Remove']")
	WebElement  removePolicyTreeRowOption;
	
	@FindBy(xpath="//span[text()='Property']//parent::a//parent::span//parent::td//following-sibling::td//div[contains(@class,'dropdown dropdown-position')]//ul//span[text()='Core Covers']")
	WebElement  addCoreCovers;
	
	@FindBy(xpath="//span[text()='Core Covers']//parent::span//parent::a//parent::span//parent::td//parent::tr")
	WebElement  RowCoreCover;
			
	@FindBy(xpath="//span[text()='Review Premium']")
	WebElement  lnkreviewPremium;
	
	@FindBy(xpath="//span[text()='Manage Billing']")
	WebElement  lnkManageBilling;
		
	@FindBy(xpath="//span[text()='Review Referrals']")
	WebElement  lnkReviewReferrals;
		
	public UIRef_PolicyTree(WebDriver driver, ExtentTest report) {
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
	
	public UIRef_PolicyHeader clickAddPolicyExtension(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, editIcon);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", editIcon);
			WaitUtils.waitForElement(driver, contextMenuAddPolicyExtension);
			contextMenuAddPolicyExtension.click();
			//WaitUtils.waitForSpinner(driver);
			Log.message("Clicked the Add Policy Extension on Policy Tree Page", driver, extentedReport);
			return new UIRef_PolicyHeader(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking the Add Policy Extension : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking the Add Policy Extension : " + e);
		}

	}
	public UIRef_PropertyDetails clickAddProperty(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, addPropertyInPolicyExtn);
			addPropertyInPolicyExtn.click();
			Log.message("Clicked the context menu options on policy extension row on policy tree page", driver, extentedReport,true);
			WaitUtils.waitForElement(driver, addProperty);
			addProperty.click();
			WaitUtils.waitForSpinner(driver);
			Log.pass("Clicked the Add Property Link on Policy Tree Page", driver, extentedReport,true);
			return new UIRef_PropertyDetails(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking the Add Property Link : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking the Add Property Link : " + e);
		}

	}
	public UIRef_PolicyCoreCovers clickAddCoreCovers(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, dropOptionsInPropertyRow);
			dropOptionsInPropertyRow.click();
			WaitUtils.waitForElement(driver, addCoreCovers);
			addCoreCovers.click();
			WaitUtils.uiRefwaitForSpinner(driver);
			Log.message("Clicked the Add Core Covers Link on Policy Tree Page", driver, extentedReport);			
			return new UIRef_PolicyCoreCovers(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking the Add Core Cover Link :" + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking the Add Core Cover Link : " + e);
		}
	}
	
	public void ValidateCoreCoverRow(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, RowCoreCover);
			Log.message("Text Value in Core Cover Row is : " + RowCoreCover.getText(), driver, extentedReport);
		}catch(Exception e){
			Log.fail("Error in Policy Tree while Validation of Core Cover Row : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error in Policy Tree while Validation of Core Cover Row" + e );			
		}
	}	
	public UIRef_PolicyReviewPremium ClickReviewPremium(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, lnkreviewPremium);
			lnkreviewPremium.click();
			Log.message("Clicked the Review Premium", driver, extentedReport);
			return new UIRef_PolicyReviewPremium(driver, extentedReport);
		}catch(Exception e){
			Log.fail("Error while Click of the Review Premium Link on Create Policy Page :" + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Click of the Review Premium Link on Create Policy Page : " + e );
		}
	}		
	public UIRef_PolicyManageBilling ClickManageBilling(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForelementToBeClickable(driver, lnkManageBilling, "Error while Click of the Manage Billing Link ");		
			JavascriptExecutor executor = (JavascriptExecutor) driver;
		    executor.executeScript("arguments[0].click();", lnkManageBilling);
			Log.message("Clicked the Manage Billing Link", driver, extentedReport);
			return new UIRef_PolicyManageBilling(driver, extentedReport);
		}catch(Exception e){
			Log.fail("Error while click of the manage billing link on create policy page :" + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Click of the Manage Billing Link on Create Policy Page" + e );		
		}
	}	
	public UIRef_PolicyReviewReferrals ClickReviewReferrals(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForelementToBeClickable(driver, lnkReviewReferrals, "Error while Click of the Review Referrals Link ");		
			JavascriptExecutor executor = (JavascriptExecutor) driver;
		    executor.executeScript("arguments[0].click();", lnkReviewReferrals);
			Log.message("Clicked the Review Referrals Link", driver, extentedReport);
			return new UIRef_PolicyReviewReferrals(driver, extentedReport);
		}catch(Exception e){
			Log.fail("Error while Click of the Review Referrals Link" + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Click of the Review Referrals Link" + e );
		}
	}
	
	
	public void clickAddLegalExpenses(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, addPropertyInPolicyExtn);
			addPropertyInPolicyExtn.click();
			WaitUtils.waitForElement(driver, addLegalExpenses2.get(1)); 
			addLegalExpenses2.get(1).click();
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked the Add Expenses Link on Policy Tree Page", driver, extentedReport);
			WaitUtils.waitForElement(driver, addlegalExpensesSaveBtn);
			addlegalExpensesSaveBtn.click();
			Log.message("Clicked the Save button of Legal Expenses Coverage", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while the Addition of Legal Coverage : " + e);
		}

	}
	
	public void clickAddHomeEmergency(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, dropOptionsInPropertyRow);
			dropOptionsInPropertyRow.click();
			WaitUtils.waitForElement(driver, addHomeEmergency3.get(2)); 
			addHomeEmergency3.get(2).click();
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked the Add Home Emergency on Policy Tree Page", driver, extentedReport);
			WaitUtils.waitForElement(driver, addHomeEmergencySaveBtn1.get(0));
			addHomeEmergencySaveBtn1.get(0).click();
			Log.message("Clicked the Save button of Home Emergency Coverage", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while the Addition of Home Emergecny Coverage : " + e);
		}

	}

}
