package com.ssp.uirefresh_pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.WaitUtils;

public class UIRef_PolicyDocumentationDialog extends LoadableComponent<UIRef_PolicyDocumentationDialog>{
	private static WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	
	
	@FindBy(xpath = "//div[@class='modal-content']//a[@title='Cancel']//span[text()='Cancel']")
	List <WebElement> btnDocCancel;
			
	@FindBy(xpath = "//div[@class='modal-content']//button[@title='Close'][@class='close']")
	List <WebElement> btnDocCloseX;
	
	@FindBy(xpath = "//div[@class='modal fade ng-isolate-scope modal_window_scrolling in']/div[@class='modal-dialog modal-lg']/div[@class='modal-content']/div[2]/div[4]/div/a[2]/span/span")
	List <WebElement> allBtn;
		
	@FindBy(css = "div:nth-child(61) > div > div > div.inner-content.inner_modal.ng-scope > div.row > div > a.btn.btn-primary.btn-default.f-right.gutter-left-8.ng-scope > span > span")
	WebElement btnCnclBA;
	
	@FindBy(css = "div.modal.fade.ng-isolate-scope.modal_window_scrolling.in > div > div > div.inner-content.inner_modal.ng-scope > div.row > div > a.btn.btn-primary.btn-default.f-right.gutter-left-8.ng-scope > span > span")
	WebElement btnCnclpositionCancel;
	
	@FindBy(css = "div.modal.fade.ng-isolate-scope.modal_window_scrolling.in > div > div > div.inner-content.inner_modal.ng-scope > div.row > div > a.btn.btn-primary.btn-default.f-right.gutter-left-8.ng-scope > span > span")
	WebElement btnCnclReinstate;
	
	@FindBy(css = "div:nth-child(62) > div > div > div.inner-content.inner_modal.ng-scope > div.row > div > a.btn.btn-primary.btn-default.f-right.gutter-left-8.ng-scope > span > span")
	WebElement btnCnclEndorse;
	
	@FindBy(xpath = "//div[3]/div/div[@class='modal-content']")
	WebElement documentationWin;
		
	@FindBy(xpath = "//a[@title='Add']//span[text()='Add']")
	WebElement documentationAddbtn;
	
	@FindBy(xpath = "//h3[@class='modal-title']")
	WebElement documentHeader;
	
	@FindBy(xpath = "//form[@name='documentationVM.documentationForm']//a[@title='Cancel']//span[text()='Cancel']")
	WebElement detailCancel;
	
	@FindBy(xpath = "//form[@name='documentationVM.documentationForm']//a[@title='Add']//span[text()='Add']")
	WebElement detailAdd;
	
	public UIRef_PolicyDocumentationDialog(WebDriver driver, ExtentTest extentedReport) {
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
	
	public void ClickDocumentCloseCncl(WebDriver driver, ExtentTest report) {
		try {
			WaitUtils.waitForSpinner(driver);
			WaitUtils.waitForElement(driver, documentationWin);
			btnCnclpositionCancel.click();
			Log.message("Clicked the Cancel Button on Documentation for Policy Cancellation Cooling-off Position", driver, extentedReport);
		} catch (Exception e) {		
			Log.message("Error in Click of he Cancel Button on Documentation for Policy Cancellation Cooling-off Position" + e , driver, extentedReport);
		}

	}
	
	public void ClickDocumentCloseReinstate(WebDriver driver, ExtentTest report) {
		try {
			WaitUtils.waitForSpinner(driver);
			WaitUtils.waitForElement(driver, documentationWin);
			btnCnclpositionCancel.click();
			Log.message("Clicked the Cancel Button on Documentation for Policy Cancellation Cooling-off Position", driver, extentedReport);
		} catch (Exception e) {
			Log.message("Error in Click of he Cancel Button on Documentation for Policy Cancellation Cooling-off Position" + e , driver, extentedReport);
		}

	}

	public void ClickDocumentNBCancel0(WebDriver driver, ExtentTest report) throws Exception{
		try{
				
			WaitUtils.waitForSpinner(driver);
			Thread.sleep(9000);
			WaitUtils.waitForElement(driver, documentationWin);
			Log.message("Cancel Dialog Title verified as " + documentHeader.getText(), driver, extentedReport);
		    //WaitUtils.waitForelementToBeClickable(driver, documentationAddbtn, "Timed out to find Add button");
		    //documentationAddbtn.click();
			WaitUtils.waitForelementToBeClickable(driver, btnDocCancel.get(0), "Timed out to find Dialog Close button");
			//btnDocCancel.click();
			btnDocCancel.get(0).getAttribute("title");
			Log.message("Value in Button on Documentation dialog" + btnDocCancel.get(0).getAttribute("title"), driver, extentedReport);
			//btnDocCancel.sendKeys(Keys.ENTER);
			//btnDocCancel.click();
			Actions actions = new Actions(driver);
		    actions.sendKeys(btnDocCancel.get(0), Keys.RETURN).perform();
			//JavascriptExecutor executor = (JavascriptExecutor) driver;
			Log.message("Clicked the Cancel Button on Documentation dialog", driver, extentedReport);
			
			/*for(int i=0; i<=5 ; i++){
				Thread.sleep(2000);
				if( btnDocCancel.isDisplayed() && btnDocCancel.isEnabled()){
					//Actions action = new Actions(driver);
					//action.moveToElement(btnDocCancel).click().perform();
					//btnDocCloseX.click();
					btnDocCancel.sendKeys(Keys.ENTER);
					Log.message("Clicked the (X) Link  on Documentation dialog in attempt " + i , driver, extentedReport);
				}
			}*/			
			
		}catch(Exception e){
			//throw new Exception("Error while Click of Cancel Button on Documentation dialog" + e );
			Log.message("Error in Click of Cancel on Documentation Page " + e , driver, extentedReport);
			try{
				btnDocCloseX.get(0).click();
			}catch(Exception e1){
			Log.message("Error in Click of (X) button on Documentation Page " + e1 , driver, extentedReport);
			}
			
		}
	}
	
	
	public void ClickDocumentClose(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForSpinner(driver);
			//Thread.sleep(9000);
			//WaitUtils.waitForListElement(driver, documentationAddbtn, 180);
			//WaitUtils.waitForElement(driver, documentationWin);
			//WaitUtils.waitForelementToBeClickable(driver, documentationAddbtn.get(0), "Timed out to find Add button");
		    //documentationAddbtn.get(0).click();
			//WaitUtils.waitForelementToBeClickable(driver, btnDocClose, "Timed out to find Dialog Close X");
			JavascriptExecutor executor = (JavascriptExecutor) driver;
		    executor.executeScript("arguments[0].click();", btnDocCloseX); 
			//btnDocClose.click();
			Log.message("Clicked the (X) Link on Documentation dialog", driver, extentedReport);
			WaitUtils.waitUntilElementDisappear(driver, btnDocCloseX.get(0));			
		}catch(Exception e){
			throw new Exception("Error while Click of Close Button on Documentation dialog" + e );
		}
	}
	
	public void ClickDocumentCancel2(WebDriver driver, ExtentTest report) throws Exception{
		try{	
			WaitUtils.waitForSpinner(driver);
			Thread.sleep(5000);
			WaitUtils.waitForElement(driver, documentationWin);
			Log.message("Cancel Dialog Title verified as " + documentHeader.getText(), driver, extentedReport);
		    WaitUtils.waitForListElement(driver, btnDocCancel, 40);
			Log.message("Clicked the Cancel Button on Documentation dialog", driver, extentedReport);
			
			for(int i=0; i < btnDocCancel.size() ; i++){
				if( btnDocCancel.get(i).isDisplayed() && btnDocCancel.get(i).isEnabled()){
					Thread.sleep(2000);
					
					new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
			        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
			        .withMessage("Unable to Click Document Cancel Button").until(ExpectedConditions.elementToBeClickable(btnDocCancel.get(i)));
					
					//btnDocCancel.get(i).click();
			        Actions action = new Actions(driver);
					action.moveToElement(btnDocCancel.get(i)).click().perform();		
			        		
					Thread.sleep(2000);
					Log.message("Clicked the Cancel button on Documentation dialog of Index " + i , driver, extentedReport);
				}
			}			
			
		}catch(Exception e){
			Log.message("Error in Click of Cancel on Documentation Page " + e, driver, extentedReport);
			try {
				WaitUtils.waitForListElement(driver, btnDocCloseX, 40);
				for (int i = 0; i < btnDocCloseX.size(); i++) {
					if (btnDocCloseX.get(i).isDisplayed() && btnDocCloseX.get(i).isEnabled()) {
						Thread.sleep(2000);
						//btnDocCloseX.get(i).click();
						Actions action = new Actions(driver);
						action.moveToElement(btnDocCancel.get(i)).click().perform();	
						Thread.sleep(2000);
						Log.message("Clicked the (X) button on Documentation dialog of Index " + i, driver,
								extentedReport);
					}
				}
			} catch (Exception e1) {
				Log.message("Error in Click of (X) on Documentation Page " + e1, driver, extentedReport);
			}
		}
	}
}
