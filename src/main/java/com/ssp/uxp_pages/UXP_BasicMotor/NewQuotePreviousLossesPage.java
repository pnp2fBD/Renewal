package com.ssp.uxp_pages.UXP_BasicMotor;

import java.util.HashMap;
import java.util.List;
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

public class NewQuotePreviousLossesPage extends LoadableComponent<NewQuotePreviousLossesPage>{
  
  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  ExtentTest extentedReport;
  
  private int noOfYear;
  private static int counter;
  HashMap<Integer,HashMap<String,String>> claimsDetails;

  private void setNoOfYear(HashMap<String,String>testdata) throws Exception {
    if(testdata.get("Claim Date").equals("")){
      throw new Exception("Please enter values for field 'Claim Date' in testdata sheet");
    }
    this.noOfYear = Integer.parseInt( testdata.get("Claim Date"));
  }
  

  @FindBy(xpath = "//h1[text()='Previous Losses']")
  private WebElement previousLossesHeading;
  
  @FindBy(css = "input[id*='ComponentPVLItem_pLossDateClaimMade']")
  private WebElement dateClaimMadeTxtbox;
  
  @FindBy(css = "select[name*='COMPONENTPVLITEM[1].PLOSSDESCRIPTION']")
  private WebElement descriptionDropdown;
  
  @FindBy(css = "input[name*='COMPONENTPVLITEM[1].PLOSSTHIRDPARTY']")
  private WebElement costThirdPartyTxtbox;
  
  @FindBy(css = "input[name*='COMPONENTPVLITEM[1].PLOSSPERSONALINJURY")
  private WebElement costPersonalInjuryTxtbox;
  
  @FindBy(css = "input[name*='COMPONENTPVLITEM[1].PLOSSOWNDAMAGE']")
  private WebElement costOwnDamageTxtbox;
  
  @FindBy(css = "input[name*='COMPONENTPVLITEM[1].PLOSSCOSTTOTAL']")
  private WebElement costTotalTxtbox;
  
  @FindBy(css = "tr[id*='C2__p0_TBL_37681AB6F124109E916974_R']")
  private List <WebElement> claimsTableListRecords;
  
  @FindBy(css = "button[title='Add']")
  private WebElement addBtn;
  
  @FindBy(css = "button[title='Continue']")
  private WebElement continueBtn;
  
  @FindBy(css = "button[title='Back'][id='C2__Back-ComponentPRD_ComponentPHD_ComponentDRV_ComponentPVL']")
  private WebElement backBtn;
  
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
    if (isPageLoaded && !previousLossesHeading.isDisplayed()) {
      Log.fail("User is unable to navigate to New Quote Previous Losses page", driver, extentedReport);
      Assert.fail("Correct page is not displayed");
    }
    uielement = new ElementLayer(driver);
    
  }
  
  public NewQuotePreviousLossesPage(WebDriver driver, ExtentTest extentedReport){
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }
  
  /**
   * To add a claim
   * 
   * @param testdata
   * @param claimdata
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public HashMap<Integer,HashMap<String,String>> addClaims(HashMap<String, String> testdata, HashMap<String, String> claimdata, String claimCount,
      WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
  
    String date, costThirdParty, costPersonalInjury, costOwnDamage;
    try {
      int noOfClaims = Integer.parseInt(claimCount);
      costThirdParty = claimdata.get("Cost 3rdParty");
      costPersonalInjury = claimdata.get("Cost Personal Injury");
      costOwnDamage = claimdata.get("Cost Own Damage");
      if (noOfClaims > 0) {
        setNoOfYear(testdata);
        for (int i = 0; i < noOfClaims; i++) {
          
          if(counter>0){
            costThirdParty = costThirdParty + counter;
            claimdata.put("Cost 3rdParty", costThirdParty);
            costPersonalInjury = costPersonalInjury + counter;
            claimdata.put("Cost Personal Injury",costPersonalInjury);
            costOwnDamage = costOwnDamage + counter;
            claimdata.put("Cost Own Damage",costOwnDamage);
          }
          
          WaitUtils.waitForElementPresent(driver, dateClaimMadeTxtbox,
              "Date claim made text box is not present");
          date = GenericUtils.setDate("past", dateClaimMadeTxtbox, -noOfClaims, noOfYear);
          claimdata.put("Date Claim Made", date);
          WaitUtils.waitForElementPresent(driver, descriptionDropdown,
              "Description dropdown is not displayed");
          GenericUtils.selectValueFromDropdown(descriptionDropdown, claimdata.get("Description"),
              driver, extentedReport, screenshot);
          WaitUtils.waitForElementPresent(driver, costThirdPartyTxtbox,
              "Cost 3rd party text box is not displayed");
          GenericUtils.enterInTextbox(costThirdParty, costThirdPartyTxtbox, driver,
              extentedReport, screenshot);
          WaitUtils.waitForElementPresent(driver, costPersonalInjuryTxtbox,
              "Cost personal injury text box is not displayed");
          GenericUtils.enterInTextbox(costPersonalInjury,
              costPersonalInjuryTxtbox, driver, extentedReport, screenshot);
          WaitUtils.waitForElementPresent(driver, costOwnDamageTxtbox,
              "Cost own damage text box is not displayed");
          GenericUtils.enterInTextbox(costOwnDamage, costOwnDamageTxtbox, driver,
              extentedReport, screenshot);
          
          int totalCost = Integer.parseInt(costThirdParty)
              + Integer.parseInt(costPersonalInjury)
              + Integer.parseInt(costOwnDamage);
          String costTotal = String.valueOf(totalCost);
          claimdata.put("Cost Total", costTotal);
          
          WaitUtils.waitForElementPresent(driver, costTotalTxtbox,
              "Cost total text box is not displayed");
          GenericUtils.enterInTextbox(costTotal, costTotalTxtbox, driver, extentedReport,
              screenshot);
          
          WaitUtils.waitForElementPresent(driver, addBtn, "Add button is not displayed");
          WaitUtils.waitForelementToBeClickable(driver, addBtn, "Add button is not clickable");
          addBtn.click();
          WaitUtils.waitForSpinner(driver);
          counter++;
          claimsDetails.put(i+1, claimdata);
        }   
      }else
        Log.message("Driver has no previous loss history", driver, extentedReport, screenshot);   
      return claimsDetails;
    } catch (Exception e) {
      throw new Exception("Unable to add claim(s) on New Quote Previous Losses page: " + e);
    }
  }
  
  /**
   * To validate claim count
   * 
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public boolean validateClaimCount(String claimCount, WebDriver driver, ExtentTest extentedReport,
      boolean screenshot) throws Exception {
    boolean status = false;
    try {
      int noOfClaims = Integer.parseInt(claimCount);
      int claimsRowCount = claimsTableListRecords.size();
      if(claimsRowCount<1){
        throw new Exception("Claims are not getting added in Engagement Centre on Previous Losses page");
      }
      if (claimsRowCount == noOfClaims) {
        status = true;
      }
      return status;
    } catch (Exception e) {
      throw new Exception("Unable to validate claim count on New Quote Previous Losses page: " + e);
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
  public NewQuoteDriverPage clickOnContinue(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnContinue method");
      WaitUtils.waitForElementPresent(driver, continueBtn, "Continue button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, continueBtn, "Continue button is not clickable");
      continueBtn.click();
      WaitUtils.waitForSpinner(driver);
      return new NewQuoteDriverPage(driver, extentedReport).get();
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
  public NewQuotePreviousConvictionsPage clickOnBack(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Log.event("Entered clickOnBack method");
      WaitUtils.waitForElementPresent(driver, backBtn, "Back button is not displayed");
      WaitUtils.waitForelementToBeClickable(driver, backBtn, "Back button is not clickable");
      backBtn.click();
      WaitUtils.waitForSpinner(driver);
      return new NewQuotePreviousConvictionsPage(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Unable to click back on New Quote Driver page: "+e);
    }     
  }

}
