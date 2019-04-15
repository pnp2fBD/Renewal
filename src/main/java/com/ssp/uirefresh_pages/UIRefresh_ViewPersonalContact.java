package com.ssp.uirefresh_pages;


import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.WaitUtils;

public class UIRefresh_ViewPersonalContact extends LoadableComponent<UIRefresh_ViewPersonalContact> {
	
	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	JavascriptExecutor executor;
	
	
	@FindBy(css = "#generalDetails > a")
	WebElement generalDetails;
	
	@FindBy(css = "custom-drop-down[list='contactAvailableValues.status'] span[id*='select2-nameFormat-']")
	WebElement contactStatus;
	
	@FindBy(css = "span[id*='select2-statusReason-']")
	WebElement statusReason;
	
	@FindBy(css = "a[ng-click='breadCrumbClicked(breadcrum)'] > span > span[class='ng-scope']")
	WebElement home;
	
		
	public UIRefresh_ViewPersonalContact(WebDriver driver, ExtentTest report) {
		this.driver = driver;
		this.extentedReport = report;
		PageFactory.initElements(driver, this);
		executor = (JavascriptExecutor) driver;
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
	
	public void clickongeneralDetails(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			(new WebDriverWait(driver, 300).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.withMessage("Timed out to click on General Details"))
							.until(ExpectedConditions.elementToBeClickable(generalDetails));
			executor.executeScript("arguments[0].click();", generalDetails);
			Log.message("Clicked on the General details  ", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Unable to click on General Details" + e);
		}
	}
	
	public void verifyContactStatus(HashMap<String, String> testdata,ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			(new WebDriverWait(driver, 300).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.withMessage("Timed out to find contact status"))
							.until(ExpectedConditions.visibilityOf(contactStatus));
			String status=contactStatus.getText();
			System.out.println("Contact Status is: "+status);
			Log.assertThat(status.equalsIgnoreCase(testdata.get("ContactStatus")), "Corret status is appearing", "Incorrect status is appearing");
			Log.message("Clicked on the General details  ", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Unable to get Contact Status" + e);
		}
	}
	
	public void verifyContactReason(HashMap<String, String> testdata,ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			String reason=statusReason.getText();
			System.out.println("Contact Reason is: "+reason);
			Log.assertThatExtentReport(reason.equalsIgnoreCase(testdata.get("StatusReason")), "Corret reason is appearing", "Incorrect reason is appearing",driver,extentedReport);
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		}
	}
	
	public void clickonHome(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			(new WebDriverWait(driver, 300).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.withMessage("Timed out to find Home button"))
							.until(ExpectedConditions.elementToBeClickable(home));
			executor.executeScript("arguments[0].click();", home);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked on the Home button  ", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Unable to click on General Details" + e);
		}
	}

}
