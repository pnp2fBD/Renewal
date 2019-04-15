package cucumber;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseCucumberTest;
import com.ssp.support.CucumberLog;
import com.ssp.support.Log;
import com.ssp.support.WebDriverFactory;
import com.ssp.uxp_pages.CustDashboardPage;
import com.ssp.uxp_pages.HomePage;
import com.ssp.uxp_pages.LoginPage;
import com.ssp.uxp_pages.SearchPage;
import com.ssp.uxp_pages.UXP_BasicMotor.NewQuoteDriverDetailsPage;
import com.ssp.uxp_pages.UXP_BasicMotor.NewQuoteMedicalConditionsPage;
import com.ssp.uxp_pages.UXP_BasicMotor.NewQuoteModificationsPage;
import com.ssp.uxp_pages.UXP_BasicMotor.NewQuotePreviousConvictionsPage;
import com.ssp.uxp_pages.UXP_BasicMotor.NewQuotePreviousLossesPage;
import com.ssp.uxp_pages.UXP_BasicMotor.NewQuoteProposerDetailsPage;
import com.ssp.uxp_pages.UXP_BasicMotor.NewQuoteSecurityPage;
import com.ssp.uxp_pages.UXP_BasicMotor.NewQuoteVehicleDetailsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.stepdefination.runner.TestRunnerNCD;

public class NcdStepDefination {
  
  static WebDriver driver;
  HashMap<String, String> testData;
  HashMap<String, HashMap<String,String>> vehicleData;
  HashMap<String, String> claimData;
  HashMap<Integer,HashMap<String,String>> claimDetails;
  Map<String, HashMap<String, String>> dataMapTest;
  String policyURN;
  String browser;
  String accountName;
  static ExtentTest extentedReport;
  HomePage homePage;
  LoginPage loginPage;
  SearchPage searchPage;
  HashMap<String, String> custDetails;
  private String DateYear;
  private String DateDay;
  private String UI_Rfresh_temp_password = null;
  Iterator<String> itr, itr1, itr2;
  private String username;
  private String password;
  private boolean screenshot = true;
  private String dateOfPolicy;
  private String dateType;
  private int noOfYear;
  private int noOfDays;
  private String title, lastName, firstName,driverName = null;
  
  private CustDashboardPage custdashboardpage;
  private NewQuoteProposerDetailsPage newQuoteProposerDetailsPage;
  private NewQuoteVehicleDetailsPage newQuoteVehicleDetailsPage;
  private NewQuoteSecurityPage newQuoteSecurityPage;
  private NewQuoteModificationsPage newQuoteModificationsPage;
  private NewQuoteDriverDetailsPage newQuoteDriverDetailsPage;
  private NewQuoteMedicalConditionsPage newQuoteMedicalConditionsPage;
  private NewQuotePreviousConvictionsPage newQuotePreviousConvictionsPage;
  private NewQuotePreviousLossesPage newQuotePreviousLossesPage;
  
  public static WebDriver getDriver() {
    return driver;
  }
  
  private void setDriverName(HashMap<String,String>testdata){
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
  }
  
  private void setCredentials(HashMap<String,String> testData) throws Exception {
    this.username = testData.get("UserName");
    this.password = testData.get("Password");
    this.UI_Rfresh_temp_password = testData.get("UI_Refresh_Password");
    if(username.equals("")||password.equals("")||UI_Rfresh_temp_password.equals("")){
      throw new Exception("Please enter value in fields 'UserName', 'Password' & 'UI_Refresh_Password' in testdata sheet");
    }
  }
  
  private void setDateYear(HashMap<String, String> testdata) throws Exception {
    this.DateYear = testdata.get("BackdateYear/FuturedateYear");
    if (DateYear.equals("")) {
      throw new Exception("Please enter value for BackdateYear/FuturedateYear in testdata sheet");
    }
  }
  
  private void setDateDay(HashMap<String, String> testdata) throws Exception {
    this.DateDay = testdata.get("BackdateDay/FuturedateDay");
    if (DateDay.equals("")) {
      throw new Exception("Please enter value for BackdateDay/FuturedateDay in testdata sheet");
    }
  }
  
  
  
  private void setPolicyDate(HashMap<String, String> testdata) throws Exception {
    this.dateOfPolicy = testdata.get("Date of Policy");
    if (dateOfPolicy.equals("")) {
      throw new Exception("Please enter value for 'Date of Policy' in testdata sheet");
    }
  }
  
  private void setPolicyType(HashMap<String, String> testdata) throws Exception{
    switch(dateOfPolicy){
      case "Present":
        dateType = "current";
        noOfDays = 0;
        noOfYear = 0;
        System.out.println("-------------New current date policy to be created------------");
        break;
      case "Backdated":
        dateType = "past";
        setDateDay(testdata);
        setDateYear(testdata);
        noOfDays = Integer.parseInt(DateDay);
        noOfYear = Integer.parseInt(DateYear);
        System.out.println("-------------New back dated policy to be created--------------");
        break;
      case "Futuredated":
        dateType = "future";
        setDateDay(testdata);
        setDateYear(testdata);
        noOfDays = Integer.parseInt(DateDay);
        noOfYear = Integer.parseInt(DateYear);
        System.out.println("-------------New future dated policy to be created------------");
        break;
    }
  }
  
  @Given("^Fetch new test case data for basic motor policy from the \"([^\"]*)\"$")
  public void fetch_new_test_case_data_for_basic_motor_policy_from_the(String arg1) throws Throwable {
    try {
      browser = TestRunnerNCD.browser;
      String className = "NCD_";
      String vehicleClassName = "MotorData_";
      String claimsClassName = "ClaimsData_";
      testData = BaseCucumberTest.getTestData(className, arg1);
      vehicleData = BaseCucumberTest.getTestDataWithStringKeyValue(vehicleClassName);
       claimData = BaseCucumberTest.getTestDataWithoutID(claimsClassName);
      String description = testData.get("Description");
      Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");
      extentedReport = BaseCucumberTest.addTestInfo(arg1, description);
      Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");
      setCredentials(testData);
      setPolicyDate(testData);
      setPolicyType(testData);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }
  
  @When("^Initiate a basic  motor policy quote in engagement centre and enter the vehicle details$")
  public void initiate_a_basic_motor_policy_quote_in_engagement_centre_and_enter_the_vehicle_details() throws Throwable {

    String brandname = testData.get("Brand Name");
    String website_EC = TestRunnerNCD.website_EC;
    driver = WebDriverFactory.get(browser);
    
      try{
        
        // Navigate to Login Page
        loginPage = new LoginPage(driver, website_EC, extentedReport).get();
        Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, screenshot);

        // Login to the application
        homePage = loginPage.loginToSSP(username, password, screenshot, extentedReport);
        Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
            "Login failed", driver, extentedReport, screenshot);
        Log.message("Logged in User id:" + username + "& Password:" + password, driver,
            extentedReport);

        // Click on Take Call link
        homePage.clickTakeCall(extentedReport);
        homePage.clickMyBrands(brandname, extentedReport);
        searchPage = new SearchPage(driver, extentedReport).get();
        Log.softAssertThat(
            searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
            "Search page is verified", "Search Page is not verified", driver, extentedReport, screenshot);

        // Enter Customer Details
        searchPage.clickCreateCustomer(true, extentedReport);
        custDetails = searchPage.enterCustomerDetails(testData, screenshot, extentedReport);

        // Confirm customer details and create customer
        custdashboardpage = searchPage.confirmCreateCustomer(screenshot, extentedReport);
        Log.softAssertThat(
            custdashboardpage.verifyContactName(testData.get("Title") + " "
                + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
                true),
            "Verified FirstName and LastName on Customer Dashboard page",
            "Not Verified Customer Name on Dashboard page", driver, extentedReport, screenshot);

        // Create New Quote
        custdashboardpage.clickNewQuote(true, extentedReport);

        // Select quote details      
        custdashboardpage.enterDate(dateType, noOfDays, noOfYear, extentedReport, screenshot);
        custdashboardpage.selectNewQuoteDetails(testData, extentedReport, screenshot);
        newQuoteProposerDetailsPage = custdashboardpage.clickContinueQuoteMotor(screenshot, extentedReport);
        
        //Enter proposer details
        newQuoteProposerDetailsPage.clickDeclarationAgreed(testData, driver, extentedReport, screenshot);
        newQuoteProposerDetailsPage.selectProposerOccupation(testData, driver, extentedReport, screenshot);
        newQuoteVehicleDetailsPage = newQuoteProposerDetailsPage.clickOnContinue(driver, extentedReport, screenshot);
       
        //Enter vehicle details & validate the same
        newQuoteVehicleDetailsPage.performAutoVehicleSearch(vehicleData, testData, driver, extentedReport, screenshot);
        newQuoteVehicleDetailsPage.validateVehicleDetailsOnSearch(driver, extentedReport, screenshot);
        newQuoteVehicleDetailsPage.enterVehicleDetails(testData, driver, extentedReport, screenshot);
        
      }catch (Exception e) {
        CucumberLog.exception(e, driver, extentedReport);
      }
  }

  @When("^Add ncd details with NCD used \"([^\"]*)\" years$")
  public void add_ncd_details_with_NCD_used_years(String arg1) throws Throwable {
    try{
      newQuoteVehicleDetailsPage.enterNcdFields(arg1, driver, extentedReport, screenshot);
    }catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }
  
  @When("^Add driver details with an option of adding other policy details and navigate to previous losses screen$")
  public void add_driver_details_with_an_option_of_adding_other_polixy_details_and_navigate_to_previous_losses_screen() throws Throwable {
    try{
      
      newQuoteSecurityPage = newQuoteVehicleDetailsPage.clickOnContinue(driver, extentedReport, screenshot);
      
      //Navigate to modifications page
      newQuoteModificationsPage = newQuoteSecurityPage.clickOnContinue(driver, extentedReport, screenshot);  
      newQuoteDriverDetailsPage = newQuoteModificationsPage.clickOnContinue(driver, extentedReport, screenshot);  
      
      //Enter driver details
      setDriverName(custDetails);     
      newQuoteDriverDetailsPage.clickDriverNameSerachCreateDriver(custDetails, driver, extentedReport, screenshot);
      newQuoteDriverDetailsPage.validateDriverName(driverName, driver, extentedReport, screenshot);
      newQuoteDriverDetailsPage.selectMandatoryItems(testData, driver, extentedReport, screenshot);
      
      //Navigate to previous losses page
      newQuoteMedicalConditionsPage = newQuoteDriverDetailsPage.clickOnContinue(driver, extentedReport, screenshot);
      newQuotePreviousConvictionsPage = newQuoteMedicalConditionsPage.clickOnContinue(driver, extentedReport, screenshot);
      newQuotePreviousLossesPage = newQuotePreviousConvictionsPage.clickOnContinue(driver, extentedReport, screenshot);
      
    }catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Add \"([^\"]*)\" previous losses to the policy$")
  public void add_previous_losses_to_the_policy(String arg1) throws Throwable {
    try{
      claimDetails = newQuotePreviousLossesPage.addClaims(testData, claimData, arg1, driver, extentedReport, screenshot);      
      if(!arg1.equals("0") && claimDetails.size()>0){
        boolean status = newQuotePreviousLossesPage.validateClaimCount(arg1, driver, extentedReport, screenshot);
        Log.softAssertThat(status,
            "All the claims have been added successfully on Previous Losses page",
            "The claims have not been added successfully on Previous Losses page", driver,
            extentedReport, screenshot);
      }
    }catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }
  

}
