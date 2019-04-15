package com.ssp.uxp_pages.UXP_BasicMotor;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

public class NewQuoteVehicleDetailsPage extends LoadableComponent<NewQuoteVehicleDetailsPage>{

  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  ExtentTest extentedReport;
  private String estimatedValue;
  private String locationVehicleOverNight;
  private String coverType;
  private String vehicleNumber;
  private String make, model, fuelType, transmission, bodyType, engineCC;
  
  private void setVehicleNumber(HashMap<String,String> testdata) throws Exception {
    this.vehicleNumber = testdata.get("Vehicle Number");
    if(vehicleNumber.equals("")){
      throw new Exception("Please enter field value for 'Vehicle Number' in testdata sheet");
    }
  }
  
  private void setVehicleDetails(HashMap<String,HashMap<String,String>> vehicledata) throws Exception {
    HashMap<String,String> detailsData;
      if(vehicledata.isEmpty()){
        throw new Exception("Please enter field value(s) in 'Motor_VehicleDetails' tab of testdata sheet");
      }
      for (Map.Entry<String, HashMap<String, String>> test : vehicledata.entrySet()) {
        if(test.getKey().equals(vehicleNumber)){
          detailsData = test.getValue();
          this.make = detailsData.get("Make");
          this.model = detailsData.get("Model");
          this.fuelType = detailsData.get("Fuel Type");
          this.transmission = detailsData.get("Transmission");
          this.bodyType = detailsData.get("Body Type");
          this.engineCC = detailsData.get("Engine CC");
          break;
        }
      }
  }
    
  private void setVehiclePageFieldData(HashMap<String,String> testdata) throws Exception {
    this.estimatedValue = testdata.get("Estimated Value");
    this.locationVehicleOverNight = testdata.get("Vehicle Overnight Location");
    this.coverType = testdata.get("Cover Type");    
    if(estimatedValue.equals("")||locationVehicleOverNight.equals("")||coverType.equals("")){
      throw new Exception("Please enter field value(s) for 'Estimated Value', 'Vehicle Overnight Location' & 'Cover Type' in testdata sheet");
    }
  }

  @FindBy(xpath = "//h1[text()='Vehicle']")
  private WebElement vehiclesHeading;
  
  @FindBy(css = "input[name*='VEHICLELOOKUP[1].VEHICLEREGISTRATION']")
  private WebElement vehicleRegisNoTxtbox;
  
  @FindBy(css = "button[title*='Find Vehicle']")
  private WebElement findVehicleBtn;
  
  @FindBy(css = "button[title*='Manual Vehicle Lookup']")
  private WebElement manualVehicleLookupBtn;
  
  @FindBy(css = "input[name*='DVLAMANUFACTURER_READONLY']")
  private WebElement vehicleMakeTxtbox;
  
  @FindBy(css = "input[name*='DVLAMODEL_READONLY']")
  private WebElement vehicleModelTxtbox;
  
  @FindBy(css = "input[name*='DVLAFUELTYPE_READONLY']")
  private WebElement vehicleFuelTypeTxtbox;
  
  @FindBy(css = "input[name*='DVLATRANSMISSION_READONLY']")
  private WebElement vehicleTransmissionTxtbox;
  
  @FindBy(css = "input[name*='DVLABODYTYPE_READONLY']")
  private WebElement vehicleBodyTypeTxtbox;
  
  @FindBy(css = "input[name*='DVLAENGINECC_READONLY']")
  private WebElement vehicleEngineCCTxtbox;
  
  @FindBy(css = "input[name*='DVLAENGINECC_READONLY']")
  private WebElement vehicleTypeDropdown;
  
  @FindBy(css = "input[name*='VALUEVEHICLE']")
  private WebElement estimatedValueTxtbox;
  
  @FindBy(css = "select[name*='LOCATIONGARAGED']")
  private WebElement locationKeptOvrntDropdown;
  
  @FindBy(css = "select[name*='COVERTYPE']")
  private WebElement coverTypeDropdown;
  
  @FindBy(css = "select[name*='VEHICLENCD']")
  private WebElement ncdUsedDropdown;
  
  @FindBy(css = "input[name*='VEHICLENCDPROTECTED'][type='checkbox']")
  private WebElement ncdProtectedCheckbox;
  
  @FindBy(css = "button[title='Continue']")
  private WebElement continueBtn;
  
  @FindBy(css = "button[title='Back'][id='C2__Back-ComponentPRD_ComponentPHD_ComponentVEH']")
  private WebElement backBtn;
  
  
  public NewQuoteVehicleDetailsPage(WebDriver driver, ExtentTest extentedReport){
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }
  
  @Override
  protected void isLoaded() {
    if (!isPageLoaded) {
      Assert.fail();
    }
    if (isPageLoaded && !vehiclesHeading.isDisplayed()) {
      Log.fail("User is not navigated to New Quote vehicle details page", driver, extentedReport);
      Assert.fail();
    }
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }
  
  /**
   * To enter vehicle number
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  private void enterVehRegNo(HashMap<String,HashMap<String,String>> vehicledata, HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered enterVehRegNo method");
      setVehicleNumber(testdata);
      setVehicleDetails(vehicledata);
      WaitUtils.waitForElementPresent(driver, vehicleRegisNoTxtbox, "Vehicle registration no. textbox is not present");
      GenericUtils.enterInTextbox(vehicleNumber, vehicleRegisNoTxtbox, driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Unable to enter vehicle registration no. on New Quote Vehicle Details page: "+e);
    }     
  }
  
  /**
   * To click find vehicle button
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  private void clickOnFindVehicle(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnFindVehicle method");
      WaitUtils.waitForElementPresent(driver, findVehicleBtn, "Find Vehicle button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, findVehicleBtn, "Find Vehicle button is not clickable");
      findVehicleBtn.click();
    }catch(Exception e){
      throw new Exception("Unable to click find vehicle on New Quote Vehicle Details page: "+e);
    }     
  }
  
  /**
   * To click manual vehicle lookup button
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void clickOnManualVehicleLookup(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnManualVehicleLookup method");
      WaitUtils.waitForElementPresent(driver, manualVehicleLookupBtn, "Manual Vehicle Lookup button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, manualVehicleLookupBtn, "Manual Vehicle Lookup button is not clickable");
      manualVehicleLookupBtn.click();
    }catch(Exception e){
      throw new Exception("Unable to click manual vehicle lookup on New Quote Vehicle Details page: "+e);
    }     
  }
  
  /**
   * To perform auto vehicle look up
   * 
   * @param vehicledata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void performAutoVehicleSearch(HashMap<String,HashMap<String,String>> vehicledata, HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered performAutoVehicleSearch method");
      enterVehRegNo(vehicledata, testdata, driver, extentedReport, screenshot);
      clickOnFindVehicle(driver, extentedReport, screenshot);
      WaitUtils.waitForSpinner(driver);
    }catch(Exception e){
      throw new Exception("Unable to perform vehicle search on New Quote Vehicle Details page: "+e);
    }  
  }
  
  /**
   * To validate vehicle details
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateVehicleDetailsOnSearch(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      boolean status = true;
      String screenDataPopulated;
      StringBuffer message = new StringBuffer("Data validation failed on- ");
      
      screenDataPopulated = vehicleMakeTxtbox.getAttribute("value");
      if(!screenDataPopulated.equals(make)){
        status = false;
        message.append("Vehicle Make_");
      }
      screenDataPopulated = vehicleModelTxtbox.getAttribute("value");
      if(!screenDataPopulated.equals(model)){
        status = false;
        message.append("Vehicle Model_");
      }
      screenDataPopulated = vehicleFuelTypeTxtbox.getAttribute("value");
      if(!screenDataPopulated.equals(fuelType)){
        status = false;
        message.append("Vehicle FuelType_");
      }
      screenDataPopulated = vehicleTransmissionTxtbox.getAttribute("value");
      if(!screenDataPopulated.equals(transmission)){
        status = false;
        message.append("Vehicle Transmission_");
      }
      screenDataPopulated = vehicleBodyTypeTxtbox.getAttribute("value");
      if(!screenDataPopulated.equals(bodyType)){
        status = false;
        message.append("Vehicle BodyType_");
      }
      screenDataPopulated = vehicleEngineCCTxtbox.getAttribute("value");
      if(!screenDataPopulated.equals(engineCC)){
        status = false;
        message.append("Vehicle EngineCC");
      }
      message.append("Field(s)");
      Log.softAssertThat(status, "Vehicle details validaiton is successful", 
          "Vehicle details validaiton failed. "+message, driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Unable to validate vehicle details on New Quote Vehicle details page: "+e);
    }
  }
  
  /**
   * To select vehicle type
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void selectVehicleType(HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered selectVehicleType method");
      String type = testdata.get("Vehicle Type");
      WaitUtils.waitForElementPresent(driver, vehicleTypeDropdown, "Vehicle type dropdown  is not present");
      GenericUtils.selectValueFromDropdown(vehicleTypeDropdown, type, driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Unable to select the value of vehicle type on New Quote Vehicle details page: "+e);
    }     
  }
  
  /**
   * To enter vehicle details
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  private void enterVehicleFields(HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered enterVehicleFields method");
      
      WaitUtils.waitForElementPresent(driver, estimatedValueTxtbox, "Estimated value textbox is not present");
      GenericUtils.enterInTextbox(estimatedValue, estimatedValueTxtbox, driver, extentedReport, screenshot);
      
      WaitUtils.waitForElementPresent(driver, locationKeptOvrntDropdown, "Location of vehicle kept overnight dropdown is not present");
      GenericUtils.selectValueFromDropdown(locationKeptOvrntDropdown, locationVehicleOverNight, driver, extentedReport, screenshot);
      
      WaitUtils.waitForElementPresent(driver, coverTypeDropdown, "Cover type dropdown is not present");
      GenericUtils.selectValueFromDropdown(coverTypeDropdown, coverType, driver, extentedReport, screenshot); 
    }catch(Exception e){
      throw new Exception("Unable to enter vehicle fields on New Quote Vehicle details page: "+e);
    }     
  }
  
  /**
   * To enter NCD details
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void enterNcdFields(String ncdUsed, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered enterNcdFields method");
 
      WaitUtils.waitForElementPresent(driver, ncdUsedDropdown, "Cover type dropdown is not present");
      GenericUtils.selectValueFromDropdown(ncdUsedDropdown, ncdUsed, driver, extentedReport, screenshot); 
      
      WaitUtils.waitForElementPresent(driver, ncdProtectedCheckbox, "NCD protected checkbox is not present");
      if(ncdProtectedCheckbox.isSelected()){
       Log.fail("NCD protected checkbox is check by default", driver, extentedReport, screenshot); 
      }else
        ncdProtectedCheckbox.click();
    }catch(Exception e){
      throw new Exception("Unable to enter ncd fields on New Quote Vehicle details page: "+e);
    }     
  }
  
  /**
   * To enter vehicle and ncd details
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void enterVehicleDetails(HashMap<String, String> testdata, WebDriver driver,
      ExtentTest extentedReport, boolean screenshot) throws Exception {
    try {
      Log.event("Entered enterVehicleAndNcdDetails method");
      setVehiclePageFieldData(testdata);
      enterVehicleFields(testdata, driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception(
          "Unable to enter vehicle and ncd details on New Quote Vehicle details page: " + e);
    }
  }
  
  /**
   * Click on continue
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public NewQuoteSecurityPage clickOnContinue(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnContinue method");
      WaitUtils.waitForElementPresent(driver, continueBtn, "Continue button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, continueBtn, "Continue button is not clickable");
      continueBtn.click();
      return new NewQuoteSecurityPage(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Unable to click continue on New Quote Vehicle details page: "+e);
    }     
  }
  
  /**
   * Click on back
   * 
   * @param testdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public NewQuoteProposerDetailsPage clickOnBack(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnBack method");
      WaitUtils.waitForElementPresent(driver, backBtn, "Back button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, backBtn, "Back button is not clickable");
      backBtn.click();
      return new NewQuoteProposerDetailsPage(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Unable to click back on New Quote Vehicle details page: "+e);
    }     
  }
  
}