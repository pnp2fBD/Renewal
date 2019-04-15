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
import com.ssp.utils.WaitUtils;

public class UIRef_PropertyDetails extends LoadableComponent<UIRef_PropertyDetails> {

	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;

	@FindBy(xpath = "//select[@name='riskAddress']")
	WebElement addressFormat;

	@FindBy(xpath = "//textarea[@id='resizable'][@name='riskAddress']")
	WebElement riskAddress;

	@FindBy(xpath = "//textarea[@id='resizable'][@name='riskAddress']//parent::div//following-sibling::div/a/i[@class='fa fa-sitemap']")
	WebElement riskAddressIcon;

	@FindBy(xpath = "//*[@id='postCode']")
	WebElement postcodetxt;

	@FindBy(xpath = "//span[text()='Search']")
	WebElement postcodeSearchBtn;

	@FindBy(xpath = "//table[contains(@id,'DataTables_Table_')]//tr[1]//td[3]//a")
	WebElement addressRetriveIcon;
	// a[@class='icon_refresh'][@title='Retrieve']

	@FindBy(xpath = "//select[@name='propertyType']")
	WebElement propertyType;

	@FindBy(xpath = "//*[@id='yearBuilt']")
	WebElement yearBuild;

	@FindBy(xpath = "//select[@name='numberOfBedrooms']")
	WebElement numberOfBedrooms;

	@FindBy(xpath = "//select[@name='constructWall']")
	WebElement constructWall;

	@FindBy(xpath = "//select[@name='constructRoof']")
	WebElement constructRoof;

	@FindBy(xpath = "//select[@name='ownershipType']")
	WebElement ownershipType;

	@FindBy(xpath = "//select[@name='occupiedDuringDay']")
	WebElement occupiedDuringDay;

	@FindBy(xpath = "//select[@name='soleOccupancy']")
	WebElement soleOccupancy;

	@FindBy(xpath = "//select[@name='occupiedDuringNight']")
	WebElement occupiedDuringNight;

	@FindBy(xpath = "//a[@title='Save']//following-sibling::a[@title='Next']")
	List<WebElement> nextBtn;

	@FindBy(xpath = " //div[@id='split-container']//a[@title='Save']//span[text()='Save']")
	List<WebElement> okBtnPolicyHeader;
	
	@FindBy(xpath = " //div[@id='split-container']//a[@title='Save']//span[text()='Save']")
	List<WebElement> saveBtnPolicyDetails;


	public UIRef_PropertyDetails(WebDriver driver, ExtentTest report) {
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
	
	public void addressFormat(String addressFormatVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, addressFormat);
			Select select = new Select(addressFormat);
			select.selectByValue(addressFormatVal);
			Log.message("Entered addressFormat Value is : " + addressFormatVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the addressFormat : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the addressFormat " + e);
		}
	}

	public void selectAddressFromPostcode(String postcode,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, riskAddressIcon);
			riskAddressIcon.click();
			Log.message("Clicked the Address Icon  ", extentedReport);
			WaitUtils.waitForElement(driver, postcodetxt);
			postcodetxt.sendKeys(postcode);
			Log.message("Enter postcode is : " + postcode, extentedReport);
			postcodeSearchBtn.click();
			Log.message("Clicked the postcode search button", extentedReport);
			WaitUtils.waitForelementToBeClickable(driver, addressRetriveIcon,"Error while retrive address");
			addressRetriveIcon.click();
			Log.message("Clicked the Address retreive Link", extentedReport);
			WaitUtils.waitForElement(driver, riskAddress);
			if (riskAddress.getText().contains(postcode)) {
				Log.message("Address selected with the given postcode : " + postcode, extentedReport);
			} else {
				Log.message("Address is empty while the given postcode : " + postcode, extentedReport);
			}

		} catch (Exception e) {
			Log.fail("Error while selecting the postcode : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while selecting the postcode " + e);
		}
	}

	public void propertyType(String propertyTypeVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, propertyType);
			Select select = new Select(propertyType);
			select.selectByValue(propertyTypeVal);
			Log.message("Entered propertyType Value is : " + propertyTypeVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the propertyType : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the propertyType " + e);
		}
	}

	public void yearBuild(String yearBuildVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, yearBuild);
			yearBuild.clear();
			yearBuild.sendKeys(yearBuildVal);
			Log.message("Entered yearBuild Value is : " + yearBuildVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the yearBuild : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the yearBuild " + e);
		}
	}

	public void numberOfBedrooms(String numberOfBedroomsVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, numberOfBedrooms);
			Select select = new Select(numberOfBedrooms);
			select.selectByValue(numberOfBedroomsVal);
			Log.message("Entered numberOfBedrooms Value is : " + numberOfBedroomsVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the numberOfBedrooms : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the numberOfBedrooms " + e);
		}
	}
	
	//constructWall
	public void constructWall(String constructWallVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, constructWall);
			Select select = new Select(constructWall);
			select.selectByValue(constructWallVal);
			Log.message("Entered constructWall Value is : " + constructWallVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the constructWall : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the constructWall " + e);
		}
	}
	
	//constructRoof
	public void constructRoof(String constructRoofVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, constructRoof);
			Select select = new Select(constructRoof);
			select.selectByValue(constructRoofVal);
			Log.message("Entered constructRoof Value is : " + constructRoofVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the constructRoof : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the constructRoof " + e);
		}
	}
	
	//nextBtn
	public void ClicknextBtn(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, nextBtn.get(0));
			nextBtn.get(0).click();
			Log.message("Clicked the Next Button on Property Details", driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while Click the Next Button on Property Details : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Click the Next Button on Property Details " + e);
		}
	}
	
	//ownershipType
	public void ownershipType(String ownershipTypeVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, ownershipType);
			Select select = new Select(ownershipType);
			select.selectByValue(ownershipTypeVal);
			Log.message("Entered ownershipType Value is : " + ownershipTypeVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the ownershipType : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the ownershipType " + e);
		}
	}
	//occupiedDuringDay
	public void occupiedDuringDay(String occupiedDuringDayVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, occupiedDuringDay);
			Select select = new Select(occupiedDuringDay);
			select.selectByValue(occupiedDuringDayVal);
			Log.message("Entered occupiedDuringDay Value is : " + occupiedDuringDayVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the occupied during day : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the occupiedDuringDay " + e);
		}
	}
	//soleOccupancy
	public void soleOccupancy(String soleOccupancyVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, soleOccupancy);
			Select select = new Select(soleOccupancy);
			select.selectByValue(soleOccupancyVal);
			Log.message("Entered soleOccupancy Value is : " + soleOccupancyVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the sole occupancy : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the soleOccupancy " + e);
		}
	}
	//occupiedDuringNight
	public void occupiedDuringNight(String occupiedDuringNightVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, occupiedDuringNight);
			Select select = new Select(occupiedDuringNight);
			select.selectByValue(occupiedDuringNightVal);
			Log.message("Entered occupiedDuringNight Value is : " + occupiedDuringNightVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while entering the occupied during night : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while entering the occupiedDuringNight " + e);
		}
	}
	
		
	//saveBtnPolicyDetails
	public void ClickSaveBtn(WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, saveBtnPolicyDetails.get(1));
			saveBtnPolicyDetails.get(1).click();
			Log.message("Clicked the Save Button on Property Details", driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while Click the Save Button on Property Details : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Click the Save Button on Property Details " + e);
		}
	}
}
