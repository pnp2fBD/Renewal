package com.ssp.uirefresh_pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;

import com.ssp.utils.WaitUtils;

/**
 * @author Sunil Juneja
 * @Date - 12-Oct-2018
 */

public class UIRef_PolicyBuisnessDetails extends LoadableComponent<UIRef_PolicyBuisnessDetails>{
	
	private WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;
	private static String policyURNNo=null;
	
	@FindBy(xpath="//div[@class='input-groups policy_insurer policy_insurer_woscrl']/select")
	WebElement txtinsured;
	
	@FindBy(xpath="//div[@class='input-groups policy_insurer_woscrl']/select")
	WebElement  txtPolicyHolder;
		
	@FindBy(xpath="//div[@class='input-groups policy_insurer_woscrl']//a[@title='Search']/i")
	WebElement  PolicyHolderSearchIcon;
	
	@FindBy(xpath="//input[@id='_jtPolHldr']")
	WebElement  txtJointPolicyholder;
	
	@FindBy(xpath="//custom-input-box[@name='int']/input[@id='int']")
	WebElement  txtIntermediary;
	
	@FindBy(xpath="//select[@name='agreement']")
	WebElement  agencyAgreement;
		
	@FindBy(xpath="//custom-drop-down[@name='agreement']/span/span[1]/span/span[2]")
	WebElement  agencyAgreementArrow;
		
	@FindBy(css="#split-main-container > div:nth-child(4) > div > div > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > form > div:nth-child(1) > div > div:nth-child(1) > div.col-md-5.zero-space.pad_10_sm > div.col-md-12.gutter-top-10 > div > custom-input-box > input")
	WebElement  IntermediaryNameLbl;
	
	@FindBy(xpath="//custom-drop-down[@name='agreement']//span[@class='select2-selection select2-selection--single']")
	WebElement  agencyAgreementLbl;
	
	@FindBy(xpath="//div[@class='input-icon datepicker_position calendertable']/input[@name='inceptionDate']")
	WebElement  inceptionDate;
	
	@FindBy(xpath="//custom-drop-down[@name='product'][@key='_prdTrNm']/select[@name='product']")
	WebElement  product;
	
	@FindBy(xpath="//custom-drop-down[@class='ng-pristine ng-untouched ng-valid ng-isolate-scope'][@key='_schTrNm']/select")
	WebElement  scheme;
	
	@FindBy(xpath="//a[@title='OK'][@class='btn btn-primary f-right gutter-left-10']/span")
	WebElement  okBtn;
	
	@FindBy(css="#split-main-container > div.widgets-breadcrumb.ng-scope > ul > li:nth-child(4) > a > span.ng-binding.ng-scope")
	WebElement  policyURN;
	
	@FindBy(xpath = "//span[text()='Review Referrals']")
	WebElement lnkReviewReferrals;

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		if (!isPageLoaded) {
			Assert.fail();
		}
		if (isPageLoaded && !driver.getTitle().contains("BusinessDetails")) {
			Log.fail("UI Refresh BusinessDetails Page did not open up. Site might be down.", driver, extentedReport);
		}
		uielement = new ElementLayer(driver);
	}

	@Override
	protected void isLoaded() throws Error {
		isPageLoaded = true;
		// driver.get(sspSelfServiceURL);
		WaitUtils.waitForPageLoad(driver);
	}

	//Constructor to initialize the page objects
	public UIRef_PolicyBuisnessDetails(WebDriver driver, ExtentTest extentedReport) {	
		this.driver = driver;
		this.extentedReport = extentedReport;
		PageFactory.initElements(driver, this);
	}
	
	public void enterIntermediary(String intermediaryID, WebDriver driver, ExtentTest extentedReport) throws Exception{
		try {
			WaitUtils.waitForElement(driver,txtIntermediary);
			txtIntermediary.clear();
			txtIntermediary.sendKeys(intermediaryID);
			txtIntermediary.sendKeys(Keys.TAB);
			Log.message("Entered the IntermediaryID : " + intermediaryID, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the intermediary ID on Buisness Details Page  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while entering intermediaryID : " + e);
		}
	}
	
	public void enterAgencyAgreement(String agencyAgreementValue,WebDriver driver, ExtentTest extentedReport) throws Exception{
		try {
			WaitUtils.waitForElement(driver,agencyAgreementArrow);
			agencyAgreementArrow.click();
			Thread.sleep(5000);
			//driver.findElement(By.xpath("//custom-drop-down[@name='agreement']/span/span[1]/span/span[2]")).click();
			WaitUtils.waitForElement(driver,agencyAgreement);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", agencyAgreement);
			(new WebDriverWait(driver, 180).pollingEvery(3000, TimeUnit.MILLISECONDS) 
		              .ignoring(NoSuchElementException.class, StaleElementReferenceException.class) 
		              .withMessage("Unable to find agencyAgreement")) 
		              .until(ExpectedConditions.presenceOfNestedElementLocatedBy(agencyAgreement,By.tagName("option")));
		
			Select select = new Select(agencyAgreement);
			//Log.message("Agency Agreement options value " + select.getFirstSelectedOption(), driver, extentedReport, true);
			select.selectByIndex(1);
			select.selectByValue(agencyAgreementValue);
			Log.message("Entered the Agency Agreement is : " + agencyAgreement.getAttribute("value"), driver, extentedReport ,true);
			} catch (Exception e) {
			Log.fail("Error while entering the agency agreement on Buisness Details Page  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while entering the agencyAgreement " + e);
		}
	}
	
	public void enterInceptionDate(WebDriver driver, ExtentTest extentedReport) throws Exception{
		try {
			WaitUtils.waitForElement(driver, inceptionDate); 
			String strDate = GenericUtils.setDate("current", inceptionDate, 0, 0);
			inceptionDate.click();
			inceptionDate.sendKeys(Keys.TAB);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			Log.message("Entered Inception Date is : " + strDate, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the inception date on Buisness Details Page  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while entering the Inception date : " + e);
		}
	}
	
	public void enterProduct(String productvalue,WebDriver driver, ExtentTest extentedReport) throws Exception{
		try {
			WaitUtils.waitForElement(driver, product); 
			Select select = new Select(product);
			Log.message("Select Values : " + select.getOptions());
			// WebDriverWait wait = new WebDriverWait(driver, 40);
			 //wait.until(ExpectedConditions.textToBePresentInElementValue(product, "Household Modular"));
			 //wait.until(ExpectedConditions.elementToBeSelected(product));
			 WaitUtils.waitForelementToBeClickable(driver, product, "Product Dropdown");
			select.selectByValue(productvalue);
			Log.message("Entered the Product is : " + productvalue, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the product on Buisness Details Page  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while entering the Product : " + e);
		}
	}
	
	public void enterSchme(String schmevalue,WebDriver driver, ExtentTest extentedReport) throws Exception{
		try {
			WaitUtils.waitForElement(driver, scheme); 
			Select select = new Select(scheme);
			 WaitUtils.waitForelementToBeClickable(driver, scheme, "Product Dropdown");
			select.selectByValue(schmevalue);
			Log.message("Entered the product scheme : " + schmevalue, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the schme on Buisness Details Page  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while entering the Scheme : " + e);
		}
	}
	
	public UIRef_PolicyTree clickOKbuisnessdetails(WebDriver driver, ExtentTest extentedReport) throws Exception{
		try {
			
			WaitUtils.waitForElement(driver, okBtn); 
			okBtn.click();
			Log.pass("Clicked the OK button on Buisness details : ", driver, extentedReport,true);
			return new UIRef_PolicyTree(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clik OK on Buisness Details Page  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while click OK on Buisness Details Page: " + e);
		}
	}
	
	
	/*public void waitforOptionInSelect(WebElement element, String attribute){
	 WebDriverWait wait = new WebDriverWait(driver, 40);
	 	boolean statusOfElementToBeReturned;	 
	    try {
	      WebElement waitElement =
	          (WebElement) wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
	        		  //visibilityOfAllElements(elements));
	      if (waitElement.isDisplayed() && waitElement.isEnabled()) {
	        statusOfElementToBeReturned = true;
	        Log.event("List Element is displayed:: " + elements.toString());
	      }
	    } catch (Exception ex) {
	      statusOfElementToBeReturned = false;
	      Log.event("Unable to find list element after " + StopWatch.elapsedTime(startTime)
	          + " sec ==> " + elements.toString());
	    }*/
	
	public String getPolicyURN(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			(new WebDriverWait(driver, 500).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.withMessage("Timed out to find 'Role' dropdown"))
							.until(ExpectedConditions.elementToBeClickable(txtIntermediary));
			policyURNNo = policyURN.getText();
			policyURNNo = policyURNNo.replaceAll("[()]","");
			Log.pass("Policy URN Num is: " + policyURNNo, driver, extentedReport,true);

		} catch (Exception e) {
			Log.fail("Error while getting the policy URN num  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error getting the Policy URN No : " + e);
		}
		return policyURNNo;

	}
	
	public UIRef_ContactSearchDialog ClickpolicyHolderSearchIcon (WebDriver driver, ExtentTest extentedReport) throws Exception{
		try {
			
			WaitUtils.waitForElement(driver, PolicyHolderSearchIcon); 
			PolicyHolderSearchIcon.click();
			Log.pass("Clicked the PolicyHolderSearchIcon on Buisness details : ", driver, extentedReport,true);
			return new UIRef_ContactSearchDialog(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the policy header search icon on buisness details page  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while entering the PolicyHolderSearchIcon on Buisness Details Page: " + e);
		}
	}
	
	public void ValidateJointPolicyHolder (String jointcontactNameVal, WebDriver driver, ExtentTest extentedReport) throws Exception{
		try {
			WaitUtils.waitForElement(driver, txtJointPolicyholder);
			String str = txtJointPolicyholder.getAttribute("value");
			boolean match = false;
			ArrayList aList= new ArrayList(Arrays.asList(str.split(",")));
			for(int i=0;i<aList.size();i++)
			{
				if (jointcontactNameVal.contains(aList.get(i).toString())) {
					match = true;
					Log.message("Joint Policyholder added is : " + aList.get(i).toString(), driver, extentedReport,true);
				}
			}			
			Log.pass("Joint Policyholder on the Policy are : " + txtJointPolicyholder.getAttribute("value"), driver, extentedReport,true);
		} catch (Exception e) {
			Log.fail("Error while validating the JointpolicyHolder on Buisness Details Page  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while validating the JointpolicyHolder on Buisness Details Page: " + e);
		}
	}
	
	public UIRef_PolicyReviewReferrals NavigateToReviewReferral(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, lnkReviewReferrals,
					"Error while Click of the Review Referrals Link ");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", lnkReviewReferrals);
			Log.message("Clicked the Review Referrals Link", driver, extentedReport);
			return new UIRef_PolicyReviewReferrals(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while click of the review referrals link  " + e.getMessage(), driver, extentedReport, true);
			throw new Exception("Error while Click of the Review Referrals Link" + e);
		}
	}
	
}
