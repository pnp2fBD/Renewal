package cucumber.stepdefination.runner;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseCucumberTest;
import com.ssp.support.CucumberLog;
import com.ssp.support.Log;
import com.ssp.support.WebDriverFactory;
import com.ssp.uirefresh_pages.UIRefresh_Dashboard;
import com.ssp.uirefresh_pages.UIRefresh_Login;
import com.ssp.uirefresh_pages.UIRefresh_Manage_contacts;
import com.ssp.uirefresh_pages.UIRefresh_System_User_Role;
import com.ssp.uirefresh_pages.UIRefrsh_EditPersonalContact;
import com.ssp.uxp_SSPages.CardDetailsPageSS;
import com.ssp.uxp_SSPages.ConfirmationPage;
import com.ssp.uxp_SSPages.ContactDetailsPage;
import com.ssp.uxp_SSPages.CustomerSelfService;
import com.ssp.uxp_SSPages.CustomerSigninPage;
import com.ssp.uxp_SSPages.PaymentPage;
import com.ssp.uxp_SSPages.SelfServiceCustomerDashboard;
import com.ssp.uxp_SSPages.YourPolicyDetailsBuildContCover;
import com.ssp.uxp_SSPages.YourPolicyDetailsContactCover;
import com.ssp.uxp_SSPages.YourPolicyDetailsGardenCover;
import com.ssp.uxp_SSPages.YourPolicyDetailsHomeEmergency;
import com.ssp.uxp_SSPages.YourPolicyDetailsLegalExpense;
import com.ssp.uxp_SSPages.YourPolicyDetailsProperty;
import com.ssp.uxp_SSPages.YourPolicyDetailsTechnologyCover;
import com.ssp.uxp_SSPages.YourQuotePageFromPP;
import com.ssp.uxp_pages.CardDetailsPage;
import com.ssp.uxp_pages.CustDashboardPage;
import com.ssp.uxp_pages.GetQuotePage;
import com.ssp.uxp_pages.HomePage;
import com.ssp.uxp_pages.LoginPage;
import com.ssp.uxp_pages.NewQuotePage;
import com.ssp.uxp_pages.SearchPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.stepdefination.runner.TestRunnerRenewals;


public class RenewalStepDefination {
  static WebDriver driver;
  HashMap<String, String> testData;
  Map<String, HashMap<String, String>> dataMapTest;
  String policyURN;
  String browser;
  String accountName;
  static ExtentTest extentedReport;
  HomePage homePage;
  LoginPage loginPage;
  SearchPage searchPage;
  CustomerSigninPage ss_loginPage;
  CustDashboardPage custdashboardpage;
  NewQuotePage newquotepage;
  CardDetailsPage carddetailspage;
  SelfServiceCustomerDashboard custDashboard;
  YourQuotePageFromPP yourQuotePageFromPP;
  YourPolicyDetailsContactCover yourPolicyDetailsContactCover;
  YourPolicyDetailsProperty yourPolicyDetailsProperty;
  YourPolicyDetailsBuildContCover yourPolicyDetailsBuildContCover;
  YourPolicyDetailsGardenCover yourPolicyDetailsGardenCover;
  YourPolicyDetailsTechnologyCover yourPolicyDetailsTechnologyCover;
  YourPolicyDetailsHomeEmergency yourPolicyDetailsHomeEmergency;
  YourPolicyDetailsLegalExpense yourPolicyDetailsLegalExpense;
  ContactDetailsPage contactDetailsPage;
  PaymentPage paymentPage;
  CardDetailsPageSS cardDetailsPageSS;
  ConfirmationPage confirmationPage;
  HashMap<String, String> custDetails;
  UIRefresh_Login uiRefreshlogin;
  UIRefresh_Dashboard uiRefreshdashboard;
  UIRefresh_Manage_contacts manageContacts;
  UIRefrsh_EditPersonalContact editPersonalContact;
  UIRefresh_System_User_Role userrole;
  UIRefrsh_EditPersonalContact editPersonalContact_complete;
  HashMap<String, String> policyDetails;
  CustomerSelfService cusSelfservice;
  GetQuotePage getquotepage;
  private String tcID;
  private static int unsucessfulPymtCounter = 0;
  private String backDateYear;
  private String newPaymentPlan;
  private String newPaymentMethod;
  private String auditsList;
  private int tableTCSize = 2;
  private String tcType;
  private int afterTCAttachTablesize = 2;
  private String user_Email = null;
  private String UI_Rfresh_temp_password = null;
  private String Renewal_Quote;
  private String Renewal_Premium;
  private String startdate;
  private String endDate;
  private String homeEmergencyCoverAction;
  private String legalExpensesCoverAction;
  Iterator<String> itr, itr1, itr2;
  private static FileInputStream fis;
  private boolean screenshot = true;
  private String addOnCoversEC;
  private String addOnCoversSS;
  private String existTCAdd;
  private String website_UIRefresh;
  private String username;
  private String password;
  private boolean changePayment = false;

  /*
   * multiple policy counter
   * 
   * @variable policyCounter
   */
  private int policyCounter = 0;
  public String notificationCallCenter =
      "It is no longer possible to renew your Household Standard policy online. Please contact our Call Centre to renew your policy.";

  private Properties testDataSS = new Properties();

  public static ExtentTest getExtentedReport() {
    return extentedReport;
  }

  public static WebDriver getDriver() {
    return driver;
  }

  private void setAuditsList(HashMap<String, String> testData) throws Exception {
    this.auditsList = testData.get("AuditsToVerify");
    if (auditsList.equals("")) {
      throw new Exception(
          "Please enter the audits with comma (',') splitter in the testdata sheet");
    }
  }
  
  private void setTestCaseID(HashMap<String, String> testData) throws Exception {
    this.tcID = testData.get("TC_ID");
    if (tcID.equals("")) {
      throw new Exception(
          "Please enter the field value for 'TC_ID' in the testdata sheet");
    }
  }
  
  private void setTCType(HashMap<String, String> testData) throws Exception {
    this.tcType = testData.get("TCType");
    if (tcType.equals("")) {
      throw new Exception(
          "Please enter the TCType field in the testdata sheet");
    }
  }
  
  private void setNewPaymentType(HashMap<String, String> testData) throws Exception {
    this.newPaymentMethod = testData.get("NewPaymentMethod");
    this.newPaymentPlan = testData.get("NewPaymentPlan");
    if (newPaymentMethod.equals("")||newPaymentPlan.equals("")) {
      throw new Exception(
          "Please enter the 'NewPaymentPlan' & 'NewPaymentMethod' field(s) in the testdata sheet");
    }
  }
 
  private void setExistTCAdd(HashMap<String,String> testData) throws Exception {
    this.existTCAdd = testData.get("Terms and Conditions_Add");
    if(existTCAdd.equals("")){
      throw new Exception("Please enter value in field 'Terms and Conditions_Add' in testdata sheet");
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

  @Given("^Fetch the test case data from the \"([^\"]*)\"$")
  public void fetch_the_test_case_data_from_the_TC_ID(String arg1) throws Exception {
    System.out.println("Starting the testcase");
    try {
      browser = TestRunnerRenewals.browser;
      String className = "SelfServiceRenewal298_";
      testData = BaseCucumberTest.getTestData(className, arg1);
      String description = testData.get("Description");
      Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");
      extentedReport = BaseCucumberTest.addTestInfo(arg1, description);
      Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");
      fis = new FileInputStream(
          System.getProperty("user.dir") + "\\src\\main\\resources\\testdataSS.properties");
      testDataSS.load(fis);
      setBackDateYear(testData);
      setPolicyCounter(testData);
      setCredentials(testData);
      setTestCaseID(testData);
    } catch (Exception e) {
      CucumberLog.endTestCase(extentedReport);
    }
  }

  @When("^Create a renewal policy in EC and Review the policy in EC$")
  public void create_a_renewal_policy_in_EC() throws Exception {
    // Write code here that turns the phrase above into concrete actions
    String userName = testData.get("UserName");
    String password = testData.get("Password");
    // String description = testData.get("Description");

    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    driver = WebDriverFactory.get(browser);
    String website_EC = TestRunnerRenewals.website_EC;
    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);
      // Create New Quote
      custdashboardpage.clickNewQuote(true, extentedReport);
      // Past date
      custdashboardpage.enterNewQuotePastDate(5, testData, extentedReport, true);
      custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
      NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

      // Create New Quote
      Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
          "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
      newquotepage.enterPolicyDetails(testData, true, extentedReport);
      newquotepage.clickNextOne(extentedReport);
      newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
      newquotepage.clickNextTwo(extentedReport);
      newquotepage.clickView(extentedReport);
      newquotepage.clickAgree(extentedReport);

      // Get and Buy Quote
      newquotepage.clickGetQuote(extentedReport);
      newquotepage.clickBuy(extentedReport);

      newquotepage.selectPayment(testData, true, extentedReport);

      newquotepage.takePayment(extentedReport);
      Thread.sleep(20000);// To be deleted
      CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

      // Enter Card Details
      carddetailspage.enterCardNumber(testData, extentedReport, false);
      carddetailspage.selectExpiry(testData, extentedReport, false);
      carddetailspage.enterVerification(extentedReport, false);
      carddetailspage.enterName(testData, extentedReport, true);
      carddetailspage.clickbuy(extentedReport, true);
      Log.message("Entered Card Details and Navigated to Verificaion page", driver, extentedReport,
          true);

      // confirm payment
      custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);
      Log.softAssertThat(
          custdashboardpage.uielement.verifyPageElements(Arrays.asList("lnkEditDetails"),
              custdashboardpage),
          "Navigated to Customer Dashboard page", "Not navigated to Customer Dashboard page",
          driver, extentedReport, true);

      // check for policy status
      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
      startdate = policyDetails.get("InceptionDate");
      endDate = policyDetails.get("ExpiryDate");

      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);



        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");
        Renewal_Quote = policyDetails.get("PolicyNo");
        custdashboardpage.clickComplete(extentedReport);
        Log.softAssertThat(homePage.verifyHomePage(),
            "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
            "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
            driver, extentedReport, true);

        // Click on Take Call link
        homePage.clickTakeCall(extentedReport);
        homePage.clickMyBrands(brandname, extentedReport);
        // Search with Valid Policy Number
        searchPage.searchValidPolicy(policyDetails.get("PolicyNo"), true, extentedReport);
        CustDashboardPage custdashboardPage =
            searchPage.selectPolicy_from_SearchPage(true, extentedReport);
        Log.message("Navigated to Cutomer Dashboard after selecting policy from search page",
            driver, extentedReport, true);

        // Verifying Customer Details
        custdashboardPage.clickPassVerification(extentedReport, true);
        custdashboardPage.verifyCustomerDashboardPage();
        Log.softAssertThat(
            custdashboardPage.verifyContactName(
                title + " " + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
                extentedReport, true),
            "Verified FirstName and LastName on Customer Dashboard : "
                + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
            "Not Verified Customer Name on Dashboard", driver, extentedReport, true);
        // invite Renewal

        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);
        // custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);

        // #C2__C6__navdrop > div > div > ul >
        // li.dropdown.renewal-quote.drop-inline.dropdown-hover.open > ul > li:nth-child(2) > a
        // newquotepage.clickQuoteSave("Renew", "Renewal Quote", extentedReport);
        newquotepage.clickBuyMTA(extentedReport, true);
        // custdashboardpage.selecteRenewalVaration(extentedReport);
        newquotepage.generateRenewVariation("Renewal Quote two");


      }
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    /*
     * finally { CucumberLog.endTestCase(extentedReport); driver.quit(); }
     */
  }



  @When("^Create a username and password for SS user in UIRefresh$")
  public void create_a_username_and_password_for_SS_user_in_UIRefresh() throws Throwable {
    
    website_UIRefresh = TestRunnerRenewals.website_UIRefresh;
    try {
      uiRefreshlogin = new UIRefresh_Login(driver, website_UIRefresh, extentedReport).get();
      Log.pass("Successfully opened UI Refresh: " + website_UIRefresh, driver, extentedReport, screenshot);

      uiRefreshdashboard = uiRefreshlogin.loginToUIRefresh(username, password, screenshot, extentedReport);
      uiRefreshdashboard.clickOnContact(extentedReport, screenshot);
      manageContacts = uiRefreshdashboard.clickOnManageContact(extentedReport, screenshot);
      manageContacts.enterName(custDetails.get("Last Name"),screenshot);
      manageContacts.clickBtnSearch(extentedReport, driver,screenshot);
      editPersonalContact = manageContacts.clickBtnEdit(extentedReport, driver, screenshot);

      editPersonalContact.clickBtnRole(extentedReport, driver, screenshot);
      userrole = editPersonalContact.clickBtnEdit(extentedReport, driver, screenshot);

      user_Email = custDetails.get("Last Name") + "@emailssp.com";
      userrole.enter_PrincipalID(custDetails.get("Last Name") + "@emailssp.com3",screenshot);
      userrole.tick_user_Must_change_Checkbox(extentedReport, screenshot);
      userrole.tickPasswordResetCheckbox(extentedReport,screenshot);
      userrole.enter_TemporaryPassword(UI_Rfresh_temp_password, screenshot);

      editPersonalContact = userrole.clickBtnsave(extentedReport, driver, screenshot);
      manageContacts = editPersonalContact.clickBtnCompletetab(extentedReport, driver, screenshot);
      uiRefreshdashboard = manageContacts.clickBtnComplete(extentedReport, driver, screenshot);

      uiRefreshdashboard.logout(extentedReport, screenshot);

      Log.pass(
          "Sucessfully updated the Principal ID and PassWord of Contact : " + website_UIRefresh,
          driver, extentedReport, true);
    }

    catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
    }
  }

  @Then("^Login to SS Successfully$")
  public void login_to_SS_Successfully() throws Throwable {
    
    String website_SS = TestRunnerRenewals.website_SS;
    // driver = WebDriverFactory.get(browser);
    try {
      cusSelfservice = new CustomerSelfService(driver, website_SS, extentedReport);
      cusSelfservice.get();
      Log.pass("Customer Self Service page is displayed : " + website_SS, driver, extentedReport,
          true);
      cusSelfservice.clickBtnSignIn(extentedReport, driver);
      Log.pass("Sign in button displayed and  " + website_SS, driver, extentedReport, true);
      CustomerSigninPage ss_loginPage = new CustomerSigninPage(driver, extentedReport);
      custDashboard = ss_loginPage.loginToSSPCustomerSelfService(user_Email,
          UI_Rfresh_temp_password, extentedReport, false);
      Log.pass("CustomerDashboard Page is displayed  : " + website_SS, driver, extentedReport,
          true);
      Log.softAssertThat(true, "Successfully logged into SelfService", "Login failed", driver,
          extentedReport, true);
      Log.message("Logged in User id:" + user_Email + "& Password:" + UI_Rfresh_temp_password, driver,
          extentedReport);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Open the policy in SS which was created in EC$")
  public void open_the_policy_in_SS_which_was_created_in_EC() throws Exception {

    driver = WebDriverFactory.get(browser);

    String description = testData.get("Description");
    String user_Email = testData.get("SS_username");
    String password_SS = testData.get("SS_Password");
    Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");


    Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");

    // Get the web driver instance
    // String web = "index.html?domain=core.siriusfs.com";
    String website_Self = TestRunnerRenewals.website_SS;
    String website_SS = website_Self; // + web;

    try {

      System.out.println("website -------" + website_SS);
      // driver.get(website_SS);

      cusSelfservice = new CustomerSelfService(driver, website_SS, extentedReport);

      cusSelfservice.get();
      Log.pass("Customer Self Service page is displayed : " + website_SS, driver, extentedReport,
          true);
      cusSelfservice.clickBtnSignIn(extentedReport, driver);

      Log.pass("Sign in button displayed and  " + website_SS, driver, extentedReport, true);

      ss_loginPage = new CustomerSigninPage(driver, extentedReport);

      custDashboard = ss_loginPage.loginToSSPCustomerSelfService(user_Email, password_SS,
          extentedReport, false);

      Log.pass("CustomerDashboard Page is displayed  : " + website_SS, driver, extentedReport,
          true);

      Log.softAssertThat(true, "Successfully logged into SelfService", "Login failed", driver,
          extentedReport, true);
      Log.message("Logged in User id:" + user_Email + "& Password:" + password_SS, driver,
          extentedReport);


    }

    catch (Exception e) {
      Log.exception(e, driver, extentedReport);
    } finally {
      // Log.endTestCase(extentedReport);
      // driver.quit();
    }
  }



  @Then("^previous renewal quote premiumshould appear$")
  public void previous_renewal_quote_premiumshould_appear() throws Exception {
    try {


      System.out.println("previous renewal quote premiumshould appear");



    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      Log.endTestCase(extentedReport);
      driver.quit();
    }
  }


  @Then("^start date and end date should appear$")
  public void start_date_and_end_date_should_appear() throws Exception {
    try {
      System.out.println("start date and end date should appear");
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
    }
  }

  @Then("^Countdown message should appear$")
  public void countdown_message_should_appear() throws Exception {
    try {
      System.out.println("Countdown message should appear");
      boolean flag = custDashboard.Verify_Renewal_CoundownNotification(
          "Your Household Standard Policy is due for Renewal in 1 days.");
      if (flag) {
        Log.pass("String Matched");
      } else {
        throw new Exception(" countdown Message Mismatch");

      }
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
    }
  }

  @Then("^Renew my policy button should appear$")
  public void renew_my_policy_button_should_appear() throws Exception {
    try {
      System.out.println("Countdown message should appear");
      if (custDashboard.Verify_Renew_my_policybutton_isdisplayed_and_isenabled(driver,
          extentedReport)) {
        Log.pass("Countdown message appeared");
      } else {
        throw new Exception("Renew My policy button Not Present");
      }
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
    }
  }

  @When("^Click on Renew my policy button$")
  public void click_on_Renew_my_policy_button() throws Exception {
    try {
      yourQuotePageFromPP = custDashboard.clickRenewMyPolicy("743", extentedReport, screenshot);
      Log.pass("Clicked on Renew my Policy button");
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
    }

  }


  /*
   * @Then("^Validate all the three types of Payments options on Price Presentation screen$") public
   * void validate_all_the_three_types_of_Payments_options_on_Price_Presentation_screen() throws
   * Exception { try { if (yourQuotePageFromPP.validateAnnualPremiumamount(extentedReport)) {
   * Log.pass("Annual Premium Details are appearing correct"); } else
   * Log.fail("Annual Premium Details are not appearing correct", driver, extentedReport, true); if
   * (yourQuotePageFromPP.validateMonthlyPremiumamount(extentedReport)) {
   * Log.pass("Monthly Premium Details are appearing correct"); } else
   * Log.fail("Monthly Premium Details are not appearing correct", driver, extentedReport, true);
   * 
   * if (yourQuotePageFromPP.fortnightlyPremiumamount(extentedReport)) {
   * Log.pass("Monthly Premium Details are appearing correct"); } else
   * Log.fail("Fortnightly Premium Details are not appearing correct", driver, extentedReport,
   * true); } catch (Exception e) { Log.exception(e, driver, extentedReport);
   * CucumberLog.endTestCase(extentedReport); } finally { CucumberLog.endTestCase(extentedReport);
   * driver.quit(); } }
   */

  /*
   * @Then("^Validate the data of Building and Content Coverage type$") public void
   * validate_the_data_of_Building_and_Content_Coverage_type() throws Exception { try { if
   * (yourQuotePageFromPP.validateBuildingData(extentedReport)) {
   * Log.pass("Building data is appearing correctly on Price Presenation screen"); } else
   * Log.fail("Building data is not appearing correctly on Price Presenation screen", driver,
   * extentedReport, true); if (yourQuotePageFromPP.validateContentData(extentedReport)) {
   * Log.pass("Content data is appearing correctly on Price Presenation screen"); } else
   * Log.fail("Content data is not appearing correctly on Price Presenation screen", driver,
   * extentedReport, true); } catch (Exception e) { Log.exception(e, driver, extentedReport);
   * CucumberLog.endTestCase(extentedReport); } finally { CucumberLog.endTestCase(extentedReport);
   * driver.quit(); } }
   */


  /*
   * @Then("^Validate the Add ons options are available on Price Presenation screen$") public void
   * validate_the_Add_ons_options_are_available_on_Price_Presenation_screen() throws Exception { try
   * { if (yourQuotePageFromPP.validateAddons(extentedReport)) {
   * Log.pass("Add ons are appearing correctly on Price Presenation screen"); } else
   * Log.fail("Add ons are not appearing correctly on Price Presenation screen", driver,
   * extentedReport, true); } catch (Exception e) { Log.exception(e, driver, extentedReport);
   * CucumberLog.endTestCase(extentedReport); }
   * 
   * }
   */

  /*
   * @Then("^Validate the Terms and Conditions section is available on Price Presenation screen$")
   * public void
   * validate_the_Terms_and_Conditions_section_is_available_on_Price_Presenation_screen() throws
   * Exception { try { if (yourQuotePageFromPP.verifyTermsAndConditionSection(extentedReport)) {
   * Log.pass("Terms and Conditions are appearing correctly on Price Presenation screen"); } else
   * Log.fail("Terms and Conditions are not appearing correctly on Price Presenation screen",
   * driver, extentedReport, true); } catch (Exception e) { Log.exception(e, driver,
   * extentedReport); CucumberLog.endTestCase(extentedReport); } }
   */

  /*
   * @Then("^Validate that the user can only View Terms and Conditions$") public void
   * validate_that_the_user_can_only_View_Terms_and_Conditions() throws Exception { try { if
   * (yourQuotePageFromPP.viewTermsAndCondition(extentedReport)) {
   * Log.pass("User is able to View Terms and Conditions"); } else
   * Log.fail("User is not able to View Terms and Conditions", driver, extentedReport, true); }
   * catch (Exception e) { Log.exception(e, driver, extentedReport);
   * CucumberLog.endTestCase(extentedReport); } }
   */

  ///// Sprint 6 needs to be updated for above scenarios

  @Then("^Validate the message Your policy is due for renewal is displayed$")
  public void validate_the_message_Your_policy_is_due_for_renewal_is_displayed() throws Exception {
    try {

      boolean flag = custDashboard
          .Verify_Renewal_CoundownNotification("Some of your policies are due for renewal");
      if (flag) {
        Log.pass("Notification Message Appear");
      } else {
        throw new Exception(" Notification Mismatch");

      }



    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }
  }



  @Then("^Validate View document button should display$")
  public void validate_View_document_button_should_display() throws Exception {
    try {



      System.out.println("View Document button");



      if (custDashboard.Verify_ViewDocumentbutton()) {
        Log.pass("View Document button displayed");
      } else {
        throw new Exception("View Document button is not displayed ");

      }



    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }
  }

  @Then("^Validate Hide document button and Renewal document section should display$")
  public void validate_Hide_document_button_and_Renewal_document_section_should_display()
      throws Throwable {

    try {

      System.out.println("View Document button");



      if (custDashboard.Verify_HideDocumentbutton()) {
        Log.pass("Hide Document button is displayed");
      } else {
        throw new Exception("Hide Document button is not displayed ");

      }



    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }
  }



  /*
   * @Then("^Logout from SS$") public void logout_from_SS() throws Exception { try {
   * yourQuotePageFromPP.signoutPricePresenation(extentedReport); System.out.println("log out"); }
   * catch (Exception e) { Log.exception(e, driver, extentedReport);
   * CucumberLog.endTestCase(extentedReport);
   * 
   * }
   * 
   * finally { driver.quit(); CucumberLog.endTestCase(extentedReport); } }
   */



  @Then("^Validate  View Hide button is displayed  and the Renewal Document section  is displayed$")
  public void validate_renewal_document_and_the_Renewal_Document_section_is_displayed()
      throws Throwable {

    try {

      System.out.println("View Document button");



      if (custDashboard.Verify_HideDocumentbutton()) {
        Log.pass("Hide Document button is displayed");
      } else {
        throw new Exception("Hide Document button is not displayed ");

      }



    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }
  }



  @Then("^Validate  View document button is displayed  and the Renewal Document section  is not displayed$")
  public void validate_Hide_renewal_document_and_the_Renewal_Document_section_is_displayed()
      throws Throwable {
    try {
      System.out.println("Hide document button clicking ");
      if (custDashboard.ClickhideRenewalDocument()) {
        Log.pass("View  Document button is displayed");
      } else {
        throw new Exception("Hide Document button is not displayed ");
      }
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
    } finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }
  }

  @Then("^Validate that Renewal policy document is appearing$")
  public void validate_that_Renewal_policy_document_is_appearing() throws Throwable {
    try {
      if (custDashboard.validateRenewalDocument(driver, extentedReport)) {
        Log.pass("Documents are appearing on policy", driver, extentedReport, true);
      } else {
        Log.fail("Number of documents generated are more/less than expected", driver,
            extentedReport, true);
      }
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
      e.printStackTrace();
    }
  }

  @Then("^Validate multiple policy is displayed$")
  public void validate_multiple_policy_is_displayed() throws Exception {
    boolean validation = false;
    int counter = 0;
    StringBuffer message = new StringBuffer("Policy Details validation failed on policy URN- ");
    try {
      for (Map.Entry<String, HashMap<String, String>> test : dataMapTest.entrySet()) {
        validation = custDashboard.validateRenewalPolicySection(test, screenshot);
        if (validation == true) {
          Log.pass("Policy details validated sucessfully for policy no - " + test.getKey(), driver,
              extentedReport, screenshot);
          counter++;
        } else {
          Log.fail("Validation failed for policy no - " + test.getKey(), driver, extentedReport,
              screenshot);
          message.append(test.getKey());
        }
      }
      if (counter == dataMapTest.size()) {
        Log.pass("Validation sucessful for all the policies", driver, extentedReport, screenshot);
      } else {
        Log.fail("Validation failed for multiple policy" + message, driver, extentedReport,
            screenshot);
      }

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }


  @Then("^Validate shop Around message is displayed for a ssc user$")
  public void validate_shop_Around_message_is_displayed_for_a_ssc_user() throws Throwable {

    String ShopAround_Message =
        "Please make sure you are happy with the renewal price, cover and terms before purchasing your policy.";
    try {
      if (custDashboard.validate_shopAround_Notification(ShopAround_Message)) {

        Log.pass(ShopAround_Message + "----- is matched");
      } else {
        throw new Exception(ShopAround_Message + "----------mis-matched");

      }


    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }
  }



  @Then("^Validate Notifivation message related to Auto renew is displayed$")
  public void validate_Notifivation_message_related_to_Auto_renew_is_displayed() throws Throwable {

    String SetToAUTORENEW =
        "Your policy is due to renew automatically. It is recommended that you review the details of your policy renewal to ensure that all details are correct and up to date.";

    try {
      if (custDashboard.validate_AutoRenew_Notification(SetToAUTORENEW)) {

        Log.pass(SetToAUTORENEW + "----- is matched");

      }



    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }

  }



  @Then("^Validate notification message when Auto Renew is uncheck at the time of policy creation$")
  public void validate_notification_message_when_Auto_Renew_is_uncheck_at_the_time_of_policy_creation()
      throws Throwable {

    String SetToNoTAutoRenew =
        "Your Policy is not due to renew automatically. Please click Renew My Policy before the renewal start date to avoid your policy expiring.";

    try {
      if (custDashboard.validate_notAutoRenew_Notification(SetToNoTAutoRenew)) {

        Log.pass(SetToNoTAutoRenew + "----- is matched");

      }


      else {
        throw new Exception("Message Not displayed or matched");

      }


    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }

  }



  @Then("^verify Start Date$")
  public void verify_Start_Date() throws Throwable {

    startdate = "08/10/2018";


    try {

      if (custDashboard.VerifyPolicyRenewalStartDate(startdate)) {

        Log.pass(startdate + "----- is matched");

      } else {
        Log.message(startdate + "----- is matched");
      }

    }

    catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }

  }

  @Then("^End Date$")
  public void end_Date() throws Throwable {
    // Write code here that turns the phrase above into concrete actions

    endDate = "07/10/2019";


    try {
      if (custDashboard.VerifyPolicyRenewalEndDate(endDate)) {

        Log.pass(startdate + "----- is matched");

      } else {
        Log.message(startdate + "----- is matched");
      }


    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }

  }

  @Then("^Renewal Premium$")
  public void current_Premium() throws Throwable {

    Renewal_Premium = "464.49";
    try {
      if (custDashboard.Verify_Policy_Renewal_Premium(Renewal_Premium)) {

        Log.pass(Renewal_Premium + "----- is matched");

      } else {
        Log.message(Renewal_Premium + "----- is matched");
      }

    }

    catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }

  }

  @Then("^Renewal Quote$")
  public void renewal_Quote() throws Throwable {
    Renewal_Quote = "396";
    try {

      if (custDashboard.verifyRenewalPolicyNumber(Renewal_Quote)) {

        Log.pass(Renewal_Quote + "----- is matched");

      } else {
        Log.message(Renewal_Quote + "----- is matched");
      }

    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }

  }


  @Then("^Previous Premium$")
  public void previous_Premium() throws Throwable {
    try {

    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }


  }

  @When("^Create a renewal policy in EC and Uncheck Auto Renew checkbox and Review the policy in EC$")
  public void create_a_renewal_policy_in_EC_and_Uncheck_Auto_Renew_checkbox_and_Review_the_policy_in_EC()
      throws Throwable {



    String userName = testData.get("UserName");
    String password = testData.get("Password");
    // String description = testData.get("Description");

    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    String MTAcreationReason = testData.get("MTAdjReason");
    driver = WebDriverFactory.get(browser);

    String website_EC = TestRunnerRenewals.website_EC;


    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);
      // Create New Quote
      custdashboardpage.clickNewQuote(true, extentedReport);
      // Past date
      custdashboardpage.enterNewQuotePastDate(5, testData, extentedReport, true);
      custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
      NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

      // Create New Quote
      Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
          "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
      newquotepage.enterPolicyDetails(testData, true, extentedReport);
      newquotepage.clickNextOne(extentedReport);
      newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
      newquotepage.clickNextTwo(extentedReport);
      newquotepage.clickView(extentedReport);
      newquotepage.clickAgree(extentedReport);

      // Get and Buy Quote
      newquotepage.clickGetQuote(extentedReport);
      newquotepage.clickBuy(extentedReport);

      newquotepage.selectPayment(testData, true, extentedReport);

      newquotepage.takePayment(extentedReport);
      Thread.sleep(20000);// To be deleted
      CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

      // Enter Card Details
      carddetailspage.enterCardNumber(testData, extentedReport, false);
      carddetailspage.selectExpiry(testData, extentedReport, false);
      carddetailspage.enterVerification(extentedReport, false);
      carddetailspage.enterName(testData, extentedReport, true);
      carddetailspage.clickbuy(extentedReport, true);
      Log.message("Entered Card Details and Navigated to Verificaion page", driver, extentedReport,
          true);

      // Click continue button
      // newquotepage = carddetailspage.clickContinueButton(extentedReport);
      // Log.softAssertThat(newquotepage.verifyPaymentTrasaction(extentedReport), "Payment was
      // successful",
      // "Payment was not successful", driver, extentedReport, true);


      // confirm payment
      custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);
      Log.softAssertThat(
          custdashboardpage.uielement.verifyPageElements(Arrays.asList("lnkEditDetails"),
              custdashboardpage),
          "Navigated to Customer Dashboard page", "Not navigated to Customer Dashboard page",
          driver, extentedReport, true);

      // check for policy status
      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
      startdate = policyDetails.get("InceptionDate");
      endDate = policyDetails.get("ExpiryDate");

      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);



        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");
        Renewal_Quote = policyDetails.get("PolicyNo");

        custdashboardpage.clickComplete(extentedReport);
        Log.softAssertThat(homePage.verifyHomePage(),
            "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
            "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
            driver, extentedReport, true);

        // Click on Take Call link
        homePage.clickTakeCall(extentedReport);
        homePage.clickMyBrands(brandname, extentedReport);
        // Search with Valid Policy Number
        searchPage.searchValidPolicy(policyDetails.get("PolicyNo"), true, extentedReport);
        CustDashboardPage custdashboardPage =
            searchPage.selectPolicy_from_SearchPage(true, extentedReport);
        Log.message("Navigated to Cutomer Dashboard after selecting policy from search page",
            driver, extentedReport, true);

        // Verifying Customer Details
        custdashboardPage.clickPassVerification(extentedReport, true);
        custdashboardPage.verifyCustomerDashboardPage();
        Log.softAssertThat(
            custdashboardPage.verifyContactName(
                title + " " + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
                extentedReport, true),
            "Verified FirstName and LastName on Customer Dashboard : "
                + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
            "Not Verified Customer Name on Dashboard", driver, extentedReport, true);

        custdashboardpage.clickManagePolicy(extentedReport);
        custdashboardpage.clickMidTermAdjustment(extentedReport);
        custdashboardpage.enterDateForMTA(extentedReport);
        custdashboardpage.selectMidTermAdjReason(MTAcreationReason, extentedReport);
        custdashboardpage.clickMidTermContinue(extentedReport);
        getquotepage = new GetQuotePage(driver, extentedReport);
        getquotepage.unCheckAutoRenew();
        getquotepage.unCheckAutoReview();
        getquotepage.clickSavedButton(extentedReport);

        // invite Renewal

        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);
        // custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);

        // #C2__C6__navdrop > div > div > ul >
        // li.dropdown.renewal-quote.drop-inline.dropdown-hover.open > ul > li:nth-child(2) > a
        newquotepage.clickQuoteSave("Renew", "Renewal Quote", extentedReport);

        custdashboardpage.selecteRenewalVaration(extentedReport);
        newquotepage.generateRenewVariation("Renewal Quote two");

      }
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }


  }

  @When("^Create a renewal policy in EC which has already Expired and Review the policy in EC$")
  public void create_a_renewal_policy_in_EC_which_has_already_Expired_and_Review_the_policy_in_EC()
      throws Throwable {
    String userName = testData.get("UserName");
    String password = testData.get("Password");
    // String description = testData.get("Description");

    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");

    driver = WebDriverFactory.get(browser);

    String website_EC = TestRunnerRenewals.website_EC;


    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);
      // Create New Quote
      custdashboardpage.clickNewQuote(true, extentedReport);
      // Past date
      custdashboardpage.enterNewQuotePastDate(-40, testData, extentedReport, true);
      custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
      NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

      // Create New Quote
      Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
          "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
      newquotepage.enterPolicyDetails(testData, true, extentedReport);
      newquotepage.clickNextOne(extentedReport);
      newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
      newquotepage.clickNextTwo(extentedReport);
      newquotepage.clickView(extentedReport);
      newquotepage.clickAgree(extentedReport);

      // Get and Buy Quote
      newquotepage.clickGetQuote(extentedReport);
      newquotepage.clickBuy(extentedReport);

      newquotepage.selectPayment(testData, true, extentedReport);

      newquotepage.takePayment(extentedReport);
      Thread.sleep(20000);// To be deleted
      CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

      // Enter Card Details
      carddetailspage.enterCardNumber(testData, extentedReport, false);
      carddetailspage.selectExpiry(testData, extentedReport, false);
      carddetailspage.enterVerification(extentedReport, false);
      carddetailspage.enterName(testData, extentedReport, true);
      carddetailspage.clickbuy(extentedReport, true);
      Log.message("Entered Card Details and Navigated to Verificaion page", driver, extentedReport,
          true);

      // Click continue button
      // newquotepage = carddetailspage.clickContinueButton(extentedReport);
      // Log.softAssertThat(newquotepage.verifyPaymentTrasaction(extentedReport), "Payment was
      // successful",
      // "Payment was not successful", driver, extentedReport, true);


      // confirm payment
      custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);
      Log.softAssertThat(
          custdashboardpage.uielement.verifyPageElements(Arrays.asList("lnkEditDetails"),
              custdashboardpage),
          "Navigated to Customer Dashboard page", "Not navigated to Customer Dashboard page",
          driver, extentedReport, true);

      // check for policy status
      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
      startdate = policyDetails.get("InceptionDate");
      endDate = policyDetails.get("ExpiryDate");

      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);



        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");
        Renewal_Quote = policyDetails.get("PolicyNo");

        custdashboardpage.clickComplete(extentedReport);
        Log.softAssertThat(homePage.verifyHomePage(),
            "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
            "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
            driver, extentedReport, true);

        // Click on Take Call link
        homePage.clickTakeCall(extentedReport);
        homePage.clickMyBrands(brandname, extentedReport);
        // Search with Valid Policy Number
        searchPage.searchValidPolicy(policyDetails.get("PolicyNo"), true, extentedReport);
        CustDashboardPage custdashboardPage =
            searchPage.selectPolicy_from_SearchPage(true, extentedReport);
        Log.message("Navigated to Cutomer Dashboard after selecting policy from search page",
            driver, extentedReport, true);

        // Verifying Customer Details
        custdashboardPage.clickPassVerification(extentedReport, true);
        custdashboardPage.verifyCustomerDashboardPage();
        Log.softAssertThat(
            custdashboardPage.verifyContactName(
                title + " " + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
                extentedReport, true),
            "Verified FirstName and LastName on Customer Dashboard : "
                + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
            "Not Verified Customer Name on Dashboard", driver, extentedReport, true);


        // invite Renewal

        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);
        // custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);

        // #C2__C6__navdrop > div > div > ul >
        // li.dropdown.renewal-quote.drop-inline.dropdown-hover.open > ul > li:nth-child(2) > a
        newquotepage.clickQuoteSave("Renew", "Renewal Quote", extentedReport);

        custdashboardpage.selecteRenewalVaration(extentedReport);
        newquotepage.generateRenewVariation("Renewal Quote two");

      }
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }


  }


  @Then("^Validate the callcenter notification is displayed for an Expired policy$")
  public void validate_the_callcenter_notification_is_displayed_for_an_Expired_policy()
      throws Throwable {

    try {

      boolean flag = custDashboard.Verify_CallCentreNotification(notificationCallCenter);

      if (flag) {
        Log.pass("Call center Message is displayed and matched");
      } else {
        throw new Exception("Call centre message is not displayed");

      }


    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }
  }


  @When("^Create a renewal policy in EC which is Expiring today$")
  public void create_a_renewal_policy_in_EC_which_is_Expiring_today() throws Throwable {
    String userName = testData.get("UserName");
    String password = testData.get("Password");
    // String description = testData.get("Description");

    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");

    driver = WebDriverFactory.get(browser);

    String website_EC = TestRunnerRenewals.website_EC;


    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);

      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);

      for (int i = 0; i <= 3; i++) {
        // Create New Quote
        custdashboardpage.clickNewQuote(true, extentedReport);
        // Past date
        custdashboardpage.enterNewQuotePastDate(0, testData, extentedReport, true);
        custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
        NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

        // Create New Quote
        Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
            "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
        newquotepage.enterPolicyDetails(testData, true, extentedReport);
        newquotepage.clickNextOne(extentedReport);
        newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);

        // Get and Buy Quote
        newquotepage.clickGetQuote(extentedReport);
        newquotepage.clickBuy(extentedReport);

        newquotepage.selectPayment(testData, true, extentedReport);

        newquotepage.takePayment(extentedReport);
        Thread.sleep(20000);// To be deleted
        CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

        // Enter Card Details
        carddetailspage.enterCardNumber(testData, extentedReport, false);
        carddetailspage.selectExpiry(testData, extentedReport, false);
        carddetailspage.enterVerification(extentedReport, false);
        carddetailspage.enterName(testData, extentedReport, true);
        carddetailspage.clickbuy(extentedReport, true);
        Log.message("Entered Card Details and Navigated to Verificaion page", driver,
            extentedReport, true);

        // Click continue button
        // newquotepage = carddetailspage.clickContinueButton(extentedReport);
        // Log.softAssertThat(newquotepage.verifyPaymentTrasaction(extentedReport), "Payment was
        // successful",
        // "Payment was not successful", driver, extentedReport, true);


        // confirm payment
        custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);
        Log.softAssertThat(
            custdashboardpage.uielement.verifyPageElements(Arrays.asList("lnkEditDetails"),
                custdashboardpage),
            "Navigated to Customer Dashboard page", "Not navigated to Customer Dashboard page",
            driver, extentedReport, true);

        // check for policy status
        policyDetails = custdashboardpage.getPolicyURN(extentedReport);
        Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
        Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
        startdate = policyDetails.get("InceptionDate");
        endDate = policyDetails.get("ExpiryDate");

        if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
          Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
              true);



          Log.message("Renewal Policy Created Successfully in Accepted status", driver,
              extentedReport, true);
          policyDetails.get("PolicyNo");
          Renewal_Quote = policyDetails.get("PolicyNo");

          custdashboardpage.clickComplete(extentedReport);
          Log.softAssertThat(homePage.verifyHomePage(),
              "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
              "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
              driver, extentedReport, true);

          // Click on Take Call link
          homePage.clickTakeCall(extentedReport);
          homePage.clickMyBrands(brandname, extentedReport);
          // Search with Valid Policy Number
          searchPage.searchValidPolicy(policyDetails.get("PolicyNo"), true, extentedReport);
          CustDashboardPage custdashboardPage =
              searchPage.selectPolicy_from_SearchPage(true, extentedReport);
          Log.message("Navigated to Cutomer Dashboard after selecting policy from search page",
              driver, extentedReport, true);

          // Verifying Customer Details
          custdashboardPage.clickPassVerification(extentedReport, true);
          custdashboardPage.verifyCustomerDashboardPage();
          Log.softAssertThat(
              custdashboardPage.verifyContactName(
                  title + " " + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
                  extentedReport, true),
              "Verified FirstName and LastName on Customer Dashboard : "
                  + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
              "Not Verified Customer Name on Dashboard", driver, extentedReport, true);


          // invite Renewal

          custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);
          // custdashboardpage.clickContinueWarningMsg(extentedReport, true);
          newquotepage.clickNextOne(extentedReport);
          newquotepage.clickNextTwo(extentedReport);
          newquotepage.clickView(extentedReport);
          newquotepage.clickAgree(extentedReport);
          newquotepage.clickGetQuote(extentedReport);

          // #C2__C6__navdrop > div > div > ul >
          // li.dropdown.renewal-quote.drop-inline.dropdown-hover.open > ul > li:nth-child(2) > a
          newquotepage.clickQuoteSave("Renew", "Renewal Quote", extentedReport);

          custdashboardpage.selecteRenewalVaration(extentedReport);
        }
        newquotepage.generateRenewVariation("Renewal Quote two");

      }
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }


  }

  @When("^Create a Multiple renewal policy and review it and validate only reviewed policy is displayed for Renewal$")
  public void create_a_Multiple_renewal_policy_and_review_it_and_validate_only_reviewed_policy_is_displayed_for_Renewal()
      throws Exception {

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    String website_EC = TestRunnerRenewals.website_EC;
    driver = WebDriverFactory.get(browser);
    dataMapTest = new HashMap<String, HashMap<String, String>>();

    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);

      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);

      // Create multiple policy based on the counter
      policyCounter = Integer.parseInt(testData.get("PolicyCounter"));
      for (int i = 0; i <= policyCounter; i++) {

        // Create New Quote
        custdashboardpage.clickNewQuote(true, extentedReport);

        // Past date
        custdashboardpage.enterNewQuotePastDate(i, testData, extentedReport, true);
        custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
        NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

        // Create New Quote
        Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
            "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
        newquotepage.enterPolicyDetails(testData, true, extentedReport);
        newquotepage.clickNextOne(extentedReport);
        newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);

        // Get and Buy Quote
        newquotepage.clickGetQuote(extentedReport);
        newquotepage.clickBuy(extentedReport);
        newquotepage.selectPayment(testData, true, extentedReport);
        
        String paymentPlan = testData.get("Payment Plan").toString();

        if (paymentPlan.equals("Annual")) {

          newquotepage.takePayment(extentedReport);
          CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

          // Enter Card Details
          carddetailspage.enterCardNumber(testData, extentedReport, false);
          carddetailspage.selectExpiry(testData, extentedReport, false);
          carddetailspage.enterVerification(extentedReport, false);
          carddetailspage.enterName(testData, extentedReport, true);
          carddetailspage.clickbuy(extentedReport, true);
          Log.message("Entered Card Details and Navigated to Verificaion page", driver,
              extentedReport, true);
          newquotepage = new NewQuotePage(driver, extentedReport);
        } else
          accountName = newquotepage.returnAccountName();

        // confirm payment
        custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);
        Log.softAssertThat(
            custdashboardpage.uielement.verifyPageElements(Arrays.asList("lnkEditDetails"),
                custdashboardpage),
            "Navigated to Customer Dashboard page", "Not navigated to Customer Dashboard page",
            driver, extentedReport, true);

        // check for policy status
        policyDetails = custdashboardpage.getPolicyURN(extentedReport);
        Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
        Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
        startdate = policyDetails.get("InceptionDate");
        endDate = policyDetails.get("ExpiryDate");
        if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
          Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
              true);
          Log.message("Renewal Policy Created Successfully in Accepted status", driver,
              extentedReport, true);
          policyDetails.get("PolicyNo");
          Renewal_Quote = policyDetails.get("PolicyNo");

          homePage = custdashboardpage.clickComplete(extentedReport);
          Log.softAssertThat(homePage.verifyHomePage(),
              "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
              "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
              driver, extentedReport, true);

          // Click on Take Call link
          homePage.clickTakeCall(extentedReport);
          homePage.clickMyBrands(brandname, extentedReport);

          // Search with Valid Policy Number
          searchPage.searchValidPolicy(policyDetails.get("PolicyNo"), true, extentedReport);
          CustDashboardPage custdashboardPage =
              searchPage.selectPolicy_from_SearchPage(true, extentedReport);
          Log.message("Navigated to Cutomer Dashboard after selecting policy from search page",
              driver, extentedReport, true);

          // Verifying Customer Details
          custdashboardPage.clickPassVerification(extentedReport, true);
          custdashboardPage.verifyCustomerDashboardPage();
          Log.softAssertThat(
              custdashboardPage.verifyContactName(
                  title + " " + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
                  extentedReport, true),
              "Verified FirstName and LastName on Customer Dashboard : "
                  + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
              "Not Verified Customer Name on Dashboard", driver, extentedReport, true);

          custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);

          // custdashboardpage.clickContinueWarningMsg(extentedReport, true);
          newquotepage.clickNextOne(extentedReport);
          newquotepage.clickNextTwo(extentedReport);
          newquotepage.clickView(extentedReport);
          newquotepage.clickAgree(extentedReport);
          newquotepage.clickGetQuote(extentedReport);
          newquotepage.clickBuyMTA(extentedReport, true);
          newquotepage.generateRenewVariation("Renewal Quote two");
        }

        policyDetails = custdashboardpage.getPolicyURNRenewal(extentedReport);
        policyURN = policyDetails.get("PolicyNo");
        dataMapTest.put(policyURN, policyDetails);
      }
      homePage = custdashboardpage.clickComplete(extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(),
          "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
          "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
          driver, extentedReport, true);
      homePage.doLogout(extentedReport);

    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
    }
  }

  @When("^Suspend the user with GDPR Reason$")
  public void suspend_the_user_with_GDPR_Reason() throws Throwable {
    try {

    } catch (Exception e) {

    }
  }

  @When("^Create a Multiple renewal policy with Add on Covers and review it and validate only reviewed policy is displayed for Renewal$")
  public void create_a_Multiple_renewal_policy_with_Add_on_Covers_and_review_it_and_validate_only_reviewed_policy_is_displayed_for_Renewal()
      throws Exception {

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    String website_EC = TestRunnerRenewals.website_EC;
    driver = WebDriverFactory.get(browser);

    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);

      // Create multiple policy based on the counter
      policyCounter = Integer.parseInt(testData.get("PolicyCounter"));
      for (int i = 0; i <= policyCounter; i++) {

        // Create New Quote
        custdashboardpage.clickNewQuote(true, extentedReport);

        // Past date
        custdashboardpage.enterNewQuotePastDate(i, testData, extentedReport, true);
        custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
        NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

        // Create New Quote
        Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
            "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
        newquotepage.enterPolicyDetails(testData, true, extentedReport);
        newquotepage.clickNextOne(extentedReport);
        newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);

        // Get and Buy Quote
        newquotepage.clickGetQuote(extentedReport);
        newquotepage.clickBuy(extentedReport);
        newquotepage.selectPayment(testData, true, extentedReport);
        newquotepage.takePayment(extentedReport);

        CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

        // Enter Card Details
        carddetailspage.enterCardNumber(testData, extentedReport, false);
        carddetailspage.selectExpiry(testData, extentedReport, false);
        carddetailspage.enterVerification(extentedReport, false);
        carddetailspage.enterName(testData, extentedReport, true);
        carddetailspage.clickbuy(extentedReport, true);
        Log.message("Entered Card Details and Navigated to Verificaion page", driver,
            extentedReport, true);
        // confirm payment
        custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);
        Log.softAssertThat(
            custdashboardpage.uielement.verifyPageElements(Arrays.asList("lnkEditDetails"),
                custdashboardpage),
            "Navigated to Customer Dashboard page", "Not navigated to Customer Dashboard page",
            driver, extentedReport, true);
        // check for policy status
        policyDetails = custdashboardpage.getPolicyURN(extentedReport);
        Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
        Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
        startdate = policyDetails.get("InceptionDate");
        endDate = policyDetails.get("ExpiryDate");
        if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
          Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
              true);
          Log.message("Renewal Policy Created Successfully in Accepted status", driver,
              extentedReport, true);
          policyDetails.get("PolicyNo");
          Renewal_Quote = policyDetails.get("PolicyNo");

          custdashboardpage.clickComplete(extentedReport);
          Log.softAssertThat(homePage.verifyHomePage(),
              "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
              "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
              driver, extentedReport, true);

          // Click on Take Call link
          homePage.clickTakeCall(extentedReport);
          homePage.clickMyBrands(brandname, extentedReport);
          // Search with Valid Policy Number
          searchPage.searchValidPolicy(policyDetails.get("PolicyNo"), true, extentedReport);
          CustDashboardPage custdashboardPage =
              searchPage.selectPolicy_from_SearchPage(true, extentedReport);
          Log.message("Navigated to Cutomer Dashboard after selecting policy from search page",
              driver, extentedReport, true);

          // Verifying Customer Details
          custdashboardPage.clickPassVerification(extentedReport, true);
          custdashboardPage.verifyCustomerDashboardPage();
          Log.softAssertThat(
              custdashboardPage.verifyContactName(
                  title + " " + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
                  extentedReport, true),
              "Verified FirstName and LastName on Customer Dashboard : "
                  + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
              "Not Verified Customer Name on Dashboard", driver, extentedReport, true);

          custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);
          // custdashboardpage.clickContinueWarningMsg(extentedReport, true);
          newquotepage.clickNextOne(extentedReport);
          newquotepage.clickNextTwo(extentedReport);
          newquotepage.clickView(extentedReport);
          newquotepage.clickAgree(extentedReport);
          newquotepage.clickGetQuote(extentedReport);
          newquotepage.clickBuyMTA(extentedReport, true);
          newquotepage.generateRenewVariation("Renewal Quote two");
        }


        /*
         * String tempDate = newquotepage.RenewalStartDate(driver, extentedReport);
         * policiesDate.add(tempDate); itr = policiesDate.iterator(); while (itr.hasNext()) {
         * System.out.println("For " + i + " Policy Dates are" + itr.next()); }
         * 
         * 
         * String tempRenewalPremium = newquotepage.RenewalPremium(driver, extentedReport);
         * policiesRenewalPremium.add(tempRenewalPremium); itr1 = policiesRenewalPremium.iterator();
         * while (itr1.hasNext()) { System.out.println("For " + i + " Renewal Premium are" +
         * itr1.next()); }
         * 
         * String tempPolicyPremium = newquotepage.policyPremium(driver, extentedReport);
         * policiesPremium.add(tempPolicyPremium); itr2 = policiesPremium.iterator(); while
         * (itr2.hasNext()) { System.out.println("For " + i + " Renewal Premium are" + itr2.next());
         * }
         */

        policyURN = policyDetails.get("PolicyNo");
        dataMapTest = new HashMap<String, HashMap<String, String>>();
        dataMapTest.put(policyURN, policyDetails);
        policyDetails.clear();
      }

    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
    }
  }

  @Then("^Validate Renewal information is displayed for all the policy that is due for Renewal$")
  public void validate_Renewal_information_is_displayed_for_all_the_policy_that_is_due_for_Renewal()
      throws Throwable {
    try {
      validate_multiple_policy_is_displayed();
    }

    catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }

  }


  @Then("^Validate the message if policy is expirting today$")
  public void validate_the_message_if_policy_is_expirting_today() throws Throwable {


    try {
      String Notification = "Your Household Standard Policy is now due for Renewal .";
      java.util.Date date = new java.util.Date();
      System.out.println(date);
      String datenew = date.toString();
      System.out.println(datenew);
      if (custDashboard.validate_policyExpairingToday_Notification(Notification)) {

        Log.pass(Notification + "----- is matched");
      } else {
        throw new Exception(Notification + "----------mis-matched");

      }


    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);

    }

    finally {
      CucumberLog.endTestCase(extentedReport);
      driver.quit();
    }

  }



  @Given("^Fetch new test case data from the \"([^\"]*)\"$")
  public void fetch_new_test_case_data_from_the(String arg1) throws Exception {
    // Write code here that turns the phrase above into concrete actions
    System.out.println("Starting the background");
    try {
      browser = TestRunnerRenewals.browser;
      String className = "SelfServiceRenewal298_";
      testData = BaseCucumberTest.getTestData(className, arg1);
      String description = testData.get("Description");
      Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");
      extentedReport = BaseCucumberTest.addTestInfo(arg1, description);
      Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");

      fis = new FileInputStream(
          System.getProperty("user.dir") + "\\src\\main\\resources\\testdataSS.properties");
      testDataSS.load(fis);
      setBackDateYear(testData);
      setCredentials(testData);
      tcID = testData.get("TC_ID");
    } catch (Exception e) {
      CucumberLog.endTestCase(extentedReport);
    }
  }

  private void setBackDateYear(HashMap<String, String> testdata) throws Exception {
    this.backDateYear = testdata.get("RenewalBackdateYear");
    if (backDateYear.equals("")) {
      throw new Exception("Please enter value for RenewalBackdateYear in testdata sheet");
    }
  }

  private void setPolicyCounter(HashMap<String, String> testdata) throws Exception {
    String counter = testdata.get("PolicyCounter");
    if (counter.equals("")) {
      throw new Exception("Please enter value for PolicyCounter in testdata sheet");
    } else
      this.policyCounter = Integer.parseInt(counter);
  }


  private String getAddOnCoversEC() {
    return addOnCoversEC;
  }

  private void setAddOnCoversEC(HashMap<String, String> testdata) throws Exception {
    try {
      this.addOnCoversEC = testdata.get("coverToSelect_NB");
      if (addOnCoversEC.equals("")) {
        throw new Exception(
            "Please enter addon covers on coverToSelect_NB section in testdata sheet");
      }
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  private String getAddOnCoversSS() {
    return addOnCoversSS;
  }

  private void setAddOnCoversSS(HashMap<String, String> testdata) throws Exception {
    try {
      this.addOnCoversSS = testdata.get("AddOnCovers_SS_ADD_REMOVE");
      if (addOnCoversSS.equals("")) {
        throw new Exception(
            "Please enter addon covers on AddOnCovers_SS_ADD_REMOVE section in testdata sheet");
      }
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }


  @Then("^Login to SS$")
  public void login_to_SS() throws Exception {
    String website_SS = TestRunnerRenewals.website_SS;
    //driver = WebDriverFactory.get(browser);
    try {
      cusSelfservice = new CustomerSelfService(driver, website_SS, extentedReport).get();
      Log.pass("Customer Self Service page is displayed : " + website_SS, driver, extentedReport,
          true);
      CustomerSigninPage ss_loginPage = cusSelfservice.clickBtnSignIn(extentedReport, driver);
      Log.pass("User has successfully navigated to Customer sign in page" + website_SS, driver,
          extentedReport, true);
      /*custDashboard = ss_loginPage.loginToSSPCustomerSelfService("wory@emailssp.com", "Passw0rd",
          extentedReport, screenshot);    */
        custDashboard = ss_loginPage.loginToSSPCustomerSelfService(user_Email,
        UI_Rfresh_temp_password, extentedReport, screenshot);
       
      Log.pass("User has sucessfully navigated to customer dashboard page.", driver, extentedReport,
          screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Select policy URN to be renewed$")
  public void select_policy_URN_to_be_renewed() throws Exception {
    try {
     yourQuotePageFromPP = custDashboard.clickRenewMyPolicy(policyURN, extentedReport, screenshot);
     //yourQuotePageFromPP = custDashboard.clickRenewMyPolicy("576", extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^User verifies cover details on Your Quote page$")
  public void user_verifies_cover_details_on_Your_Quote_page() throws Exception {
    try {

      /*
       * yourQuotePageFromPP.verifyYourPremiumText(policyDetails.get("RenewalPremium"),policyDetails
       * .get("PolicyPremium"),
       * testDataProperty.getProperty("yourPremiumLineOne"),testDataProperty.getProperty(
       * "yourPremiumLineOne"), screenshot);
       */

      // Validate default Covers
      String SS_CoversToChk = testData.get("Covers_SS");
      String[] coversToChck = SS_CoversToChk.split(",");
      for (int i = 0; i < coversToChck.length; i++) {
        String[] coversToAdd = coversToChck[i].split("_");
        String coverSec = coversToAdd[0].trim();
        String Exp_limit_Amt = coversToAdd[1].trim();
        String Exp_Acess_Amt = coversToAdd[2].trim();
        String Exp_Prem_Amt = coversToAdd[3].trim();

        String valToReturn =
            yourQuotePageFromPP.verify_DefaultCovers(coversToChck[i], true, extentedReport);
        if (!(valToReturn.equalsIgnoreCase("false"))) {

          Log.pass("New quote Page has the default values as expected for " + coverSec
              + " cover,Limit : " + Exp_limit_Amt.trim() + ",Excess:" + Exp_Acess_Amt.trim()
              + ",Premium:" + Exp_Prem_Amt.trim(), driver, extentedReport, true);
        } else {
          Log.fail("New quote Page has failed to populate with following default values for "
              + coverSec + " cover,Limit : " + Exp_limit_Amt + ", Excess : " + Exp_Acess_Amt
              + ",Premium:" + Exp_Prem_Amt, driver, extentedReport, true);
        }
      }
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify policy data on Your Quote page$")
  public void verify_policy_data_on_Your_Quote_page() throws Exception {

    HashMap<Integer, String> termsAndConditions = new HashMap<Integer, String>();
    try {

      yourQuotePageFromPP.verifyYourPremiumText(testDataSS, policyDetails, screenshot);

      if (yourQuotePageFromPP.verifyPolicyComponents(testData, screenshot)) {
        Log.pass("Expected policy components are displayed in SS.", driver, extentedReport,
            screenshot);
      } else
        Log.fail("All the policy components are not present in SS.", driver, extentedReport,
            screenshot);

      // Validate default Covers
      String SS_CoversToChk = testData.get("Covers_SS");
      String[] coversToChck = SS_CoversToChk.split(",");
      for (int i = 0; i < coversToChck.length; i++) {
        String[] coversToAdd = coversToChck[i].split("_");
        String coverSec = coversToAdd[0].trim();
        String Exp_limit_Amt = coversToAdd[1].trim();
        String Exp_Acess_Amt = coversToAdd[2].trim();
        String Exp_Prem_Amt = coversToAdd[3].trim();

        String valToReturn =
            yourQuotePageFromPP.verify_DefaultCovers(coversToChck[i], true, extentedReport);
        if (!(valToReturn.equalsIgnoreCase("false"))) {

          Log.pass("Your quote page has the default values as expected for " + coverSec
              + " cover, Limit : " + Exp_limit_Amt.trim() + ", Excess : " + Exp_Acess_Amt.trim()
              + " and Premium : " + Exp_Prem_Amt.trim(), driver, extentedReport, true);
        } else {
          Log.fail("Your quote page has failed to populate with following default values for "
              + coverSec + " cover, Limit : " + Exp_limit_Amt + ", Excess : " + Exp_Acess_Amt
              + " and Premium : " + Exp_Prem_Amt, driver, extentedReport, true);
        }
      }

      termsAndConditions.put(1,
          "This is Term and Condition is a Mandatory Term and Condition for testing purposes.");
      termsAndConditions.put(afterTCAttachTablesize,
          "This is Term and Condition is a Mandatory Term and Condition for testing purposes.");
      yourQuotePageFromPP.verifyTermsAndConditionsSection(testData, afterTCAttachTablesize,
          termsAndConditions, tcType, testDataSS, screenshot);

      yourQuotePageFromPP.verifyTermsAndConditionsWarningSection(testDataSS, screenshot);
      
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Accept Terms & Conditions and click on Buy button$")
  public void accept_Terms_Conditions_and_click_on_Buy_button() throws Exception {
    try {
      contactDetailsPage = yourQuotePageFromPP.navigateToContactDetailsPage(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^User navigates to Payment page and performs Annual card payment using existing card details$")
  public void user_navigates_to_Payment_page_and_performs_DD_monthly_payment_using_existing_bank_details()
      throws Exception {
    try {

      paymentPage.validateSelectedPaymentPlan(testData, driver, extentedReport, screenshot);
      paymentPage.validateSelectedPaymentMethod(testData, driver, extentedReport, screenshot);
      paymentPage.selectExistingCardForPayment(driver, extentedReport);
      paymentPage.selectpolicyHolderEntryCard(testData, screenshot);

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Clicks on Buy button to confirms payment$")
  public void clicks_on_Buy_button_to_confirms_payment() throws Exception {
    try {
      confirmationPage = paymentPage.clickOnBuyButton(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify that payment is successful$")
  public void verify_that_payment_is_successful() throws Exception {
    try {
      /*confirmationPage.validatePaymentStatus(testData, testDataSS, driver, extentedReport, "558",
          screenshot);*/
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Navigate to dashboard and logout of SS$")
  public void navigate_to_dashboard_and_logout_of_SS() throws Exception {
    try {
      custDashboard =
          confirmationPage.clickOnBackToYourDashboardButtonSSPayment(driver, extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }

  }

  @Then("^Navigate to dashboard from Confirmation Page$")
  public void navigate_to_dashboard_from_confirmation_page() throws Exception {
    try {
      custDashboard =
          confirmationPage.clickOnBackToYourDashboardButtonSSPayment(driver, extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Logout of SS$")
  public void logout_of_SS() throws Exception {
    try {
      custDashboard.signOut();
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify data on Contact Details page$")
  public void verify_data_on_Contact_Details_page() throws Exception {
    // Write code here that turns the phrase above into concrete actions
    try {
      contactDetailsPage.validateUserDetails(testData, screenshot);
      contactDetailsPage.verifyCorrespAndMarketPref(testData, screenshot);
      contactDetailsPage.verifySMSOptionSelection(testData, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Navigate to Payment page$")
  public void navigate_to_Payment_page() throws Exception {
    try {
      paymentPage = contactDetailsPage.clickProceed(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify existing payment plan and payment method$")
  public void verify_existing_payment_plan_and_payment_method() throws Exception {
    try {
      paymentPage.validateSelectedPaymentPlan(testData, driver, extentedReport, screenshot);
      paymentPage.validateSelectedPaymentMethod(testData, driver, extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Select existing card$")
  public void select_existing_card() throws Exception {
    try {
      paymentPage.selectExistingCardForPayment(driver, extentedReport);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Navigate to confimation page$")
  public void navigate_to_confimation_page() throws Exception {
    try {
      confirmationPage = paymentPage.clickOnBuyButton(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }
  
  @Then("^Make payment using pay later option$")
  public void make_payment_using_pay_later_option() throws Throwable {
    try {
      confirmationPage = paymentPage.clickOnPayLaterButton(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify the payment status$")
  public void verify_the_payment_status() throws Exception {
    try {
      confirmationPage.validatePaymentStatus(testData, policyDetails, testDataSS, changePayment, driver, extentedReport,
          policyURN, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Select the policy holder entry$")
  public void select_the_policy_holder_entry() throws Exception {
    String paymentMethod;
    try {   
      if (!changePayment) {
        paymentMethod = testData.get("Payment Method");
        if (paymentMethod.equals("Card")) {
          paymentPage.selectpolicyHolderEntryCard(testData, screenshot);
        } else
          paymentPage.selectpolicyHolderEntryDD(testData, screenshot);
      } else {
        paymentMethod = testData.get("NewPaymentMethod");
        if (paymentMethod.equals("Card")) {
          paymentPage.selectpolicyHolderEntryCard(testData, screenshot);
        } else
          paymentPage.selectpolicyHolderEntryDD(testData, screenshot);
      }
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Add a new card$")
  public void add_a_new_card() throws Exception {
    try {
      cardDetailsPageSS = paymentPage.addNewCardForpayment(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Enter the card details and make payment$")
  public void enter_the_card_details_and_make_payment() throws Exception {
    try {
      cardDetailsPageSS.switchToCardDetailsFrame();
      cardDetailsPageSS.enterCardDetails(testData, screenshot);
      // cardDetailsPageSS.selectExpiry(testData, screenshot);
      if (tcID.equals("TC_005") && unsucessfulPymtCounter == 0) {
        cardDetailsPageSS.enterInvalidName(screenshot);
        unsucessfulPymtCounter++;
      }
      confirmationPage = cardDetailsPageSS.clickbuy(screenshot);

      //confirmationPage = new ConfirmationPage(driver);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify the existing addon covers$")
  public void verify_the_existing_addon_covers() throws Exception {
    try {

      if (yourQuotePageFromPP.verifyPolicyComponents(testData, screenshot)) {
        Log.pass("All selected cover sections are displayed.", driver, extentedReport, screenshot);
      } else
        Log.fail("All selected cover sections are not displayed.", driver, extentedReport,
            screenshot);

      String SS_CoversToChk = testData.get("Covers_SS");
      String[] coversToChck = SS_CoversToChk.split(",");

      for (int i = 0; i < coversToChck.length; i++) {
        String[] coversToAdd = coversToChck[i].split("_");
        String coverSec = coversToAdd[0].trim();
        String Exp_limit_Amt = coversToAdd[1].trim();
        String Exp_Acess_Amt = coversToAdd[2].trim();
        String Exp_Prem_Amt = coversToAdd[3].trim();

        String valToReturn =
            yourQuotePageFromPP.verify_DefaultCovers(coversToChck[i], true, extentedReport);
        if (!(valToReturn.equalsIgnoreCase("false"))) {
          Log.pass("Your Quote page has the default values as expected for " + coverSec
              + " cover,Limit : " + Exp_limit_Amt.trim() + ",Excess:" + Exp_Acess_Amt.trim()
              + ",Premium:" + Exp_Prem_Amt.trim(), driver, extentedReport, true);
        } else {
          Log.fail("Your Quote page has failed to populate with following default values for "
              + coverSec + " cover,Limit : " + Exp_limit_Amt + ", Excess : " + Exp_Acess_Amt
              + ",Premium:" + Exp_Prem_Amt, driver, extentedReport, true);
        }
      }
      setAddOnCoversEC(testData);
      String addOnCovers = getAddOnCoversEC();
      String[] addOnCoversToCheck = addOnCovers.split(",");
      for (int i = 0; i < addOnCoversToCheck.length; i++) {
        String[] coversToCheck = addOnCoversToCheck[i].split("_");
        String coverName = coversToCheck[0].trim();
        String action = coversToCheck[2].trim();
        yourQuotePageFromPP.validateStatusAddOnCovers(coverName, action, screenshot);
      }
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Add a new addon cover and Recalculate$")
  public void add_a_new_addon_cover_and_Recalculate() throws Exception {
    try {

      // Add an add-on cover
      int counter = 0;
      setAddOnCoversSS(testData);
      String SS_AddOnCovers = getAddOnCoversSS();
      String[] addOnCoversToAddRemove = SS_AddOnCovers.split(",");
      for (int i = 0; i < addOnCoversToAddRemove.length; i++) {
        String[] coversToAddRemove = addOnCoversToAddRemove[i].split("_");
        String coversec = coversToAddRemove[0].trim();
        String action = coversToAddRemove[2].trim();

        if (action.equalsIgnoreCase("Remove")) {
          counter++;
          continue;
        }
        if (counter > 1) {
          throw new Exception("Unable to add " + coversec + " cover due to test data issue.");
        }
        String valToReturn =
            yourQuotePageFromPP.addOnCoversAddRemove(addOnCoversToAddRemove[i], screenshot);
        if (!(valToReturn.equalsIgnoreCase("false"))) {
          Log.pass(
              "User has successfully performed action " + action + " for " + coversec + " cover.",
              driver, extentedReport, true);
        } else {
          Log.fail("User unable to perform action " + action + " for " + coversec + " cover.",
              driver, extentedReport, true);
          throw new Exception("Unable to add Add-on covers.");
        }

        yourQuotePageFromPP.validateAddOnCoverStatus(coversec, action, screenshot);
      }
      yourQuotePageFromPP.clickRecalculateButn(screenshot);
      yourQuotePageFromPP.verifyPremiumOnRecalculate(screenshot);

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Remove the existing addon cover and Recalculate$")
  public void remove_the_existing_addon_cover_and_Recalculate() throws Exception {
    try {

      // Add an add-on cover
      int counter = 0;
      setAddOnCoversSS(testData);
      String SS_AddOnCovers = getAddOnCoversSS();
      String[] addOnCoversToAddRemove = SS_AddOnCovers.split(",");

      for (int i = 0; i < addOnCoversToAddRemove.length; i++) {
        String[] coversToAddRemove = addOnCoversToAddRemove[i].split("_");
        String coversec = coversToAddRemove[0].trim();
        String action = coversToAddRemove[2].trim();

        if (action.equalsIgnoreCase("Add")) {
          counter++;
          continue;
        }
        if (counter > 1) {
          throw new Exception("Unable to remove " + coversec + " cover due to test data issue.");
        }
        String valToReturn =
            yourQuotePageFromPP.addOnCoversAddRemove(addOnCoversToAddRemove[i], screenshot);
        if (!(valToReturn.equalsIgnoreCase("false"))) {
          Log.pass(
              "User has successfully performed action " + action + " for " + coversec + " cover.",
              driver, extentedReport, true);
        } else {
          Log.fail("User unable to perform action " + action + " for " + coversec + " cover.",
              driver, extentedReport, true);
          throw new Exception("Unable to add or remove Add-on covers.");
        }
        yourQuotePageFromPP.validateAddOnCoverStatus(coversec, action, screenshot);
      }
      yourQuotePageFromPP.clickRecalculateButn(screenshot);
      yourQuotePageFromPP.verifyPremiumOnRecalculate(screenshot);

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Cancel the changes made on Your Quote page and verify that user navigates to Customer Dashboard$")
  public void cancel_the_changes_made_on_Your_Quote_page_and_verify_that_user_navigates_to_Customer_Dashboard()
      throws Exception {
    try {
      custDashboard = yourQuotePageFromPP.clickCancelButn(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }


  @Then("^Navigate to Your Quote page and verify that renewal premium amount is unchanged$")
  public void navigate_to_Your_Quote_page_and_verify_that_renewal_premium_amount_is_unchanged()
      throws Exception {
    try {
      yourQuotePageFromPP = custDashboard.clickRenewMyPolicy("743", extentedReport, screenshot);
      yourQuotePageFromPP.verifyrenewalPremiumAfterAddOnCancel("503.49", screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify that no changes have been made to Add on Covers$")
  public void verify_that_no_changes_have_been_made_to_Add_on_Covers() throws Exception {
    try {
      String SS_AddOnCovers = testData.get("AddOnCovers_SS_ADD_REMOVE");
      String[] addOnCoversToAddRemove = SS_AddOnCovers.split(",");
      for (int i = 0; i < addOnCoversToAddRemove.length; i++) {
        String[] coversToAddRemove = addOnCoversToAddRemove[i].split("_");
        String coversec = coversToAddRemove[1].trim();
        if (coversec.equals("HomeEmergency")) {
          yourQuotePageFromPP.validateAddOnCoverStatus("HomeEmergency", homeEmergencyCoverAction,
              screenshot);
        } else if (coversec.equals("LegalExpenses")) {
          yourQuotePageFromPP.validateAddOnCoverStatus("LegalExpenses", legalExpensesCoverAction,
              screenshot);
        }
      }
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Logout from Your Quote page$")
  public void logout_from_your_quote_page() throws Exception {
    try {
      cusSelfservice = yourQuotePageFromPP.signout(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Update mobile number and click save on Contact Details page$")
  public void update_mobile_number_and_click_save_on_Contact_Details_page() throws Exception {
    try {
      contactDetailsPage.enterMobNo(testData, screenshot);
      contactDetailsPage.clickSave(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Navigate to View my insurance details page$")
  public void navigate_to_View_my_insurance_details_page() throws Exception {
    try {
      yourPolicyDetailsContactCover = yourQuotePageFromPP.clickViewDetailsButn(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Validate all the added cover details on these pages and finally naviagte to YourQuote page$")
  public void validate_all_the_added_cover_details_on_these_pages_and_finally_naviagte_to_YourQuote_page()
      throws Exception {
    try {
      yourPolicyDetailsContactCover.validatePolicyDetailsSection(testData, policyURN, screenshot);
      yourPolicyDetailsContactCover.validateCoverTypeSection(testData, screenshot);

      yourPolicyDetailsProperty = yourPolicyDetailsContactCover.clickNextBtn(screenshot);

      yourPolicyDetailsBuildContCover = yourPolicyDetailsProperty.clickNextBtn(screenshot);
      yourPolicyDetailsBuildContCover.validateCoverSections(testData, screenshot);

      yourPolicyDetailsGardenCover = yourPolicyDetailsBuildContCover.clickNextBtn(screenshot);
      yourPolicyDetailsTechnologyCover = yourPolicyDetailsGardenCover.clickNextBtn(screenshot);
      yourPolicyDetailsHomeEmergency = yourPolicyDetailsTechnologyCover.clickNextBtn(screenshot);
      yourPolicyDetailsLegalExpense = yourPolicyDetailsHomeEmergency.clickNextBtn(screenshot);

      yourQuotePageFromPP = yourPolicyDetailsLegalExpense.clickNextBtn(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Create a renewal quote on policy and add two freestyle T&C and edit one of them in EC$")
  public void create_a_renewal_quote_on_policy_and_add_two_freestyle_T_C_and_edit_one_of_them_in_EC()
      throws Exception {

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    String website_EC = TestRunnerRenewals.website_EC;
    driver = WebDriverFactory.get(browser);

    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);

      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);

      // Create New Quote
      custdashboardpage.clickNewQuote(true, extentedReport);
      // Past date
      custdashboardpage.enterNewQuotePastDate(0, testData, extentedReport, true);
      custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
      NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

      // Create New Quote
      Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
          "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
      newquotepage.enterPolicyDetails(testData, true, extentedReport);
      newquotepage.clickNextOne(extentedReport);
      newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
      newquotepage.clickNextTwo(extentedReport);
      newquotepage.clickView(extentedReport);
      newquotepage.clickAgree(extentedReport);

      // Get and Buy Quote
      newquotepage.clickGetQuote(extentedReport);
      newquotepage.clickBuy(extentedReport);
      newquotepage.selectPayment(testData, true, extentedReport);
      newquotepage.takePayment(extentedReport);

      CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

      // Enter Card Details
      carddetailspage.enterCardNumber(testData, extentedReport, false);
      carddetailspage.selectExpiry(testData, extentedReport, false);
      carddetailspage.enterVerification(extentedReport, false);
      carddetailspage.enterName(testData, extentedReport, true);
      carddetailspage.clickbuy(extentedReport, true);
      Log.message("Entered Card Details and Navigated to Verificaion page", driver, extentedReport,
          true);

      // confirm payment
      custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);
      /*
       * Log.softAssertThat(
       * custdashboardpage.uielement.verifyPageElements(Arrays.asList("lnkEditDetails"),
       * custdashboardpage), "Navigated to Customer Dashboard page",
       * "Not navigated to Customer Dashboard page", driver, extentedReport, true);
       */

      // check for policy status
      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
      startdate = policyDetails.get("InceptionDate");
      endDate = policyDetails.get("ExpiryDate");
      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);
        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");
        Renewal_Quote = policyDetails.get("PolicyNo");

        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);

        // custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);
        newquotepage.clickBuyMTA(extentedReport, true);
        newquotepage.clickTermsAndConditionSection(extentedReport);
        tableTCSize = newquotepage.originalNoTC();
        newquotepage.clickAttachTermAndConditionButton(extentedReport);
        newquotepage.createFreeStyleTC(testData, screenshot, extentedReport);
        newquotepage.clickAttachTermAndConditionButton(extentedReport);
        newquotepage.createFreeStyleTC(testData, screenshot, extentedReport);
        afterTCAttachTablesize = newquotepage.newNoTC();
        newquotepage.verifyAdditionOfTC(tableTCSize, afterTCAttachTablesize, screenshot,
            extentedReport);
        newquotepage.editTC(testData, screenshot, extentedReport);
        newquotepage.verifyEditedTCDetails(testData, screenshot, extentedReport);
        newquotepage.generateRenewVariation("Renewal Quote two");
      }

      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      policyURN = policyDetails.get("PolicyNo");

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify that added and edited T&C are present in Self Service$")
  public void verify_that_added_and_editedT_C_are_present_in_Self_Service() throws Exception {
    try {
      HashMap<Integer, String> termsAndConditions = new HashMap<Integer, String>();
      termsAndConditions.put(1,
          "This is Term and Condition is a Mandatory Term and Condition for testing purposes.");
      termsAndConditions.put(afterTCAttachTablesize,
          "This is Term and Condition is a Mandatory Term and Condition for testing purposes.");
      yourQuotePageFromPP.verifyTermsAndConditionsSection(testData, afterTCAttachTablesize,
          termsAndConditions, tcType, testDataSS, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Create a policy in EC and perform renewal on it and save the policy in reviewed status$")
  public void create_a_policy_in_EC_and_perform_renewal_on_it_and_save_the_policy_in_reviewed_status()
      throws Exception {

    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    String website_EC = TestRunnerRenewals.website_EC;
    driver = WebDriverFactory.get(browser);

    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(username, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + username + "& Password:" + password, driver,
          extentedReport);

      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);

      // Create New Quote
      custdashboardpage.clickNewQuote(true, extentedReport);

      // Past date
      custdashboardpage.enterNewQuotePastDate(-5, testData, extentedReport, true);
      custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
      NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

      // Create New Quote
      Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
          "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
      newquotepage.enterPolicyDetails(testData, true, extentedReport);
      newquotepage.clickNextOne(extentedReport);
      newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
      newquotepage.clickNextTwo(extentedReport);
      newquotepage.clickView(extentedReport);
      newquotepage.clickAgree(extentedReport);

      // Get and Buy Quote
      newquotepage.clickGetQuote(extentedReport);
      newquotepage.clickBuy(extentedReport);
      newquotepage.selectPayment(testData, true, extentedReport);


      String paymentPlan = testData.get("Payment Plan").toString();

      if (paymentPlan.equals("Annual")) {

        newquotepage.takePayment(extentedReport);
        CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

        // Enter Card Details
        carddetailspage.enterCardNumber(testData, extentedReport, false);
        carddetailspage.selectExpiry(testData, extentedReport, false);
        carddetailspage.enterVerification(extentedReport, false);
        carddetailspage.enterName(testData, extentedReport, true);
        carddetailspage.clickbuy(extentedReport, true);
        Log.message("Entered Card Details and Navigated to Verificaion page", driver,
            extentedReport, true);
        newquotepage = new NewQuotePage(driver, extentedReport);
      } else
        accountName = newquotepage.returnAccountName();

      // confirm payment
      custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);

      // check for policy status
      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
      startdate = policyDetails.get("InceptionDate");
      endDate = policyDetails.get("ExpiryDate");
      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);
        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");
        Renewal_Quote = policyDetails.get("PolicyNo");

        // Perform renewal
        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);

        if (paymentPlan.equals("Monthly")) {
          custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        }
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);
        newquotepage.clickBuyMTA(extentedReport, true);
        newquotepage.generateRenewVariation("Renewal Quote two");
      }

      policyDetails = custdashboardpage.getPolicyURNRenewal(extentedReport);
      policyURN = policyDetails.get("PolicyNo");

      // Navigate to Home and return to dashboard
      homePage = custdashboardpage.clickComplete(extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(),
          "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
          "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
          driver, extentedReport, true);
      homePage.doLogout(extentedReport);

    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
    }
  }

  @When("^Create a renewal quote on policy and add new T&C and edit one of them in EC$")
  public void create_a_renewal_quote_on_policy_and_add_new_T_C_and_edit_one_of_them_in_EC()
      throws Exception {

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    String website_EC = TestRunnerRenewals.website_EC;
    driver = WebDriverFactory.get(browser);

    try {
      setExistTCAdd(testData);      
      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);

      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);

      // Create New Quote
      custdashboardpage.clickNewQuote(true, extentedReport);
      // Past date
      custdashboardpage.enterNewQuotePastDate(0, testData, extentedReport, true);
      custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
      NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

      // Create New Quote
      Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
          "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
      newquotepage.enterPolicyDetails(testData, true, extentedReport);
      newquotepage.clickNextOne(extentedReport);
      newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
      newquotepage.clickNextTwo(extentedReport);
      newquotepage.clickView(extentedReport);
      newquotepage.clickAgree(extentedReport);

      // Get and Buy Quote
      newquotepage.clickGetQuote(extentedReport);
      newquotepage.clickBuy(extentedReport);
      newquotepage.selectPayment(testData, true, extentedReport);
      newquotepage.takePayment(extentedReport);

      CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

      // Enter Card Details
      carddetailspage.enterCardNumber(testData, extentedReport, false);
      carddetailspage.selectExpiry(testData, extentedReport, false);
      carddetailspage.enterVerification(extentedReport, false);
      carddetailspage.enterName(testData, extentedReport, true);
      carddetailspage.clickbuy(extentedReport, true);
      Log.message("Entered Card Details and Navigated to Verificaion page", driver, extentedReport,
          true);

      // confirm payment
      custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);
      Log.softAssertThat(
          custdashboardpage.uielement.verifyPageElements(Arrays.asList("lnkEditDetails"),
              custdashboardpage),
          "Navigated to Customer Dashboard page", "Not navigated to Customer Dashboard page",
          driver, extentedReport, true);

      // check for policy status
      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
      startdate = policyDetails.get("InceptionDate");
      endDate = policyDetails.get("ExpiryDate");
      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);
        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");
        Renewal_Quote = policyDetails.get("PolicyNo");

        custdashboardpage.clickComplete(extentedReport);
        Log.softAssertThat(homePage.verifyHomePage(),
            "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
            "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
            driver, extentedReport, true);

        // Click on Take Call link
        homePage.clickTakeCall(extentedReport);
        homePage.clickMyBrands(brandname, extentedReport);

        // Search with Valid Policy Number
        searchPage.searchValidPolicy(policyDetails.get("PolicyNo"), true, extentedReport);
        CustDashboardPage custdashboardPage =
            searchPage.selectPolicy_from_SearchPage(true, extentedReport);
        Log.message("Navigated to Cutomer Dashboard after selecting policy from search page",
            driver, extentedReport, true);

        // Verifying Customer Details
        custdashboardPage.clickPassVerification(extentedReport, true);
        custdashboardPage.verifyCustomerDashboardPage();
        Log.softAssertThat(
            custdashboardPage.verifyContactName(
                title + " " + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
                extentedReport, true),
            "Verified FirstName and LastName on Customer Dashboard : "
                + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
            "Not Verified Customer Name on Dashboard", driver, extentedReport, true);

        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);

        // custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);
        newquotepage.clickBuyMTA(extentedReport, true);
        newquotepage.clickTermsAndConditionSection(extentedReport);
        tableTCSize = newquotepage.originalNoTC();
        String[] tAndCName = existTCAdd.split(",");
        for (int i = 0; i < tAndCName.length; i++) {
          newquotepage.clickAttachTermAndConditionButton(extentedReport);
          String tAndC = tAndCName[i];
          newquotepage.addTermsAndCondition(tAndC, screenshot, extentedReport);
        }
        afterTCAttachTablesize = newquotepage.newNoTC();
        newquotepage.verifyAdditionOfTC(tableTCSize, afterTCAttachTablesize, screenshot,
            extentedReport);
        newquotepage.editTC(testData, screenshot, extentedReport);
        newquotepage.generateRenewVariation("Renewal Quote two");
      }

      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      policyURN = policyDetails.get("PolicyNo");

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Update contact name and click on cancel button$")
  public void update_contact_name_and_click_on_cancel_button() throws Exception {
    try {
      contactDetailsPage.enterName(testData, screenshot);
      custDashboard = contactDetailsPage.clickCancel(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Logout from Contact Details page$")
  public void logout_from_Contact_Details_page() throws Exception {
    try {

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Logout from Payment page$")
  public void logout_from_Payment_page() throws Throwable {
    try {
      paymentPage.signout(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify audit creation in EC for the changes in SS$")
  public void verify_audit_creation_in_EC_for_the_changes_in_SS() throws Throwable {

    String brandname = testData.get("Brand Name");
    String website_EC = TestRunnerRenewals.website_EC;
    boolean status;
  
    try {   
      setAuditsList(testData);
      
      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(username, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + username + "& Password:" + password, driver,
          extentedReport);

      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);
      searchPage.searchAndSelectPolicyId(policyURN, extentedReport, screenshot);
      custdashboardpage = new CustDashboardPage(driver, extentedReport).get();
      custdashboardpage.clickPassVerification(extentedReport, true);
      String[] audits = auditsList.split(",");
      status = custdashboardpage.verifySystemAuditHistory(extentedReport, audits, user_Email);
      Log.softAssertThat(status, "Audit has been sucessfully created with expected messages",
          "Audit has not been created with expected messages", driver, extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify that name is not updated$")
  public void verify_that_name_is_not_updated() throws Exception {
    try {
      contactDetailsPage.validateUpdatedSSName(testData, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify that mobile number is updated$")
  public void verify_that_mobile_number_is_updated() throws Exception {
    try {
      contactDetailsPage.validateUpdatedSSMobNo(testData, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify existing bank account details$")
  public void verify_existing_bank_account_details() throws Exception {
    try {
      paymentPage.validateExistingAccountDetails(testData, accountName, driver, extentedReport,
          screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Select the bank account holder entry and accept the DD T&C$")
  public void select_the_bank_account_holder_entry_and_accept_the_DD_T_C() throws Exception {
    try {
      paymentPage.selectpolicyHolderEntryDD(testData, screenshot);
      paymentPage.acceptDDTandC(driver, extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify existing card details$")
  public void verify_existing_card_details() throws Throwable {
    try {
      paymentPage.validateExistingCardDetails(testData, driver, extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Create a policy with Add on covers in EC and perform renewal on it and save the policy in reviewed status$")
  public void create_a_policy_with_Add_on_covers_in_EC_and_perform_renewal_on_it_and_save_the_policy_in_reviewed_status()
      throws Throwable {

    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    String website_EC = TestRunnerRenewals.website_EC;
    driver = WebDriverFactory.get(browser);

    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(username, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + username + "& Password:" + password, driver,
          extentedReport);

      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);

      // Create New Quote
      custdashboardpage.clickNewQuote(true, extentedReport);

      // Past date
      custdashboardpage.enterNewQuotePastDate(0, testData, extentedReport, true);
      custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
      NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

      // Create New Quote
      Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
          "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
      newquotepage.enterPolicyDetails(testData, true, extentedReport);
      newquotepage.clickNextOne(extentedReport);
      newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
      newquotepage.clickNextTwo(extentedReport);
      newquotepage.clickView(extentedReport);
      newquotepage.clickAgree(extentedReport);

      // Get and Buy Quote
      newquotepage.clickGetQuote(extentedReport);

      // Add the insurance cover items
      String[] coversToAdd = testData.get("coverToSelect_NB").toString().split(",");
      for (int i = 0; i < coversToAdd.length; i++) {
        boolean BoolVal = false;
        String ins_RowtoInteract =
            newquotepage.SelectInsuranceItem(coversToAdd[i], true, extentedReport);
        BoolVal = newquotepage.enterInsCoverDetails(testData, coversToAdd[i], ins_RowtoInteract,
            true, extentedReport);
        String[] coverType = coversToAdd[i].split("_");
        if (BoolVal != false) {
          Log.pass(coverType[2] + " " + coverType[0] + " cover done successfully", driver,
              extentedReport, true);
        } else {
          Log.fail("Failed to " + coverType[2] + " " + coverType[0] + " cover", driver,
              extentedReport, true);
        }

      }

      newquotepage.clickReCalculate(extentedReport);
      newquotepage.clickBuy(extentedReport);
      newquotepage.selectPayment(testData, true, extentedReport);

      String paymentPlan = testData.get("Payment Plan").toString();

      if (paymentPlan.equals("Annual")) {

        newquotepage.takePayment(extentedReport);
        CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

        // Enter Card Details
        carddetailspage.enterCardNumber(testData, extentedReport, false);
        carddetailspage.selectExpiry(testData, extentedReport, false);
        carddetailspage.enterVerification(extentedReport, false);
        carddetailspage.enterName(testData, extentedReport, true);
        carddetailspage.clickbuy(extentedReport, true);
        Log.message("Entered Card Details and Navigated to Verificaion page", driver,
            extentedReport, true);
      } else
        accountName = newquotepage.returnAccountName();

      // confirm payment
      custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);

      // check for policy status
      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
      startdate = policyDetails.get("InceptionDate");
      endDate = policyDetails.get("ExpiryDate");
      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);
        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");
        Renewal_Quote = policyDetails.get("PolicyNo");

        // Perform renewal
        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);

        if (paymentPlan.equals("Monthly")) {
          custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        }
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);
        newquotepage.clickBuyMTA(extentedReport, true);
        newquotepage.generateRenewVariation("Renewal Quote two");
      }

      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      policyURN = policyDetails.get("PolicyNo");

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify card add error message$")
  public void verify_card_add_error_message() throws Exception {
    try {
      paymentPage.addNewCardWithoutPolicyHolder(screenshot);
      paymentPage.validateAddCardErrorMessage(testData, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Validate renewal documents$")
  public void validate_renewal_documents() throws Exception {
    try {
      for (Map.Entry<String, HashMap<String, String>> test : dataMapTest.entrySet()) {
        custDashboard.validateRenewalDocuments(test, extentedReport, screenshot);
      }
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @When("^Create a policy in EC using different \"([^\"]*)\" and perform renewal with reviewed status$")
  public void create_a_policy_in_EC_using_different_and_perform_renewal_with_reviewed_status(
      String arg1) throws Throwable {

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    String website_EC = TestRunnerRenewals.website_EC;
    driver = WebDriverFactory.get(browser);

    int date = Integer.parseInt(arg1);
    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);

      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);

      // Confirm customer details and create customer
      CustDashboardPage custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);

      // Create New Quote
      custdashboardpage.clickNewQuote(true, extentedReport);

      // Past date
      custdashboardpage.enterNewQuotePastDate(date, testData, extentedReport, true);
      custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
      NewQuotePage newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

      // Create New Quote
      Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
          "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
      newquotepage.enterPolicyDetails(testData, true, extentedReport);
      newquotepage.clickNextOne(extentedReport);
      newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
      newquotepage.clickNextTwo(extentedReport);
      newquotepage.clickView(extentedReport);
      newquotepage.clickAgree(extentedReport);

      // Get and Buy Quote
      newquotepage.clickGetQuote(extentedReport);
      newquotepage.clickBuy(extentedReport);
      newquotepage.selectPayment(testData, true, extentedReport);


      String paymentPlan = testData.get("Payment Plan").toString();

      if (paymentPlan.equals("Annual")) {

        newquotepage.takePayment(extentedReport);
        CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);

        // Enter Card Details
        carddetailspage.enterCardNumber(testData, extentedReport, false);
        carddetailspage.selectExpiry(testData, extentedReport, false);
        carddetailspage.enterVerification(extentedReport, false);
        carddetailspage.enterName(testData, extentedReport, true);
        carddetailspage.clickbuy(extentedReport, true);
        Log.message("Entered Card Details and Navigated to Verificaion page", driver,
            extentedReport, true);
      } else
        accountName = newquotepage.returnAccountName();

      // confirm payment
      custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);

      // check for policy status
      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
      startdate = policyDetails.get("InceptionDate");
      endDate = policyDetails.get("ExpiryDate");
      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);
        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");
        Renewal_Quote = policyDetails.get("PolicyNo");

        // Perform renewal
        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);

        if (paymentPlan.equals("Monthly")) {
          custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        }
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);
        newquotepage.clickBuyMTA(extentedReport, true);
        newquotepage.generateRenewVariation("Renewal Quote two");
      }

      policyDetails = custdashboardpage.getPolicyURNRenewal(extentedReport);
      policyURN = policyDetails.get("PolicyNo");

      // Navigate to Home and return to dashboard
      homePage = custdashboardpage.clickComplete(extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(),
          "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
          "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
          driver, extentedReport, true);
      homePage.doLogout(extentedReport);

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify policy \"([^\"]*)\" on customer dashboard$")
  public void verify_policy_on_customer_dashboard(String arg1) throws Throwable {
    try {
      custDashboard.validateRenewalHomeMessagePossibleRenw(policyURN, arg1, driver, extentedReport,
          screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Change payment plan and payment method$")
  public void change_payment_plan_and_payment_method() throws Throwable {
    try {
      setNewPaymentType(testData);
      changePayment = true;
      paymentPage.makePaymentSS(testData, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify renewal message and status$")
  public void verify_renewal_message_and_status() throws Throwable {
    try {
      custDashboard.validateRenewalSuccess(driver, extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Validate unsucessful payment screen$")
  public void validate_unsucessful_payment_screen() throws Throwable {
    try {
      confirmationPage.validateUnsuccessfullPaymentScreen(testDataSS, driver, extentedReport);
      paymentPage = confirmationPage.clickOnBackToPaymentButton(driver, extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify policy \"([^\"]*)\" on customer dashboard when renewal is not possible in SS$")
  public void verify_policy_on_customer_dashboard_when_renewal_is_not_possible_in_SS(String arg1)
      throws Throwable {
    try {
      custDashboard.validateRenewalHomeMessageNotPossibleRenw(policyURN, arg1, driver,
          extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Verify renewal \"([^\"]*)\" with and status$")
  public void verify_renewal_with_and_status(String arg1) throws Throwable {
    try {
      custDashboard.validateRenewalHomeMessageafterRenewalFutureDate(policyURN, arg1, driver,
          extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }
  
  @Then("^Add new bank account details and make payment$")
  public void add_new_bank_account_details_and_make_payment() throws Throwable {
    try {
      paymentPage.clickOnAddNewBankAccountButton(driver, extentedReport);     
      paymentPage.addAccountDetailswindow(testData, "Save", driver, extentedReport, screenshot);
      paymentPage.validateAddedBankAcct(testData, driver, extentedReport, screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  } 
  
  @When("^Create a new business quote without add on covers$")
  public void create_a_new_business_quote_without_add_on_covers() throws Throwable {
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    String website_EC = TestRunnerRenewals.website_EC;
    driver = WebDriverFactory.get(browser);

    try {

      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(username, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + username + "& Password:" + password, driver,
          extentedReport);

      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      custDetails = searchPage.enterCustomerDetails(testData, true, extentedReport);
      
      if(tcID.equals("TC_015")){
        searchPage.SelectOptMarketing(testData, driver, screenshot);
      }

      // Confirm customer details and create customer
      custdashboardpage = searchPage.confirmCreateCustomer(true, extentedReport);
      Log.softAssertThat(
          custdashboardpage.verifyContactName(testData.get("Title") + " "
              + custDetails.get("First Name") + " " + custDetails.get("Last Name"), extentedReport,
              true),
          "Verified FirstName and LastName on Customer Dashboard page",
          "Not Verified Customer Name on Dashboard page", driver, extentedReport, true);

      // Create New Quote
      custdashboardpage.clickNewQuote(true, extentedReport);

      // Past date
      custdashboardpage.enterNewQuotePastDate(-5, testData, extentedReport, true);
      custdashboardpage.selectNewQuoteDetails(testData, extentedReport, true);
      newquotepage = custdashboardpage.clickContinueQuote(true, extentedReport);

      // Create New Quote
      Log.softAssertThat(newquotepage.verifyNewQuotePage(true, extentedReport),
          "Verified NewQuotePage ", "Not verified NewQuotePage", driver, extentedReport, true);
      newquotepage.enterPolicyDetails(testData, true, extentedReport);
      newquotepage.clickNextOne(extentedReport);
      newquotepage.enterCustomerDetails(testData, true, Corecover, extentedReport);
      newquotepage.clickNextTwo(extentedReport);
      newquotepage.clickView(extentedReport);
      newquotepage.clickAgree(extentedReport);

      // Get and Buy Quote
      newquotepage.clickGetQuote(extentedReport);
      newquotepage.clickBuy(extentedReport);
      newquotepage.selectPayment(testData, true, extentedReport);


      String paymentPlan = testData.get("Payment Plan").toString();

      if (paymentPlan.equals("Annual")) {

        newquotepage.takePayment(extentedReport);
        carddetailspage = newquotepage.selectVisacard(extentedReport);

        // Enter Card Details
        carddetailspage.enterCardNumber(testData, extentedReport, false);
        carddetailspage.selectExpiry(testData, extentedReport, false);
        carddetailspage.enterVerification(extentedReport, false);
        carddetailspage.enterName(testData, extentedReport, true);
        carddetailspage.clickbuy(extentedReport, true);
        Log.message("Entered Card Details and Navigated to Verificaion page", driver,
            extentedReport, true);
        newquotepage = new NewQuotePage(driver, extentedReport);
      } else
        accountName = newquotepage.returnAccountName();

      // confirm payment
      custdashboardpage = newquotepage.confirmPayment(testData, extentedReport);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }
  
  @When("^Perform renewal save the policy in reviewed status$")
  public void perform_renewal_save_the_policy_in_reviewed_status() throws Throwable {
    try {
      String paymentPlan = testData.get("Payment Plan").toString();

      // check for policy status
      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
      startdate = policyDetails.get("InceptionDate");
      endDate = policyDetails.get("ExpiryDate");
      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);
        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");
        Renewal_Quote = policyDetails.get("PolicyNo");

        // Perform renewal
        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);

        if (paymentPlan.equals("Monthly")) {
          custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        }
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);
        newquotepage.clickBuyMTA(extentedReport, true);
        newquotepage.generateRenewVariation("Renewal Quote two");
      }

      policyDetails = custdashboardpage.getPolicyURNRenewal(extentedReport);
      policyURN = policyDetails.get("PolicyNo");

      // Navigate to Home and return to dashboard
      homePage = custdashboardpage.clickComplete(extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(),
          "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
          "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
          driver, extentedReport, true);
      homePage.doLogout(extentedReport);

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }
  
  @When("^Perform renewal with addition of two T&C and save the policy in reviewed status$")
  public void perform_renewal_with_addition_of_two_T_C_and_save_the_policy_in_reviewed_status()
      throws Throwable {
    try {
      setTCType(testData);
      String paymentPlan = testData.get("Payment Plan").toString();

      // check for policy status
      policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));
      startdate = policyDetails.get("InceptionDate");
      endDate = policyDetails.get("ExpiryDate");
      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);
        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");
        Renewal_Quote = policyDetails.get("PolicyNo");

        // Perform renewal
        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);

        if (paymentPlan.equals("Monthly")) {
          custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        }
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);
        newquotepage.clickBuyMTA(extentedReport, true);
        newquotepage.clickTermsAndConditionSection(extentedReport);
        tableTCSize = newquotepage.originalNoTC();
        if(tcType.equalsIgnoreCase("freeStyle")){       
          newquotepage.clickAttachTermAndConditionButton(extentedReport);
          newquotepage.createFreeStyleTC(testData, screenshot, extentedReport);
          newquotepage.clickAttachTermAndConditionButton(extentedReport);
          newquotepage.createFreeStyleTC(testData, screenshot, extentedReport);
        }
        else if(tcType.equalsIgnoreCase("existingSystem")){
          setExistTCAdd(testData);
          String[] tAndCName = existTCAdd.split(",");
          newquotepage.clickAttachTermAndConditionButton(extentedReport);
          for (int i = 0; i < tAndCName.length; i++) {      
            String tAndC = tAndCName[i];
            tAndC = tAndC.trim();
            newquotepage.addTermsAndCondition(tAndC, screenshot, extentedReport);
          }
        }        
        afterTCAttachTablesize = newquotepage.newNoTC();
        newquotepage.verifyAdditionOfTC(tableTCSize, afterTCAttachTablesize, screenshot,
            extentedReport);
        newquotepage.editTC(testData, screenshot, extentedReport);
        newquotepage.verifyEditedTCDetails(testData, screenshot, extentedReport);
        newquotepage.generateRenewVariation("Renewal Quote two");

      }
      policyDetails = custdashboardpage.getPolicyURNRenewal(extentedReport);
      policyURN = policyDetails.get("PolicyNo");

      // Navigate to Home and return to dashboard
      homePage = custdashboardpage.clickComplete(extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(),
          "Successfully navigated to Home Page after clicking complete button in customer dashboard page",
          "Failed to navigate to Home Page after clicking complete button in customer dashboard page",
          driver, extentedReport, true);
      homePage.doLogout(extentedReport);

    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }
  
  @Then("^Accept Direct Debit T&C$")
  public void accept_Direct_Debit_T_C() throws Throwable {
      try{
        paymentPage.acceptDDTandC(driver, extentedReport, screenshot);
      }catch (Exception e) {
        CucumberLog.exception(e, driver, extentedReport);
      }
  }
  
  @Then("^Remove contact name fields$")
  public void remove_contact_name_fields() throws Throwable {
      try{
        contactDetailsPage.clearName(testData, screenshot);
      }catch (Exception e) {
        CucumberLog.exception(e, driver, extentedReport);
      }
  }

  @Then("^Optin to maketing preference however no option should be selected for the same$")
  public void optin_to_maketing_preference_however_no_option_should_be_selected_for_the_same() throws Throwable {
    try{
      contactDetailsPage.removeMarketPrefOption(testData, screenshot);
    }catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Click on save and validate required fields$")
  public void click_on_save_and_validate_required_fields() throws Throwable {
    try{
      contactDetailsPage.clickSave(screenshot);
      contactDetailsPage.validateReqdfieldMesgContactDetPage(testData, screenshot);    
    }catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

  @Then("^Update contact details and marketing preference$")
  public void update_contact_details_and_marketing_preference() throws Throwable {
    try{
      contactDetailsPage.enterName(testData, screenshot);
      contactDetailsPage.selectMarketPref(testData, screenshot);
    }catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }
  
  @Then("^Validate required fields on payment page$")
  public void validate_required_fields_on_payment_page() throws Throwable {
    try {
      paymentPage.deSelectCard(testData, driver, extentedReport, screenshot);
      paymentPage.validateReqdFieldMsgforCardPymt(driver, extentedReport, screenshot);
      setNewPaymentType(testData);
      changePayment = true;
      paymentPage.selectPaymentPlan(testData, screenshot);
      paymentPage.validateReqdFieldMsgforPmtPlanMthdChange(testData, driver, extentedReport, screenshot);
      paymentPage.signout(screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }
  
  @When("^Suspend the user with GDPR$")
  public void suspend_the_user_with_GDPR() throws Throwable {

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String website_EC = TestRunnerRenewals.website_EC;
    try {
      // Navigate to Login Page
      loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);

      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      searchPage.enterLastName(custDetails.get("Last Name"), extentedReport);
      searchPage.enterFirstName(custDetails.get("First Name"), extentedReport);
      searchPage.clickSearch(extentedReport);
      Log.message("Advanced search - Searching with the last and first name ", extentedReport);

      custdashboardpage =
          searchPage.selectPolicy_from_SearchPage(true, extentedReport);
      custdashboardpage.clickPassVerification(extentedReport, true);
      custdashboardpage.verifyCustomerDashboardPage();
       Log.softAssertThat(
           custdashboardpage.verifyContactName(
       title + " " + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
       extentedReport, true),
       "Verified FirstName and LastName on Customer Dashboard : "
       + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
       "Not Verified Customer Name on Dashboard", driver, extentedReport, true);
       Log.message("Navigated to Cutomer Dashboard after selecting policy from search page",
       driver, extentedReport, true);
       custdashboardpage.suspendUserGDPR(true, extentedReport, driver);
       custdashboardpage.clickComplete(extentedReport);
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
      CucumberLog.endTestCase(extentedReport);
    }
  }
  
  @Then("^Validate that Suspended user is not able to Renew the policy$")
  public void validate_that_Suspended_user_is_not_able_to_Renew_the_policy() throws Exception {

    try {
      custDashboard.validateWarningMessageForSuspendedUser(driver, extentedReport,
          testDataSS.getProperty("gdprWarningMessage"), screenshot);
      custDashboard.validateRenewalButtonForSuspendedUser(driver, extentedReport, policyURN,
          screenshot);
    } catch (Exception e) {
      CucumberLog.exception(e, driver, extentedReport);
    }
  }

}
