package com.ssp.uxp_SSPages;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;
import cucumber.stepdefination.runner.TestRunnerRenewals;


/**
 * @author Varun Wadhwa
 *
 */

public class YourQuotePageFromPP extends LoadableComponent<YourQuotePageFromPP> {

  private WebDriver driver;
  private Actions action;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  private ExtentTest extentedReport;
  private int wait;
  private int counter;
  
  private String buildingsCoverLimitPrefix = "div[id*='ComponentBLD_sumInsured_R";
  private String buildingsExcessPrefix = "div[id*='ComponentBLD_excess_R";
  private String buildingsPremiumPrefix = "div[id*='ComponentBLD_net_amount_R";
  
  private String contentsCoverLimitPrefix = "div[id*='ComponentCNT_sumInsured_R";
  private String contentsExcessPrefix = "div[id*='ComponentCNT_excess_R";
  private String contentsPremiumPrefix = "div[id*='ComponentCNT_net_amount_R";
  
  private String amountSuffix = "'] span";
  private String currencySuffix = "'][class*='DG-table-prefix'] div";
  private String originalSSRenewalPremium;
  private String newSSRenewalPremium;
  
  @FindBy(css = "div#C2__TXT_A759A2CAAFF75D551474503 >div>nav>div>ul>li>span[class*='step-text']")
  List<WebElement> BreadcrumbTextList;
  
  @FindBy(xpath = "//h2[contains(text(),'Your quote')]")
  WebElement yourQuoteHeadText;
  
  @FindBy(css = "div#helpContent")
  WebElement yourQuoteHelpIcon;
  
  @FindBy(css = "span.tetris_helpText")
  WebElement yourQuoteHelpTextMessage;
  
  @FindBy(css = "div#C2__FMT_58B60997B46F408D121447")
  WebElement yourPremiumSection;
  
  @FindBy(css = "div#C2__p1_HEAD_9E12E862CA0AB8DC31536>div")
  WebElement yourPremiumHeadText;
  
  @FindBy(xpath = "//div[contains(text(),'Your renewal premium')]")
  WebElement renewalQuotePremiumValue;
  
  @FindBy(xpath = "//div[contains(text(),'Last year�s premium')]")
  WebElement previousPolicyPremiumValue;
  
  @FindBy(css = "#C2__p1_HEAD_F8AC30C04A06F1C8459825 > div")
  WebElement premiumSectionTextLineOne;
  
  @FindBy(css = "#C2__p1_HEAD_F8AC30C04A06F1C8461637 > div")
  WebElement premiumSectionTextLineTwo;
  
  @FindBy(xpath = "//h2[contains(text(),'Buildings')]")
  WebElement buildingsHeadText;
  
  @FindBy(xpath = "//h2[contains(text(),'Contents')]")
  WebElement contentsHeadText;
  
  @FindBy(
      css = "div[id*='C2__C1__FMT_474B1A717265DD51202612_R']:not([style*='display: none'])")
  List<WebElement> buildingsRowCount;
  
  @FindBy(
      css = "div[id*='C2__C1__FMT_474B1A717265DD51202614_R']:not([style*='display: none']) div[class*='table-prefix']")
  List<WebElement> buildingsCoverCurrency;
  
  @FindBy(
      css = "div[id*='C2__C1__FMT_474B1A717265DD51202614_R']:not([style*='display: none'])>div span[id]")
  List<WebElement> buildingsCoverRow;
  
  @FindBy(
      css = "div[id*='C2__C1__FMT_474B1A717265DD51202958_R']:not([style*='display: none'])")
  List<WebElement> contentsRowCount;
  
  @FindBy(
      css = "div[id*='C2__C1__FMT_474B1A717265DD51202960_R']:not([style*='display: none']) div[class*='table-prefix']")
  List<WebElement> contentsCoverCurrency;
  
  @FindBy(
      css = "div[id*='C2__C1__FMT_474B1A717265DD51202960_R']:not([style*='display: none'])>div span[id]")
  List<WebElement> contentsCoverRow;
  
  @FindBy(xpath = "//h2[contains(text(),'Additional Cover Options')]")
  WebElement additionalCoverHeadText;
  
  @FindBy(
      css = "div[id*='C2__C1__FMT_474B1A717265DD51203730_R']")
  List<WebElement> additionalCoverTypeRowCount;
  
  @FindBy(
      css = "div[id*='C2__C1__FMT_474B1A717265DD51203730_R1'] span[id*='label']")
  WebElement homeEmergencyCoverType; 
  
  @FindBy(css = "div[id*='ComponentHE_sumInsured_R1'] span")
  WebElement homeEmergencyCoverLimit; 
  
  @FindBy(css = "div[id*='ComponentHE_sumInsured_R1'][class*='DG-table-prefix'] div")
  WebElement homeEmergencyCoverLimitCurrency; 
  
  @FindBy(
      css = "div[id*='ComponentHE_net_amount_R1'] span")
  WebElement homeEmergencyCoverPremium;
  
  @FindBy(
      css = "div[id*='ComponentHE_net_amount_R1'][class*='DG-table-prefix'] div")
  WebElement homeEmergencyCoverPremiumCurrency;
  
  @FindBy(
      css = "div[id*='Delete_PricePresentationTable_ComponentHH_ComponentPE_ComponentPT_ComponentHE']:not([style*='display: none']) button")
  WebElement homeEmergencyRemoveButton;
  
  @FindBy(
      css = "div[id*='Add_PricePresentationTable__ComponentHH_ComponentPE_ComponentPT_ComponentHE']:not([style*='display: none']) button")
  WebElement homeEmergencyAddButton;
  
  @FindBy(
      css = "div[id*='C2__C1__FMT_474B1A717265DD51203730_R2'] span[id*='label']")
  WebElement legalExpensesCoverType; 
  
  @FindBy(
      css = "div[id*='ComponentHE_sumInsured_R2'] span")
  WebElement legalExpensesCoverLimit; 
  
  @FindBy(css = "div[id*='ComponentHE_sumInsured_R2'][class*='DG-table-prefix'] div")
  WebElement legalExpensesCoverLimitCurrency; 
  
  @FindBy(
      css = "div[id*='ComponentHE_net_amount_R2'] span")
  WebElement legalExpensesCoverPremium; 
  
  @FindBy(
      css = "div[id*='ComponentHE_net_amount_R2'][class*='DG-table-prefix'] div")
  WebElement legalExpensesCoverPremiumCurrency;
  
  @FindBy(
      css = "div[id*='Delete_PricePresentationTable_ComponentHH_ComponentPE_ComponentLE']:not([style*='display: none']) button")
  WebElement legalExpenseRemoveButton;
  
  @FindBy(
      css = "div[id*='Add_PricePresentationTable__ComponentHH_ComponentPE_ComponentLE']:not([style*='display: none']) button")
  WebElement legalExpenseAddButton;

  @FindBy(xpath = "//b[contains(text(),'Terms and Conditions')]")
  WebElement termsandConditionsHeadingText;

  @FindBy(css = "div[id*=C2__C2__row_QUE_304899FEFEF1DDEB353990_R1]")
  List<WebElement> termsandConditionsList;

  @FindBy(css = "button[class*='btn btn-default btn-close-tc btn-close-panel']")
  List<WebElement> termsandConditionsViewCloseButtons;

  @FindBy(css = "div[id*='QUE_304899FEFEF1DDEB822129_R1'] div[class='col-sm-12 noPadLft']")
  List<WebElement> termsandConditionsDescriptionMessage1and2;

  @FindBy(css = "div[id*='C2__FMT_72450C6A93D25290633273']")
  WebElement termsandConditionsRedMessageSection;

  @FindBy(css = "input[name*='TERMANDCONDITIONCHECKED']")
  WebElement checkBoxTandC;

  @FindBy(xpath = "//span[contains(text(),'accept the terms and conditions')]")
  WebElement checkboxMessage;

  @FindBy(xpath = "//div[contains(text(),'Please tick to confirm')]")
  WebElement warningMessage;

  @FindBy(css = "button[id*='C2__BUT_C840CB81AE04E7271878370']")
  WebElement recalculateButton;

  @FindBy(css = "button[id*='cancelQuote']")
  WebElement cancelQuoteButton;

  @FindBy(css = "button[title*='Buy']")
  WebElement buyButton;
  
  @FindBy(css = "button[title*='Save'][class*='mta-buy']")
  WebElement saveButton;
  
  @FindBy(css = "button[title='View my insurance details']")
  WebElement viewInsuranceButton;
  
  @FindBy(css = "div[id*='TXT']:not([style*='display: none']) p[class*='f_size14']")
  WebElement tandcGlobalMessage;
  
  @FindBy(css = "li[class*='dropdown'] a[class*='dropdown-toggle']")
  WebElement welcomeUserDropdown;
  
  @FindBy(xpath = "//a[contains(text(),'Back to Dashboard')]")
  WebElement welcomeUserDropdownBackToDash;
  
  @FindBy(xpath = "//a[contains(text(),'Sign Out')]")
  WebElement welcomeUserDropdownSignOut;
  
  @FindBy(css = "div[id*='C2__FMT_72450C6A93D25290633273'] span[class*='mandatory-messageTC']")
  WebElement reqdFieldMsg;

  public YourQuotePageFromPP(WebDriver driver, ExtentTest extentedReport) {
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
    action = new Actions(driver);
  }

  @Override
  protected void isLoaded() {
    if (!isPageLoaded) {
      Assert.fail();
    }
    WaitUtils.waitForElementPresent(driver, yourQuoteHeadText, "Your Quote page did not open up.");
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

  /**
   * Return to Dashboard from Your Quote page
   * 
   * @return
   * @throws Exception
   */
  public SelfServiceCustomerDashboard returnToDashBoard(boolean screenshot) throws Exception{
    try{
      WaitUtils.waitForSpinner(driver);
      action.moveToElement(welcomeUserDropdown).build().perform();
      WaitUtils.waitForElement(driver, welcomeUserDropdown);
      WaitUtils.waitForelementToBeClickable(driver, welcomeUserDropdown, "Welcome dropdown not clickable");
      welcomeUserDropdown.click();
      WaitUtils.waitForElement(driver, welcomeUserDropdownBackToDash);
      WaitUtils.waitForelementToBeClickable(driver, welcomeUserDropdown, "Back to Dashboard not clickable");
      welcomeUserDropdownBackToDash.click();
      Log.message("User has clicked Back to dashboard button", driver, extentedReport, screenshot);
      WaitUtils.waitForSpinner(driver);
      return new SelfServiceCustomerDashboard(driver).get();
    }catch(Exception e){
      throw new Exception("Error on clicking back to dashboard button. "+e);
      }   
  }
  
  /**
   * Signout of application from Your Quote page
   * 
   * @return
   * @throws Exception
   */
  public CustomerSelfService signout(boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForOnlySpinner(driver);
      action.moveToElement(welcomeUserDropdown).build().perform();
      WaitUtils.waitForElement(driver, welcomeUserDropdown);
      WaitUtils.waitForelementToBeClickable(driver, welcomeUserDropdown,
          "Welcome dropdown not clickable");
      welcomeUserDropdown.click();
      WaitUtils.waitForElement(driver, welcomeUserDropdownSignOut);
      WaitUtils.waitForelementToBeClickable(driver, welcomeUserDropdownSignOut,
          "Sign Out not clickable");
      welcomeUserDropdownSignOut.click();
      Log.message("User has clicked Sign Out button", driver, extentedReport, screenshot);
      WaitUtils.waitForSpinner(driver);
      return new CustomerSelfService(driver, TestRunnerRenewals.website_SS, extentedReport).get();
    } catch (Exception e) {
      throw new Exception("Error on clicking signout button. "+e);
    }
  }
  
  /**
   * Verify different standard covers in the policy
   * 
   * @param cover
   * @return
   * @throws Exception
   */
  public boolean verifyPolicyComponents(HashMap<String,String>  testData, boolean screenshot) throws Exception {
    boolean status = true;
    StringBuffer message = new StringBuffer("Cover(s) not present - ");
    String cover = testData.get("Cover");
    try {
      switch (cover) {
        case "Buildings":
          if (!buildingsHeadText.isDisplayed()) {
            status = false;
            message.append("Building");
          }
        case "Buildings and Contents":
          if (!(buildingsHeadText.isDisplayed() && contentsHeadText.isDisplayed())) {
            status = false;
            message.append("Buiding & Contents");
          }
        case "Contents":
          if (!contentsHeadText.isDisplayed()) {
            status = false;
            message.append("Contents");
          }
      }
      return status;
    } catch (Exception e) {
      Log.fail("Issue in verifying policy cover.", driver, extentedReport, screenshot);
      throw new Exception("Issue in verifying policy cover. "+e);
    }
  }
  
  /**
   * To verify status of Add on covers in SS after action performed in EC/SS 
   * 
   * @param cover
   * @param action
   * @throws Exception
   */
  public void validateStatusAddOnCovers(String cover, String action, boolean screenshot) throws Exception{
    try {
      boolean status = false;
      String pastAction;
      if(action.equalsIgnoreCase("Add")){
        pastAction = "Added";
      }else
        pastAction = "Removed";
      switch (cover) {

        case "Home Emergency":           
          if(action.equalsIgnoreCase("Add") && homeEmergencyRemoveButton.isDisplayed()){                
            status = true;
          }          
          if(action.equalsIgnoreCase("Remove") && homeEmergencyAddButton.isDisplayed()){    
            status = true;     
          }
          break;

        case "Legal Expenses":
          if(action.equalsIgnoreCase("Add") && legalExpenseRemoveButton.isDisplayed()){                
            status = true;
          }          
          if(action.equalsIgnoreCase("Remove") && legalExpenseAddButton.isDisplayed()){    
            status = true;     
          }
          break;
      }
      Log.softAssertThat(status, "The addon cover "+cover+" is "+pastAction+" after "+action+" action",
          "The addon cover "+cover+" is not "+pastAction+" after "+action+" action", driver,
          extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while verification on status of Add-On Covers "+e);
    }
  }
  
  /**
   * To verify Add on covers functionality
   * 
   * @param cover
   * @param action
   * @throws Exception
   */
  public void validateAddOnCoverStatus(String cover, String action, boolean screenshot) throws Exception{
    try {
      boolean status = false;
      String coverStatus;
      if(action.equals("Add")){
        coverStatus = "added";
      }else if(action.equals("Remove")){
        coverStatus = "removed";
      }else
        coverStatus = "(Please define cover status in code)";
      switch (cover) {

        case "Home Emergency":           
          if(action.equalsIgnoreCase("Add") && homeEmergencyRemoveButton.isDisplayed()){                
            status = true;
          }          
          if(action.equalsIgnoreCase("Remove") && homeEmergencyAddButton.isDisplayed()){    
            status = true;     
          }
          break;

        case "Legal Expenses":
          if(action.equalsIgnoreCase("Add") && legalExpenseRemoveButton.isDisplayed()){                
            status = true;
          }          
          if(action.equalsIgnoreCase("Remove") && legalExpenseAddButton.isDisplayed()){    
            status = true;     
          }
          break;
      }
      Log.softAssertThat(status, "The Add on functionality is working as expected. Cover '"+cover+"' is "+coverStatus+" on the policy",
          "The Add on functionality is not working as expected. Cover '"+cover+"' is not "+coverStatus+" on the policy", driver,
          extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while adding or removing Add-On Covers "+e);
    }
  }
  
  /**
   * To add or remove Add-On Covers
   * 
   * @param testDataToCheck
   * @param screenshot
   * @param extentedReport
   * @throws Exception
   */
  public String addOnCoversAddRemove(String testDataToCheck, boolean screenshot) throws Exception {

    String[] coversToAdd = testDataToCheck.split("_");
    String cover = coversToAdd[0];
    String action = coversToAdd[2];
    String varToReturn="false";

    try {
      switch (cover) {
        
        case "Home Emergency":           
          if(action.equalsIgnoreCase("Add")){
            WaitUtils.waitForelementToBeClickable(driver, homeEmergencyAddButton, 
                "Home Emergency add button is not clickable");
            homeEmergencyAddButton.click();
            WaitUtils.waitForOnlySpinner(driver);
            varToReturn = "User has successfully added Home Emergency";
          }else if(action.equalsIgnoreCase("Remove")){
            WaitUtils.waitForelementToBeClickable(driver, homeEmergencyRemoveButton, 
                "Home Emergency remove button is not clickable");
            homeEmergencyRemoveButton.click();
            WaitUtils.waitForOnlySpinner(driver);
            varToReturn = "User has successfully removed Home Emergency";
          }
          break;

        case "Legal Expenses":
          if(action.equalsIgnoreCase("Add")){
            WaitUtils.waitForelementToBeClickable(driver, legalExpenseAddButton, 
                "Legal Expenses add button is not clickable");
            legalExpenseAddButton.click();
            WaitUtils.waitForOnlySpinner(driver);
            varToReturn = "User has successfully added Legal Expenses";
          }else if(action.equalsIgnoreCase("Remove")){
            WaitUtils.waitForelementToBeClickable(driver, legalExpenseRemoveButton, 
                "Legal Expenses remove button is not clickable");
            legalExpenseRemoveButton.click();
            WaitUtils.waitForOnlySpinner(driver);
            varToReturn = "User has successfully removed Legal Expenses";
          }
          break;
      }
      return varToReturn;
    }catch(Exception e){
      throw new Exception("Error while adding or removing Add-On Covers "+e);
    }
  }

  /**
   * Verify premium and text values in Your Premium section
   * 
   * @param renewalPremium
   * @param previousPremium
   * @param premiumLineOne
   * @param premiumLineTwo
   * @param extentedReport
   * @throws Exception
   */
  public void verifyYourPremiumText(Properties testDataSS, HashMap<String,String> policyDetails,
      boolean screenshot) throws Exception {
    try{
      boolean status = true;
      StringBuffer message = new StringBuffer("Failing on- ");
      String premiumLineOne, premiumLineTwo, renewalPremium, previousPremium;
      
      renewalPremium = policyDetails.get("RenewalPremium");
      previousPremium = policyDetails.get("PolicyPremium");    
      premiumLineOne = testDataSS.getProperty("yourPremiumLineOne");
      premiumLineTwo = testDataSS.getProperty("yourPremiumLineTwo");

      if (!GenericUtils.verifyWebElementSubText(renewalQuotePremiumValue, renewalPremium, "£")) {
        status = false;
        message.append("renewalPremium--- ");
      }
      
      String[] premiumsOrig = renewalQuotePremiumValue.getText().split("£");
      originalSSRenewalPremium = premiumsOrig[1].trim();
      
      if (!GenericUtils.verifyWebElementSubText(previousPolicyPremiumValue, previousPremium, "£")) {
        status = false;
        message.append("previousPremium--- ");
      }
      if (!GenericUtils.verifyWebElementTextEquals(premiumSectionTextLineOne, premiumLineOne)) {
        status = false;
        message.append("premiumLineOne--- ");
      }
      if (!GenericUtils.verifyWebElementTextEquals(premiumSectionTextLineTwo, premiumLineTwo)) {
        status = false;
        message.append("premiumLineTwo");
      }
      Log.softAssertThat(status, "The text fields for premium section are as expected",
          "The text fields for premium have values different than expected: " + message, driver,
          extentedReport, screenshot);}
    catch(Exception e){
      throw new Exception("Error while validating 'Your Premium' section. "+e);
    }
  }

  /**
   * To verify Terms and Conditions warning section
   * 
   * @param messageOne
   * @param messageTwo
   * @param screenshot
   * @throws Exception
   */
  public void verifyTermsAndConditionsWarningSection(Properties testdataSS, boolean screenshot) throws Exception {
    boolean status = true;
    StringBuffer message = new StringBuffer("Faling on- ");
    String messageOne, messageTwo;
    messageOne = testdataSS.getProperty("t&cMessageOne");
    messageTwo = testdataSS.getProperty("t&cMessageTwo");
    try{
      WaitUtils.waitForElementPresent(driver, termsandConditionsRedMessageSection,
          "Terms and Conditions section not displayed.");
      if (!WaitUtils.waitForElement(driver, checkBoxTandC, 3)) {
        status = false;
        message.append("checkBoxTandC as check box not present--- ");
      }
      if (checkBoxTandC.isSelected()) {
        status = false;
        message.append("checkBoxTandC as check box is already selected--- ");
      }
      if (!GenericUtils.verifyWebElementTextEquals(checkboxMessage, messageOne)) {
        status = false;
        message.append("Message one is not as expected--- ");
      }
      if (!GenericUtils.verifyWebElementTextEquals(warningMessage, messageTwo)) {
        status = false;
        message.append("Message two is not as expected");
      }
      Log.softAssertThat(status, "The terms and conditions warning section is validated successfully",
          "The terms and conditions warning section is not validated successfully: " + message,
          driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while validating T&C warning section. "+e);
    }
  }
  
  

  /**
   * @return
   */
  private boolean acceptTermsAndConditions(boolean screenshot) throws Exception{
    try{
      WaitUtils.waitForElementPresent(driver, buyButton, "Buy button is not present");
      WaitUtils.waitForelementToBeClickable(driver, checkBoxTandC,
          "User is unable to click on T&C check box");
      if(checkBoxTandC.isSelected()){
        Log.fail("Checkbox is selected by default", driver, extentedReport, screenshot);        
      }
      else{
        checkBoxTandC.click();
      } 
      return true;
    }catch(Exception e){
      throw new Exception("Error on clicking Accept T&C checkbox. "+e);
    }
  }

  /**
   * To verify the functionality for T&C section
   * 
   * @param tcSize
   * @param termsAndConditions
   * @param screenshot
   * @throws Exception
   */
  public void verifyTermsAndConditionsSection(HashMap<String,String> testData,int tcSize, HashMap<Integer,String> termsAndConditions, String tcType, Properties testDataSS,boolean screenshot)
      throws Exception {
    boolean status = true;
    StringBuffer message = new StringBuffer("Faling on- ");
    int counter=0;
    String tcGlobalText = testDataSS.getProperty("t&CGlobalMessage");
    
    String editedTC="";
    
    if(tcSize>2){
      if(tcType.equals("freeStyle")){
        editedTC = "Edit"+testData.get("TCWord")+(tcSize-2);
        for(int i=2;i<tcSize;i++){
          if(i==(tcSize-1)){
            termsAndConditions.put(i, editedTC);
            break;
          }
          termsAndConditions.put(i, testData.get("TCWord")+(i-1));
        }
      }
      else if(tcType.equals("existingSystem")){
        String[] tcTitleName = testData.get("Terms and Conditions_Add").split(",");
        editedTC = "Edit TC- "+testData.get("Terms and Conditions_Edit");
        for(int i=2;i<tcSize;i++){
          String tcTitle = tcTitleName[i-2];
          if(i==(tcSize-1)){
            termsAndConditions.put(i, editedTC);
            break;
          }
          switch(tcTitle){
            case "Accidental damage excess" :
              termsAndConditions.put(i, testDataSS.getProperty("AccDmageTC"));
              break;
            case "Optional Term and Condition for testing purposes" :
              termsAndConditions.put(i, testDataSS.getProperty("OptionalTC"));
              break;
          }         
        }
      }    
    }
    try{
      if (!WaitUtils.waitForElement(driver, termsandConditionsHeadingText)) {
        status = false;
        message.append("termsandConditionsHeadingText--- " );
      }
      if (!WaitUtils.waitForListElement(driver, termsandConditionsViewCloseButtons, wait)) {
        status = false;
        message.append("termsandConditionsViewCloseButtons--- " );
      }
      if (termsandConditionsList.size() == tcSize
          && termsandConditionsViewCloseButtons.size() == tcSize) {
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        for (int i = 0; i < termsandConditionsList.size(); i++) {
          for (int j = 0; j < 2; j++) {
            String buttonText = termsandConditionsViewCloseButtons.get(i).getText();
            switch (buttonText) {
              case "View":      
                termsandConditionsViewCloseButtons.get(i).click();
                action.moveToElement(termsandConditionsDescriptionMessage1and2.get(i)).build().perform();
                if (GenericUtils.verifyMatchingTextContainsElementFromList(
                    termsandConditionsDescriptionMessage1and2, termsAndConditions.get(i+1))) {                
                  counter++;
                } else {
                  Log.fail("Terms and Conditions text is not as expected on row number "
                +(i+1)+". Expected value- ("+termsAndConditions.get(i+1)+") Actual value- ("+termsandConditionsDescriptionMessage1and2.get(i).getText()+")", driver, extentedReport, screenshot);
                  message.append("Terms and Conditions text is not as expected on row number "
                +(i+1)+". Expected value- ("+termsAndConditions.get(i+1)+") Actual value- ("+termsandConditionsDescriptionMessage1and2.get(i).getText()+")--- ");
                }  
                break;
              case "Close":
                termsandConditionsViewCloseButtons.get(i).click();
                break;
              default:
                status =false;
                message.append("T&C button text is not as expected--- ");
                break;
            }
          }
        }
      }
      if(counter != termsandConditionsViewCloseButtons.size()){
        status = false;
      }
      if(!tandcGlobalMessage.getText().equals(tcGlobalText)){
        status = false;
        System.out.println(tandcGlobalMessage.getText());
        message.append("T&C global message is incorrect");
      }
      Log.softAssertThat(status, "The Terms and Conditions message section is validated successfully",
          "The Terms and Conditions message section is not validated successfully: " +message,
          driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while validating Terms and Conditions Section. "+e);
    }
  }
  
   
  /**
   * To validate Required field message
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  private int validateRequiredFieldMsg(boolean screenshot) throws Exception {
    int counter=0;
    try{  
        if(reqdFieldMsg.isDisplayed()){
          counter++;
        }else
          Log.fail("Required field message is not displayed", driver, extentedReport, screenshot);
      return counter;  
    }catch(Exception e){
      throw new Exception("Error while validating mandatory message. "+e);
    }
  }
  
  /**
   * To validate Required field message and navigate to Contact Details page on clicking Buy button
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public ContactDetailsPage navigateToContactDetailsPage(boolean screenshot) throws Exception{
    int condition;
    try{
      clickBuyButton(screenshot);
      condition = validateRequiredFieldMsg(screenshot);
      if(condition==0){
        throw new Exception("Mandatory message- 'Required field' is not displayed");
      }else{
        acceptTermsAndConditions(screenshot);
        clickBuyButton(screenshot);
        return new ContactDetailsPage(driver, extentedReport).get();
      }
    }catch(Exception e){
      throw new Exception("Error on naviagtion from YourQuote page to ContactDetails page. "+e);
    }  
  }
  
  /**
   * To click Buy button after accepting T&C
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public void clickBuyButton(boolean screenshot) throws Exception {
    try {
      WaitUtils.waitForElementPresent(driver, buyButton, "Buy button is not present.");
      WaitUtils.waitForelementToBeClickable(driver, buyButton, "Buy button is not clickable");
      GenericUtils.javaScriptExecutorToClick(driver, buyButton);
      WaitUtils.waitForOnlySpinner(driver);
      Log.message("Clicked on buy button", driver, extentedReport, screenshot);     
    }catch(Exception e){
      throw new Exception("Error on clicking Buy button. "+e);
    }
  }
  
  /**
   * To click re calculate button
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public YourQuotePageFromPP clickRecalculateButn(boolean screenshot) throws Exception{
    try{
      WaitUtils.waitForElementPresent(driver, recalculateButton, "Recalculate button is not present.");
      WaitUtils.waitForelementToBeClickable(driver, recalculateButton, "Recalculate button is not clickable.");
      recalculateButton.click();
      WaitUtils.waitForOnlySpinner(driver);
      Log.message("User has successfully clicked on Re-Calculate button.", driver, extentedReport, screenshot);
      return this.get();
    }catch(Exception e){
      throw new Exception("Error on clicking Re-Calculate button. "+e);
    }

  }
  
  /**
   * To click save button
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public SelfServiceCustomerDashboard clickSaveButn(boolean screenshot) throws Exception{
    try{
      WaitUtils.waitForelementToBeClickable(driver, saveButton, "Save button is not clickable.");
      saveButton.click();
      WaitUtils.waitForSpinner(driver);
      Log.message("User has successfully clicked Save button.", driver, extentedReport, screenshot);
      return new SelfServiceCustomerDashboard(driver).get();
    }catch(Exception e){
      throw new Exception("Error on clicking save button. "+e);
    }
  }

  /**
   * To click Cancel button
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public SelfServiceCustomerDashboard clickCancelButn(boolean screenshot) throws Exception{
    try{
      WaitUtils.waitForelementToBeClickable(driver, cancelQuoteButton, "Save button is not clickable.");
      cancelQuoteButton.click();
      WaitUtils.waitForSpinner(driver);
      Log.message("User has successfully clicked Cancel button.", driver, extentedReport, screenshot);
      return new SelfServiceCustomerDashboard(driver).get();
    }catch(Exception e){
      throw new Exception("Error on clicking save button. "+e);
    }
  }
  
  /**
   * To click viewInsurance button
   * 
   * @param screenshot
   * @return
   * @throws Exception
   */
  public YourPolicyDetailsContactCover clickViewDetailsButn(boolean screenshot) throws Exception{
    try{
      WaitUtils.waitForElementPresent(driver, viewInsuranceButton, "View my insurance details button is not present");
      WaitUtils.waitForelementToBeClickable(driver, viewInsuranceButton, "View my insurance details button is not clickable.");
      viewInsuranceButton.click();
      WaitUtils.waitForOnlySpinner(driver);
      Log.message("User has successfully clicked View my insurance details button.", driver, extentedReport, screenshot);
      return new YourPolicyDetailsContactCover(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Error on clicking View my insurance details button. "+e);
    }

  }
  
  /**
   * To verify premium on clicking Recalculate button
   * 
   * @param originalPremium
   * @param screenshot
   * @throws Exception
   */
  public void verifyPremiumOnRecalculate(boolean screenshot) throws Exception{

    boolean status = true;
    String[] premiumRenewal = renewalQuotePremiumValue.getText().trim().split(":");
    newSSRenewalPremium = premiumRenewal[1];
    try{
      if(newSSRenewalPremium.equals(originalSSRenewalPremium)){
        status = false;
      }
      Log.softAssertThat(status, "The premium is recalculated successfully.", "The premium is not recalculated successfully.",
          driver, extentedReport, screenshot);
      originalSSRenewalPremium = newSSRenewalPremium;
    }catch(Exception e){
      throw new Exception("Error on verifying recalculated premium. "+e);
    }
  }
  
  /**
   * To validate Renewal Premium after canceling addon cover addition or removal
   * 
   * @param originalRenewalPremium
   * @throws Exception
   */
  public void verifyrenewalPremiumAfterAddOnCancel(String originalRenewalPremium, boolean screenshot) throws Exception{   
    boolean status = true;
    
    try{
      if (!GenericUtils.verifyWebElementSubText(renewalQuotePremiumValue, originalRenewalPremium, ":")) {
        status = false;
      }
      Log.softAssertThat(status, "The renewal premium is same as original premium after clicking cance on Your Quote page.", 
          "The renewal premium is not same as original premium after clicking cance on Your Quote page.",
          driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error on verifying renewal premium after add on cancel. "+e);
    }
  }
  
/*  *//**
   * To click Buy button
   * 
   * @param screenshot
   * @return 
   * @return
   * @throws Exception
   *//*
  public ContactDetailsPage verifyBuyButtonState(boolean screenshot) throws Exception {    
    if (buyButton.isEnabled()) {
      buyButton.click();
      return new ContactDetailsPage(driver, extentedReport);
    } else {
      Log.message("Buy button is disabled", driver, extentedReport, screenshot);
      throw new Exception("Buy button is disabled");
    }
  }*/
  
  /**
   * To validate Add-on Cover financial
   * 
   * @param covLimitCur
   * @param covLimit
   * @param prem
   * @param premCur
   * @param cover_limit_Amt
   * @param premium_Amt
   * @return
   */
  private String validateAddOnCoverUtil(WebElement covLimitCur, WebElement covLimit, WebElement prem, WebElement premCur, 
      String cover_limit_Amt, String premium_Amt){

    String valToReturn="false";

    String Lmt_Val = covLimitCur.getText() + covLimit.getText();
    String premium_Val = prem.getText() + premCur.getText();

    if ((Lmt_Val.equals(cover_limit_Amt)) && (premium_Val.equals(premium_Amt))) {
      valToReturn = "Cover : LimitAmt =" + Lmt_Val + ", PremAmt =" + premium_Val;

    } else
      Log.message("Default amount were not populated for Cover, it displayed : LimitAmt ="
          + Lmt_Val + ", PremAmt =" + premium_Val, driver, extentedReport, true);
    return valToReturn;
  }
  
  /**
   * To validate Add-on Cover financial only premium
   * 
   * @param covLimitCur
   * @param covLimit
   * @param prem
   * @param premCur
   * @param cover_limit_Amt
   * @param premium_Amt
   * @return
   */
  private String validateAddOnCoverPremiumUtil(String cover, WebElement prem, WebElement premCur, String premium_Amt){

    String valToReturn="false";

    
    String premium_Val = prem.getText() + premCur.getText();

    if ((premium_Val.equals(premium_Amt))) {
      valToReturn = " PremAmt = " + premium_Val;

    } else
      Log.message("Default amount were not populated for Cover, "+cover+" it displayed :  PremAmt = " + premium_Val, driver, extentedReport, true);
    return valToReturn;
  }
  
  /**
   * To validate cover financial
   * 
   * @param counter
   * @param prefixCovLmt
   * @param prefixExcess
   * @param prefixPrem
   * @param amtSuffix
   * @param currencySuffix
   * @param cover_limit_Amt
   * @param excess_Amt
   * @param premium_Amt
   * @return
   */
  private String validateCoverUtil(int counter, String prefixCovLmt, String prefixExcess, String prefixPrem, 
      String amtSuffix, String currencySuffix,String cover_limit_Amt, String excess_Amt, String premium_Amt){
    
    String valToReturn="false";
    WebElement covLimitCurrency = driver.findElement(By.cssSelector(prefixCovLmt+counter+currencySuffix));
    WebElement covLimitAmount = driver.findElement(By.cssSelector(prefixCovLmt+counter+amtSuffix));
    WebElement excessCurrency = driver.findElement(By.cssSelector(prefixExcess+counter+currencySuffix));
    WebElement excessAmount = driver.findElement(By.cssSelector(prefixExcess+counter+amtSuffix));
    WebElement premiumCurrency = driver.findElement(By.cssSelector(prefixPrem+counter+currencySuffix));
    WebElement premiumAmount = driver.findElement(By.cssSelector(prefixPrem+counter+amtSuffix));
    
    String Lmt_Val = covLimitCurrency.getText() + covLimitAmount.getText();
    String exces_Val = excessCurrency.getText() + excessAmount.getText();
    String premium_Val = premiumCurrency.getText() + premiumAmount.getText();
   
    if ((Lmt_Val.equals(cover_limit_Amt)) && (exces_Val.equals(excess_Amt)) && (premium_Val.equals(premium_Amt))) {
      valToReturn = "Cover : LimitAmt =" + Lmt_Val + ", ExcessAmt =" + exces_Val + ", PremAmt =" + premium_Val;
      
    } else
      Log.message("Default amount were not populated for Cover, it displayed : LimitAmt ="
          + Lmt_Val + ", ExcessAmt =" + exces_Val + ", PremAmt =" + premium_Val, driver, extentedReport, true);
    return valToReturn;
  }
  
  /**
   * Get Default values of Insurance covers
   * 
   * @return String
   * @param extentedReport
   * @throws Exception
   * @param testDataToCheck : string
   * @param Screenshot :boolean
   */
  public String verify_DefaultCovers(String testDataToCheck, boolean screenshot,
      ExtentTest extentedReport) throws Exception {

    String[] coversToAdd = testDataToCheck.split("_");
    String coverSec = coversToAdd[0];
    String cover_limit_Amt = coversToAdd[1];
    String excess_Amt = coversToAdd[2];
    String premium_Amt = coversToAdd[3];

    String valToReturn = "false";
    try {
      switch (coverSec) {

        case "Buildings":
          counter = 1;
          System.out.println("Buildings!");
          valToReturn = validateCoverUtil(counter, buildingsCoverLimitPrefix, buildingsExcessPrefix, buildingsPremiumPrefix, 
              amountSuffix, currencySuffix, cover_limit_Amt, excess_Amt, premium_Amt);
          break;

        case "Contents":
          counter = 1;
          System.out.println("Contents!");
          valToReturn = validateCoverUtil(counter, contentsCoverLimitPrefix, contentsExcessPrefix, contentsPremiumPrefix, 
              amountSuffix, currencySuffix, cover_limit_Amt, excess_Amt, premium_Amt);
          break;

        case "BuildingsAccidentalDamage":
          counter = 2;
          System.out.println("Buildings Accidental Damage!");
          valToReturn = validateCoverUtil(counter, buildingsCoverLimitPrefix, buildingsExcessPrefix, buildingsPremiumPrefix, 
              amountSuffix, currencySuffix, cover_limit_Amt, excess_Amt, premium_Amt);
          break;
          
        case "ContentsAccidentalDamage":
          counter = 2;
          System.out.println("Contents Accidental Damage!");
          valToReturn = validateCoverUtil(counter, contentsCoverLimitPrefix, contentsExcessPrefix, contentsPremiumPrefix, 
              amountSuffix, currencySuffix, cover_limit_Amt, excess_Amt, premium_Amt);
          break;
          
        case "HomeEmergency":
          System.out.println("HomeEmergency!");
          valToReturn = validateAddOnCoverPremiumUtil(coverSec, homeEmergencyCoverPremiumCurrency, 
              homeEmergencyCoverPremium, premium_Amt);
          break; 
          
        case "LegalExpenses":
          System.out.println("LegalExpenses!");
          valToReturn = validateAddOnCoverPremiumUtil(coverSec, legalExpensesCoverPremiumCurrency, 
              legalExpensesCoverPremium, premium_Amt);
          break; 
      }
      return valToReturn;
    } catch (Exception e) {
      throw new Exception(
          "Unable to get the default values from covers section. Exception occured: " + e);}
  }
  
}
