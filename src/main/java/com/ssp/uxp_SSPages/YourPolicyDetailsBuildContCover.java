package com.ssp.uxp_SSPages;

import java.time.Duration;
import java.util.HashMap;
import org.openqa.selenium.NoSuchElementException;
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
import com.ssp.support.Log;
import com.ssp.utils.ElementLayer;
import com.ssp.utils.WaitUtils;

public class YourPolicyDetailsBuildContCover extends LoadableComponent<YourPolicyDetailsBuildContCover> {

  private WebDriver driver;
  private boolean isPageLoaded;
  public ElementLayer uielement;
  private ExtentTest extentedReport;

  @FindBy(xpath="//h2[contains(text(),'Buildings Cover')]")
  WebElement buildingHeadText;
  @FindBy(css="input[id*='ComponentBLD_coverAmount']")
  WebElement buildingCoverAmt;
  @FindBy(css="select[id*='ComponentBLD_excessAmount']")
  WebElement buildingExcessAmt;
  
  @FindBy(xpath="//h2[contains(text(),'Contents Cover')]")
  WebElement cntHeadText;
  @FindBy(css="input[id*='ComponentCNT_sumContentsValue']")
  WebElement cntCoverAmt;
  @FindBy(css="select[id*='ComponentCNT_excessAmount']")
  WebElement cntExcessAmt;

  @FindBy(css="button[title*='Back'][class*='action-button']")
  WebElement backBtn;

  @FindBy(css="button[title*='Next']")
  WebElement nextBtn;

  public YourPolicyDetailsBuildContCover(WebDriver driver, ExtentTest extentedReport) {
    this.driver = driver;
    this.extentedReport = extentedReport;
    ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, WaitUtils.maxElementWait);
    PageFactory.initElements(finder, this);
  }

  @Override
  protected void isLoaded(){
    // TODO Auto-generated method stub
    if (!isPageLoaded) {
      Assert.fail();
    }
    (new WebDriverWait(driver, 180).pollingEvery(Duration.ofMillis(200))
        .ignoring(NoSuchElementException.class).withMessage("Your Policy Details page did not open up."))
    .until(ExpectedConditions.visibilityOf((WebElement) buildingHeadText));
    uielement = new ElementLayer(driver);
  }

  @Override
  protected void load() {
    // TODO Auto-generated method stub
    isPageLoaded = true;
    WaitUtils.waitForPageLoad(driver);
  }

  /**
   * Validate cover sections
   * 
   * @param testData
   * @param screenshot
   * @throws Exception
   */
  public void validateCoverSections(HashMap<String,String> testData, boolean screenshot) throws Exception{
    try{
      boolean status = false;
      StringBuffer message = new StringBuffer("Failing on- ");    
      String cover = testData.get("Cover");
      String nbCovers = testData.get("NB_Check");      
      String[] totalCovers = nbCovers.split(",");
      String buildingCoverAmount = null;
      String contCoverAmount = null;
      String buildingExcessAmount = null ;
      String contExcessAmount = null;

      for(int i=0;i<totalCovers.length;i++){        
        String[] coversToCheck = totalCovers[i].split("_");
        String coverName = coversToCheck[0];
        if(coverName.equals("BLD")){
          buildingCoverAmount = coversToCheck[1];
          buildingExcessAmount = coversToCheck[2];
        }else if(coverName.equals("CNT")){
          contCoverAmount = coversToCheck[1];
          contExcessAmount = coversToCheck[2];
        }
      }

      Select buildingExcessDropdown = new Select(buildingExcessAmt);
      Select contentsExcessDropdown = new Select(cntExcessAmt);
      String coverAmount=null;
      String excessAmount = null;

      switch(cover){

        case "Buildings" :
          if(buildingHeadText.isDisplayed()){
            coverAmount = "£"+buildingCoverAmt.getAttribute("value");
            excessAmount = "£"+ buildingExcessDropdown.getFirstSelectedOption().getText();
            if(!buildingCoverAmt.isEnabled() && coverAmount.equals(buildingCoverAmount)){
              status = true;
              Log.message("Building cover amount is: "+coverAmount, driver, extentedReport, screenshot);
            }else{
              status = false;
              message.append("Cover amount not as expected or the element is not disabled");
            }
            if(!buildingExcessAmt.isEnabled() && excessAmount.equals(buildingExcessAmount)){
              status = true;
              Log.message("Building excess amount is: "+excessAmount, driver, extentedReport, screenshot);
            }else{
              status = false;
              message.append("Excess amount not as expected or the element is not disabled");
            }
          }else
            message.append("Building heading is not displayed.");
          

        case "Contents" : 
          if(cntHeadText.isDisplayed()){
            coverAmount = "£"+cntCoverAmt.getAttribute("value");
            excessAmount = "£"+ contentsExcessDropdown.getFirstSelectedOption().getText();
            if(!cntCoverAmt.isEnabled() && coverAmount.equals(contCoverAmount)){
              status = true;
              Log.message("Contents cover amount is: "+coverAmount, driver, extentedReport, screenshot);
            }else{
              status = false;
              message.append("Cover amount not as expected or the element is not disabled");
            }
            if(!cntExcessAmt.isEnabled() && excessAmount.equals(contExcessAmount)){
              status = true;
              Log.message("Contents excess amount is: "+excessAmount, driver, extentedReport, screenshot);
            }else{
              status = false;
              message.append("Excess amount not as expected or the element is not disabled");
            }
          }else
            message.append("Contents heading is not displayed.");
          
        case "Buildings and Contents" :
          if(cntHeadText.isDisplayed()){
            String bldCoverAmount = "£"+buildingCoverAmt.getAttribute("value");
            String bldExcessAmount = "£"+ buildingExcessDropdown.getFirstSelectedOption().getText();
            String cntCoverAmount = "£"+cntCoverAmt.getAttribute("value");
            String cntExcessAmount = "£"+ contentsExcessDropdown.getFirstSelectedOption().getText();
            if(!buildingCoverAmt.isEnabled() && !cntCoverAmt.isEnabled() && bldCoverAmount.equals(buildingCoverAmount) 
                && cntCoverAmount.equals(contCoverAmount)){
              status = true;
              Log.message("Building cover amount is: "+bldCoverAmount+" and Contents cover amount is: "+cntCoverAmount, 
                  driver, extentedReport, screenshot);
            }else{
              status = false;
              message.append("Cover amount not as expected or the element is not disabled");
            }
            if(!buildingExcessAmt.isEnabled() && !cntExcessAmt.isEnabled() && bldExcessAmount.equals(buildingExcessAmount) 
                && cntExcessAmount.equals(contExcessAmount)){
              status = true;
              Log.message("Building excess amount is: "+bldExcessAmount+" and Contents excess amount is: "+cntExcessAmount, 
                  driver, extentedReport, screenshot);
            }else{
              status = false;
              message.append("Excess amount not as expected or the element is not disabled");
            }
          }else
            message.append("Building or Contents heading is not displayed.");
      }
      
      Log.softAssertThat(status, "Cover sections - "+cover+" - validated successfully.", "Cover sections - "+cover+" - validaiton failed.", driver, extentedReport);
    }catch(Exception e){
      throw new Exception("Error while validating cover sections. "+e);}
  }
  
  
  /**
   * To click Next button
   * 
   * @param screenshot
   * @throws Exception
   */
  public YourPolicyDetailsGardenCover clickNextBtn(boolean screenshot) throws Exception{

    try{
      WaitUtils.waitForElementPresent(driver, nextBtn, "Next button is not present.");
      WaitUtils.waitForelementToBeClickable(driver, nextBtn, "Next button is not clickable.");
      nextBtn.click();
      WaitUtils.waitForOnlySpinner(driver);
      Log.message("User has clicked next button and navigated.", driver, extentedReport, screenshot);
      return new YourPolicyDetailsGardenCover(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Error while clicking Next Button. "+e);}
  }

  /**
   * To click Back button
   * 
   * @param screenshot
   * @throws Exception
   */
  public YourPolicyDetailsProperty clickBacktBtn(boolean screenshot) throws Exception{

    try{
      WaitUtils.waitForElementPresent(driver, backBtn, "Back button is not present.");
      backBtn.click();
      WaitUtils.waitForSpinner(driver);
      Log.message("User has clicked back button and navigated.", driver, extentedReport, screenshot);
      return new YourPolicyDetailsProperty(driver, extentedReport).get();
    }catch(Exception e){
      throw new Exception("Error while clicking Back Button. "+e);}
  }

}
