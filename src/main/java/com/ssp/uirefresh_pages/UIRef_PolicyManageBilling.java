package com.ssp.uirefresh_pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;

public class UIRef_PolicyManageBilling extends LoadableComponent<UIRef_PolicyManageBilling>{
	
	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;
	
	@FindBy(xpath = "//select[@name='paymentPlan']")
	WebElement paymentPlan;
	
	//custom-drop-down[@name='paymentPlan']//span[@role='presentation']
		
	@FindBy(xpath = "//select[@name='paymentMethod']")
	List <WebElement> paymentMethod;
	
	@FindBy(xpath = "//a//span[text()='OK']")
	WebElement okBtnManageBilling;
	

	public UIRef_PolicyManageBilling(WebDriver driver, ExtentTest report) {
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
	
	public void paymentPlan(String paymentPlanVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, paymentPlan, "Error while entering the paymentPlan");			
			paymentPlan.sendKeys(paymentPlanVal);			
			/*Select select = new Select(paymentPlan);
			select.selectByValue(paymentPlanVal);*/			
			Log.message("Entered paymentPlan Value is : " + paymentPlanVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the payment plan : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the paymentPlan " + e);
		}
	}
	
	public void paymentMethod(String paymentMethodVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForListElement(driver, paymentMethod,180);
			paymentMethod.get(0).click();	
			paymentMethod.get(0).sendKeys(paymentMethodVal);
//			Select select = new Select(paymentMethod.get(0));
//			select.selectByValue(paymentMethodVal);
			Log.message("Entered paymentMethod Value is : " + paymentMethodVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the payment method : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the payment Method " + e);
		}
	}
	
	public void SelectAnnualCard(String paymentPlanVal, String paymentMethodVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			//Payment Plan
			WaitUtils.waitForelementToBeClickable(driver, paymentPlan, "Error while entering the paymentPlan");
			paymentPlan.sendKeys(paymentPlanVal);
			Log.message("Selected the paymentPlan value : " + paymentPlanVal, driver, extentedReport);
			//Payment Method
			WaitUtils.waitForElement(driver, paymentMethod.get(0),180);
			paymentMethod.get(0).sendKeys(paymentMethodVal);
			Log.message("Selected the  paymentMethod value  : " + paymentMethodVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while selection of annual card : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while the selection of Annual Card " + e);
		}
	}
	public void SelectMonthlyDirectDebit(String paymentPlanVal, String paymentMethodVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			//Payment Plan
			WaitUtils.waitForelementToBeClickable(driver, paymentPlan, "Error while selection of the payment lePlan");
			paymentPlan.sendKeys(paymentPlanVal);
			Log.message("Selected the paymentPlan value  : " + paymentPlanVal, driver, extentedReport);
			//Payment Method
			WaitUtils.waitForelementToBeClickable(driver, paymentMethod.get(1),"Error while selection of the payment Method");
			paymentMethod.get(1).sendKeys(paymentMethodVal);
			Log.message("Select the paymentMethod value : " + paymentMethodVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while selection of monthly Direct debit : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while the selection of Monthly DirectDebit " + e);
		}
	}

	public UIRef_PolicyComplete ClickOKbtnManageBilling(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, okBtnManageBilling);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
		    executor.executeScript("arguments[0].click();", okBtnManageBilling);
			Log.pass("Clicked the OK button on Manage Billing", driver, extentedReport,true);
			return new UIRef_PolicyComplete(driver, extentedReport);
		}catch(Exception e){
			Log.fail("Error while click of the OK Button of Manage Billing : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Click of the OK Button of Manage Billing" + e );
		}
	}	
}
