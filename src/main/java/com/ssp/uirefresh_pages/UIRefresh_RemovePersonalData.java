package com.ssp.uirefresh_pages;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.support.StopWatch;
import com.ssp.utils.WaitUtils;

public class UIRefresh_RemovePersonalData extends LoadableComponent<UIRefresh_RemovePersonalData> {
	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;

	@FindBy(css = "#ctcURN")
	WebElement contactURN;

	@FindBy(css = "#forename")
	WebElement contactForename;

	@FindBy(css = "#surname")
	WebElement contactSurname;

	@FindBy(css = "#postCode")
	WebElement contactPostcode;

	@FindBy(css = "#policyUrn")
	WebElement policyURN;

	@FindBy(css = "input[name='dateOfBirth']")
	WebElement dateofBirth;

	@FindBy(css = "a[title='Remove Contact from system']")
	WebElement removeContactData;

	@FindBy(css = "a[title='Complete']")
	WebElement complete;
	
	@FindBy(css = "a[title='Continue']")
	WebElement permanentRemoveData;
	
	@FindBy(css = "a[title='Do not Continue']")
	WebElement abortTransaction;
	
	@FindBy(css = "#message")
	WebElement message;

	public UIRefresh_RemovePersonalData(WebDriver driver, ExtentTest report) {
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
		// TODO Auto-generated method stub
		isPageLoaded = true;
		// driver.get(sspURL);
		WaitUtils.waitForPageLoad(driver);

	}

	public void verifyMandatorymessages (ExtentTest extentedReport, WebDriver driver, String errorMessage, boolean screenShot) throws Exception {
		try {
			int flag=0;
			List<WebElement> mandatoryMessage=driver.findElements(By.cssSelector("#message"));
			for (int i=0; i<mandatoryMessage.size();i++)
			{
				if (errorMessage.equalsIgnoreCase(mandatoryMessage.get(i).getText()))
				{
					flag=1;
					break;
				}
			}
			if (flag==1)
			{
				Log.pass("The error message "+ errorMessage + " exists on the screen" );
			}
			else
				Log.fail("The error message "+ errorMessage + " dosesn not exists on the screen" );
		} catch (Exception e) {
			throw new Exception("Error appeared while verifying error message : " + e);
		}
	}
		
	public void enterfirstName (ExtentTest extentedReport, WebDriver driver, String Forename) throws Exception
	{
		try
		{
			 WaitUtils.waitForSpinner(driver);
		      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
		          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
		          .withMessage("Unable to find First Name textfield"))
		              .until(ExpectedConditions.visibilityOf(contactForename));
		      contactForename.clear();
		      contactForename.sendKeys(Forename);
		      Log.message("First Name has been entered");
		}
		catch (Exception e) {
			throw new Exception("Error appeared while entering First Name: " + e);
		}	
	}
	
	public void enterlastName (ExtentTest extentedReport, WebDriver driver, String Forename) throws Exception
	{
		try
		{
			 WaitUtils.waitForSpinner(driver);
		      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
		          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
		          .withMessage("Unable to find Last Name textfield"))
		              .until(ExpectedConditions.visibilityOf(contactSurname));
		      contactSurname.clear();
		      contactSurname.sendKeys(Forename);      
		      Log.message("Last Name has been entered");
		}
		catch (Exception e) {
			throw new Exception("Error appeared while entering Last Name: " + e);
		}	
	}
	
	public void enterURN (ExtentTest extentedReport, WebDriver driver, String urn) throws Exception
	{
		try
		{
			 WaitUtils.waitForSpinner(driver);
		      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
		          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
		          .withMessage("Unable to find URN textfield"))
		              .until(ExpectedConditions.visibilityOf(contactURN));
		      contactURN.clear();
		      contactURN.sendKeys(urn);       
		      Log.message("URN has been entered");
		}
		catch (Exception e) {
			throw new Exception("Error appeared while entering URN: " + e);
		}	
	}
	public void enterpostalCode (HashMap<String, String> testdata,ExtentTest extentedReport, WebDriver driver) throws Exception
	{
		try
		{
			 WaitUtils.waitForSpinner(driver);
		      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
		          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
		          .withMessage("Unable to find Postal Code textfield"))
		              .until(ExpectedConditions.visibilityOf(contactPostcode));
		      contactPostcode.clear();
		      contactPostcode.sendKeys(testdata.get("Post Code"));
		      Log.message("Postal Code has been entered");
		}
		catch (Exception e) {
			throw new Exception("Error appeared while entering Postal code: " + e);
		}	
	}
	
	public void enterdateofBirth (HashMap<String, String> testdata,ExtentTest extentedReport, WebDriver driver) throws Exception
	{
		try
		{
			 WaitUtils.waitForSpinner(driver);
		      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
		          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
		          .withMessage("Unable to find Date of Birth textfield"))
		              .until(ExpectedConditions.visibilityOf(dateofBirth));
		      dateofBirth.clear();
		      dateofBirth.sendKeys(testdata.get("Date of Birth"));
		}
		catch (Exception e) {
			throw new Exception("Error appeared while entering Date of Birth: " + e);
		}	
	}
	
	public void verifyBlankFields (ExtentTest extentedReport, WebDriver driver) throws Exception
	{
		try
		{
			WaitUtils.waitForElement(driver, contactURN);
			System.out.println("URN: "+ contactURN.getText());
			System.out.println("FirstName: "+ contactForename.getText());
			System.out.println("Postal Code: "+ contactPostcode.getText());
			System.out.println("LastName: "+ contactSurname.getText());
			Log.assertThat(contactURN.getText().equalsIgnoreCase(""), "URN textfield is blank: PASS", "URN textfield is not blank: FAIL"); 
			Log.assertThat(contactForename.getText().equalsIgnoreCase(""), "First Name textfield is blank: PASS", "First Name textfield is not blank: FAIL"); 
			Log.assertThat(contactSurname.getText().equalsIgnoreCase(""), "Last Name textfield is blank: PASS", "Last Name textfield is not blank: FAIL"); 
			Log.assertThat(contactPostcode.getText().equalsIgnoreCase(""), "Postal Code textfield is blank: PASS", "Postal Code textfield is not blank: FAIL");       
		}
		catch (Exception e) {
			throw new Exception("Error appeared while verifying blank fields: " + e);
		}	
	}
	
	public void clickRemovecontact (ExtentTest extentedReport, WebDriver driver) throws Exception
	{
		try
		{
			WaitUtils.waitForSpinner(driver);
		      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
		          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
		          .withMessage("Unable to find Remove Contact button"))
		              .until(ExpectedConditions.elementToBeClickable(removeContactData));
		      JavascriptExecutor executor = (JavascriptExecutor) driver;
			  executor.executeScript("arguments[0].click();", removeContactData); 
			  WaitUtils.waitForSpinner(driver);
			  Log.message("Clicked on Remove Contact from system button ", driver, extentedReport);    
		}
		catch (Exception e) {
			throw new Exception("Error appeared while entering Date of Birth: " + e);
		}	
	}
	
	public void clickCompleteButton (ExtentTest extentedReport, WebDriver driver) throws Exception
	{
		try
		{
			WaitUtils.waitForSpinner(driver);
		      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
		          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
		          .withMessage("Unable to find Comple Button on Remove Personal Data screen"))
		              .until(ExpectedConditions.elementToBeClickable(complete));
		      JavascriptExecutor executor = (JavascriptExecutor) driver;
			  executor.executeScript("arguments[0].click();", complete); 
			  WaitUtils.waitForSpinner(driver);
			  Log.message("Clicked on complete button ", driver, extentedReport);    
		}
		catch (Exception e) {
			throw new Exception("Error appeared while entering Date of Birth: " + e);
		}	
	}

	public void permanentlyRemoveData (ExtentTest extentedReport, WebDriver driver) throws Exception
	{
		try
		{
			WaitUtils.waitForSpinner(driver);
		      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
		          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
		          .withMessage("Unable to find Permanent Remove button"))
		              .until(ExpectedConditions.elementToBeClickable(permanentRemoveData));
		      JavascriptExecutor executor = (JavascriptExecutor) driver;
			  executor.executeScript("arguments[0].click();", permanentRemoveData); 
			  WaitUtils.waitForSpinner(driver);
			  Log.message("Clicked on Permanently Remove button ", driver, extentedReport);    
		}
		catch (Exception e) {
			throw new Exception("Error appeared while clicking on Permanently Remove button: " + e);
		}	
	}
	
	public void doNotRemoveData (ExtentTest extentedReport, WebDriver driver) throws Exception
	{
		try
		{
			WaitUtils.waitForSpinner(driver);
		      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
		          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
		          .withMessage("Unable to find Abort Transaction button"))
		              .until(ExpectedConditions.elementToBeClickable(abortTransaction));
		      JavascriptExecutor executor = (JavascriptExecutor) driver;
			  executor.executeScript("arguments[0].click();", abortTransaction); 
			  Log.message("Clicked on Abort Transaction button ", driver, extentedReport);    
		}
		catch (Exception e) {
			throw new Exception("Error appeared while clicking on Abort transaction button: " + e);
		}	
	}
	
	public void doNotContinue (ExtentTest extentedReport, WebDriver driver) throws Exception
	{
		try
		{
			WaitUtils.waitForSpinner(driver);
		      (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
		          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
		          .withMessage("Unable to find Do not Continue button"))
		              .until(ExpectedConditions.elementToBeClickable(abortTransaction));
		      JavascriptExecutor executor = (JavascriptExecutor) driver;
			  executor.executeScript("arguments[0].click();", abortTransaction); 
			  WaitUtils.waitForSpinner(driver);
			  Log.message("Clicked on Do not Continue button ", driver, extentedReport);    
		}
		catch (Exception e) {
			throw new Exception("Error appeared while entering Date of Birth: " + e);
		}	
	}
	
	public String errorMessageVerification (HashMap<String, String> testdata,ExtentTest extentedReport, WebDriver driver, String policyID) throws Exception
	{
		try
		{
			String message= testdata.get("RemovalErrorMessage");
			message=message.replaceAll("[1]",policyID);
			System.out.println("The updated error message: "+message);
		    return message;
		}
		catch (Exception e) {
			throw new Exception("Error appeared while verifying error message " + e);
		}	
	}

}
