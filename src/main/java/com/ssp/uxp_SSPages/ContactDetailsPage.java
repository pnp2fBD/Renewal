package com.ssp.uxp_SSPages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


public class ContactDetailsPage extends LoadableComponent<ContactDetailsPage> {

  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  private String updatedSSName;
  private String updatedSSHomeNo;
  private String updatedSSMobNo;
  private String updatedSSBusNo;
  private String updatedCorPref;
  private String updatedMarkPref;
  private String updatedMarkPrefOption;
  private String titleToSelect;
  private String first_Name;   
  private String last_Name;
  private boolean removeFlag=true;
  private int flag;
  ExtentTest extentedReport;
  String website_SS = TestRunnerRenewals.website_SS;

  @FindBy(css = "div[id*='TXT_A759A2CAAFF75D551460379'] li[class*='active']")
  WebElement activePageBreadCrumb;
  @FindBy(css = "div[id*='644A45EDEAA7BED1147834'] h4")
  WebElement yourContactDetailsText;
  @FindBy(css = "div[id*='644A45EDEAA7BED1147834'] h4")
  WebElement yourContactDetailsTitleText;
  @FindBy(xpath = "//select[contains(@name,'TITLE')]")
  WebElement title;
  @FindBy(css = "input[name*='FIRSTNAME']")
  WebElement firstName;
  @FindBy(css = "input[name*='LASTNAME']")
  WebElement lastName;
  @FindBy(css = "input[name*='MOBILENUMBER']")
  WebElement mobileNumber;
  @FindBy(css = "input[name*='HOMENUMBER']")
  WebElement homeNumber;
  @FindBy(css = "input[name*='BUSINESSNUMBER']")
  WebElement businessNumber;

  @FindBy(css = "label[for*='C2__QUE_3661A386F2BF3E14683219'] span")
  List<WebElement> emailPostText;
  @FindBy(css = "input[name*='CORRESPONDENCEPREFERENCE']")
  List<WebElement> emailPostRadioBtn;
  @FindBy(css = "input[name*='SMSNOTIFICATION']")
  WebElement smsCheckbox;
  
  @FindBy(css = "label[for*='C2__QUE_644A45EDEAA7BED1142855'][style*='padding'] span")
  List<WebElement> optInMarketingText;
  @FindBy(css = "input[name*='OPTINTOMARKETING']")
  List<WebElement> optInMarketingRadioBtn;
  @FindBy(css = "label[for*='C2__QUE_644A45EDEAA7BED1207488_0']")
  WebElement optInMarketingOptionsTextEmail;
  @FindBy(css = "label[for*='C2__QUE_644A45EDEAA7BED1207494_0']")
  WebElement optInMarketingOptionsTextPost;
  @FindBy(css = "label[for*='C2__QUE_644A45EDEAA7BED1207500_0']")
  WebElement optInMarketingOptionsTextPhone;
  @FindBy(css = "label[for*='C2__QUE_644A45EDEAA7BED1207506_0']")
  WebElement optInMarketingOptionsTextSMS;
  @FindBy(css = "div[id='C2__p1_GRP_644A45EDEAA7BED1207482'] div[class*='pref_by noPadLft clrNone'] input[name*='C2__CONTACTCENTRE[1].BUYQUOTE[1].MARKETINGPREFERENCES[1]']")
  List<WebElement> optInMarketingOptionsCheckbox;
  
  @FindBy(css = "button[id*='BUT_4194CB4844FC309E1861314']")
  WebElement backToYourQouteBtn;
  @FindBy(css = "button[id*='BUT_3661A386F2BF3E14799940']")
  WebElement saveBtn;
  @FindBy(css = "button[id*='BUT_4194CB4844FC309E1861283']")
  WebElement proceedToPaymentBtn;
  @FindBy(css = "button[id*='BUT_F7120F1BCAF20CE4837585']")
  WebElement cancelBtn;
  @FindBy(css = "li[class*='dropdown'] a[class*='dropdown-toggle']")
  WebElement welcomeUserDropdown;
  @FindBy(css = "li[class*='dropdown open'] li[entname*='MenuItem 2'] a")
  WebElement welcomeUserDropdownBackToDash;
  @FindBy(css = "li[class*='dropdown open'] li[entname*='MenuItem 3'] a")
  WebElement welcomeUserDropdownSignOut;
  @FindBy(xpath = "//select[contains(@name,'TITLE')]/parent::div//span[contains(@id,'ERRORMESSAGE')]")
  WebElement titleReqdMessageTxt;
  @FindBy(xpath = "//input[contains(@name,'FIRSTNAME')]/parent::div//span[contains(@id,'ERRORMESSAGE')]")
  WebElement firstNameReqdMessageTxt;
  @FindBy(xpath = "//input[contains(@name,'LASTNAME')]/parent::div//span[contains(@id,'ERRORMESSAGE')]")
  WebElement lastNameReqdMessageTxt;
  @FindBy(xpath = "//div[contains(@class,'preference-check')]/parent::div//span[contains(@id,'ERRORMESSAGE')][contains(@style,'display: inline')]")
  WebElement mrktOptionsReqdMessageTxt;
  
  
  public ContactDetailsPage(WebDriver driver, ExtentTest extentedReport) {
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }

  @Override
  protected void load() {
    // TODO Auto-generated method stub
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

  @Override
  protected void isLoaded() {
    // TODO Auto-generated method stub
    if (!isPageLoaded) {
      Assert.fail();
    }
    (new WebDriverWait(driver, 180).pollingEvery(200, TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class)
        .withMessage("Your Quote page did not open up."))
    .until(ExpectedConditions.visibilityOf(activePageBreadCrumb));
    uielement = new ElementLayer(driver);

  }
  
  private void setName(HashMap<String,String>testdata) throws Exception {
    this.titleToSelect = testdata.get("Title").trim();
    this.first_Name = testdata.get("SS_Updted FirstName").trim();   
    this.last_Name = testdata.get("SS_Updted LastName").trim();    
    if(titleToSelect.equals("")||first_Name.equals("")||last_Name.equals("")){
      throw new Exception("Please enter field(s) value for 'Title', 'SS_Updted FirstName' & 'SS_Updted LastName' in testdata sheet");
    }
  }
  
  private void setUpdatedCorPref(HashMap<String,String>testdata) throws Exception {
    this.updatedCorPref = testdata.get("SS_Cor_Pref_email");
    if(updatedCorPref.equals("")){
      throw new Exception("Please enter field value for 'SS_Cor_Pref_email' in testdata sheet");
    }
  }

  private void setUpdatedMarPref(HashMap<String,String>testdata) throws Exception {
    this.updatedMarkPref = testdata.get("SS_Mark_Pref");
    this.updatedMarkPrefOption = testdata.get("SS_Mark_Pref_Optns");
    if(updatedMarkPref.equals("")){
      throw new Exception("Please enter field value for 'SS_Mark_Pref' in testdata sheet");
    }else if(updatedMarkPref.equals("Yes") && updatedMarkPrefOption.equals("")){
      throw new Exception("Please enter field value for 'SS_Mark_Pref_Optns' in testdata sheet");
    }
  }
  
  /**
   * Validation for Contact Details Page
   * @return
   * @throws Exception
   */
  public boolean verifyContactDetailsPage() throws Exception {
    WaitUtils.waitForSpinner(driver);
    if (!WaitUtils.waitForElement(driver, yourContactDetailsText))
      throw new Exception("Contact Details Page is not loaded");
    return true;
  }
  
  /**
   * Return to Dashboard from Contact Details page
   * 
   * @return
   * @throws Exception
   */
  public SelfServiceCustomerDashboard returnToDashBoard() throws Exception{
    try{
      WaitUtils.waitForElement(driver, welcomeUserDropdown);
      welcomeUserDropdown.click();
      WaitUtils.waitForElement(driver, welcomeUserDropdownBackToDash);
      welcomeUserDropdownBackToDash.click();
      WaitUtils.waitForSpinner(driver);
      return new SelfServiceCustomerDashboard(driver).get();
    }catch(Exception e){
      throw new Exception("Error on clicking back to dashboard button. "+e);
      }   
  }
  
  /**
   * Signout of application from Contact Details page
   * 
   * @return
   * @throws Exception
   */
  public CustomerSelfService signout() throws Exception{
    try{
      WaitUtils.waitForElement(driver, welcomeUserDropdown);
      welcomeUserDropdown.click();
      WaitUtils.waitForElement(driver, welcomeUserDropdownSignOut);
      welcomeUserDropdownSignOut.click();
      WaitUtils.waitForSpinner(driver);
      return new CustomerSelfService(driver, TestRunnerRenewals.website_SS, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Error on clicking back to dashboard button. "+e);
      }   
  }

  /**
   * Verifying user details 
   * 
   * @param userDetails
   * @param screenshot
   * @throws Exception
   */
  public void validateUserDetails(Map<String,String> userDetails, boolean screenshot) throws Exception{      
    boolean status = true;
    StringBuffer message = new StringBuffer("Failing on- ");

    try{
      if(!GenericUtils.validateSelectedValueDropdown(title, userDetails.get("Title"), driver, extentedReport, screenshot)){
        status = false;
        message.append("Title--- ");
      } 
      if(!GenericUtils.validateStringFromTextBox(userDetails.get("First Name"), firstName, driver, extentedReport)){
        status = false;
        message.append("First Name--- ");
      }
      if(!GenericUtils.validateStringFromTextBox(userDetails.get("Last Name"), lastName, driver, extentedReport)){
        status = false;
        message.append("Last Name--- ");
      }
      if(!GenericUtils.validateStringFromTextBox(userDetails.get("Home Phone"), homeNumber, driver, extentedReport)){
        status = false;
        message.append("Home Number--- ");
      }
      if(!GenericUtils.validateStringFromTextBox(userDetails.get("Mobile Number"), mobileNumber, driver, extentedReport)){
        status = false;
        message.append("Mobile Number--- ");
      }
      if(!GenericUtils.validateStringFromTextBox(userDetails.get("Business Number"), businessNumber, driver, extentedReport)){
        status = false;
        message.append("Business Number--- ");
      }
      Log.softAssertThat(status, "The user details are validated and have been populated correctly.", "The user details have not been populated correctly: "+message, 
          driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while validating user details on Contact Details page. "+e);
    }
  }
  
  /**
   * Validate updation of name in SS
   * 
   * @param testData
   * @param screenshot
   * @throws Exception
   */
  public void validateUpdatedSSName(HashMap<String,String> testData, boolean screenshot) throws Exception{   
    try{
      WaitUtils.waitForElementPresent(driver, firstName, "First Name textbox is not displayed");
      WaitUtils.waitForElementPresent(driver, lastName, "Last Name textbox is not displayed");
      String first_Name = firstName.getAttribute("value").toString();
      String last_Name = lastName.getAttribute("value").toString();
      String nameSS = first_Name+" "+last_Name;
      if(flag==1){
        if(nameSS.equals(updatedSSName)){
          Log.pass("User name is updated in SS", driver, extentedReport, screenshot);
        }else
          Log.fail("User name has not been updated in SS", driver, extentedReport, screenshot);
      }     
      if(flag==0){
        if(nameSS.equals(updatedSSName)){
          Log.fail("User name is updated in SS", driver, extentedReport, screenshot);
        }else
          Log.pass("User name has not been updated in SS", driver, extentedReport, screenshot);
      }     
    }catch(Exception e){throw new Exception("Error validation of name update in SS. "+e);}
  }
  
  /**
   * Validate updation of mobile no in SS
   * 
   * @param testData
   * @param screenshot
   * @throws Exception
   */
  public void validateUpdatedSSMobNo(HashMap<String,String> testData, boolean screenshot) throws Exception{  
    try{ 
      WaitUtils.waitForElementPresent(driver, mobileNumber, "Mobile number textbox is not displayed");
      String mobNo = mobileNumber.getAttribute("value").toString();
      if(flag==1){
        if(mobNo.equals(updatedSSMobNo)){
          Log.pass("Mobile number is updated in SS", driver, extentedReport, screenshot);
        }else
          Log.fail("Mobile number has not been updated in SS", driver, extentedReport, screenshot);
      }     
      if(flag==0){
        if(mobNo.equals(updatedSSMobNo)){
          Log.fail("Mobile number is updated in SS", driver, extentedReport, screenshot);
        }else
          Log.pass("Mobile number has not been updated in SS", driver, extentedReport, screenshot);
      }     
    }catch(Exception e){throw new Exception("Error validation of mobile no update in SS. "+e);}
  }
  
  /**
   * Validate updation of home no in SS
   * 
   * @param testData
   * @param screenshot
   * @throws Exception
   */
  public void validateUpdatedSSHomeNo(HashMap<String,String> testData, boolean screenshot) throws Exception{  
    try{
      WaitUtils.waitForElementPresent(driver, homeNumber, "Home number textbox is not displayed");
      String homeNo = homeNumber.getAttribute("value").toString();
      if(flag==1){
        if(homeNo.equals(updatedSSHomeNo)){
          Log.pass("Home number is updated in SS", driver, extentedReport, screenshot);
        }else
          Log.fail("Home number has not been updated in SS", driver, extentedReport, screenshot);
      }     
      if(flag==0){
        if(homeNo.equals(updatedSSHomeNo)){
          Log.fail("Home number is updated in SS", driver, extentedReport, screenshot);
        }else
          Log.pass("Home number has not been updated in SS", driver, extentedReport, screenshot);
      }     
    }catch(Exception e){throw new Exception("Error validation of home no update in SS. "+e);}
  }
  
  /**
   * Validate updation of business no in SS
   * 
   * @param testData
   * @param screenshot
   * @throws Exception
   */
  public void validateUpdatedSSBusNo(HashMap<String,String> testData, boolean screenshot) throws Exception{  
    try{
      WaitUtils.waitForElementPresent(driver, businessNumber, "Business number textbox is not displayed");
      String busNo = businessNumber.getAttribute("value").toString();
      if(flag==1){
        if(busNo.equals(updatedSSBusNo)){
          Log.pass("Business number is updated in SS", driver, extentedReport, screenshot);
        }else
          Log.fail("Business number has not been updated in SS", driver, extentedReport, screenshot);
      }     
      if(flag==0){
        if(busNo.equals(updatedSSBusNo)){
          Log.fail("Business number is updated in SS", driver, extentedReport, screenshot);
        }else
          Log.pass("Business number has not been updated in SS", driver, extentedReport, screenshot);
      }     
    }catch(Exception e){throw new Exception("Error validation of business no update in SS. "+e);}
  }
  
  /**
   * To clear the user name
   * 
   * @param newTitle
   * @param screenshot
   * @throws Exception
   */
  public void clearName(HashMap<String,String> testData, boolean screenshot) throws Exception{
    try{
      String titleToSelect = "- Please select -";
      WaitUtils.waitForElementPresent(driver, title, "The title dropdown is not present");
      GenericUtils.selectValueFromDropdown(title, titleToSelect, driver, extentedReport, screenshot);
      WaitUtils.waitForOnlySpinner(driver);
      WaitUtils.waitForElementPresent(driver, firstName, "The first name textbox is not present");
      firstName.clear();
      yourContactDetailsText.click();
      WaitUtils.waitForOnlySpinner(driver);
      WaitUtils.waitForElementPresent(driver, lastName, "The last name textbox is not present");
      lastName.clear();
      yourContactDetailsText.click();
      WaitUtils.waitForOnlySpinner(driver);     
      Log.message("User has successfully cleared user name on Contact Details page", driver, extentedReport, screenshot);
      removeFlag = false;
    }catch(Exception e){throw new Exception("Error while entering name. "+e);}
  }

  /**
   * To enter user name
   * 
   * @param newTitle
   * @param screenshot
   * @throws Exception
   */
  public void enterName(HashMap<String,String> testData, boolean screenshot) throws Exception{

    try{
      setName(testData);    
      if(first_Name.equals("")){
        first_Name = GenericUtils.getRandomCharacters("alpha", 6);
        testData.put("SS_Updted FirstName", first_Name);
      }
      if(last_Name.equals("")){
        last_Name = GenericUtils.getRandomCharacters("alpha", 4);
        testData.put("SS_Updted LastName", last_Name);
      }
      WaitUtils.waitForElementPresent(driver, title, "The title dropdown is not present");
      GenericUtils.selectValueFromDropdown(title, titleToSelect, driver, extentedReport, screenshot);
      WaitUtils.waitForElementPresent(driver, firstName, "The first name textbox is not present");
      GenericUtils.enterInTextbox(first_Name, firstName, driver, extentedReport, screenshot);
      yourContactDetailsText.click();
      WaitUtils.waitForOnlySpinner(driver);
      WaitUtils.waitForElementPresent(driver, lastName, "The last name textbox is not present");
      GenericUtils.enterInTextbox(last_Name, lastName, driver, extentedReport, screenshot);     
      yourContactDetailsText.click();
      WaitUtils.waitForOnlySpinner(driver);
      
      updatedSSName = first_Name+" "+last_Name;
      Log.message("New name - "+updatedSSName+" has been updated in SS", driver, extentedReport, screenshot);
      Log.message("User has successfully entered user name on Contact Details page", driver, extentedReport, screenshot);
    }catch(Exception e){throw new Exception("Error while entering name. "+e);}
  }

  /**
   * To enter mobile number
   * 
   * @param mobNo
   * @param screenshot
   * @throws Exception
   */
  public void enterMobNo(HashMap<String,String> testData, boolean screenshot) throws Exception{
    try{
      updatedSSMobNo = testData.get("SS_Updted Mob_No");
      WaitUtils.waitForElementPresent(driver, mobileNumber, "Mobile number text box not present.");
      GenericUtils.enterInTextbox(updatedSSMobNo, mobileNumber, driver, extentedReport, screenshot);
      yourContactDetailsText.click();
      WaitUtils.waitForSpinner(driver);
      Log.message("New mobile no - "+updatedSSMobNo+" has been updated in SS", driver, extentedReport, screenshot);
      Log.message("User has successfully entered mobile number on Contact Details page", driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error on entering mobile number. "+e);
    }
  }

  /**
   * To enter home number
   * 
   * @param homeNo
   * @param screenshot
   * @throws Exception
   */
  public void enterHomeNo(HashMap<String,String> testData, boolean screenshot) throws Exception{
    try{
      updatedSSHomeNo = testData.get("SS_Updated Home_No");
      WaitUtils.waitForElementPresent(driver, homeNumber, "Home number text box not present.");
      GenericUtils.enterInTextbox(updatedSSHomeNo, homeNumber, driver, extentedReport, screenshot);  
      WaitUtils.waitForSpinner(driver);
      Log.message("New home no - "+updatedSSHomeNo+" has been updated in SS", driver, extentedReport, screenshot);
      Log.message("User has successfully entered home number on Contact Details page", driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error on entering home number "+e);
    }
  }

  /**
   * To enter business number
   * 
   * @param busNo
   * @param screenshot
   * @throws Exception
   */
  public void enterBusinessNo(HashMap<String,String> testData, boolean screenshot) throws Exception{
    try{
      updatedSSBusNo = testData.get("SS_Updated Business_No");
      WaitUtils.waitForElementPresent(driver, businessNumber, "Business number text box not present.");
      GenericUtils.enterInTextbox(updatedSSBusNo, businessNumber, driver, extentedReport, screenshot); 
      WaitUtils.waitForSpinner(driver);
      Log.message("New business no - "+updatedSSBusNo+" has been updated in SS", driver, extentedReport, screenshot);
      Log.message("User has successfully entered business number on Contact Details page", driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error on entering business number "+e);
    }
  }

  /**
   * To verify the selected preference options
   * 
   * @param corresPref
   * @param marketPref
   * @param screenshot
   * @throws Exception
   */
  public void verifyCorrespAndMarketPref(HashMap <String,String> testData, boolean screenshot) throws Exception{ 
    boolean status = true;
    StringBuffer message = new StringBuffer("Failing on- ");
    String corresPref = testData.get("Cor_Pref_email");
    String marketPref = testData.get("Mark_Pref");
    String markPrefOptions = testData.get("Mark_Pref_Optns");

    try{
      if(!GenericUtils.validateSelectedRadioBtn(emailPostRadioBtn, emailPostText, corresPref, driver, extentedReport, screenshot)){
        status= false;
        message.append(corresPref+" option is not selected--- ");
      }
      if(!GenericUtils.validateSelectedRadioBtn(optInMarketingRadioBtn, optInMarketingText, marketPref, driver, extentedReport, screenshot)){
        status= false;
        message.append(marketPref+" option is not selected--- ");
      }
      if(marketPref.equals("Yes")&&optInMarketingRadioBtn.get(0).isSelected()){
        switch(markPrefOptions){
          case "Email":
            if(optInMarketingOptionsCheckbox.get(0).isSelected()){
              Log.pass("Correct marketing option is selected by default", driver, extentedReport, screenshot);
            }else{
              Log.fail("Correct marketing option is not selected by default", driver, extentedReport, screenshot);}
            break;
          case "Post":
            if(optInMarketingOptionsCheckbox.get(1).isSelected()){
              Log.pass("Correct marketing option is selected by default", driver, extentedReport, screenshot);
            }else{
              Log.fail("Correct marketing option is not selected by default", driver, extentedReport, screenshot);}
            break;
          case "Phone":
            if(optInMarketingOptionsCheckbox.get(2).isSelected()){
              Log.pass("Correct marketing option is selected by default", driver, extentedReport, screenshot);
            }else{
              Log.fail("Correct marketing option is not selected by default", driver, extentedReport, screenshot);}
            break;
          case "SMS":
            if(optInMarketingOptionsCheckbox.get(3).isSelected()){
              Log.pass("Correct marketing option is selected by default", driver, extentedReport, screenshot);
            }else{
              Log.fail("Correct marketing option is not selected by default", driver, extentedReport, screenshot);}
            break;
        }
      }
      Log.softAssertThat(status, "The correct preference options have been selected.", "The correct preference options have not been selected: "+message,
          driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while validating correspondance and marketing preference on Contact Details page. "+e);
    }
  }
  
  /**
   * To validate required field message
   * 
   * @param testData
   * @param screenshot
   * @throws Exception
   */
  public void validateReqdfieldMesgContactDetPage(HashMap <String,String> testData, boolean screenshot)throws Exception{
    try{
      if(titleReqdMessageTxt.isDisplayed() && firstNameReqdMessageTxt.isDisplayed() && lastNameReqdMessageTxt.isDisplayed() && mrktOptionsReqdMessageTxt.isDisplayed()){
        Log.pass("All the required field error messages are displayed", driver, extentedReport, screenshot);
      }else
        Log.fail("All the required field error messages are not displayed", driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while validating required field messages on Contact Details page. "+e);
    }
  } 
  
  /**
   * To select Correspondence preference
   * 
   * @param corresPref
   * @param screenshot
   * @throws Exception
   */
  public void selectCorresPref(HashMap<String, String> testdata, boolean screenshot) throws Exception{

    try{
      setUpdatedCorPref(testdata);
      GenericUtils.selectRadioBtn(emailPostRadioBtn, emailPostText, updatedCorPref, driver, extentedReport, screenshot);
      Log.message("User has successfully selected Correspondence preference option on Contact Details opage", driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while selecting correspondance preference on Contact Details page. "+e);
    }
  }
  
  /**
   * To select Marketing preference
   * 
   * @param markPref
   * @param screenshot
   * @throws Exception
   */
  public void selectMarketPref(HashMap<String, String> testdata, boolean screenshot)
      throws Exception {

    try {
      setUpdatedMarPref(testdata);
      for(int i=0;i<optInMarketingText.size();i++){
        if(optInMarketingText.get(i).getText().equals(updatedMarkPref) && !optInMarketingText.get(i).isSelected()){
          optInMarketingText.get(i).click();
          WaitUtils.waitForOnlySpinner(driver);
          break;
        }
      }
      //GenericUtils.selectRadioBtn(optInMarketingText, optInMarketingText, updatedMarkPref,
          //driver, extentedReport, screenshot);
      if (updatedMarkPref.equals("Yes")) {
        switch (updatedMarkPrefOption) {
          case "Email":
            optInMarketingOptionsCheckbox.get(0).click();
            WaitUtils.waitForOnlySpinner(driver);
            break;
          case "Post":
            optInMarketingOptionsCheckbox.get(1).click();
            WaitUtils.waitForOnlySpinner(driver);
            break;
          case "Phone":
            optInMarketingOptionsCheckbox.get(2).click();
            WaitUtils.waitForOnlySpinner(driver);
            break;
          case "SMS":
            optInMarketingOptionsCheckbox.get(3).click();
            WaitUtils.waitForOnlySpinner(driver);
            break;
        }
      }
      Log.message(
          "User has successfully selected marketing preference option on Contact Details opage",
          driver, extentedReport, screenshot);
    } catch (Exception e) {
      throw new Exception("Error while validating marketing preference on Contact Details page. "+e);
    }
  }
  
  /**
   * To remove marketing preference option
   * 
   * @param markPref
   * @param screenshot
   * @throws Exception
   */
  public void removeMarketPrefOption(HashMap<String, String> testdata, boolean screenshot)
      throws Exception {
    try {
      if (optInMarketingRadioBtn.get(1).isSelected()) {
        optInMarketingRadioBtn.get(0).click();
        WaitUtils.waitForOnlySpinner(driver);
      }
      for (WebElement option : optInMarketingOptionsCheckbox) {
        //WaitUtils.waitForElementPresent(driver, option, "Marketing option element is present");
        if (option.isSelected()) {
          option.click();
          WaitUtils.waitForOnlySpinner(driver);
          break;
        }
      }
      Log.message(
          "User has successfully removed marketing preference option on Contact Details page",
          driver, extentedReport, screenshot);
      removeFlag = false;
    } catch (Exception e) {
      throw new Exception("Error while validating marketing preference on Contact Details page. "+e);
    }
  }
  
  /**
   * To select SMS option
   * 
   * @param screenshot
   * @throws Exception
   */
  public void selectSMSOption(boolean screenshot) throws Exception{

    try{
      GenericUtils.selectCheckBox(smsCheckbox, driver, extentedReport, screenshot);
      Log.message("User has successfully selected SMS option on Contact Details opage", driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while selecting SMS option on Contact Details page. "+e);
    }
  }
  
  /**
   * To verify SMS option is selected by default
   * 
   * @param screenshot
   * @throws Exception
   */
  public void verifySMSOptionSelection(HashMap<String,String> testData, boolean screenshot)throws Exception{
    boolean status = true;
    String message = "Failing On- ";
    String check = testData.get("SMS_Opting");
        
    if(check.equals("No")){
      if(smsCheckbox.isSelected()){
        status=false;
        message = message+"SMS checkbox.";
      }
    }
    else if(check.equals("Yes")){
      if(!smsCheckbox.isSelected()){
        status=false;
        message = message+"SMS checkbox.";
      }
    }
    Log.softAssertThat(status, "The SMS option selection is as expected", "The SMS option selection is not as expected: "+message,
        driver, extentedReport, screenshot);
  }
  
  /**
   * To click on Proceed To Payment Options button
   * 
   * @param screenshot
   * @throws Exception
   */
  public PaymentPage clickProceed(boolean screenshot)throws Exception{
    try{
      WaitUtils.waitForElementPresent(driver, proceedToPaymentBtn, "Procedd To Payment button is not displayed.");
      WaitUtils.waitForelementToBeClickable(driver, proceedToPaymentBtn, "User is unable to click on 'Proceed' button.");
      GenericUtils.javaScriptExecutorToClick(driver, proceedToPaymentBtn);
      WaitUtils.waitForSpinner(driver);
      Log.message("User has clicked Proceed to payment options button.", driver, extentedReport, screenshot);
      return new PaymentPage(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Error while clicking proceed button on Contact Details page. "+e);
    }
  }
  
  /**
   * To click on Save button
   * 
   * @param screenshot
   * @throws Exception
   */
  public ContactDetailsPage clickSave(boolean screenshot)throws Exception{
    try{
      WaitUtils.waitForElementPresent(driver, saveBtn, "Save button is not displayed on Contact Details page.");
      WaitUtils.waitForelementToBeClickable(driver, saveBtn, "User is unable to click on 'Save' button.");
      GenericUtils.javaScriptExecutorToClick(driver, saveBtn);
      flag = 1;
      Log.message("User has successfully saved the Quote on Contact Details page.", driver, extentedReport, screenshot);
      return this.get();
    }catch(Exception e){
      throw new Exception("Error while clicking save button on Contact Details page. "+e);
    }
  }
  
  /**
   * To click on Cancel button
   * 
   * @param screenshot
   * @throws Exception
   */
  public SelfServiceCustomerDashboard clickCancel(boolean screenshot)throws Exception{
    try{
      WaitUtils.waitForElementPresent(driver, cancelBtn, "Cancel button is not displayed on Contact Details page.");
      WaitUtils.waitForelementToBeClickable(driver, cancelBtn, "User is unable to click on 'Cancel' button.");
      GenericUtils.javaScriptExecutorToClick(driver, cancelBtn);
      flag=0;
      //cancelBtn.click();
      Log.message("User has successfully canceled the Quote on Contact Details page.", driver, extentedReport, screenshot);
      return new SelfServiceCustomerDashboard(driver).get();
    }catch(Exception e){
      throw new Exception("Error while clicking cancel button on Contact Details page. "+e);
    }
  }
  
  /**
   * To click on Back button
   * 
   * @param screenshot
   * @throws Exception
   */
  public YourQuotePageFromPP clickBack(boolean screenshot)throws Exception{
    try{
      WaitUtils.waitForElementPresent(driver, backToYourQouteBtn, "Back To Your Quote button is not displayed on Contact Details page.");
      WaitUtils.waitForelementToBeClickable(driver, backToYourQouteBtn, "User is unable to click on 'Back' button.");
      GenericUtils.javaScriptExecutorToClick(driver, backToYourQouteBtn);
      //backToYourQouteBtn.click();
      Log.message("User has successfully returned to Your Quote page after clicking Back button.", driver, extentedReport, screenshot);
      return new YourQuotePageFromPP(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Error while clicking back button on Contact Details page. "+e);
    }
  }
}
