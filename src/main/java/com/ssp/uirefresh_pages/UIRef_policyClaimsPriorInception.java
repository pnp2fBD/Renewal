package com.ssp.uirefresh_pages;

import java.util.List;

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
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

public class UIRef_policyClaimsPriorInception extends LoadableComponent<UIRef_policyClaimsPriorInception> {

	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;

	@FindBy(xpath = "//a[@title='Add']//span[text()='Add']")
	WebElement Addbtn;

	@FindBy(xpath = "//input[@id='amount']")
	WebElement valueOfClaim;

	@FindBy(xpath = "//select[@name='statusOfClaim']")
	WebElement statusOfClaim;

	@FindBy(xpath = "//select[@name='atCurrentAddress']")
	WebElement atRiskAddress;

	@FindBy(xpath = "//select[@name='coverSection']")
	WebElement coverSection;

	@FindBy(xpath = "//select[@name='typeOfClaim']")
	WebElement typeOfClaim;

	@FindBy(xpath = "//input[@name='dateClaimMade']")
	WebElement dateOfClaim;

	@FindBy(xpath = "//a[@title='Add'][@class='btn btn-primary f-left']//span[text()='Add']")
	WebElement AddClaimbtn;

	@FindBy(xpath = "//a[@title='Continue']//span[text()='Continue']")
	WebElement alertContinuebtn;

	@FindBy(xpath = "//table[contains(@id,'DataTables_Table')]/tbody/tr[1]")
	WebElement row1;

	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > div > div > div.tab-section.dynamicPage.ng-scope.no-class.ng-valid-maxlength.ng-valid-date.ng-valid-date-disabled.ng-dirty.ng-valid.ng-valid-required > div > div > div.tab-pane.ng-scope.active > div > div.row.ng-scope > div > a:nth-child(1)")
	WebElement Savebtn;

	public UIRef_policyClaimsPriorInception(WebDriver driver, ExtentTest report) {
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

	
	public void ClickAddMain(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, Addbtn);
			Addbtn.click();
			Log.pass("Clicked the Add in Claims prior to incpetion", driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while Clicking the Add in Claims prior to incpetion");
		}
	}

	public void enterValueOfClaim(String valueOfClaimVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, valueOfClaim);
			WaitUtils.waitForelementToBeClickable(driver, valueOfClaim, "Error in ValueOfClaim");
			valueOfClaim.clear();
			valueOfClaim.sendKeys(valueOfClaimVal);
			Log.message("Entered the valueOfClaim is : " + valueOfClaimVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the valueOfClaim : " + e);
		}
	}

	public void enterStatusOfClaim(String statusOfClaimVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, statusOfClaim, "statusOfClaim is not avaiable");
			Select select = new Select(statusOfClaim);
			select.selectByValue(statusOfClaimVal);
			Log.message("Entered statusOfClaim is : " + statusOfClaimVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the statusOfClaim : " + e);
		}
	}

	public void enterAtRiskAddress(String atRiskAddressVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, atRiskAddress);
			Select select = new Select(atRiskAddress);
			select.selectByValue(atRiskAddressVal);
			Log.message("Entered the atRiskAddressVal is : " + atRiskAddressVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the atRiskAddressVal : " + e);
		}
	}

	public void enterCoverSection(String coverSectionVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, coverSection);
			Select select = new Select(coverSection);
			select.selectByValue(coverSectionVal);
			Log.message("Entered coverSection Val is : " + coverSectionVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the coverSectionVal : " + e);
		}
	}

	public void entertypeOfClaim(String typeOfClaimVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, typeOfClaim);
			Select select = new Select(typeOfClaim);
			select.selectByValue(typeOfClaimVal);
			Log.message("Entered typeOfClaimVal Val is : " + typeOfClaimVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the typeOfClaimVal : " + e);
		}
	}

	public void enterDateOfClaim(String dateOfClaimVal, String dayInt, String yearInt, WebDriver driver,
			ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, dateOfClaim);
			String strDate = GenericUtils.setDate(dateOfClaimVal, dateOfClaim, Integer.parseInt(dayInt),
					Integer.parseInt(yearInt));
			Log.message("Entered the dateOfClaim value is : " + strDate, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the dateOfClaim " + e);
		}
	}
	
	
	public void ClickAddClaimbtn(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, AddClaimbtn);
			AddClaimbtn.click();
			Log.pass("Clicked the Add Claim in Claims prior to incpetion", driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while Clicking the Add Claim in Claims prior to incpetion");
		}
	}
	
	public void ClickAlertContinuebtn(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, alertContinuebtn);
			alertContinuebtn.click();
			Log.pass("Clicked the alertContinuebtn in Claims prior to incpetion", driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while Clicking the alertContinuebtn in Claims prior to incpetion");
		}
	}
	
	public void Clickrow1(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, row1, "Unable to select the prior claim row");
			row1.click();
			Log.pass("Clicked the row1 in Claims prior to incpetion", driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while Clicking the row1 in Claims prior to incpetion");
		}
	}
	
	public void ClickSavebtn(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, Savebtn);
			Savebtn.click();
			Log.pass("Clicked the Savebtn in Claims prior to incpetion", driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while Clicking the Savebtn in Claims prior to incpetion");
		}
	}
			
}
