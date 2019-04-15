/**
 * 
 */
package com.ssp.uirefresh_pages;

import static org.testng.Assert.fail;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

/**
 * @author jheelum.dutta
 *
 */

public class UIRefresh_Manage_contacts extends LoadableComponent<UIRefresh_Manage_contacts> {

	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;
	
	@FindBy(css = "a[title='Search']")
	WebElement btn_Search;

	@FindBy(css = "#name")
	WebElement txt_Name;

	@FindBy(css = "#edit")
	WebElement btn_Edit;
	
	@FindBy(css = "#urn")
	WebElement urn;
	
	@FindBy(css = "a[class='sideArrow']")
	WebElement filters;
	
	@FindBy(css="span[id*='select2--'][title='Active']")
	WebElement contactStatus;
	
	@FindBy(css = "#split-main-container > div:nth-child(4) > div > div > div.row > div > a > span > span")
	WebElement btn_complete;
	
	@FindBy(css="#split-main-container > div:nth-child(4) > div > div > div.title_main.clearfix > h2 > span > span")
	WebElement manageContacts;
	
	@FindBy(css="#view")
	WebElement viewfirstContact;
	
	@FindBy(css="#edit")
	WebElement editContact;
	
	@FindBy(xpath="//li[contains(@id,'Active')]")
	WebElement  selectActive;
	
	@FindBy(xpath="//li[contains(@id,'Terminated')]")
	WebElement  selectTerminate;
	
	@FindBy(css="button[title=Create]")
	WebElement createBtn;
	
	@FindBy(css="a[title='Create Personal Contact']")
	WebElement createPersonalLink;
	
	@FindBy(css="span[data-i18n*='manage_contacts_label']>span")
    WebElement manageContactsHeadTxt;	
	
	/**
	 * 
	 * Constructor class for Customer SelfService Page Here we initializing the
	 * driver for page factory objects. For ajax element waiting time has added
	 * while initialization
	 * 
	 * @param driver
	 *            : Webdriver
	 */
	public UIRefresh_Manage_contacts(WebDriver driver, ExtentTest report) {
		this.driver = driver;
		this.extentedReport = report;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	  isPageLoaded = true;
      // driver.get(sspSelfServiceURL);
      WaitUtils.waitForPageLoad(driver);
      // isPageLoaded = true;
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
      if (!isPageLoaded) {
        Assert.fail();
      }
      if (isPageLoaded && !manageContactsHeadTxt.isDisplayed()) {
        Log.fail("UI Refresh Manage Contacts Page did not open up. Site might be down.", driver,
            extentedReport);
      }
      uielement = new ElementLayer(driver);
	}

	public void enterName(String name, boolean screenshot) throws Exception {
		try {
		    Log.event("Entered enterName method");
			WaitUtils.waitForElementPresent(driver, txt_Name,"Text box for name is not not present");
			txt_Name.clear();
			txt_Name.sendKeys(name);
			Log.message("Entered the UserName : " + name, driver, extentedReport, screenshot);
		} catch (Exception e) {
			throw new Exception("Error while entering Username : " + e);
		}
	}
	
	public void enterURN(String URN) throws Exception {
		try {
			WaitUtils.waitForElement(driver, urn);
			urn.clear();
			urn.sendKeys(URN);
			Log.message("Entered the URN : " + URN, driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while entering URN on Manage Contact screen : " + e);
		}
	}

	public void clickBtnSearch(ExtentTest extentedReport, WebDriver driver, boolean screenshot) throws Exception {
		try {
		    Log.event("Entered clickBtnSearch method");
		    WaitUtils.waitForElementPresent(driver, btn_Search, "Search button for user is not displayed");
		    WaitUtils.waitForelementToBeClickable(driver, btn_Search , "Search button is not clickable");
		    GenericUtils.javaScriptExecutorToClick(driver, btn_Search);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked Search button on Manage ContactPage ", driver, extentedReport, screenshot);
		} catch (Exception e) {
			throw new Exception("Error while clicking Search button : " + e);
		}

	}

	public UIRefrsh_EditPersonalContact clickBtnEdit(ExtentTest extentedReport, WebDriver driver, boolean screenshot) throws Exception {
		try {
		  Log.event("Entered clickBtnEdit method");
		  WaitUtils.waitForElementPresent(driver, btn_Edit, "Edit button is not present");
		  WaitUtils.waitForelementToBeClickable(driver, btn_Edit, "Edit button is not clickable");
		  GenericUtils.javaScriptExecutorToClick(driver, btn_Edit);	
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked Edit  icon Manage Contact Details Page", driver, extentedReport);
			return new UIRefrsh_EditPersonalContact(driver, extentedReport).get();
		} catch (Exception e) {
			throw new Exception("Error while clicking Edit Contact Details Page : " + e);
		}

	}

	@SuppressWarnings("deprecation")
	public UIRefresh_Dashboard clickBtnComplete(ExtentTest extentedReport, WebDriver driver, boolean screenshot) throws Exception {
		try {
      Log.event("Entered clickBtnComplete method");
      WaitUtils.waitForElementPresent(driver, btn_complete, "Complete button is not present");
      WaitUtils.waitForelementToBeClickable(driver, btn_complete,
          "Complete button is not clickable");
      GenericUtils.javaScriptExecutorToClick(driver, btn_complete);
      WaitUtils.waitForSpinner(driver);
      Log.message("Clicked Complete button on Manage contact page  ", driver, extentedReport, screenshot);
      return new UIRefresh_Dashboard(driver, extentedReport).get();
		} catch (Exception e) {
			throw new Exception("Error while clicking Complete button : " + e);
		}
	}
	
	public void clickonFilters(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, btn_complete);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", filters);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked on Filter link  ", driver, extentedReport);
		} catch (Exception e) {
			throw new Exception("Error while clicking Complete button : " + e);
		}
	}
	
	public void selectStatus(HashMap<String, String> testdata,ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver, contactStatus);
			contactStatus.click();
			String status=testdata.get("ContactStatus");
			switch (status)
			{
			case "Active": 	WaitUtils.waitForElement(driver, selectActive);
							selectActive.click();
			    			break;	
			    			
			case "Terminated": WaitUtils.waitForElement(driver, selectTerminate);
							   selectTerminate.click();
							   break;
			}
			Log.message("Status Selected as : " + testdata.get("ContactStatus"), extentedReport);
			WaitUtils.waitForSpinner(driver);
		} catch (Exception e) {
			throw new Exception("Unable to Select Status" + e);
		}
	}
	
	public UIRefresh_ViewPersonalContact viewContact(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			(new WebDriverWait(driver, 300).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.withMessage("Timed out to find 'Role' dropdown"))
							.until(ExpectedConditions.elementToBeClickable(viewfirstContact));
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", viewfirstContact);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked on the contact to view the details  ", driver, extentedReport);
			return new UIRefresh_ViewPersonalContact(driver, extentedReport).get();
		} catch (Exception e) {
			throw new Exception("Unable to click on the contact to view the details" + e);
		}
	}
	
	public void verifyEditContactlink (ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver,editContact);
			editContact.click();
			 if (manageContacts.getText().trim().replaceAll("\\s+", " ").equals("Manage Contacts")) {
				 Log.pass("Cannot Edit the contact: Pass");
			}
			else
				Log.fail("Edit link is enabled: Fail");
			Log.message("Clicked on the contact to view the details  ", driver, extentedReport);
			
			//	if (!editContact.isEnabled())
			//		Log.pass("Cannot Edit the contact: Pass");
			
			} catch (Exception e) {
			throw new Exception("Unable to click on the contact to view the details" + e);
		}
	}
	
	public UIRefrsh_EditPersonalContact clickCreatePersonalContact(ExtentTest extentedReport, WebDriver driver) throws Exception {
		try {
			WaitUtils.waitForElement(driver,createBtn);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", createBtn);
			WaitUtils.waitForElement(driver,createPersonalLink);
			executor.executeScript("arguments[0].click();", createPersonalLink);
			Log.message("Clicked the Create Personal Contact on Manage Contact", driver, extentedReport, true);
			WaitUtils.uiRefwaitForSpinner(driver);
			return new UIRefrsh_EditPersonalContact(driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while clicking the button create personal contact on Manage Contact", driver, extentedReport, true);
			throw new Exception("Error while clicking the button create personal contact on Manage Contact : " + e);
		}

	}
	
}
