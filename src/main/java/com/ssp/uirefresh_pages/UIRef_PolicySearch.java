package com.ssp.uirefresh_pages;

import org.openqa.selenium.By;

/**
 * @author Sunil Juneja
 * @Date - 11-Oct-2018
 */


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

public class UIRef_PolicySearch extends LoadableComponent<UIRef_PolicySearch> {
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	public String policyUrn;

	//button[@title='Create']
	
	@FindBy(css = "button[title*='Create']")
	WebElement createbtn;
		
	@FindBy(xpath = "//a/span[text()='Create Personal Lines Policy']")
	WebElement createPersonalLinePolicylnk;
	
	@FindBy(css = "#policyUrn")
	WebElement policyNum;
		
	@FindBy(css = "#contacts")
	WebElement contactSearch;
	
	@FindBy(xpath = "//a[@title='Search']//parent::a//preceding-sibling::custom-input-box/input")
	WebElement contactNameLabel;
		
	@FindBy(xpath = "//a[@title='Search']/i")
	WebElement contactSearchIcon;
	
	@FindBy(xpath = "//input[@name='name']")
	WebElement modalName;
	
	@FindBy(xpath = "//div[@class='modal-content']//a[@title='Search']//span[text()='Search']")
	WebElement modalSearchBtn;
		
	//@FindBy(xpath = "//div[@id='example_wrapper']//tbody/tr[1]/td[5]")
	
	@FindBy(xpath = "//tr[1]//a[@title='Retrieve']")
	WebElement modalRetreiveRow1;
	
	@FindBy(xpath = "//div[@class='modal-content']")
	WebElement contactModalWin;
	
	//div[@class='table-content']/table[@role='grid']/tbody/tr/td
	//css - div.table-content > table > tbody:nth-child(2) > tr
	
	/*@FindBy(css = "#table[role='grid'] > tbody:nth-child(2) > tr")
	WebElement tableRow1;
	
	@FindBy(css = "#table[role='grid'] > tbody:nth-child(2) > tr > td > div  > a[data-toggle]")
	WebElement tableRow1Dropdown;
	
	@FindBy(css = "#table[role='grid'] > tbody:nth-child(2) > tr > td > a[title='Show Policy History']")
	WebElement tableRow1History;*/
	
	@FindBy(xpath= "//div[@class='table-content']/table[@role='grid']/tbody/tr/td[contains(@title,'Effective')]/span")
	WebElement tableRow1PolicyPosition;
	
	@FindBy(xpath = "//div[@class='table-content']/table[@role='grid']/tbody/tr/td[6]/span")
	WebElement tableRow1PolicyStatus;
		
	@FindBy(xpath = "//div[@class='table-content']/table[@role='grid']/tbody/tr[1]/td[10]/div/a")
	WebElement tableRow1DropDownOption;
			
	@FindBy(xpath = "//div[@class='table-content']/table[@role='grid']/tbody/tr[1]/td[9]/a")
	WebElement editPolicyLink;
	
	@FindBy(xpath = "//span[text()='Endorse']")
	WebElement endorseLnk;	
	
	@FindBy(xpath = "//span[text()='Adjust Billing']")
	WebElement adjustBillingLnk;
	
	@FindBy(xpath = "//span[text()='Lapse']")
	WebElement lapseLink;
	
	@FindBy(xpath = "//span[text()='Short-term Adjustment']")
	WebElement shortTermAdjustmentLnk;
	
	@FindBy(xpath = "//span[text()='Invite Renewal']")
	WebElement inviteRenewalLnk;
	
	@FindBy(xpath = "//span[text()='Change Intermediary']")
	WebElement changeIntermediaryLnk;
	
	@FindBy(xpath = "//span[text()='Cancel']")
	WebElement cancelLnk;
	
	@FindBy(xpath = "//span[text()='Cancel(Cooling Off)']")
	WebElement cancelCoolingOffLnk;
	
	@FindBy(xpath = "//span[text()='Reinstate']")
	WebElement reinstateLnk;
	
	@FindBy(xpath = "//span[text()='Reverse Transaction']")
	WebElement reverseTransactionLnk;
			
	@FindBy(xpath = "//a[@title='Search']//span[text()='Search']")
	WebElement searchBtn;
	
	@FindBy(css = "a[title='Complete']")
	WebElement btnPolSearchComplete;
		
	public UIRef_PolicySearch(WebDriver driver, ExtentTest extentedReport) {
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
	
	public void enterContactURN(String contactURN,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, contactSearch);
			contactSearch.clear();
			contactSearch.sendKeys(contactURN);
			contactSearch.sendKeys(Keys.TAB);
			Log.message("Entered the contactURN : " + contactURN, driver, extentedReport);
			WaitUtils.uiRefwaitForSpinner(driver);
		}catch(Exception e){
			Log.fail("Error in entering the contact URN : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entring contactURN : " + e);
		}
	}
	
	public void verifyContactName(String nameValue,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, contactNameLabel);
			String[] str = nameValue.trim().split(" ");
			String strRev = "";
			for(String s: str){
				strRev = s + " " + strRev;
			}
			System.out.println(" Reverse contact name is :" + strRev);
			System.out.println("Actual contact Name is " + contactNameLabel.getAttribute("value"));
			
			if(contactNameLabel.getAttribute("value").equalsIgnoreCase(strRev)){
			Log.message("Contact Name verified : " + contactNameLabel.getAttribute("Value"), driver, extentedReport);
			}
			//else 
			//Log.message("Contact Name actual value " + contactNameLabel.getAttribute("Value") + "is not verified with the value : " + nameValue + " Mr John", driver, extentedReport);
			//Thread.sleep(5000, 0);
			//driver.findElement(By.cssSelector("#split-main-container > div:nth-child(4) > div > div > form > div > div > div:nth-child(2) > div:nth-child(3) > div > custom-drop-down > select")).sendKeys("Policy");
		}catch(Exception e){
			Log.fail("Error in verification of Contact Name : " + e,driver,extentedReport,true);
		}
	}
	
	public void enterpolicyNum(String policyNumber,WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.waitForElement(driver, policyNum);
			policyNum.clear();
			policyNum.sendKeys(policyNumber);
			Thread.sleep(2000);
			policyNum.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			Log.message("Entered the policy Number : " + policyNumber, driver, extentedReport);	
		}catch(Exception e){
			Log.fail("Error in entering the policy num : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entring the policyNum : " + e);
		}
	}
	
	public UIRef_PolicyBuisnessDetails clickcreatePolicyLnk(WebDriver driver, ExtentTest report) throws Exception {
		try{
			WaitUtils.uiRefwaitForSpinner(driver);
			WaitUtils.waitForElement(driver, createbtn);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", createbtn);
			//createbtn.click();
			WaitUtils.waitForElement(driver, createPersonalLinePolicylnk);
			Log.message("Clicked the Create Button", extentedReport);
			createPersonalLinePolicylnk.click();
			Log.message("Clicked the Create Personal Line Policy link", extentedReport);			
			return new UIRef_PolicyBuisnessDetails(driver, extentedReport);			
		}catch(Exception e){
			Log.fail("Error in clicking the create personal line policy link : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error in clicking the Create Personal Line Policy link : " + e);
		}
	}
	
	public void clickBtnSearch(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, searchBtn);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", searchBtn);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked Search button on Policy Search ", driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error in clicking policy search button : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking Policy Search button : " + e);
		}		
	}		
	//btnPolSearchComplete
	public void clickBtnPolSearchComplete(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnPolSearchComplete);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", btnPolSearchComplete);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked complete button on policy search ", driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking complete button on policy search : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while clicking complete button on policy search : " + e);
		}		
	}	
	public String getpolicyNum(WebDriver driver, ExtentTest report) throws Exception {
		try{
			String policyURNVal;
			WaitUtils.waitForElement(driver, policyNum);
			policyURNVal = policyNum.getAttribute("value");
			Log.pass("policy Number is : " + policyURNVal, driver, extentedReport,true);	
			return policyURNVal;
		}catch(Exception e){
			Log.fail("Error while getting the policy urn on policy search : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while getting the policyURN on Policy Search Page : " + e);
		}		
	}	
	public void ValidateRow1data(WebDriver driver, ExtentTest report) throws Exception {
		try{
			String policyURNVal;
			WaitUtils.waitForElement(driver, tableRow1PolicyStatus);
			policyURNVal = getpolicyNum(driver,extentedReport);
			if(tableRow1PolicyStatus.getText().contains("Accepted")){
				Log.pass("policy is Accepted with Policy URN Num is : " + policyURNVal, driver, extentedReport,true);
			}
			if(tableRow1PolicyPosition.getText().contains("NB")){
				Log.pass("Policy is in state of : " + tableRow1PolicyPosition.getText(), driver, extentedReport,true);
			}
			Log.message("Policy Search Row 1 is validated" , driver, extentedReport);		
		}catch(Exception e){
			Log.fail("Error while getting the row1 data on policy search : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Fetching the Row1 Data on policy search page : " + e);
		}
	}
	
	
	public void ValidatePolicyPosition(String tableRow1PolicyPositionVal, WebDriver driver, ExtentTest report) throws Exception {
		try{
			String policyURNVal;
			WaitUtils.waitForElement(driver, tableRow1PolicyPosition);
			String actual = tableRow1PolicyPosition.getText();
			policyURNVal = getpolicyNum(driver,extentedReport);			
			Log.assertThatExtentReport(actual.equalsIgnoreCase(tableRow1PolicyPositionVal), 
					"Policy Position Validated as : " + actual + " with Policy URN " + policyURNVal, 
					"Policy Position is still same as : " + actual + " of Policy URN " + policyURNVal, driver, extentedReport);
					
		}catch(Exception e){
			throw new Exception("Error while Fetching the Row1 Data on Policy Search Page : " + e);
		}
	}	
	public void clickcontactSearchIcon(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, contactSearchIcon);
			contactSearchIcon.click();
			Log.pass("Clicked the Search Contact link on Policy Search", driver,extentedReport, true);
			
		} catch (Exception e) {
			throw new Exception("Error while clicking of Search Contact link on Policy Search " + e);
		}		
	}	
	public void enterContactName(String contactNameVal, ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, modalName);
			modalName.sendKeys(contactNameVal);
			Log.pass("Entered the Contact Name on Contact Search Dialog", driver,extentedReport, true);		
		} catch (Exception e) {
			throw new Exception("Error while entering the Contact Name on Contact Search Dialog" + e);
		}		
	}	
	public void clickModalSearchbtn(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, modalSearchBtn);
			modalSearchBtn.click();
			Log.pass("Clicked the modalSearchBtn on Contact Search Dialog", driver,extentedReport, true);
			
		} catch (Exception e) {
			throw new Exception("Error while clicking the modalSearchBtn on Contact Search Dialog" + e);
		}		
	}
	
	public void clickmodalRetreiveRow1(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, modalRetreiveRow1);
			modalRetreiveRow1.click();
			Log.pass("Clicked the modalRetreiveRow1 on Contact Search Dialog", driver,extentedReport, true);
			WaitUtils.waitUntilElementDisappear(driver, contactModalWin);
		} catch (Exception e) {
			throw new Exception("Error while clicking the modalRetreiveRow1 on Contact Search Dialog" + e);
		}		
	}
	
	public void clicktableRow1DropDownOption(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while clicking the tableRow1 DropDownOption on Policy Search Screen : " + e);
		}
		
	}
	
	public UIRef_PolicyEndorseDialog clickEndorse(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
			WaitUtils.waitForElement(driver, endorseLnk);
			endorseLnk.click();
			Log.pass("Clicked the Endorse Dialog on Selected Policy", driver,extentedReport, true);
			return new UIRef_PolicyEndorseDialog(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the Endorse on Policy Search Screen : " + e);
		}
		
	}
	
	public UIRef_PolicyAdjustBilling clickAdjustBilling(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
			WaitUtils.waitForElement(driver, adjustBillingLnk);
			adjustBillingLnk.click();
			Log.pass("Clicked the adjustBillingLnk on Policy Search", driver,extentedReport, true);
			WaitUtils.uiRefwaitForSpinner(driver);
			return new UIRef_PolicyAdjustBilling(driver, extentedReport);
			
		} catch (Exception e) {
			throw new Exception("Error while clicking the adjustBillingLnk on Policy Search Screen : " + e);
		}
		
	}
	
	public UIRef_PolicyLapseDialog clickLapse(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
			
			WaitUtils.waitForElement(driver, lapseLink);
			lapseLink.click();
			Log.pass("Clicked the lapseLink on Policy Search", driver,extentedReport, true);
			return new UIRef_PolicyLapseDialog(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the lapseLink on Policy Search Screen : " + e);
		}		
	}
	
	public UIRef_PolicySTAdialog clickShortTermAdjustment(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
			
			WaitUtils.waitForElement(driver, shortTermAdjustmentLnk);
			shortTermAdjustmentLnk.click();
			Log.pass("Clicked the shortTermAdjustmentLnk on Policy Search", driver,extentedReport, true);
			return new UIRef_PolicySTAdialog(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the shortTermAdjustmentLnk on Policy Search Screen : " + e);
		}		
	}
	
	public void clickInviteRenewalLnk(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
			
			WaitUtils.waitForElement(driver, inviteRenewalLnk);
			inviteRenewalLnk.click();
			Log.pass("Clicked the inviteRenewalLnk on Policy Search", driver,extentedReport, true);
			//return new UIRef_PolicyReviewPremium(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the inviteRenewalLnk on Policy Search Screen : " + e);
		}		
	}
	
	public void clickChangeIntermediary(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
			
			WaitUtils.waitForElement(driver, changeIntermediaryLnk);
			changeIntermediaryLnk.click();
			Log.pass("Clicked the changeIntermediaryLnk on Policy Search", driver,extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while clicking the changeIntermediaryLnk on Policy Search Screen : " + e);
		}		
	}
	
	public UIRef_PolicyCancelDialog clickCancel(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
			
			WaitUtils.waitForElement(driver, cancelLnk);
			cancelLnk.click();
			Log.pass("Clicked the cancelLnk on Policy Search", driver,extentedReport, true);
			return new UIRef_PolicyCancelDialog( driver,extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the cancelLnk on Policy Search Screen : " + e);
		}		
	}
	 
	public UIRef_PolicyCancelDialog clickCancelCoolingOff(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
			WaitUtils.waitForElement(driver, cancelCoolingOffLnk);
			cancelCoolingOffLnk.click();
			Log.pass("Clicked the cancelCoolingOffLnk on Policy Search", driver,extentedReport, true);
			return new UIRef_PolicyCancelDialog( driver,extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the cancelCoolingOffLnk on Policy Search Screen : " + e);
		}		
	}
	
	public UIRef_PolicyReinstate clickReinstate(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
			WaitUtils.waitForElement(driver, reinstateLnk);
			reinstateLnk.click();
			Log.pass("Clicked the reinstateLnk on Policy Search", driver,extentedReport, true);
			return new UIRef_PolicyReinstate(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the reinstateLnk on Policy Search Screen : " + e);
		}		
	}
	
	public UIRef_PolicyReverseTransaction clickReverseTransaction(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, tableRow1DropDownOption);
			tableRow1DropDownOption.click();
			Log.pass("Clicked the tableRow1DropDownOption on Policy Search", driver,extentedReport, true);
			
			WaitUtils.waitForElement(driver, reverseTransactionLnk);
			reverseTransactionLnk.click();
			Log.pass("Clicked the reverseTransactionLnk on Policy Search", driver,extentedReport, true);
			return new UIRef_PolicyReverseTransaction(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the reverseTransactionLnk on Policy Search Screen : " + e);
		}		
	}
	
	
	public UIRef_PolicyBuisnessDetails clickEditPolicyLnk(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, editPolicyLink);
			editPolicyLink.click();
			Log.message("Clicked the edit Policy Link", extentedReport);
			return new UIRef_PolicyBuisnessDetails(driver, extentedReport);
		} catch (Exception e) {
			//Log.fail("");
			//Reporter
			throw new Exception("Error in clicking the edit Policy Link on Policy Search: " + e);
		}
	}
}
