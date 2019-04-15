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

public class UIRef_PolicyCoreCovers extends LoadableComponent<UIRef_PolicyCoreCovers>{
	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;
	public ElementLayer uielement;
		
//	@FindBy(xpath = "//label[text()='Buildings']//parent::div//following-sibling::custom-check-box/div/input[@type='checkbox'][@name='groupedChildTaken']")
//	WebElement radioBuilding;
//	
//	@FindBy(xpath = "//label[text()='Buildings']//parent::div//parent::div//parent::div//parent::div/div/div/div/custom-check-box/div/input[@name='accidentalDamage']")
//	WebElement radioBuildingAD;
	
	@FindBy(xpath = "//input[@type='checkbox'][@name='groupedChildTaken']")
	List<WebElement> radioBuilding;
	
	@FindBy(xpath = "//input[@type='checkbox'][@name='accidentalDamage']")
	List<WebElement> radioBuildingAD;
	
	
	@FindBy(xpath = "//label[text()='Buildings']//parent::div//parent::div//parent::div//parent::div//select[@name='excessAmount']")
	WebElement radioBuildingExcess;

//	@FindBy(xpath = "//label[text()='Contents']//parent::div//following-sibling::custom-check-box/div/input[@type='checkbox'][@name='groupedChildTaken']")
//	WebElement radioContents;
//
//	@FindBy(xpath = "//label[text()='Contents']//parent::div//parent::div//parent::div//parent::div/div/div/div/custom-check-box/div/input[@name='accidentalDamage']")
//	WebElement radioContentsAD;
	
	@FindBy(xpath = "//input[@type='checkbox'][@name='groupedChildTaken']")
	List<WebElement> radioContents;
	
	@FindBy(xpath = "//input[@type='checkbox'][@name='accidentalDamage']")
	List<WebElement> radioContentsAD;

	@FindBy(xpath = "//label[text()='Contents']//parent::div//parent::div//parent::div//parent::div//select[@name='excessAmount']")
	WebElement radioContentsExcess;
	
	@FindBy(xpath = "//div[@id='split-container']//a[@title='Save']//span[text()='Save']")
	List<WebElement> saveButtonCoreCovers;
		
	public UIRef_PolicyCoreCovers(WebDriver driver, ExtentTest report) {
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
	
	public void SelectBuildingNContentCovers(String BuildingCover, String ContentCover,WebDriver driver, ExtentTest report) throws Exception {
		try {
			//WaitUtils.waitForListElement(driver, radioBuilding,40);
			WaitUtils.uiRefwaitForSpinner(driver);
			WaitUtils.waitForElement(driver, radioBuilding.get(0));
			if(BuildingCover.equalsIgnoreCase("Yes") && ContentCover.equalsIgnoreCase("Yes")){
					if (!radioBuilding.get(0).isSelected() && !radioContents.get(1).isSelected()){
					radioBuilding.get(0).click();
					radioContents.get(1).click();
					Log.message("Selected the Building & Content Cover ", driver, extentedReport);
					}
			}else if(BuildingCover.equalsIgnoreCase("Yes") && !ContentCover.equalsIgnoreCase("Yes")){
					if (!radioBuilding.get(0).isSelected()){
						radioBuilding.get(0).click();
						Log.message("Selected the Building Cover Only", driver, extentedReport);
					}
			}else if(!BuildingCover.equalsIgnoreCase("Yes") && ContentCover.equalsIgnoreCase("Yes")){
				if(!radioContents.get(1).isSelected()){
					radioContents.get(1).click();
					Log.message("Selected the Content Cover Only", driver, extentedReport);
				}
			}else {
				Log.message("No Selection in Building & Content Cover ", driver, extentedReport);
			}
		} catch (Exception e) {
			Log.fail("Error while Selection of Building & Content Cover : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Selection of Building & Content Cover " + e);
		}
	}
	
	public void SelectADCovers(String ExcessCover,WebDriver driver, ExtentTest report) throws Exception {
		try {
			//WaitUtils.waitForListElement(driver, radioBuildingAD,40);
			WaitUtils.waitForElement(driver, radioBuildingAD.get(0));
			if (ExcessCover.equalsIgnoreCase("Yes")){
				if (!radioBuildingAD.get(0).isSelected() && !radioBuildingAD.get(1).isSelected()){
					radioBuildingAD.get(0).click();
					Log.message("Selected the Accidental Cover of Building", driver, extentedReport);
					radioBuildingAD.get(1).click();
					Log.message("Selected the Accidental Cover of Content", driver, extentedReport);
				}
			}else {
					Log.message("No Selection in Accidental Cover of Building & Content", driver, extentedReport);
			}
		} catch (Exception e) {
			Log.fail("Error while Selecting Accidental Cover of Building & Content: " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Selecting Accidental Cover of Building & Content" + e);
		}
	}
	
	public void SelectExcessCovers(String ExcessCoverVal,WebDriver driver, ExtentTest report) throws Exception {
		try {
			WaitUtils.waitForElement(driver, radioBuildingExcess);
			radioBuildingExcess.sendKeys(ExcessCoverVal);
			Log.message("Entered the Excess Cover of Building " + ExcessCoverVal, driver, extentedReport);
			WaitUtils.waitForElement(driver, radioContentsExcess);
			radioContentsExcess.sendKeys(ExcessCoverVal);
			Log.message("Entered the Excess Cover of Content " + ExcessCoverVal, driver, extentedReport);
		} catch (Exception e) {
			Log.fail("Error while selecting Excess Cover of Building & Content: " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while selecting Excess Cover of Building & Content" + e);
		}
	}
	
	public void ClickSaveBtnCoreCovers(WebDriver driver, ExtentTest report) throws Exception{
		try{
			WaitUtils.waitForElement(driver, saveButtonCoreCovers.get(0));
			saveButtonCoreCovers.get(0).click();
			Log.pass("Clicked the Save button on Core Covers ", driver, extentedReport,true);			
		}catch(Exception e){
			Log.fail("Error while Clicking the Save button on Core Covers : " + e.getMessage(), driver, extentedReport,true);
			throw new Exception("Error while Clicking the Save button on Core Covers");
		}
	}

}
