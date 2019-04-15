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
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;

public class UIRef_PolicyComplete extends LoadableComponent<UIRef_PolicyComplete>{
	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;

	@FindBy(xpath = "//custom-radio-button[@label='Accepted']//input[@type='radio'][@value='Accepted']")
	WebElement radioAccepted;
		
	@FindBy(xpath = "//custom-radio-button[@label='Quoted']//input[@type='radio'][@value='Quoted']")
	WebElement radioQuoted;
	
	@FindBy(xpath = "//custom-radio-button[@label='Lapsed']//input[@type='radio'][@value='Lapsed']")
	WebElement radioLapsed;
	                
	@FindBy(xpath = "//custom-radio-button[@label='Inquiry Registered']//input[@type='radio'][@value='Inquiry Registered']")
	WebElement radioIR;
	
	@FindBy(xpath = "//custom-radio-button[contains(@label,'accepted_label')]//input[@type='radio'][@value='Accepted']")
	WebElement CancelradioAccepted;
			
	@FindBy(xpath = "//a[@title='OK']//span[text()='OK']")
	WebElement oKbtnCompletePolicy;
				
	public UIRef_PolicyComplete(WebDriver driver, ExtentTest report) {
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
	
	public void ClickAccepted(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, radioAccepted);
			radioAccepted.click();
			Log.message("Checked the Accepted value on Policy Complete", driver, extentedReport);			
		}catch(Exception e){
			Log.fail("Error while click of policy accept option : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Policy Accept option" + e );
		}
	}
	
	public void ClickQuoted(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, radioQuoted);
			radioQuoted.click();
			Log.message("Checked the Quoted value on Policy Complete", driver, extentedReport);			
		}catch(Exception e){
			Log.fail("Error while click of policy quote option : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Policy Quote option" + e );
		}
	}
	
	public void ClickIR(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, radioIR);
			radioIR.click();
			Log.message("Checked the IR value on Policy Complete", driver, extentedReport);			
		}catch(Exception e){
			Log.fail("Error while click of policy IR option : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while selection of Policy IR option" + e );
		}
	}
	
	public void ClickCancelAccepted(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, CancelradioAccepted);
			String actual = CancelradioAccepted.getAttribute("Value");
			Log.assertThatExtentReport(actual.equalsIgnoreCase("Accepted"), "Accepted radio already selected", "Accepted radio not selected", driver, report);
		}catch(Exception e){
			Log.fail("Error while click of policy accept option while cancellation: " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Policy Accept option while Cancellation" + e );
		}
	}
	
	public UIRef_PolicyDocumentationDialog ClickOK(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, oKbtnCompletePolicy);
			oKbtnCompletePolicy.click();
			Log.message("Clicked the Ok Button on Policy Complete", driver, extentedReport);
			Log.pass("Clicked the Ok Button on Policy Complete", driver, extentedReport, true);
			return new UIRef_PolicyDocumentationDialog(driver, extentedReport);
		}catch(Exception e){
			Log.fail("Error while click of OK button on policy complete page : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Click of OK button on Policy Complete Page" + e );
		}
	}
	
}
