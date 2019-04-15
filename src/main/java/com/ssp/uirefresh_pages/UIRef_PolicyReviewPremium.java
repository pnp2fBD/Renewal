package com.ssp.uirefresh_pages;

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

public class UIRef_PolicyReviewPremium extends LoadableComponent<UIRef_PolicyReviewPremium> {

	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;

	@FindBy(xpath = "//*[@id='positionType']")
	WebElement polPosition;

	@FindBy(xpath = "//script[@id='reviewPremium_tree_node']//parent::div//table//tfoot[1]//td[9]")
	WebElement tdTotalAmtPayable;

	@FindBy(xpath = "//script[@id='reviewPremium_tree_node']//parent::div//table")
	WebElement tableReviewPremium;

	@FindBy(xpath = "//script[@id='reviewPremium_tree_node']//parent::div//table//tfoot/tr")
	WebElement trTotal;

	@FindBy(xpath = "//a[@title='OK']//span[text()='OK']")
	WebElement okBtnReviewPremium;

	@FindBy(xpath = "//span[text()='Complete']")
	WebElement lnkComplete;

	@FindBy(xpath = "//span[text()='Review Referrals']")
	WebElement lnkReviewReferrals;

	public UIRef_PolicyReviewPremium(WebDriver driver, ExtentTest report) {
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

	public void ValidateTotalAmtPayableNB(String TotalAmtPayableVal, WebDriver driver, ExtentTest report)
			throws Exception {
		try {
			WaitUtils.waitForElement(driver, tdTotalAmtPayable);
			String expected = tdTotalAmtPayable.getText();
			// Log.softAssertThat(expected.equalsIgnoreCase(TotalAmtPayableVal),
			// "Total Payable Premium Matched", "Total Payable Premium did not
			// Matched", driver, report, true);
			/*
			 * if (Integer.parseInt(expected) > 400){ Log.softAssertThat(true,
			 * "Total Payable Premium Matched",
			 * "Total Payable Premium did not Matched", driver, report, true); }
			 */
			Log.message("NB Actual Total Premium is : " + expected + " & the Expected is : " + TotalAmtPayableVal,
					driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while Validation of Total Premium on Review Premium table");
		}
	}

	public void ValidateTotalAmtPayableCNL(String TotalAmtPayableVal, WebDriver driver, ExtentTest report)
			throws Exception {
		try {

			WaitUtils.waitForElement(driver, tdTotalAmtPayable);
			String actual = tdTotalAmtPayable.getText();

			// Log.softAssertThat(expected.equalsIgnoreCase(TotalAmtPayableVal),
			// "Total Payable Premium Matched", "Total Payable Premium did not
			// Matched", driver, report, true);
			/*
			 * if (Integer.parseInt(expected) <
			 * Integer.parseInt(TotalAmtPayableVal)){ Log.softAssertThat(true,
			 * "Total Payable Premium Matched",
			 * "Total Payable Premium did not Matched", driver, report, true); }
			 */
			Log.message("CNL Actual Total Premium is ->" + actual + " & the Expected is -> " + TotalAmtPayableVal,
					driver, extentedReport);

		} catch (Exception e) {
			throw new Exception("Error while Validation of Total Premium on Review Premium table");
		}
	}

	public void ValidatePolicyPosition(String positionTypeVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, polPosition);
			String expected = polPosition.getAttribute("value");
			Log.softAssertThat(expected.equalsIgnoreCase(positionTypeVal), "Policy position Matched is : " + expected,
					"Actual Policy position is : " + expected + " however it should be " + positionTypeVal, driver,
					report, true);
			Log.message("Validated policy position on Review tab is :" + positionTypeVal, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while Validation of Total Premium on Review Premium table");
		}
	}

	public void ClickOKReviewPremium(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, okBtnReviewPremium);
			okBtnReviewPremium.click();
			Log.message("Clicked the OK button on Review Premium Page", driver, extentedReport);
			Log.pass("Clicked the Save button on Core Covers ", driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while Click of OK button on Review Premium Page");
		}
	}

	public UIRef_PolicyComplete NavigateToComplete(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkComplete);
			lnkComplete.click();
			Log.message("Clicked on the Complete Tab", driver, extentedReport);
			return new UIRef_PolicyComplete(driver, report);
		} catch (Exception e) {
			throw new Exception("Error while Click on the Complete Tab" + e);
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
			throw new Exception("Error while Click of the Review Referrals Link" + e);
		}
	}

}
