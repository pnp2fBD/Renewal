/**
 * 
 */
package com.ssp.uirefresh_pages;

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
import com.ssp.support.StopWatch;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

/**
 * @author jheelum.dutta
 *
 */
public class UIRefrsh_EditPersonalContact extends LoadableComponent<UIRefrsh_EditPersonalContact> {
	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;
	private static String URN = null;

	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > form > div.activity-bar.gutter-bottom-15 > div > div:nth-child(2) > p")
	WebElement btn_Role_tab;

	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > form > div > div > div:nth-child(5) > p")
	WebElement btn_complete_tab;

	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > div.tab-section.ng-scope > div > div > div.panel.panel-default.rounded-corner > h4 > div > div > custom-drop-down > select")
	WebElement role_Name_drop;

	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > div.tab-section.ng-scope > div > div > div.panel.panel-default.rounded-corner > h4 > a")
	WebElement btnAdd;

	@FindBy(css = "body > span > span > span.select2-search.select2-search--dropdown > input")
	WebElement txtRole;

	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > div.tab-section.ng-scope > div > div > div.row > div > a:nth-child(1)")
	WebElement btn_save;

	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > div.tab-section.ng-scope > div > div > div.row > div > a:nth-child(1)")
	WebElement edit;

	@FindBy(xpath = "//a[text()='System User']/ancestor::tr//a[@title='Edit']")
	WebElement btn_edit;

	@FindBy(css = "#generalDetails > a")
	WebElement link_General_details;

	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > form > div.tab-section.ng-scope > div > div > div.tab-pane.ng-scope.active > div > div > div:nth-child(1) > div > div:nth-child(1) > div.col-md-5.col-xs-12.zero-space > div.col-md-6.col-xs-6.col-sm-6 > div > custom-drop-down > select")
	WebElement drop_select_status;

	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > form > div.tab-section.ng-scope > div > div > div.tab-pane.ng-scope.active > div > div > div:nth-child(1) > div > div:nth-child(2) > div:nth-child(3) > div > div > custom-drop-down > select")
	WebElement drop_select_status_reason;

	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > form > div.activity-bar.gutter-bottom-15 > div > div:nth-child(2)")
	WebElement btn_role;

	@FindBy(css = "#split-main-container > div.widgets-breadcrumb.ng-scope > ul > li:nth-child(4) > span.urn-breadcrumb.ng-binding.ng-scope")
	WebElement contact_URN;

	@FindBy(css = "select[name=gender]")
	WebElement selGender;

	@FindBy(css = "input[name='birthDate']")
	WebElement txtDOB;

	@FindBy(css = "#contactName")
	WebElement txtContactName;

	@FindBy(css = "#contactAlias")
	WebElement txtContactAlias;

	@FindBy(css = "custom-drop-down[ng-model='otherContactValues.addressFormat'] select[name*=addressFormat]")
	WebElement selAddressFormat;

	@FindBy(css = "a[title='Show Address Dialog'] > i")
	WebElement addressIcon;

	@FindBy(css = "#addressDescription")
	WebElement txtAddDescription;

	@FindBy(css = "a[ng-click*='tab.generalDetails'] span[data-i18n*='next_button']")
	WebElement btnNext;

	@FindBy(css = "div[ng-include*='/app/components/manageContacts/generalDetails.htm'] a[title='Save']")
	WebElement btnGenDtlSave;

	@FindBy(css = "div[ng-class *= 'Banking'] span[data-i18n*='banking_label']")
	WebElement lnkBanking;
	
	@FindBy(css = "span[data-i18n*='personal_contact_label']>span")
    WebElement personalContactHeadTxt;
	
	public UIRefrsh_EditPersonalContact(WebDriver driver, ExtentTest report) {
		this.driver = driver;
		this.extentedReport = report;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
	  isPageLoaded = true;
	  WaitUtils.waitForPageLoad(driver);		
	}

	@Override
	protected void isLoaded() throws Error {
	  if (!isPageLoaded) {
        Assert.fail();
    }
	 if (isPageLoaded && !personalContactHeadTxt.isDisplayed()) {
        Log.fail("UI Refresh 'Edit Personal Contact' page did not open up. Site might be down.", driver,
            extentedReport);
      }
      uielement = new ElementLayer(driver);
	}

	public void clickBtnRole(ExtentTest extentedReport, WebDriver driver, boolean screenshot) throws Exception {
		try {
		    Log.event("Entered 'clickBtnRole' method");
			WaitUtils.waitForElementPresent(driver, btn_Role_tab, "Roles tab is not present");
			WaitUtils.waitForelementToBeClickable(driver, btn_Role_tab, "Roles tab is not clickable");
			GenericUtils.javaScriptExecutorToClick(driver, btn_Role_tab);
			Log.message("Clicked 'Roles' tab on 'Edit Personal Contact' page ", driver, extentedReport, screenshot);
		} catch (Exception e) {
			throw new Exception("Error while clicking 'Roles' tab on 'Edit Personal Contact' page: " +e);
		}
	}

	// click on general Details
	public void click_on_general_Detils(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			final long startTime = StopWatch.startTime();

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", link_General_details);

			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked General Details tab on Edit Personal Details Page ", driver, extentedReport);
			Log.event("Clicked General Details on Edit Personal Details Page", StopWatch.elapsedTime(startTime));
		} catch (Exception e) {
			throw new Exception("Error while clicking Edit Personal Details Page: " + e);
		}

	}

	public void enterRole(String role_Name_txt) throws Exception {
		try {
			WaitUtils.waitForElement(driver, txtRole);
			txtRole.clear();
			txtRole.sendKeys(role_Name_txt);
			Log.message("Entered the UserName : " + role_Name_txt, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering Username : " + e);
		}
	}

	public void clickBtnAdd(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			final long startTime = StopWatch.startTime();

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", btnAdd);

			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked Add button on Edit Personal Details Page ", driver, extentedReport);
			Log.event("Clicked Add button  on Edit Personal Details Page", StopWatch.elapsedTime(startTime));
		} catch (Exception e) {
			throw new Exception("Error while clicking Add button on Edit  Personal Details Page: " + e);
		}

	}

	public void click_on_Role_Dropdown(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			final long startTime = StopWatch.startTime();

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			boolean displayed = role_Name_drop.isDisplayed();
			executor.executeScript("arguments[0].click();", role_Name_drop);

			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked Role button on Edit Personal Details Page ", driver, extentedReport);
			Log.event("Clicked Role button  on Edit Personal Details Page", StopWatch.elapsedTime(startTime));
		} catch (Exception e) {
			throw new Exception("Error while clicking Add button on Edit  Personal Details Page: " + e);
		}

	}

	/*
	 * Select Status as Terminated
	 * 
	 */

	public void select_contact_status(String txtroleselect, ExtentTest extentedReport) throws Exception {
		try {
			// role_Name_drop.click();

			(new WebDriverWait(driver, 300).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.withMessage("Timed out to find 'status' dropdown"))
							.until(ExpectedConditions.elementToBeClickable(drop_select_status));

			Select roledropdown = new Select(drop_select_status);
			roledropdown.selectByVisibleText(txtroleselect);
			Log.message("Status Selected as : " + txtroleselect, extentedReport);
			WaitUtils.waitForSpinner(driver);
		} catch (Exception e) {
			throw new Exception("Unable to Select Status" + e);
		}
	}
	/*
	 * Select Reason
	 * 
	 */

	public void select_contact_status_reason(String txtstatusReasonselect, ExtentTest extentedReport) throws Exception {
		try {
			// role_Name_drop.click();

			(new WebDriverWait(driver, 300).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.withMessage("Timed out to find 'status' dropdown"))
							.until(ExpectedConditions.elementToBeClickable(drop_select_status_reason));

			Select roledropdown = new Select(drop_select_status_reason);
			roledropdown.selectByVisibleText(txtstatusReasonselect);
			Log.message("Reason selected as Selected as : " + txtstatusReasonselect, extentedReport);
			WaitUtils.waitForSpinner(driver);
		} catch (Exception e) {
			throw new Exception("Unable to Select Status" + e);
		}
	}

	/**
	 * Selects the roledropdowm plan.
	 * 
	 * @param role_neme
	 * @param extentedReport
	 * @throws Exception
	 */
	public void select_Role_from_Dropdown(String txtroleselect, ExtentTest extentedReport) throws Exception {
		try {
			// role_Name_drop.click();

			(new WebDriverWait(driver, 300).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.withMessage("Timed out to find 'Role' dropdown"))
							.until(ExpectedConditions.elementToBeClickable(role_Name_drop));

			Select roledropdown = new Select(role_Name_drop);
			roledropdown.selectByVisibleText(txtroleselect);
			Log.message("Role Selected : " + txtroleselect, extentedReport);
			WaitUtils.waitForSpinner(driver);
		} catch (Exception e) {
			throw new Exception("Unable to Select Role" + e);
		}
	}

	// #edit #example > tbody > tr:nth-child(5) > td:nth-child(7)

	public void clickBtnsave(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			final long startTime = StopWatch.startTime();

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", btn_save);

			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked Save button on Manage ContactPage ", driver, extentedReport);
			Log.event("Clicked Save button on Manage ContactPage", StopWatch.elapsedTime(startTime));
		} catch (Exception e) {
			throw new Exception("Error while clicking Save button : " + e);
		}

	}

	public UIRefresh_System_User_Role clickBtnEdit(ExtentTest extentedReport, WebDriver driver, boolean screenshot) throws Exception {
		try {
		    Log.event("Entered clickBtnEdit method");
		    WaitUtils.waitForElementPresent(driver, btn_edit, "Edit button is not present");
		    WaitUtils.waitForelementToBeClickable(driver, btn_edit, "Edit button is not clickable");
		    GenericUtils.javaScriptExecutorToClick(driver, btn_edit);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked Save button on Manage ContactPage ", driver, extentedReport, screenshot);
			return new UIRefresh_System_User_Role(driver, extentedReport).get();
		} catch (Exception e) {
			throw new Exception("Error while clicking Save button : " + e);
		}
	}

	public UIRefresh_Manage_contacts clickBtnCompletetab(ExtentTest extentedReport, WebDriver driver, boolean screenshot) throws Exception {
		try {
		    Log.event("Entered clickBtnCompletetab method");
			WaitUtils.waitForElementPresent(driver, btn_complete_tab, "Complete tab is not present");
			WaitUtils.waitForelementToBeClickable(driver, btn_complete_tab, "Complete tab is not clickable");
			GenericUtils.javaScriptExecutorToClick(driver, btn_complete_tab);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked Complete button on Edit personal page  ", driver, extentedReport, screenshot);
			return new UIRefresh_Manage_contacts(driver, extentedReport).get(); 
		} catch (Exception e) {
			throw new Exception("Error while clicking Complete button : " + e);
		}
	}

	public String getContactURN(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			(new WebDriverWait(driver, 500).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.withMessage("Timed out to find 'Role' dropdown"))
							.until(ExpectedConditions.elementToBeClickable(btn_complete_tab));
			URN = contact_URN.getText();
			URN = URN.replaceAll("[()]", "");
			Log.message("Contact URN is: " + URN, driver, extentedReport);

		} catch (Exception e) {
			throw new Exception("Error while clicking Save button : " + e);
		}
		return URN;

	}

	public void selectGender(String txtGenderVal, WebDriver driver, ExtentTest extentedReport) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, selGender, "Unable to select gender");
			Select roledropdown = new Select(selGender);
			roledropdown.selectByVisibleText(txtGenderVal);
			Log.message("Contact Gender Selected as : " + txtGenderVal, extentedReport);
		} catch (Exception e) {

			Log.fail("Unable to select Gender" + e, driver, extentedReport, true);
		}
	}

	public void enterDOB(String contactDOB, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, txtDOB);
			// String strDate = GenericUtils.setDate(dobVal, txtDOB,
			// Integer.parseInt(dayInt),
			// Integer.parseInt(yearInt));
			txtDOB.clear();
			txtDOB.sendKeys(contactDOB);
			Log.message("Entered the contact DOB value is : " + txtDOB.getAttribute("value"), driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering the contact dob  " + e);
		}
	}

	// btnGenDtlSave
	public void clickBtnGenDtlSave(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnGenDtlSave);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", btnGenDtlSave);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked Save button on contact general details  ", driver, extentedReport, true);
		} catch (Exception e) {
			throw new Exception("Error while clicking the Save button on contact general details : " + e);
		}
	}
	
	/*
		
	@FindBy(css = "a[ng-click*='tab.generalDetails'] span[data-i18n*='next_button']")
	WebElement btnNext;

	@FindBy(css = "div[ng-include*='/app/components/manageContacts/generalDetails.htm'] a[title='Save']")
	WebElement btnGenDtlSave;

	@FindBy(css = "div[ng-class *= 'Banking'] span[data-i18n*='banking_label']")
	WebElement lnkBanking;*/
	//txtContactName
	public void enterContactName(String inputVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, txtContactName);
			txtContactName.sendKeys(inputVal);
			Log.message("Entered the contact name is : " + inputVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the contact alias", driver, report, true);
			throw new Exception("Error while entering the contact name  " + e);
		}
	}
	//txtContactAlias
	public void enterContactAlias(String inputVal, WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, txtContactAlias);
			txtContactAlias.sendKeys(inputVal);
			Log.message("Entered the contact alias is : " + inputVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the contact alias", driver, report, true);
			throw new Exception("Error while entering the contact alias  " + e);
		}
	}
	//selAddressFormat
	public void selectAddressFormat(String inputVal, WebDriver driver, ExtentTest extentedReport) throws Exception {
		try {
			WaitUtils.waitForelementToBeClickable(driver, selAddressFormat, "Unable to select address format");
			Select roledropdown = new Select(selAddressFormat);
			roledropdown.selectByVisibleText(inputVal);
			Log.message("Selected Adress format value is : " + inputVal, extentedReport);
		} catch (Exception e) {
			Log.fail("Unable to select address format" + e, driver, extentedReport, true);
			Log.exception(e, driver, extentedReport);
		}
	}
	//addressIcon
	public UIRef_AddresSearchDialog clickAddressIcon(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, addressIcon);
			addressIcon.click();
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked the address icon on contact general details  ", driver, extentedReport, true);
			return new UIRef_AddresSearchDialog(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking the address icon on contact : " + e, driver, extentedReport, true);
			throw new Exception("Error while clicking the address icon on contact : " + e);
		}
	}
	
	//txtAddDescription
	public void verifyAddDescription(String inputVal, ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForDisabledElement(driver, txtAddDescription, 60);
			Log.softAssertThat(txtAddDescription.getAttribute("value").contains(inputVal), "Address description exist","Address description not found", driver, extentedReport, true);
		} catch (Exception e) {
			Log.fail("Selected Address description is not present", driver, extentedReport, true);
		}
	}
	
	//btnNext
	public void clickBtnNextNameAddress(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btnNext);
			btnNext.click();
			Log.message("Clicked the button Next on contact name address page  ", driver, extentedReport, true);
		} catch (Exception e) {
			Log.fail("Error while clicking the the button Next on contact name address page");
			throw new Exception("Error while clicking the the button Next on contact name address page : " + e);
		}
	}
		
	// lnkBanking;
	public UIRef_ContactBanking clicklnkBanking(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, lnkBanking);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", lnkBanking);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked the link Banking on contact page", driver, extentedReport, true);
			return new UIRef_ContactBanking(driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking the link Banking on Contact page : " + e);
		}
	}
}
