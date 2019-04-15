package com.ssp.uirefresh_pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;

public class UIRef_PolicyHeader extends LoadableComponent<UIRef_PolicyHeader> {

	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;

	@FindBy(xpath = "//select[@name='coverType']")
	WebElement coverType;	
	
	@FindBy(xpath = "//select[@name='schemeVariant']")
	WebElement schemeVariant;
	
	@FindBy(xpath = "//select[@name='buildingsContinuousInsurance']")
	WebElement buildingsContinuousInsurance;

	@FindBy(xpath = "//select[@name='contentsContinuousInsurance']")
	WebElement contentsContinuousInsurance;

	@FindBy(xpath = "//div[@id='split-container']//a[@title='Save']//span[text()='Save']")
	List<WebElement> okBtnPolicyHeader;
	
	@FindBy(xpath = "//a[text()='Claims prior to inception']")
	WebElement claimsPriorToInception;
		

	//a[@title='Add']//span[text()='Add']
	
	//input[@id="amount"]
	
	//select[@name='statusOfClaim']
	
	//select[@name='atCurrentAddress']
	
	//select[@name='coverSection']
	
	//select[@name='typeOfClaim']
	
	//input[@name='dateClaimMade']
	
	//a[@title='Add'][@class='btn btn-primary f-left']//span[text()='Add']
	
	//a[@title='Continue']//span[text()='Continue']
	
	
	//table[@id="DataTables_Table_8"]/tbody/tr[1]
	
	//Save
//	#split-main-container > div:nth-child(4) > div > div > div > div > div.tab-section.dynamicPage.ng-scope.no-class.ng-valid-maxlength.ng-valid-date.ng-valid-date-disabled.ng-dirty.ng-valid.ng-valid-required > div > div > div.tab-pane.ng-scope.active > div > div.row.ng-scope > div > a:nth-child(1)
	
	
	
	
	public UIRef_PolicyHeader(WebDriver driver, ExtentTest report) {
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
	
	public void enterCoverType(String coverTypeVal,WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, coverType);
			Select select = new Select(coverType);
//			WebDriverWait wait = new WebDriverWait(driver, 40);
			WaitUtils.waitForelementToBeClickable(driver, coverType, "coverType Dropdown");
			select.selectByValue(coverTypeVal);
			Log.message("Entered the Product is : " + coverTypeVal, driver, extentedReport);			
		}catch(Exception e){
			Log.fail("Error while entering the cover type on policy header : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the coverType : " + e);
		}
	}
	
	public void enterschemeVariant(String schemeVariantVal,WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForelementToBeClickable(driver, schemeVariant, "Unable to Scheme Variant Dropdown");
			Select select = new Select(schemeVariant);
			//select.selectByValue(schemeVariantVal);
			List<WebElement> dd = select.getOptions();
			boolean exists = false;
		    // Loop through all existing values
		    for (int j = 0; j < dd.size(); j++) {
		        System.out.println(dd.get(j).getText());
		        if(dd.get(j).getText().equalsIgnoreCase(schemeVariantVal)){
		        	select.selectByValue(schemeVariantVal);
		        	exists = true;
		        	Log.message("Selected schemeVariant is : " + schemeVariantVal, driver, extentedReport);
		        }		        
		    }
		    if (!exists){
		    	select.selectByIndex(2);
		    	Log.message("Select schemeVariant is done through index : " + select.getFirstSelectedOption(), driver, extentedReport);
		    }		  
		}catch(Exception e){
			Log.fail("Error while selecting the scheme variant on policy header : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while selecting the schemeVariant : " + e);
		}
	}
	
	public void enterBuildingsInsurance(String buildingsContinuousInsuranceVal,WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, buildingsContinuousInsurance);
			Select select = new Select(buildingsContinuousInsurance);
			//WebDriverWait wait = new WebDriverWait(driver, 40);
			WaitUtils.waitForelementToBeClickable(driver, buildingsContinuousInsurance, "buildingsContinuousInsurance Dropdown");
			select.selectByValue(buildingsContinuousInsuranceVal);
			Log.message("Entered buildingsContinuousInsuranceVal is : " + buildingsContinuousInsuranceVal, driver, extentedReport);			
		}catch(Exception e){
			Log.fail("Error while entering the buildingsContinuousInsurance : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the buildingsContinuousInsurance : " + e);
		}
	}
	
	public void enterContentsInsurance(String contentsContinuousInsuranceVal,WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, contentsContinuousInsurance);
			Select select = new Select(contentsContinuousInsurance);
			//WebDriverWait wait = new WebDriverWait(driver, 40);
			WaitUtils.waitForelementToBeClickable(driver, contentsContinuousInsurance, "contentsContinuousInsurance Dropdown");
			select.selectByValue(contentsContinuousInsuranceVal);
			Log.message("Entered contentsContinuousInsuranceVal is : " + contentsContinuousInsuranceVal, driver, extentedReport);			
		}catch(Exception e){
			Log.fail("Error while entering the contentsContinuousInsurance : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the contentsContinuousInsurance : " + e);
		}
	}
	
	public void ClickBtnPolicyHeader(WebDriver driver, ExtentTest report) throws Exception{
			try{
				WaitUtils.waitForElement(driver, okBtnPolicyHeader.get(0));
				okBtnPolicyHeader.get(0).click();
				Log.pass("Clicked the Save button on on Policy Header ", driver, extentedReport,true);
			}catch(Exception e){
				Log.fail("Error while clicking the save button on policy header : " + e.getMessage(), driver, extentedReport,true);
				throw new Exception("Error while Clicking the Save button on Policy Header");
			}
	}
	
	public UIRef_policyClaimsPriorInception ClickLnkClaimsPriorToInception(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, claimsPriorToInception);
			claimsPriorToInception.click();
			Log.pass("Clicked the Link claimsPriorToInception on Core Covers ", driver, extentedReport,true);
			return new UIRef_policyClaimsPriorInception(driver, extentedReport);
		}catch(Exception e){
			Log.fail("Error while clicking the Link Claims Prior To Inception on Policy Header : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Clicking the Link Claims Prior To Inception on Policy Header");
		}
}
}
