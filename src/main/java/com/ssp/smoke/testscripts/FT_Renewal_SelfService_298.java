/**
 * 
 *//*
package com.ssp.smoke.testscripts;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.EmailReport;
import com.ssp.support.Log;
import com.ssp.support.WebDriverFactory;
import com.ssp.uirefresh_pages.UIRefresh_Dashboard;
import com.ssp.uirefresh_pages.UIRefresh_Login;
import com.ssp.uirefresh_pages.UIRefresh_Manage_contacts;
import com.ssp.uirefresh_pages.UIRefresh_System_User_Role;
import com.ssp.uirefresh_pages.UIRefrsh_EditPersonalContact;
import com.ssp.utils.DataProviderUtils;
import com.ssp.utils.DataUtils;
import com.ssp.uxp_SSPages.CustomerSelfService;
import com.ssp.uxp_SSPages.CustomerSigninPage;
import com.ssp.uxp_SSPages.SelfServiceCustomerDashboard;
import com.ssp.uxp_pages.CardDetailsPage;
import com.ssp.uxp_pages.CustDashboardPage;
import com.ssp.uxp_pages.EC_EditPersonalDetails;
import com.ssp.uxp_pages.HomePage;
import com.ssp.uxp_pages.LoginPage;
import com.ssp.uxp_pages.ManageContactDetailsPage;
import com.ssp.uxp_pages.NewQuotePage;
import com.ssp.uxp_pages.SearchPage;

*//**
 * @author jheelum.dutta
 *
 *//*

@Listeners(EmailReport.class)
public class FT_Renewal_SelfService_298 extends BaseTest {
  private String webSite;
  private String website_EC;
  private String website_UIRefresh;
  private HashMap<String, String> customerDetails2 = new HashMap<String, String>();

  @BeforeMethod
  public void init(ITestContext context) throws IOException {
    webSite = System.getProperty("webSite") != null ? System.getProperty("webSite")
        : context.getCurrentXmlTest().getParameter("webSite");

    System.out.println(
        "Website Self Service Link-: " + context.getCurrentXmlTest().getParameter("webSite"));

    website_EC = System.getProperty("website_EC") != null ? System.getProperty("website_EC")
        : context.getCurrentXmlTest().getParameter("website_EC");

    System.out
        .println("Website EC Link-: " + context.getCurrentXmlTest().getParameter("website_EC"));

    website_UIRefresh =
        System.getProperty("website_UIRefresh") != null ? System.getProperty("website_UIRefresh")
            : context.getCurrentXmlTest().getParameter("website_UIRefresh");

    System.out.println("Website UI Refresh  Link -: "
        + context.getCurrentXmlTest().getParameter("website_UIRefresh"));

  }

  public ExtentTest addTestInfo(String testCaseId, String testDesc) {

    String browserwithos = null;
    String test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getName();
    System.out.println("test is - " + test);
    String browsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
        .getParameter("browser");
    System.out.println("Browser- " + browsername);
    String browserversion = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
        .getParameter("browser_version");
    System.out.println("browserversion- " + browserversion);
    String os = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
        .getParameter("os").substring(0, 1);
    System.out.println("OS Name - " + os);
    String osversion = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
        .getParameter("os_version");
    System.out.println("osversion - " + osversion);

    browserwithos = os + osversion + "_" + browsername + browserversion;
    System.out.println("browserwithos - " + browserwithos);
    return Log.testCaseInfo(testCaseId + " [" + test + "]",
        testCaseId + " - " + testDesc + " [" + browserwithos + "]", test);

  }

  public HashMap<String, String> getTestData(String testcaseId) {
    String env =
        Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    System.out.println("Env is------------ " + env);
    // SelfServiceLogin
    String className = "SelfServiceRenewal298_" + env;
    System.out.println("className is------------ " + className);


    return DataUtils.testDatabyID(testcaseId, className);
  }

  @Test(description = "Validate Renewal Sumary on SS",dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
  public void TC_001(String browser) throws Exception {

    // Get the web driver instance
    String tcId = "TC_001";
    final WebDriver driver = WebDriverFactory.get(browser);
    HashMap<String, String> testData = getTestData(tcId);

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String description = testData.get("Description");

    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");
    Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");

    ExtentTest extentedReport = addTestInfo(tcId, description);

    try {

      // Navigate to Login Page
      LoginPage loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      HomePage homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      SearchPage searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      HashMap<String, String> custDetails =
          searchPage.enterCustomerDetails(testData, true, extentedReport);

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
      custdashboardpage.enterNewQuotePastDate(5, 1, extentedReport, true);
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
      newquotepage.click_Invoice_Acceptbtn(extentedReport);
      
       * newquotepage.takePayment(extentedReport); Thread.sleep(20000);//To be deleted
       * CardDetailsPage carddetailspage = newquotepage.selectVisacard(extentedReport);
       * 
       * // Enter Card Details carddetailspage.enterCardNumber(testData, extentedReport, false);
       * carddetailspage.selectExpiry(testData, extentedReport, false);
       * carddetailspage.enterVerification(extentedReport, false);
       * carddetailspage.enterName(testData, extentedReport, true);
       * carddetailspage.clickbuy(extentedReport, true);
       * Log.message("Entered Card Details and Navigated to Verificaion page", driver,
       * extentedReport, true);
       
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
      HashMap<String, String> policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));

      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("NB Policy Created Successfully in accepted status", driver, extentedReport, true);



        Log.message("New Business Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");

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

      } else {
        Log.fail(
            "Failed - NB Policy Created in " + policyDetails.get("Status").toString() + " status",
            driver, extentedReport, true);
      }

      Log.testCaseResult(extentedReport);
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
    } finally {
      Log.endTestCase(extentedReport);
      //driver.quit();
    }

  }

  @Test(description = "Login to UI Refresh and update Contact PassWord",
      dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
  public void TC_002(String browser) throws Exception {

    // Get the web driver instance
    String tcId = "TC_002";
    final WebDriver driver = WebDriverFactory.get(browser);
    HashMap<String, String> testData = getTestData(tcId);

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String description = testData.get("Description");
    String name = testData.get("UI_Refresh_ManageContact_Name");
    String role_system_user_txt = testData.get("UI_Refresh_role_systemuser");
    String pricipalid = testData.get("UI_Refresh_pricipalid");
    String temp_password = testData.get("UI_Refresh_Password");
    String date = testData.get("UI_Refresh_StartDate");
    String Country = testData.get("UI_Refresh_Country");

    String language = testData.get("UI_Refresh_language");

    String status = testData.get("UI_Refresh_status");

    String status_reason = testData.get("UI_Refresh_status_Reason");
    Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");

    ExtentTest extentedReport = addTestInfo(tcId, description);

    try {

      // Navigate to Login Page
      UIRefresh_Login login = new UIRefresh_Login(driver, website_UIRefresh, extentedReport).get();
      Log.pass("UI Refresh Login Page : " + website_UIRefresh, driver, extentedReport, true);
      UIRefresh_Dashboard dashboard =
          login.loginToUIRefresh(userName, password, false, extentedReport); // Login to the
                                                                             // application
      dashboard.clickOnContact(extentedReport);
      dashboard.clickOnManageContact(extentedReport);

      UIRefresh_Manage_contacts manageContacts =
          new UIRefresh_Manage_contacts(driver, extentedReport);
      manageContacts.enterName(name);
      manageContacts.clickBtnSearch(extentedReport, driver);
      manageContacts.clickBtnEdit(extentedReport, driver);
      UIRefrsh_EditPersonalContact editPersonalContact =
          new UIRefrsh_EditPersonalContact(driver, extentedReport);
      editPersonalContact.click_on_general_Detils(extentedReport, driver);
      editPersonalContact.select_contact_status(status, extentedReport);
      editPersonalContact.select_contact_status_reason(status_reason, extentedReport);
      editPersonalContact.clickBtnRole(extentedReport, driver, true);
      // editPersonalContact.click_on_Role_Dropdown(extentedReport, driver);
      editPersonalContact.select_Role_from_Dropdown(role_system_user_txt, extentedReport);

      editPersonalContact.clickBtnAdd(extentedReport, driver);
      UIRefresh_System_User_Role userrole = new UIRefresh_System_User_Role(driver, extentedReport);
      userrole.enter_PrincipalID(pricipalid);


      userrole.select_Country(Country, extentedReport);
      userrole.select_Language(language, extentedReport);
      // userrole.tick_user_Must_change_Checkbox(extentedReport);
      userrole.tickPasswordResetCheckbox(extentedReport);
      userrole.enter_TemporaryPassword(temp_password);
      userrole.enterDate(date);
      userrole.clickBtnsave(extentedReport, driver);
      UIRefrsh_EditPersonalContact editPersonalContact_complete =
          new UIRefrsh_EditPersonalContact(driver, extentedReport);
      editPersonalContact_complete.clickBtnCompletetab(extentedReport, driver);
      UIRefresh_Manage_contacts manageContacts_complete =
          new UIRefresh_Manage_contacts(driver, extentedReport);
      manageContacts_complete.clickBtnComplete(extentedReport, driver);

      Log.testCaseResult(extentedReport);


    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
    } finally {
      Log.endTestCase(extentedReport);
      // driver.quit();
    }

  }



  @Test(
      description = "Renewal Policy created from EC and login to SS and validate Renewal Summary page",
      dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
  public void TC_003(String browser) throws Exception {

    // Get the web driver instance
    String tcId = "TC_003";
    final WebDriver driver = WebDriverFactory.get(browser);
    HashMap<String, String> testData = getTestData(tcId);

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String description = testData.get("Description");

    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");

    String UI_Rfresh_userName = testData.get("UserName");
    String UI_Rfresh_password = testData.get("Password");
    String UI_Rfresh_description = testData.get("Description");
    String UI_Rfresh_name = testData.get("UI_Refresh_ManageContact_Name");
    String UI_Rfresh_role_system_user_txt = testData.get("UI_Refresh_role_systemuser");
    String UI_Rfresh_pricipalid = testData.get("UI_Refresh_pricipalid");
    String UI_Rfresh_temp_password = testData.get("UI_Refresh_Password");
    String UI_Rfresh_date = testData.get("UI_Refresh_StartDate");
    String UI_Rfresh_Country = testData.get("UI_Refresh_Country");

    String UI_Refresh_language = testData.get("UI_Refresh_language");
    Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");

    ExtentTest extentedReport = addTestInfo(tcId, description);

    try {

      // Navigate to Login Page
      LoginPage loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      HomePage homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      SearchPage searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      HashMap<String, String> custDetails =
          searchPage.enterCustomerDetails(testData, true, extentedReport);

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
      custdashboardpage.enterNewQuotePastDate(5, 1, extentedReport, true);
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
      HashMap<String, String> policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));

      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);



        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");

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
//invite Renewal

        custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);
       // custdashboardpage.clickContinueWarningMsg(extentedReport, true);
        newquotepage.clickNextOne(extentedReport);
        newquotepage.clickNextTwo(extentedReport);
        newquotepage.clickView(extentedReport);
        newquotepage.clickAgree(extentedReport);
        newquotepage.clickGetQuote(extentedReport);
        
     //   #C2__C6__navdrop > div > div > ul > li.dropdown.renewal-quote.drop-inline.dropdown-hover.open > ul > li:nth-child(2) > a
        newquotepage.clickQuoteSave("Renew", "Renewal Quote", extentedReport);
        
        custdashboardpage.selecteRenewalVaration(extentedReport);
        newquotepage.generateRenewVariation("Renewal Quote two");
        

        Log.softAssertThat(
                custdashboardpage.uielement.verifyPageElements(Arrays.asList("lnkEditDetails"),
                        custdashboardpage),
                "Custdashboardpage Verified", "Custdashboardpage not Verified", driver, extentedReport, true);

        Log.softAssertThat(custdashboardpage.verify_reviewStatus("Review Required", extentedReport, true),
                "Review Required status displayed after saving a renewal quote",
                "Review Required status was not displayed after saving a renewal quote");

        // driver.close();
        // UI Refresh
        UIRefresh_Login login =
            new UIRefresh_Login(driver, website_UIRefresh, extentedReport).get();
        Log.pass("UI Refresh Login Page : " + website_UIRefresh, driver, extentedReport, true);
        UIRefresh_Dashboard dashboard =
            login.loginToUIRefresh(userName, password, false, extentedReport); // Login to the
                                                                               // application
        dashboard.clickOnContact(extentedReport);
        dashboard.clickOnManageContact(extentedReport);

        UIRefresh_Manage_contacts manageContacts =
            new UIRefresh_Manage_contacts(driver, extentedReport);
        manageContacts.enterName(custDetails.get("Last Name"));
        manageContacts.clickBtnSearch(extentedReport, driver);
        manageContacts.clickBtnEdit(extentedReport, driver);
        UIRefrsh_EditPersonalContact editPersonalContact =
            new UIRefrsh_EditPersonalContact(driver, extentedReport);

        editPersonalContact.clickBtnRole(extentedReport, driver, false);
       editPersonalContact.clickBtnEdit(extentedReport, driver);
         //editPersonalContact.select_Role_from_Dropdown(UI_Rfresh_role_system_user_txt,extentedReport);

        //editPersonalContact.clickBtnAdd(extentedReport, driver);
        UIRefresh_System_User_Role userrole =
            new UIRefresh_System_User_Role(driver, extentedReport);
        String user_Email = custDetails.get("Last Name") + "@emailssp.com";
        userrole.enter_PrincipalID(custDetails.get("Last Name") + "@emailssp.com3");


       // userrole.select_Country(UI_Rfresh_Country, extentedReport);
        //userrole.select_Language(UI_Refresh_language, extentedReport);

        userrole.tick_user_Must_change_Checkbox(extentedReport);
        userrole.tickPasswordResetCheckbox(extentedReport);
        userrole.enter_TemporaryPassword(UI_Rfresh_temp_password);
        // userrole.enterDate(UI_Rfresh_date);
        userrole.clickBtnsave(extentedReport, driver);
        UIRefrsh_EditPersonalContact editPersonalContact_complete =
            new UIRefrsh_EditPersonalContact(driver, extentedReport);
        editPersonalContact_complete.clickBtnCompletetab(extentedReport, driver);
        UIRefresh_Manage_contacts manageContacts_complete =
            new UIRefresh_Manage_contacts(driver, extentedReport);
        manageContacts_complete.clickBtnComplete(extentedReport, driver);
        Log.pass(
            "Sucessfully updated the Principal ID and PassWord of Contact : " + website_UIRefresh,
            driver, extentedReport, true);
        // Self Service
        CustomerSelfService cusSelfservice =
            new CustomerSelfService(driver, webSite, extentedReport);
        cusSelfservice.get();
        Log.pass("Customer Self Service page is displayed : " + webSite, driver, extentedReport,
            true);
        cusSelfservice.clickBtnSignIn(extentedReport, driver);

        Log.pass("Sign in button displayed and  " + webSite, driver, extentedReport, true);

        CustomerSigninPage ss_loginPage = new CustomerSigninPage(driver, extentedReport);

        SelfServiceCustomerDashboard Cust_Dashboard = ss_loginPage.loginToSSPCustomerSelfService(
            user_Email, UI_Rfresh_temp_password, extentedReport, false);

        Log.pass("CustomerDashboard Page is displayed  : " + webSite, driver, extentedReport, true);

        Log.softAssertThat(true, "Successfully logged into SelfService", "Login failed", driver,
            extentedReport, true);
        Log.message("Logged in User id:" + user_Email + "& Password:" + password, driver,
            extentedReport);

   
        Cust_Dashboard.VerifyPolicyStartDate(policyDetails.get("InceptionDate"), extentedReport);
        Log.pass("Start Date matched  : " + webSite, driver, extentedReport, true);

        Cust_Dashboard.VerifyPolicyEndDate(policyDetails.get("ExpiryDate"), extentedReport);
        Log.pass("End Date matched  : " + webSite, driver, extentedReport, true);


      } else {
        Log.fail(
            "Failed - NB Policy Created in " + policyDetails.get("Status").toString() + " status",
            driver, extentedReport, true);
      }
      Log.testCaseResult(extentedReport);
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
    } finally {
      Log.endTestCase(extentedReport);
    // driver.quit();
    }

  }



  @Test(description = "Suspended contact is able to log in to SS and verify Notification banner",
      dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
  public void TC_004(String browser) throws Exception {

    // Get the web driver instance
    String tcId = "TC_004";
    final WebDriver driver = WebDriverFactory.get(browser);
    HashMap<String, String> testData = getTestData(tcId);

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String description = testData.get("Description");

    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");

    String UI_Rfresh_userName = testData.get("UserName");
    String UI_Rfresh_password = testData.get("Password");
    String UI_Rfresh_description = testData.get("Description");
    String UI_Rfresh_name = testData.get("UI_Refresh_ManageContact_Name");
    String UI_Rfresh_role_system_user_txt = testData.get("UI_Refresh_role_systemuser");
    String UI_Rfresh_pricipalid = testData.get("UI_Refresh_pricipalid");
    String UI_Rfresh_temp_password = testData.get("UI_Refresh_Password");
    String UI_Rfresh_date = testData.get("UI_Refresh_StartDate");
    String UI_Rfresh_Country = testData.get("UI_Refresh_Country");

    String language = testData.get("UI_Refresh_language");
    Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");

    ExtentTest extentedReport = addTestInfo(tcId, description);

    try {

      // Navigate to Login Page
      LoginPage loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      HomePage homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      SearchPage searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      HashMap<String, String> custDetails =
          searchPage.enterCustomerDetails(testData, true, extentedReport);

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
      custdashboardpage.enterNewQuotePastDate(5, 1, extentedReport, true);
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
      HashMap<String, String> policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));

      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);



        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");

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
        
         * Click on Edit Details
         * 
         * custdashboardPage.click_on_Edit_Details_link(extentedReport); EC_EditPersonalDetails
         * editdetails =new EC_EditPersonalDetails(driver, extentedReport);
         * editdetails.Suspend_Contact_GDPR_Right_to_Restrict_Checkbox(extentedReport);
         * editdetails.clickBtnsave(extentedReport, driver);
         

        // UI Refresh
        UIRefresh_Login login =
            new UIRefresh_Login(driver, website_UIRefresh, extentedReport).get();
        Log.pass("UI Refresh Login Page : " + website_UIRefresh, driver, extentedReport, true);
        UIRefresh_Dashboard dashboard =
            login.loginToUIRefresh(userName, password, false, extentedReport); // Login to the
                                                                               // application
        dashboard.clickOnContact(extentedReport);
        dashboard.clickOnManageContact(extentedReport);

        UIRefresh_Manage_contacts manageContacts =
            new UIRefresh_Manage_contacts(driver, extentedReport);
        manageContacts.enterName(custDetails.get("Last Name"));
        manageContacts.clickBtnSearch(extentedReport, driver);
        manageContacts.clickBtnEdit(extentedReport, driver);
        UIRefrsh_EditPersonalContact editPersonalContact =
            new UIRefrsh_EditPersonalContact(driver, extentedReport);

        editPersonalContact.clickBtnRole(extentedReport, driver, false);
        editPersonalContact.clickBtnEdit(extentedReport, driver);
        // editPersonalContact.select_Role_from_Dropdown(UI_Rfresh_role_system_user_txt,
        // extentedReport);

        // editPersonalContact.clickBtnAdd(extentedReport, driver);
        UIRefresh_System_User_Role userrole =
            new UIRefresh_System_User_Role(driver, extentedReport);
        String user_Email = custDetails.get("Last Name") + "@emailssp.com";
        userrole.enter_PrincipalID(custDetails.get("Last Name") + "@emailssp.com3");


        // userrole.select_Country(UI_Rfresh_Country, extentedReport);
        // userrole.select_Language(language, extentedReport);

        userrole.tick_user_Must_change_Checkbox(extentedReport);
        userrole.tickPasswordResetCheckbox(extentedReport);
        userrole.enter_TemporaryPassword(UI_Rfresh_temp_password);
        // userrole.enterDate(UI_Rfresh_date);
        userrole.clickBtnsave(extentedReport, driver);
        UIRefrsh_EditPersonalContact editPersonalContact_complete =
            new UIRefrsh_EditPersonalContact(driver, extentedReport);
        editPersonalContact_complete.clickBtnCompletetab(extentedReport, driver);
        UIRefresh_Manage_contacts manageContacts_complete =
            new UIRefresh_Manage_contacts(driver, extentedReport);
        manageContacts_complete.clickBtnComplete(extentedReport, driver);
        Log.pass(
            "Sucessfully updated the Principal ID and PassWord of Contact : " + website_UIRefresh,
            driver, extentedReport, true);

        
         * Login in to EC and Suspend the user
         
        // Navigate to Login Page
        LoginPage loginPageEC = new LoginPage(driver, website_EC, extentedReport).get();
        Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

        // Login to the application
        HomePage homePageEC = loginPageEC.loginToSSP(userName, password, false, extentedReport);
        Log.softAssertThat(homePageEC.verifyHomePage(), "Successfully logged into UXP Home Page",
            "Login failed", driver, extentedReport, true);
        Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
            extentedReport);

        // Click on Take Call link
        homePageEC.clickTakeCall(extentedReport);
        homePageEC.clickMyBrands(brandname, extentedReport);
        // Search with Valid Policy Number
        searchPage.searchValidPolicy(policyDetails.get("PolicyNo"), true, extentedReport);
        CustDashboardPage custdashboardPageec =
            searchPage.selectPolicy_from_SearchPage(true, extentedReport);
        Log.message("Navigated to Cutomer Dashboard after selecting policy from search page",
            driver, extentedReport, true);

        // Verifying Customer Details
        custdashboardPageec.clickPassVerification(extentedReport, true);
        custdashboardPageec.verifyCustomerDashboardPage();
        Log.softAssertThat(
            custdashboardPageec.verifyContactName(
                title + " " + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
                extentedReport, true),
            "Verified FirstName and LastName on Customer Dashboard : "
                + custDetails.get("First Name") + " " + custDetails.get("Last Name"),
            "Not Verified Customer Name on Dashboard", driver, extentedReport, true);
        
         * Click on Edit Details
         
        custdashboardPageec.click_on_Edit_Details_link(extentedReport);
        EC_EditPersonalDetails editdetailsec = new EC_EditPersonalDetails(driver, extentedReport);
        editdetailsec.Suspend_Contact_GDPR_Right_to_Restrict_Checkbox(extentedReport);
        editdetailsec.clickBtnsave(extentedReport, driver);


        Log.pass("Suspended the Contact  : " + website_EC, driver, extentedReport, true);



        // Self Service
        CustomerSelfService cusSelfservice =
            new CustomerSelfService(driver, webSite, extentedReport);
        cusSelfservice.get();
        Log.pass("Customer Self Service page is displayed : " + webSite, driver, extentedReport,
            true);
        cusSelfservice.clickBtnSignIn(extentedReport, driver);

        Log.pass("Sign in button displayed and  " + webSite, driver, extentedReport, true);

        CustomerSigninPage ss_loginPage = new CustomerSigninPage(driver, extentedReport);

        SelfServiceCustomerDashboard Cust_Dashboard = ss_loginPage.loginToSSPCustomerSelfService(
            user_Email, UI_Rfresh_temp_password, extentedReport, false);

        Log.pass("CustomerDashboard Page is displayed  : " + webSite, driver, extentedReport, true);

        Log.softAssertThat(true, "Successfully logged into SelfService", "Login failed", driver,
            extentedReport, true);
        Log.message("Logged in User id:" + user_Email + "& Password:" + password, driver,
            extentedReport);

       

        Cust_Dashboard.VerifyPolicyStartDate(policyDetails.get("InceptionDate"), extentedReport);
        Log.pass("Start Date Matched  : " + webSite, driver, extentedReport, true);

        Cust_Dashboard.VerifyPolicyStartDate(policyDetails.get("ExpiryDate"), extentedReport);
        Log.pass("End Date Matched  : " + webSite, driver, extentedReport, true);


        Cust_Dashboard.VerifyPolicy_Warning_Message_Textmatched(
            "Warning: It is not currently possible to make any changes to your policy/ies or your personal details online. Please call customer service for further assistance.",
            extentedReport);
        Log.pass("Warning Message Matched  : " + webSite, driver, extentedReport, true);
        Log.softAssertThat(true, "Warning message Displayed : Customer is Suspended",
            "Contact is not Suspended", driver, extentedReport, true);
        Cust_Dashboard.VerifyPolicy_Warning_Message_is_displayed(extentedReport);

        Log.pass("Warning Message Displayed  : " + webSite, driver, extentedReport, true);

        Log.testCaseResult(extentedReport);



      } else {
        Log.fail("Failed - Unable to validate Renewal Summary Data", driver, extentedReport, true);
      }
      Log.testCaseResult(extentedReport);
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
    } finally {
      Log.endTestCase(extentedReport);
      // driver.quit();
    }

  }

  @Test(description = "Corporate Contact Creation and adding Authorized contact",
      dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
  public void TC_005(String browser) throws Exception {

    String tcId = "TC_005";
    final WebDriver driver = WebDriverFactory.get(browser);
    HashMap<String, String> testData = getTestData(tcId);

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String description = testData.get("Description");

    String brandname = testData.get("Brand Name");
    Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");

    ExtentTest extentedReport = addTestInfo(tcId, description);

    try {

      // Navigate to Login Page
      LoginPage loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("SIAAS Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      HomePage homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);

      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      SearchPage searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search Page Verified", "Search Page not Verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      searchPage.createCorporateContact(testData, extentedReport, true);
      CustDashboardPage custdashboardPage =
          searchPage.confirmCreateCustomer_CC(true, extentedReport);

      Log.softAssertThat(
          custdashboardPage.verifyContactName(testData.get("Trade Name"), extentedReport, true),
          "Verified Business name on Customer Dashboard",
          "Verification of Business name on Customer Dashboard failed", driver, extentedReport,
          true);

      Log.softAssertThat(custdashboardPage.verifyEditBusinesAddress(extentedReport, true),
          "Replaced address reflects in customer dashboard page",
          "Failed to replace the address in customer dashboard page", driver, extentedReport, true);

      Log.testCaseResult(extentedReport);
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
    } // catch
    finally {
      Log.endTestCase(extentedReport);
      driver.quit();
    } // finally

  }



  @Test(
      description = "Renewal HouseHold2 Policy created from EC and login to SS and validate Renewal Summary page",
      dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
  public void TC_006(String browser) throws Exception {

    // Get the web driver instance
    String tcId = "TC_006";
    final WebDriver driver = WebDriverFactory.get(browser);
    HashMap<String, String> testData = getTestData(tcId);

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String description = testData.get("Description");

    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");

    String UI_Rfresh_userName = testData.get("UserName");
    String UI_Rfresh_password = testData.get("Password");
    String UI_Rfresh_description = testData.get("Description");
    String UI_Rfresh_name = testData.get("UI_Refresh_ManageContact_Name");
    String UI_Rfresh_role_system_user_txt = testData.get("UI_Refresh_role_systemuser");
    String UI_Rfresh_pricipalid = testData.get("UI_Refresh_pricipalid");
    String UI_Rfresh_temp_password = testData.get("UI_Refresh_Password");
    String UI_Rfresh_date = testData.get("UI_Refresh_StartDate");
    String UI_Rfresh_Country = testData.get("UI_Refresh_Country");

    String language = testData.get("UI_Refresh_language");
    Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");

    ExtentTest extentedReport = addTestInfo(tcId, description);

    try {

      // Navigate to Login Page
      LoginPage loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      HomePage homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      SearchPage searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      HashMap<String, String> custDetails =
          searchPage.enterCustomerDetails(testData, true, extentedReport);

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
      custdashboardpage.enterNewQuotePastDate(5, 1, extentedReport, true);
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
      HashMap<String, String> policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));

      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);



        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");

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

        // driver.close();
        // UI Refresh
        UIRefresh_Login login =
            new UIRefresh_Login(driver, website_UIRefresh, extentedReport).get();
        Log.pass("UI Refresh Login Page : " + website_UIRefresh, driver, extentedReport, true);
        UIRefresh_Dashboard dashboard =
            login.loginToUIRefresh(userName, password, false, extentedReport); // Login to the
                                                                               // application
        dashboard.clickOnContact(extentedReport);
        dashboard.clickOnManageContact(extentedReport);

        UIRefresh_Manage_contacts manageContacts =
            new UIRefresh_Manage_contacts(driver, extentedReport);
        manageContacts.enterName(custDetails.get("Last Name"));
        manageContacts.clickBtnSearch(extentedReport, driver);
        manageContacts.clickBtnEdit(extentedReport, driver);
        UIRefrsh_EditPersonalContact editPersonalContact =
            new UIRefrsh_EditPersonalContact(driver, extentedReport);

        editPersonalContact.clickBtnRole(extentedReport, driver, false);
        editPersonalContact.clickBtnEdit(extentedReport, driver);
        // editPersonalContact.select_Role_from_Dropdown(UI_Rfresh_role_system_user_txt,
        // extentedReport);

        // editPersonalContact.clickBtnAdd(extentedReport, driver);
        UIRefresh_System_User_Role userrole =
            new UIRefresh_System_User_Role(driver, extentedReport);
        String user_Email = custDetails.get("Last Name") + "@emailssp.com";
        userrole.enter_PrincipalID(custDetails.get("Last Name") + "@emailssp.com3");


        // userrole.select_Country(UI_Rfresh_Country, extentedReport);
        // userrole.select_Language(language, extentedReport);

        userrole.tick_user_Must_change_Checkbox(extentedReport);
        userrole.tickPasswordResetCheckbox(extentedReport);
        userrole.enter_TemporaryPassword(UI_Rfresh_temp_password);
        // userrole.enterDate(UI_Rfresh_date);
        userrole.clickBtnsave(extentedReport, driver);
        UIRefrsh_EditPersonalContact editPersonalContact_complete =
            new UIRefrsh_EditPersonalContact(driver, extentedReport);
        editPersonalContact_complete.clickBtnCompletetab(extentedReport, driver);
        UIRefresh_Manage_contacts manageContacts_complete =
            new UIRefresh_Manage_contacts(driver, extentedReport);
        manageContacts_complete.clickBtnComplete(extentedReport, driver);
        Log.pass(
            "Sucessfully updated the Principal ID and PassWord of Contact : " + website_UIRefresh,
            driver, extentedReport, true);
        // Self Service
        CustomerSelfService cusSelfservice =
            new CustomerSelfService(driver, webSite, extentedReport).get();
        // cusSelfservice.get();
        Log.pass("Customer Self Service page is displayed : " + webSite, driver, extentedReport,
            true);
        cusSelfservice.clickBtnSignIn(extentedReport, driver);

        Log.pass("Sign in button displayed and  " + webSite, driver, extentedReport, true);

        CustomerSigninPage ss_loginPage = new CustomerSigninPage(driver, extentedReport);

        SelfServiceCustomerDashboard Cust_Dashboard = ss_loginPage.loginToSSPCustomerSelfService(
            user_Email, UI_Rfresh_temp_password, extentedReport, false);

        Log.pass("CustomerDashboard Page is displayed  : " + webSite, driver, extentedReport, true);

        Log.softAssertThat(true, "Successfully logged into SelfService", "Login failed", driver,
            extentedReport, true);
        Log.message("Logged in User id:" + user_Email + "& Password:" + password, driver,
            extentedReport);

        
        Cust_Dashboard.VerifyPolicyStartDate(policyDetails.get("InceptionDate"), extentedReport);
        Log.pass("Start Date matched  : " + webSite, driver, extentedReport, true);

        Cust_Dashboard.VerifyPolicyEndDate(policyDetails.get("ExpiryDate"), extentedReport);
        Log.pass("End Date matched  : " + webSite, driver, extentedReport, true);



      } else {
        Log.fail(
            "Failed - NB Policy Created in " + policyDetails.get("Status").toString() + " status",
            driver, extentedReport, true);
      }
      Log.testCaseResult(extentedReport);
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
    } finally {
      Log.endTestCase(extentedReport);
      driver.quit();
    }

  }
  




  @Test(description = "Validate Renewal Reminder for multiple policy of the same product",
      dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
  public void TC_007(String browser) throws Exception {

    // Get the web driver instance
    String tcId = "TC_007";
    final WebDriver driver = WebDriverFactory.get(browser);
    HashMap<String, String> testData = getTestData(tcId);
    HashMap<String, String> policyDetails = null;
    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String description = testData.get("Description");

    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");

    String UI_Rfresh_userName = testData.get("UserName");
    String UI_Rfresh_password = testData.get("Password");
    String UI_Rfresh_description = testData.get("Description");
    String UI_Rfresh_name = testData.get("UI_Refresh_ManageContact_Name");
    String UI_Rfresh_role_system_user_txt = testData.get("UI_Refresh_role_systemuser");
    String UI_Rfresh_pricipalid = testData.get("UI_Refresh_pricipalid");
    String UI_Rfresh_temp_password = testData.get("UI_Refresh_Password");
    String UI_Rfresh_date = testData.get("UI_Refresh_StartDate");
    String UI_Rfresh_Country = testData.get("UI_Refresh_Country");

    String language = testData.get("UI_Refresh_language");
    Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");

    ExtentTest extentedReport = addTestInfo(tcId, description);

    try {

      // Navigate to Login Page
      LoginPage loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      HomePage homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      SearchPage searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      HashMap<String, String> custDetails =
          searchPage.enterCustomerDetails(testData, true, extentedReport);

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
        custdashboardpage.enterNewQuotePastDate(5, 1, extentedReport, true);
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

        if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
          Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
              true);



          Log.message("Renewal Policy Created Successfully in Accepted status", driver,
              extentedReport, true);
          policyDetails.get("PolicyNo");

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

          // driver.close();
        //invite Renewal

          custdashboardpage.performRenewals_LapseRenewal("Invite Renewal", extentedReport, true);
         // custdashboardpage.clickContinueWarningMsg(extentedReport, true);
          newquotepage.clickNextOne(extentedReport);
          newquotepage.clickNextTwo(extentedReport);
          newquotepage.clickView(extentedReport);
          newquotepage.clickAgree(extentedReport);
          newquotepage.clickGetQuote(extentedReport);
          
       //   #C2__C6__navdrop > div > div > ul > li.dropdown.renewal-quote.drop-inline.dropdown-hover.open > ul > li:nth-child(2) > a
          newquotepage.clickQuoteSave("Renew", "Renewal Quote", extentedReport);
          
          custdashboardpage.selecteRenewalVaration(extentedReport);
          newquotepage.generateRenewVariation("Renewal Quote two");


        } else {
          Log.fail(
              "Failed - NB Policy Created in " + policyDetails.get("Status").toString() + " status",
              driver, extentedReport, true);
        }
      }
      // UI Refresh
      UIRefresh_Login login = new UIRefresh_Login(driver, website_UIRefresh, extentedReport).get();
      Log.pass("UI Refresh Login Page : " + website_UIRefresh, driver, extentedReport, true);
      UIRefresh_Dashboard dashboard =
          login.loginToUIRefresh(userName, password, false, extentedReport); // Login to the
                                                                             // application
      dashboard.clickOnContact(extentedReport);
      dashboard.clickOnManageContact(extentedReport);

      UIRefresh_Manage_contacts manageContacts =
          new UIRefresh_Manage_contacts(driver, extentedReport);
      manageContacts.enterName(custDetails.get("Last Name"));
      manageContacts.clickBtnSearch(extentedReport, driver);
      manageContacts.clickBtnEdit(extentedReport, driver);
      UIRefrsh_EditPersonalContact editPersonalContact =
          new UIRefrsh_EditPersonalContact(driver, extentedReport);

      editPersonalContact.clickBtnRole(extentedReport, driver, false);
      editPersonalContact.clickBtnEdit(extentedReport, driver);
      // editPersonalContact.select_Role_from_Dropdown(UI_Rfresh_role_system_user_txt,
      // extentedReport);

      // editPersonalContact.clickBtnAdd(extentedReport, driver);
      UIRefresh_System_User_Role userrole = new UIRefresh_System_User_Role(driver, extentedReport);
      String user_Email = custDetails.get("Last Name") + "@emailssp.com";
      userrole.enter_PrincipalID(custDetails.get("Last Name") + "@emailssp.com3");


      // userrole.select_Country(UI_Rfresh_Country, extentedReport);
      // userrole.select_Language(language, extentedReport);

      userrole.tick_user_Must_change_Checkbox(extentedReport);
      userrole.tickPasswordResetCheckbox(extentedReport);
      userrole.enter_TemporaryPassword(UI_Rfresh_temp_password);
      // userrole.enterDate(UI_Rfresh_date);
      userrole.clickBtnsave(extentedReport, driver);
      UIRefrsh_EditPersonalContact editPersonalContact_complete =
          new UIRefrsh_EditPersonalContact(driver, extentedReport);
      editPersonalContact_complete.clickBtnCompletetab(extentedReport, driver);
      UIRefresh_Manage_contacts manageContacts_complete =
          new UIRefresh_Manage_contacts(driver, extentedReport);
      manageContacts_complete.clickBtnComplete(extentedReport, driver);
      Log.pass(
          "Sucessfully updated the Principal ID and PassWord of Contact : " + website_UIRefresh,
          driver, extentedReport, true);
      // Self Service
      CustomerSelfService cusSelfservice =
          new CustomerSelfService(driver, webSite, extentedReport).get();
      // cusSelfservice.get();
      Log.pass("Customer Self Service page is displayed : " + webSite, driver, extentedReport,
          true);
      cusSelfservice.clickBtnSignIn(extentedReport, driver);

      Log.pass("Sign in button displayed and  " + webSite, driver, extentedReport, true);

      CustomerSigninPage ss_loginPage = new CustomerSigninPage(driver, extentedReport);

      SelfServiceCustomerDashboard Cust_Dashboard = ss_loginPage.loginToSSPCustomerSelfService(
          user_Email, UI_Rfresh_temp_password, extentedReport, false);

      Log.pass("CustomerDashboard Page is displayed  : " + webSite, driver, extentedReport, true);

      Log.softAssertThat(true, "Successfully logged into SelfService", "Login failed", driver,
          extentedReport, true);
      Log.message("Logged in User id:" + user_Email + "& Password:" + password, driver,
          extentedReport);

      
      Cust_Dashboard.VerifyPolicyStartDate(policyDetails.get("InceptionDate"), extentedReport);
      Log.pass("Start Date matched  : " + webSite, driver, extentedReport, true);

      Cust_Dashboard.VerifyPolicyEndDate(policyDetails.get("ExpiryDate"), extentedReport);
      Log.pass("End Date matched  : " + webSite, driver, extentedReport, true);


      Log.testCaseResult(extentedReport);
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
    } finally {
      Log.endTestCase(extentedReport);
      // System.out.println(policyDetails.keySet());
      // System.out.println(policyDetails.entrySet());
      driver.quit();
    }

  }


  @Test(description = "Terminated contact is not allowed to login Selfservice if Renewal Exits",
      dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
  public void TC_008(String browser) throws Exception {

    // Get the web driver instance
    String tcId = "TC_008";
    final WebDriver driver = WebDriverFactory.get(browser);
    HashMap<String, String> testData = getTestData(tcId);

    String userName = testData.get("UserName");
    String password = testData.get("Password");
    String description = testData.get("Description");

    String title = testData.get("Title");
    String brandname = testData.get("Brand Name");
    String Corecover = testData.get("Cover");

    String UI_Rfresh_userName = testData.get("UserName");
    String UI_Rfresh_password = testData.get("Password");
    String UI_Rfresh_description = testData.get("Description");
    String UI_Rfresh_name = testData.get("UI_Refresh_ManageContact_Name");
    String UI_Rfresh_role_system_user_txt = testData.get("UI_Refresh_role_systemuser");
    String UI_Rfresh_pricipalid = testData.get("UI_Refresh_pricipalid");
    String UI_Rfresh_temp_password = testData.get("UI_Refresh_Password");
    String UI_Rfresh_date = testData.get("UI_Refresh_StartDate");
    String UI_Rfresh_Country = testData.get("UI_Refresh_Country");

    String language = testData.get("UI_Refresh_language");
    Log.testCaseInfo(description + "<small><b><i>[" + browser + "]</b></i></small>");

    ExtentTest extentedReport = addTestInfo(tcId, description);

    try {
      
      

      // Navigate to Login Page
      LoginPage loginPage = new LoginPage(driver, website_EC, extentedReport).get();
      Log.pass("Engagement Centre Landing Page : " + website_EC, driver, extentedReport, true);

      // Login to the application
      HomePage homePage = loginPage.loginToSSP(userName, password, false, extentedReport);
      Log.softAssertThat(homePage.verifyHomePage(), "Successfully logged into UXP Home Page",
          "Login failed", driver, extentedReport, true);
      Log.message("Logged in User id:" + userName + "& Password:" + password, driver,
          extentedReport);
      // Click on Take Call link
      homePage.clickTakeCall(extentedReport);
      homePage.clickMyBrands(brandname, extentedReport);
      SearchPage searchPage = new SearchPage(driver, extentedReport).get();
      Log.softAssertThat(
          searchPage.uielement.verifyPageElements(Arrays.asList("txtPolicyNumber"), searchPage),
          "Search page is verified", "Search Page is not verified", driver, extentedReport, true);

      // Enter Customer Details
      searchPage.clickCreateCustomer(true, extentedReport);
      HashMap<String, String> custDetails =
          searchPage.enterCustomerDetails(testData, true, extentedReport);

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
      custdashboardpage.enterNewQuotePastDate(5, 1, extentedReport, true);
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
      HashMap<String, String> policyDetails = custdashboardpage.getPolicyURN(extentedReport);
      Log.message("InceptionDate NB:" + policyDetails.get("InceptionDate"));
      Log.message("ExpiryDate NB:" + policyDetails.get("ExpiryDate"));

      if (policyDetails.get("Status").toString().equalsIgnoreCase("Accepted")) {
        Log.pass("Renewal Policy Created Successfully in accepted status", driver, extentedReport,
            true);



        Log.message("Renewal Policy Created Successfully in Accepted status", driver,
            extentedReport, true);
        policyDetails.get("PolicyNo");

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

        // driver.close();
        // UI Refresh
        UIRefresh_Login login =
            new UIRefresh_Login(driver, website_UIRefresh, extentedReport).get();
        Log.pass("UI Refresh Login Page : " + website_UIRefresh, driver, extentedReport, true);
        UIRefresh_Dashboard dashboard =
            login.loginToUIRefresh(userName, password, false, extentedReport); // Login to the
                                                                               // application
        dashboard.clickOnContact(extentedReport);
        dashboard.clickOnManageContact(extentedReport);

        UIRefresh_Manage_contacts manageContacts =
            new UIRefresh_Manage_contacts(driver, extentedReport);
        manageContacts.enterName(custDetails.get("Last Name"));
        manageContacts.clickBtnSearch(extentedReport, driver);
        manageContacts.clickBtnEdit(extentedReport, driver);
        UIRefrsh_EditPersonalContact editPersonalContact =
            new UIRefrsh_EditPersonalContact(driver, extentedReport);

        editPersonalContact.clickBtnRole(extentedReport, driver, false);
        editPersonalContact.clickBtnEdit(extentedReport, driver);
        // editPersonalContact.select_Role_from_Dropdown(UI_Rfresh_role_system_user_txt,
        // extentedReport);

        // editPersonalContact.clickBtnAdd(extentedReport, driver);
        UIRefresh_System_User_Role userrole =
            new UIRefresh_System_User_Role(driver, extentedReport);
        String user_Email = custDetails.get("Last Name") + "@emailssp.com";
        userrole.enter_PrincipalID(custDetails.get("Last Name") + "@emailssp.com3");


        // userrole.select_Country(UI_Rfresh_Country, extentedReport);
        // userrole.select_Language(language, extentedReport);

        userrole.tick_user_Must_change_Checkbox(extentedReport);
        userrole.tickPasswordResetCheckbox(extentedReport);
        userrole.enter_TemporaryPassword(UI_Rfresh_temp_password);
        // userrole.enterDate(UI_Rfresh_date);
        userrole.clickBtnsave(extentedReport, driver);
        UIRefrsh_EditPersonalContact editPersonalContact_complete =
            new UIRefrsh_EditPersonalContact(driver, extentedReport);
        editPersonalContact_complete.clickBtnCompletetab(extentedReport, driver);
        UIRefresh_Manage_contacts manageContacts_complete =
            new UIRefresh_Manage_contacts(driver, extentedReport);
        manageContacts_complete.clickBtnComplete(extentedReport, driver);
        Log.pass(
            "Sucessfully updated the Principal ID and PassWord of Contact : " + website_UIRefresh,
            driver, extentedReport, true);
        // Self Service
        CustomerSelfService cusSelfservice =
            new CustomerSelfService(driver, webSite, extentedReport);
        cusSelfservice.get();
        Log.pass("Customer Self Service page is displayed : " + webSite, driver, extentedReport,
            true);
        cusSelfservice.clickBtnSignIn(extentedReport, driver);

        Log.pass("Sign in button displayed and  " + webSite, driver, extentedReport, true);

        CustomerSigninPage ss_loginPage = new CustomerSigninPage(driver, extentedReport);

        SelfServiceCustomerDashboard Cust_Dashboard = ss_loginPage.loginToSSPCustomerSelfService(
            user_Email, UI_Rfresh_temp_password, extentedReport, false);

        Log.pass("CustomerDashboard Page is displayed  : " + webSite, driver, extentedReport, true);

        Log.softAssertThat(true, "Successfully logged into SelfService", "Login failed", driver,
            extentedReport, true);
        Log.message("Logged in User id:" + user_Email + "& Password:" + password, driver,
            extentedReport);

       

        Cust_Dashboard.VerifyPolicyStartDate(policyDetails.get("InceptionDate"), extentedReport);
        Log.pass("Start Date matched  : " + webSite, driver, extentedReport, true);

        Cust_Dashboard.VerifyPolicyStartDate(policyDetails.get("ExpiryDate"), extentedReport);
        Log.pass("End Date matched  : " + webSite, driver, extentedReport, true);


        Log.testCaseResult(extentedReport);



      } else {
        Log.fail(
            "Failed - NB Policy Created in " + policyDetails.get("Status").toString() + " status",
            driver, extentedReport, true);
      }
      Log.testCaseResult(extentedReport);
    } catch (Exception e) {
      Log.exception(e, driver, extentedReport);
    } finally {
      Log.endTestCase(extentedReport);
      driver.quit();
    }

  }
  


}


*/