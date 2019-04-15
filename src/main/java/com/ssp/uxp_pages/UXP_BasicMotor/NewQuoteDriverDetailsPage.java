package com.ssp.uxp_pages.UXP_BasicMotor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.DateTimeUtility;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

public class NewQuoteDriverDetailsPage extends LoadableComponent<NewQuoteDriverDetailsPage>{
  
  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  ExtentTest extentedReport;
  private String employmentStatus;
  private String occupation;


  private void setMandatoryFields(HashMap<String,String> testdata) throws Exception {
    this.occupation = testdata.get("Occupation");
    this.employmentStatus= testdata.get("Employment Status");
    if(occupation.equals("")||employmentStatus.equals("")){
      throw new Exception("Please enter values for fields 'Occupation' and 'Employment Status' in testdata sheet");
    }
  }

  @FindBy(xpath = "//h1[text()='Driver']")
  private WebElement driverHeading;
  
  @FindBy(css = "button[title='Driver Name']")
  private WebElement driverNameBtn;
  
  @FindBy(css = "input[name='C2__MOTOR_SCHEME_1[1].COMPONENTPRD[1].COMPONENTPHD[1].COMPONENTDRV[1].DRIVERNAME[1].NAME_READONLY']")
  private WebElement driverNameTxtbox;
  
  @FindBy(css = "input[name*='CONTACT[1].SEARCHOVERLAY[1].SEARCHFORCONTACT']")
  private WebElement driverSearchLastnameTxtbox;
  
  @FindBy(css = "input[name*='C2__C1__CONTACT[1].SEARCHOVERLAY[1].SEARCHCONTACTSECONDNAME']")
  private WebElement driverSearchFirstnameTxtbox;
  
  @FindBy(css = "button[name='C2__C1____88B187603655E68A FormButton 191']")
  private WebElement driverSearchBtn;
  
  @FindBy(css = "table[summary='SearchResultTable']")
  private WebElement driverSearchTable;
  
  @FindBy(css = "button[title='Create New driver']")
  private WebElement createNewDriverBtn;
  
  @FindBy(xpath = "//h1[text()='You are about to add Driver ']")
  private WebElement addDriverHeading;
  
  @FindBy(css = "span[id='C2__C1__QUE_B6F4C14E4B3241702101909']")
  private WebElement addDriverNameDisplay;
  
  @FindBy(css = "button[title='Save'][name='C2__C1____97B9555F366D250C FormButton 201']")
  private WebElement driverSearchSaveBtn;
  
  @FindBy(css = "select[name*='ABIEMPLOYMENTSTATUS']")
  private WebElement employmentStatusDropdown;
  
  @FindBy(css = "select[name*='ABIOCCUPATIONCODE']")
  private WebElement occupationDropdown;
  
  @FindBy(css = "input[id*='houseOwnership_0']")
  private WebElement houseOwnershipCheckbox;
  
  @FindBy(css = "button[title='Continue']")
  private WebElement continueBtn;
  
  @FindBy(css = "button[title='Back'][id='C2__Back-ComponentPRD_ComponentPHD_ComponentDRV']")
  private WebElement backBtn;
  
  @FindBy(css = "table[summary='SearchResultTable']>tbody>tr>td[headers='C2__C1__p1_QUE_B6F4C14E4B3241702101903'] span")
  private List<WebElement> tableRecordsDriverNameList;
  
  @FindBy(css = "table[summary='SearchResultTable']>tbody>tr>td[headers='C2__C1__p1_BUT_4060ED95011C6B8F1901031'] button[title='Select']")
  private List<WebElement> tableRecordsDriverNameListSelectBtn;

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
    if (isPageLoaded && !driverHeading.isDisplayed()) {
      Log.fail("User is unable to navigate to New Quote Driver page", driver, extentedReport);
      Assert.fail();
    }
    uielement = new ElementLayer(driver);
    
  }
  
  public NewQuoteDriverDetailsPage(WebDriver driver, ExtentTest extentedReport){
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }
  
  /**
   * To perform driver search/create driver and select the driver
   * 
   * @param title
   * @param lastName
   * @param firstName
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void clickDriverNameSerachCreateDriver(HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    String title, lastName, firstName,driverName = null;
    title = testdata.get("Title").trim();
    firstName = testdata.get("First Name").trim();
    lastName = testdata.get("Last Name").trim();
    
    if(!title.equals("") && !firstName.equals("") && !lastName.equals("")){
       driverName = title + " "+firstName+" "+lastName;
    }else if(title.equals("") && !firstName.equals("") && !lastName.equals("")){
       driverName = firstName+" "+lastName;
    }else if(title.equals("") && firstName.equals("") && !lastName.equals("")){
       driverName = lastName;
    }
    
    try{
      Log.event("Entered clickDriverNameSerachCreateDriver method");
      WaitUtils.waitForElementPresent(driver, driverNameBtn, "Driver name button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, driverNameBtn, "Driver name button is not clickable");
      driverNameBtn.click();
      WaitUtils.waitForSpinner(driver);
      
      WaitUtils.waitForElementPresent(driver, driverSearchLastnameTxtbox, "Driver last name text box is not displayed");
      WaitUtils.waitForElementPresent(driver, driverSearchFirstnameTxtbox, "Driver first name text box is not displayed");
      WaitUtils.waitForElementPresent(driver, driverSearchBtn, "Driver search button is not displayed");
      
      driverSearchLastnameTxtbox.clear();
      driverSearchLastnameTxtbox.sendKeys(lastName);
      
      if(!firstName.equals("")){
        driverSearchFirstnameTxtbox.clear();
        driverSearchFirstnameTxtbox.sendKeys(firstName);
      }
       
      WaitUtils.waitForelementToBeClickable(driver, driverSearchBtn, "Driver search button is not clickable");
      driverSearchBtn.click();
      WaitUtils.waitForSpinner(driver);
      
      if(driverSearchTable.isDisplayed()){
        if(tableRecordsDriverNameList.size()>0){    
          for(int i=0;i<tableRecordsDriverNameList.size();i++){
            if(tableRecordsDriverNameList.get(i).getText().trim().equals(driverName)){
              WaitUtils.waitForElementPresent(driver, tableRecordsDriverNameListSelectBtn.get(i), "Select button to select driver is not displayed");
              WaitUtils.waitForelementToBeClickable(driver, tableRecordsDriverNameListSelectBtn.get(i), "Select button to select driver is not clickable");
              tableRecordsDriverNameListSelectBtn.get(i).click();
              WaitUtils.waitForSpinner(driver);
              
              if(!addDriverHeading.isDisplayed()){
                Log.fail("'YOU ARE ABOUT TO ADD DRIVER' heading is not displayed", driver, extentedReport, screenshot);
              }
              if(!addDriverNameDisplay.isDisplayed()){
                Log.fail("Selected name is not displayed on bottom of driver search page", driver, extentedReport, screenshot);
              }else if(!addDriverNameDisplay.getText().trim().equals(driverName)){
                Log.fail("Selected name is not same as expected value driver search page", driver, extentedReport, screenshot);
              }
              WaitUtils.waitForElementPresent(driver, driverSearchSaveBtn, "Save button to save selected driver is not displayed");
              WaitUtils.waitForelementToBeClickable(driver, driverSearchSaveBtn, "Save button to save selected driver is not clickable");
              driverSearchSaveBtn.click();
              WaitUtils.waitForSpinner(driver);
              break;
            }
          }
        }else{
          clickCreateNewDriver(driver, extentedReport, screenshot); 
         }        
      }else
        throw new Exception("Driver search table is not displayed");   
    }catch(Exception e){
      throw new Exception("Unable to perform driver search/click create driver on New Quote Driver page: "+e);
    }     
  }
  
  /**
   * To create a new driver
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void clickCreateNewDriver( WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered createNewDriver method");
      Log.message("The driver record does not exist. Please create a new driver.", driver, extentedReport, screenshot);
      WaitUtils.waitForElementPresent(driver, createNewDriverBtn, "Create new driver button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, createNewDriverBtn, "Create new driver button is not clickable");
      createNewDriverBtn.click();
      WaitUtils.waitForSpinner(driver);    
    }catch(Exception e){
      throw new Exception("Unable to create new driver on New Quote Driver page: "+e);
    }
  } 
  
  /**
   * To validate the selected driver
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void validateDriverName(String name, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    boolean status = true;
    String displayedName;
    try{
      Log.event("Entered validateSelectedDriverName method");
      WaitUtils.waitForElementPresent(driver, driverNameTxtbox, "Name of driver text box is not displayed");
      displayedName = driverNameTxtbox.getAttribute("value");
      if(!displayedName.equals(name)){
        status = false;
      }
      Log.softAssertThat(status, "Driver name displayed on screen is correct", 
          "Driver name displayed on screen is not correct", driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Unable to create new driver on New Quote Driver page: "+e);
    }
  }
  
  /**
   * To select mandatory items 
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public void selectMandatoryItems(HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      setMandatoryFields(testdata);
      WaitUtils.waitForElementPresent(driver, employmentStatusDropdown, "Employment status dropdown is not present");
      GenericUtils.selectValueFromDropdown(employmentStatusDropdown, employmentStatus, driver, extentedReport, screenshot);
      
      WaitUtils.waitForElementPresent(driver, occupationDropdown, "Occupation dropdown is not present");
      GenericUtils.selectValueFromDropdown(occupationDropdown, occupation, driver, extentedReport, screenshot);
      
      WaitUtils.waitForElementPresent(driver,houseOwnershipCheckbox, "House ownership checkbox is not present");
      if(houseOwnershipCheckbox.isSelected()){
        Log.fail("House ownership checkbox is selected by default", driver, extentedReport, screenshot);
      }
      else
        houseOwnershipCheckbox.click();     
    }catch(Exception e){
      throw new Exception("Unable to select mandatory items on New Quote Driver page: "+e);
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
  public NewQuoteMedicalConditionsPage clickOnContinue(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnContinue method");
      WaitUtils.waitForElementPresent(driver, continueBtn, "Continue button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, continueBtn, "Continue button is not clickable");
      continueBtn.click();
      WaitUtils.waitForSpinner(driver);
      return new NewQuoteMedicalConditionsPage(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Unable to click continue on New Quote Driver page: "+e);
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
  public NewQuoteModificationsPage clickOnBack(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnBack method");
      WaitUtils.waitForElementPresent(driver, backBtn, "Back button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, backBtn, "Back button is not clickable");
      backBtn.click();
      WaitUtils.waitForSpinner(driver);
      return new NewQuoteModificationsPage(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Unable to click back on New Quote Driver page: "+e);
    }     
  }
}
