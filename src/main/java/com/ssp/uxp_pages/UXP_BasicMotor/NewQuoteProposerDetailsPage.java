package com.ssp.uxp_pages.UXP_BasicMotor;

import java.util.HashMap;
import java.util.List;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewQuoteProposerDetailsPage extends LoadableComponent<NewQuoteProposerDetailsPage>{
  
    private WebDriver driver;
    private boolean isPageLoaded;
    public ElementLayer uielement;
    ExtentTest extentedReport;
    private String propOccupation;
    private String declarationAgreed;
    

    private void setPropOccupation(HashMap<String,String> testdata) throws Exception {
      this.propOccupation = testdata.get("Proposer Occupation");
      if(propOccupation.equals("")){
        throw new Exception("Please enter field value for 'Proposer Occupation' in testdata sheet");
      }
    }
    
    private void setDeclarationAgreed(HashMap<String,String> testdata) throws Exception {
      this.declarationAgreed = testdata.get("Declaration Agreed");
      if(declarationAgreed.equals("")){
        throw new Exception("Please enter field value for 'Declaration Agreed' in testdata sheet");
      }
    }

    @FindBy(css = "select[id*='proposerTradeOccupation']")
    private WebElement proposerOccupation;
    
    @FindBy(css = "label[for*='declarationAgreed'][style]")
    private List<WebElement> declarationAgreeedRadioBtn;
    
    @FindBy(css = "label[for*='declarationAgreed'][style] span")
    private List<WebElement> declarationAgreeedRadioBtnTxt;
    
    @FindBy(css = "input[name*='PREVIOUSINSURANCE']")
    private WebElement previouslyInsuredCheckbox;
    
    @FindBy(css = "select[name*='PREVIOUSINSURANCEINSURERNAME']")
    private WebElement previousInsuranceCompany;
    
    @FindBy(css = "input[name*='BESTQUOTE']")
    private WebElement bestQuoteTxtbox;
    
    @FindBy(css = "input[name*='BESTQUOTEBY']")
    private WebElement bestQuoteByTxtbox;
    
    @FindBy(css = "button[title='Continue']")
    private WebElement continueBtnDataCapOne;
    
    public NewQuoteProposerDetailsPage(WebDriver driver, ExtentTest extentedReport){
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
      if (isPageLoaded && !driver.getTitle().contains("DynamicRisk")) {
        Log.fail("User is not navigated to New Quote Page", driver, extentedReport);
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
     * To select proposer occupation from dropdown
     * 
     * @param testdata
     * @param driver
     * @param extentedReport
     * @param screenshot
     * @throws Exception
     */
    public void selectProposerOccupation(HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
      try{
        Log.event("Entered selectProposerOccupation method");
        setPropOccupation(testdata);
        WaitUtils.waitForElementPresent(driver, proposerOccupation, "Proposer occupation dropdown menu is not present");
        GenericUtils.selectValueFromDropdown(proposerOccupation, propOccupation, driver, extentedReport, screenshot);
      }catch(Exception e){
        throw new Exception("Unable to select proposer occupation on New Quote page: "+e);
      }     
    }
    
    /**
     * To enter Best Quote in text box
     * 
     * @param testdata
     * @param driver
     * @param extentedReport
     * @param screenshot
     * @throws Exception
     */
    public void enterBestQuote(HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
      try{
        Log.event("Entered enterBestQuote method");
        String bestQuote = testdata.get("Best Quote");
        WaitUtils.waitForElementPresent(driver, bestQuoteTxtbox, "Best quote textbox is not present");
        GenericUtils.enterInTextbox(bestQuote, bestQuoteTxtbox, driver, extentedReport, screenshot);
      }catch(Exception e){
        throw new Exception("Unable to input value in best quote on New Quote page: "+e);
      }     
    }
    
    /**
     * To enter Best Quote By in text box
     * 
     * @param testdata
     * @param driver
     * @param extentedReport
     * @param screenshot
     * @throws Exception
     */
    public void enterBestQuoteBy(HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
      try{
        Log.event("Entered enterBestQuoteBy method");
        String bestQuoteBy = testdata.get("Best Quote By");
        WaitUtils.waitForElementPresent(driver, bestQuoteByTxtbox, "Best quote by textbox is not present");
        GenericUtils.enterInTextbox(bestQuoteBy, bestQuoteByTxtbox, driver, extentedReport, screenshot);
      }catch(Exception e){
        throw new Exception("Unable to input value in best quote by on New Quote page: "+e);
      }     
    }
    
    /**
     * To select declaration agreed option
     * 
     * @param testdata
     * @param driver
     * @param extentedReport
     * @param screenshot
     * @throws Exception
     */
    public void clickDeclarationAgreed(HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
      try{
        Log.event("Entered clickDeclarationAgreed method");
        setDeclarationAgreed(testdata);
        GenericUtils.selectRadioBtn(declarationAgreeedRadioBtn, declarationAgreeedRadioBtnTxt, declarationAgreed, driver, extentedReport, screenshot);
      }catch(Exception e){
        throw new Exception("Unable to select declaration agreed on New Quote page: "+e);
      }     
    }
    
    /**
     * To check previously insured
     * 
     * @param testdata
     * @param driver
     * @param extentedReport
     * @param screenshot
     * @throws Exception
     */
    public void checkPreviouslyInsured(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
      try{
        Log.event("Entered checkPreviouslyInsured method");
        WaitUtils.waitForElementPresent(driver, previouslyInsuredCheckbox, "Previously insured checkbox is not present");
        GenericUtils.selectCheckBox(previouslyInsuredCheckbox, driver, extentedReport, screenshot);
      }catch(Exception e){
        throw new Exception("Unable to check previously insured on New Quote page: "+e);
      }     
    }
    
    /**
     * To enter previously insured insurance company
     * 
     * @param testdata
     * @param driver
     * @param extentedReport
     * @param screenshot
     * @throws Exception
     */
    public void selectInsuranceCompany(HashMap<String,String> testdata, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
      try{
        Log.event("Entered selectInsuranceCompany method");
        String company = testdata.get("Previously Insured Insurance Company");
        WaitUtils.waitForElementPresent(driver, previousInsuranceCompany, "Insurance company dropdown is not present");
        GenericUtils.selectValueFromDropdown(previousInsuranceCompany, company, driver, extentedReport, screenshot);
      }catch(Exception e){
        throw new Exception("Unable to select the value of previously insured insurance company on New Quote page: "+e);
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
    public NewQuoteVehicleDetailsPage clickOnContinue(WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
      try{
        Log.event("Entered clickOnContinue method");
        WaitUtils.waitForElementPresent(driver, continueBtnDataCapOne, "Continue button is not displayed");
        WaitUtils.waitForelementToBeClickable(driver, continueBtnDataCapOne, "Continue button is not clickable");
        continueBtnDataCapOne.click();
        WaitUtils.waitForSpinner(driver);
        return new NewQuoteVehicleDetailsPage(driver, extentedReport).get();
      }catch(Exception e){
        throw new Exception("Unable to click continue on New Quote page: "+e);
      }     
    }
  
}
