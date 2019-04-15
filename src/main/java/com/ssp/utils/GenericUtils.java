package com.ssp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.EnvironmentPropertiesReader;
import com.ssp.support.Log;
import com.ssp.support.StopWatch;

/**
 * Util class consists wait for page load,page load with user defined max time and is used globally
 * in all classes and methods
 * 
 */
public class GenericUtils {
  public static String MOUSE_HOVER_JS = "var evObj = document.createEvent('MouseEvents');"
      + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
      + "arguments[0].dispatchEvent(evObj);";
  private static EnvironmentPropertiesReader configProperty =
      EnvironmentPropertiesReader.getInstance();

  /**
   * To get the test orientation
   * 
   * <p>
   * if test run on sauce lab device return landscape or portrait or valid message, otherwise check
   * local device execution and return landscape or portrait or valid message
   * 
   * @return dataToBeReturned - portrait or landscape or valid message
   */
  public static String getTestOrientation() {
    String dataToBeReturned = null;
    boolean checkExecutionOnSauce = false;
    boolean checkDeviceExecution = false;
    checkExecutionOnSauce =
        (System.getProperty("SELENIUM_DRIVER") != null || System.getenv("SELENIUM_DRIVER") != null)
            ? true : false;

    if (checkExecutionOnSauce) {
      checkDeviceExecution = ((System.getProperty("runUserAgentDeviceTest") != null)
          && (System.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true"))) ? true
              : false;
      if (checkDeviceExecution) {
        dataToBeReturned = (System.getProperty("deviceOrientation") != null)
            ? System.getProperty("deviceOrientation")
            : "no sauce run system variable: deviceOrientation ";
      } else {
        dataToBeReturned = "sauce browser test: no orientation";
      }
    } else {
      checkDeviceExecution = (configProperty.hasProperty("runUserAgentDeviceTest")
          && (configProperty.getProperty("runUserAgentDeviceTest").equalsIgnoreCase("true"))) ? true
              : false;
      if (checkDeviceExecution) {
        dataToBeReturned = configProperty.hasProperty("deviceOrientation")
            ? configProperty.getProperty("deviceOrientation")
            : "no local run config variable: deviceOrientation ";
      } else {
        dataToBeReturned = "local browser test: no orientation";
      }
    }
    return dataToBeReturned;
  }

  public static WebDriver switchWindows(WebDriver driver, String windowToSwitch, String opt,
      String closeCurrentDriver, String match) throws Exception {

    WebDriver currentWebDriver = driver;
    WebDriver assingedWebDriver = driver;
    boolean windowFound = false;
    ArrayList<String> multipleWindows = new ArrayList<String>(assingedWebDriver.getWindowHandles());
    System.out.println(multipleWindows.size());
    System.out.println(multipleWindows);
    for (int i = 1; i < multipleWindows.size(); i++) {
      System.out.println(assingedWebDriver.getTitle());
      assingedWebDriver.switchTo().window(multipleWindows.get(i));

      /*
       * if (opt.equals("title")) { if(match.equals("contain")){ if
       * (assingedWebDriver.getTitle().contains(windowToSwitch)) { windowFound = true; break; } }
       * if(match.equals("equal")){ if (assingedWebDriver.getTitle().equals(windowToSwitch)) {
       * windowFound = true; break; } }
       * 
       * } else if (opt.equals("url")) { if
       * (assingedWebDriver.getCurrentUrl().contains(windowToSwitch)) { windowFound = true; break; }
       * } else if (opt.equals("class")){ if (assingedWebDriver.getClass().equals(windowToSwitch)){
       * windowFound = true; break; } }
       */
      // if

      if (opt.equals("url")) {
        if (match.equals("contain")) {
          if (assingedWebDriver.getTitle().contains(windowToSwitch)) {
            windowFound = true;
            break;
          }
        }
        if (match.equals("equal")) {
          if (assingedWebDriver.getTitle().contains(windowToSwitch)) {
            windowFound = true;
            break;
          }
        }
      }
      if (opt.equals("title")) {
        if (match.equals("contain")) {
          if (assingedWebDriver.getTitle().contains(windowToSwitch)) {
            windowFound = true;
            break;
          }
        }
        if (match.equals("equal")) {
          if (assingedWebDriver.getTitle().contains(windowToSwitch)) {
            windowFound = true;
            break;
          }
        }

      } // for

      if (!windowFound)
        throw new Exception("Window: " + windowToSwitch + ", not found!!");
      else {
        if (closeCurrentDriver.equals("true"))
          currentWebDriver.close();
      }
    }
    return assingedWebDriver;

  }// switchWindows

  /**
   * To compare two array list values,then print unique list value and print missed list value
   * 
   * @param expectedElements - expected element list
   * @param actualElements - actual element list
   * @return statusToBeReturned - returns true if both the lists are equal, else returns false
   */
  public static boolean compareTwoList(List<String> expectedElements, List<String> actualElements) {
    boolean statusToBeReturned = false;
    List<String> uniqueList = new ArrayList<String>();
    List<String> missedList = new ArrayList<String>();
    for (String item : expectedElements) {
      if (actualElements.contains(item)) {
        uniqueList.add(item);
      } else {
        missedList.add(item);
      }
    }
    Collections.sort(expectedElements);
    Collections.sort(actualElements);
    if (expectedElements.equals(actualElements)) {
      Log.event("All elements checked on this page:: " + uniqueList);
      statusToBeReturned = true;
    } else {
      Log.event("Missing element on this page:: " + missedList);
      statusToBeReturned = false;
    }
    return statusToBeReturned;
  }

  /**
   * To wait for the specific element which is in disabled state on the page
   * 
   * @param driver - current driver object
   * @param element - disabled webelement
   * @param maxWait - duration of wait in seconds
   * @return boolean - return true if disabled element is present else return false
   */
  public static boolean waitForDisabledElement(WebDriver driver, WebElement element, int maxWait) {
    boolean statusOfElementToBeReturned = false;
    long startTime = StopWatch.startTime();
    WebDriverWait wait = new WebDriverWait(driver, maxWait);
    try {
      WebElement waitElement = wait.until(ExpectedConditions.visibilityOf(element));
      if (!waitElement.isEnabled()) {
        statusOfElementToBeReturned = true;
        Log.event("Element is displayed and disabled:: " + element.toString());
      }
    } catch (Exception ex) {
      statusOfElementToBeReturned = false;
      Log.event("Unable to find disabled element after " + StopWatch.elapsedTime(startTime)
          + " sec ==> " + element.toString());
    }
    return statusOfElementToBeReturned;
  }

  /**
   * To get the text of a WebElement
   * 
   * @param element - the input field you need the value/text of
   * @param driver -
   * @return text of the input's value
   */
  public static String getTextOfWebElement(WebElement element, WebDriver driver) {
    String sDataToBeReturned = null;
    if (WaitUtils.waitForElement(driver, element)) {
      sDataToBeReturned = element.getText().trim().replaceAll("\\s+", " ");
    }
    return sDataToBeReturned;
  }
  
  /**
   * Javascript executor to click
   * 
   * @param driver
   * @param element
   */
  public static void javaScriptExecutorToClick(WebDriver driver, WebElement element){
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript("arguments[0].click();", element);
  }
  
  /**
   * Javascript executor to sendkeys
   * 
   * @param driver
   * @param element
   * @param value
   */
  public static void javaScriptExecutorToSendKeys(WebDriver driver, WebElement element,String value){
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    executor.executeScript("arguments[0].value='"+value+"';", element);
  }
  
  /**
   * To enter text in text box
   * 
   * @param textToInput
   * @param texBox
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public static void enterInTextbox(String textToInput, WebElement textBox, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      textBox.clear();
      textBox.sendKeys(textToInput);
      Log.message(textToInput+" entered in "+textBox+" successfully.", driver, extentedReport, screenshot);
    }
    catch(Exception e){
      throw new Exception("Error while entering text. Exception: "+e);
    }   
  }
  
  /**
   * To validate an input value from a text box
   * 
   * @param expectedString
   * @param textBoxWebElement
   * @param driver
   * @param extentedReport
   * @return
   * @throws Exception
   */
  public static boolean validateStringFromTextBox(String expectedString, WebElement textBoxWebElement, 
      WebDriver driver, ExtentTest extentedReport) throws Exception{
    try{ 
      String actualString = textBoxWebElement.getAttribute("value");
        if(actualString.equals(expectedString)){           
        Log.message("The string value matches the expected value in text box.", driver, true);
        return true;
      }
      else{
        Log.message("The string value is not matching the expected value in text box." , 
            driver, extentedReport, true);
        return false;}
      }catch(Exception e){
      throw new Exception("Error while validating text- from text box. Exception: "+e);
    }   
  }

  /**
   * Verify contents of a WebElement equals a passed in string variable
   * 
   * @param textToVerify - expected text
   * @param elementToVerify - element to verify the text of
   * @return true if text on screen matches passed variable contents
   * @throws Exception 
   */
  public static boolean verifyWebElementTextEquals(WebElement elementToVerify,
      String textToVerify) {
    boolean status = false;
    System.out.println(elementToVerify.getText().trim().replaceAll("\\s+", " "));
    if (elementToVerify.getText().trim().replaceAll("\\s+", " ").equals(textToVerify)) {
      status = true;
    }
    return status;
  }
  
  /**
   * Verify contents of a WebElement equals a passed in string variable
   * 
   * @param textToVerify - expected text
   * @param elementToVerify - element to verify the text of
   * @return true if text on screen matches passed variable contents
   * @throws Exception 
   */
  public static boolean verifyWebElementTextEqualsTo(WebElement elementToVerify,
      String textToVerify) throws Exception {
    boolean status = false;
    try{
    if (elementToVerify.getText().trim().replaceAll("\\s+", " ").equals(textToVerify)) {
      status = true;
    }
    return status;}
    catch(Exception e){
      throw new Exception("Error while validating the text value of a web element"+e);
    }
  }
  

  /**
   * Verify contents of a WebElement equals a passed in string variable. To verify sub string
   * 
   * @param elementToVerify
   * @param textToVerify
   * @param breakPoint
   * @return
   * @throws Exception 
   */
  public static boolean verifyWebElementSubText(WebElement elementToVerify,
      String textToVerify, String breakPoint) throws Exception {
    boolean status = false;
    try{
      String [] elementText = elementToVerify.getText().split(breakPoint);
      for(int i=0;i<elementText.length;i++){
        if (elementText[i].trim().replaceAll("\\s+", " ").equals(textToVerify)) {
          status = true;
          break;
        }
      }
      return status;
    }catch(Exception e){throw new Exception("Error while validating the sub text value of a web element"+e);}
  }
  
  /**
   * Verify contents of a WebElement equals ignoring case a passed in string variable
   * 
   * @param textToVerify - expected text
   * @param elementToVerify - element to verify the text of
   * @return true if text on screen equals ignoring case passed variable contents
   */
  public static boolean verifyWebElementTextEqualsIgnoreCase(WebElement elementToVerify,
      String textToVerify) {
    boolean status = false;
    if (elementToVerify.getText().trim().replaceAll("\\s+", " ")
        .equalsIgnoreCase(textToVerify.trim())) {
      status = true;
    }
    return status;
  }

  /**
   * Verify any text is equals a passed in string variable
   * 
   * @param textToVerify - expected text
   * @param elementToVerify - text that got from an element
   * @return true if text on screen matches passed variable contents
   */
  public static boolean verifyTextEquals(String elementTextToVerify, String textToVerify) {
    boolean status = false;
    if (elementTextToVerify.equals(textToVerify)) {
      status = true;
    }
    return status;
  }

  /**
   * Verify contents of a WebElement contains a passed in string variable
   * 
   * @param textToVerify - expected text
   * @param elementToVerify - element to verify the text of
   * @return true if text on screen matches passed variable contents
   */
  public static boolean verifyWebElementTextContains(WebElement elementToVerify,
      String textToVerify) {
    boolean status = false;
    if (elementToVerify.getText().trim().replaceAll("\\s+", " ").contains(textToVerify)) {
      status = true;
    }
    return status;
  }

  /**
   * To get matching text element from List of web elements
   * 
   * @param elements -
   * @param contenttext - text to match
   * @return elementToBeReturned as WebElement
   * @throws Exception -
   */
  public static WebElement getMatchingTextElementFromList(List<WebElement> elements,
      String contenttext) throws Exception {
    WebElement elementToBeReturned = null;
    boolean found = false;

    if (elements.size() > 0) {
      for (WebElement element : elements) {
        if (element.getText().trim().replaceAll("\\s+", " ").equalsIgnoreCase(contenttext)) {
          elementToBeReturned = element;
          found = true;
          break;
        }
      }
      if (!found) {
        throw new Exception("Didn't find the correct text(" + contenttext + ")..! on the page");
      }
    } else {
      throw new Exception("Unable to find list element...!");
    }
    return elementToBeReturned;
  }

  /**
   * To verify matching text element from List of web elements
   * 
   * @param elements -
   * @param contenttext - text to match
   * @return elementToBeReturned as WebElement
   * @throws Exception -
   */
  public static boolean verifyMatchingTextContainsElementFromList(List<WebElement> elements,
      String contenttext) throws Exception {

    boolean found = false;
    if (elements.size() > 0) {
      for (WebElement element : elements) {
        if (element.getText().trim().contains(contenttext)) {
          found = true;
          break;
        }
      }
      if (!found) {
        Log.failsoft("Didn't find the correct text(" + contenttext + ")..! on the page");
      }
    } else {
      throw new Exception("Unable to find list element...!");
    }

    return found;
  }
  
  /**
   * Actions class element
   * 
   * @param dropdown
   * @param driver
   * @return
   */
  public static Actions actionElement(WebDriver driver){
    Actions action = new Actions(driver);
    return action;
  }
  
  /**
   * To verify matching text element from List of web elements - Textbox
   * 
   * @param elements
   * @param contenttext
   * @return
   * @throws Exception
   */
  public static boolean verifyMatchingTextContainsElementFromListTextbox(List<WebElement> elements,
      String contenttext) throws Exception {

    boolean found = false;
    if (elements.size() > 0) {
      for (WebElement element : elements) {       
        if (element.getAttribute("value").contains(contenttext)) {
          found = true;
          break;
        }
      }
      if (!found) {
        Log.failsoft("Didn't find the correct text(" + contenttext + ")..! on the page");
      }
    } else {
      throw new Exception("Unable to find list element...!");
    }
    return found;
  }
  
  /**
   * Validate whether element is selected
   * @param element
   * @return
   * @throws Exception
   */
  public static boolean isSelected(WebElement element) throws Exception{
    try{     
      return element.isSelected();        
    }catch(Exception e){
      throw new Exception("Error while validating isSelected method. Exception: "+e);
    }  
  }
  
  /**
   * To select a check box
   * 
   * @param checkBox
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public static void selectCheckBox(WebElement checkBox,  WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      if(!checkBox.isSelected()){
        checkBox.click(); 
      }
      Log.message("User is able to click on checkbox successfully.", driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while selecting checkbox. Exception: "+e);
    }   
  } 
  
  /**
   * To click a button
   * 
   * @param button
   * @param driver
   * @param extentedReport
   * @param screenshot
   * @throws Exception
   */
  public static void clickButton(WebElement button,  WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
        button.click();      
      Log.message("User is able to click on the button successfully.", driver, extentedReport, screenshot);
    }catch(Exception e){
      throw new Exception("Error while clicking button. Exception: "+e);
    }   
  }

  /**
   * To validate whether the expected radio button is selected.
   * 
   * @param radioBtnList
   * @param radioBtnListText
   * @param expectedSelectedValue
   * @param driver
   * @param extentedReport
   * @return
   * @throws Exception
   */
  public static boolean validateSelectedRadioBtn(List<WebElement> radioBtnList, List<WebElement> radioBtnListText, String expectedSelectedValue, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    int check=0;
    boolean returnType=false;
    try{
      for(int i=0;i<radioBtnListText.size();i++){
        if(expectedSelectedValue.equals("")){
          returnType = true;
          check = 1;
          break;
        }
        if(radioBtnListText.get(i).getText().equals(expectedSelectedValue)){
          check++;
          if(radioBtnList.get(i).isSelected()){
            Log.message("The expected radio button is selected by default", driver, extentedReport, screenshot);
            returnType = true;
            break;
          } 
        }
      }     
      if(check == 0){
        Log.message("Invalid text options for radio button. User unable to select radio button", driver, extentedReport, true);
      }  
      return returnType;   
    }catch(Exception e){
      throw new Exception("Error while validating the default selected radio button. Exception: "+e);
    }  
  }
  
  /**
   * To validate whether the expected radio button is selected with expected substring value
   * 
   * @param radioBtnList
   * @param radioBtnListText
   * @param expectedSelectedValue
   * @param driver
   * @param extentedReport
   * @return
   * @throws Exception
   */
  public static boolean validateSelectedRadioBtnSubString(List<WebElement> radioBtnList, List<WebElement> radioBtnListText, String expectedSelectedValue, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    int check=0;
    boolean returnType=false;
    try{
      for(int i=0;i<radioBtnListText.size();i++){
        if(radioBtnListText.get(i).getText().contains(expectedSelectedValue)){
          check++;
          if(radioBtnList.get(i).isSelected()){
            Log.message("The expected radio button is selected by default", driver, extentedReport, screenshot);
            returnType = true;
            break;
          } 
        }
      }     
      if(check == 0){
        Log.message("Invalid text options for radio button. User unable to select radio button", driver, extentedReport, true);
      }  
      return returnType;   
    }catch(Exception e){
      throw new Exception("Error while validating the default selected radio button. Exception: "+e);
    }  
  }
  
  /**
   * To select radio button
   * 
   * @param radioBtnList
   * @param radioBtnListText
   * @param valueToSelect
   * @param driver
   * @param extentedReport
   * @throws Exception
   */
  public static void selectRadioBtn(List<WebElement> radioBtnList, List<WebElement> radioBtnListText, String valueToSelect, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      for(int i=0;i<radioBtnListText.size();i++){
        if(radioBtnListText.get(i).getText().equals(valueToSelect)){
          WaitUtils.waitForElementPresent(driver,  radioBtnList.get(i), "radio button is not present");
          radioBtnList.get(i).click();
          Log.message("The radio button is selected.",driver, extentedReport, screenshot);
          break;   
        }
      }         
    }catch(Exception e){
      throw new Exception("Error while selecting the radio button. Exception: "+e);
    }  
  }

  /**
   * To validate whether the expected option is selected in the drop down menu with single select option.
   * 
   * @param dropdown
   * @param expectedSelectedValue
   * @return
   * @throws Exception
   */
  public static boolean validateSelectedValueDropdown(WebElement dropdown, String expectedSelectedValue, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    boolean returnType = false;
    String actualSelectedValue; 
    try{
      Select dropDown = new Select(dropdown);
      actualSelectedValue = dropDown.getFirstSelectedOption().getText();
      if(actualSelectedValue.equals(expectedSelectedValue)){
        returnType = true;
        Log.message("The expected radio button is selected by default",driver, extentedReport, screenshot);
      }
    }catch(Exception e){
      throw new Exception("Error while validating the default selected value in dropdown. Exception: "+e);
    } 
   return returnType;
  }
  
  /**
   * @param dropdown
   * @param valueToBeSelected
   * @param driver
   * @param extentedReport
   * @throws Exception
   */
  public static void selectValueFromDropdown(WebElement dropdown, String valueToBeSelected, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception{
    try{
      Select dropDown = new Select(dropdown);
      dropDown.selectByVisibleText(valueToBeSelected);      
      Log.message("The dropdown option is selected.",driver, extentedReport, screenshot);      
    }catch(Exception e){
      throw new Exception("Error while validating the default selected value in dropdown. Exception: "+e);
    } 
  }

  /**
   * To scroll into particular element
   * 
   * @param driver -
   * @param element - the element to scroll to
   */
  public static void scrollIntoView(final WebDriver driver, WebElement element) {
    try {
      String scrollElementIntoMiddle =
          "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
              + "var elementTop = arguments[0].getBoundingClientRect().top;"
              + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
      ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
      (new WebDriverWait(driver, 20).pollingEvery(500, TimeUnit.MILLISECONDS)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Page spinners/page not loading")).until(WaitUtils.documentLoad);
    } catch (Exception ex) {
      Log.event("Moved to element");
    }
  }
  
  /**
   * To scroll into particular element
   * 
   * @param driver -
   * @param element - the element to scroll to
   */
  public static void scrollIntoView(final WebDriver driver, List<WebElement> element) {
    try {
      String scrollElementIntoMiddle =
          "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
              + "var elementTop = arguments[0].getBoundingClientRect().top;"
              + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
      ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
      (new WebDriverWait(driver, 20).pollingEvery(500, TimeUnit.MILLISECONDS)
          .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
          .withMessage("Page spinners/page not loading")).until(WaitUtils.documentLoad);
    } catch (Exception ex) {
      Log.event("Moved to element");
    }
  }

  /**
   * Switching between tabs or windows in a browser
   * 
   * @param driver -
   */
  public static void switchToNewWindow(WebDriver driver) {
    String winHandle = driver.getWindowHandle();
    for (String index : driver.getWindowHandles()) {
      if (!index.equals(winHandle)) {
        driver.switchTo().window(index);
        break;
      }
    }
    if (!((RemoteWebDriver) driver).getCapabilities().getBrowserName().matches(".*safari.*")) {
      ((JavascriptExecutor) driver).executeScript(
          "if(window.screen){window.moveTo(0, 0); window.resizeTo(window.screen.availWidth, window.screen.availHeight);};");
    }
  }

  /**
   * To perform mouse hover on an element using javascript
   * 
   * @param driver
   * @param element
   */
  public static void moveToElementJS(WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript(MOUSE_HOVER_JS, element);
  }

  /**
   * To compare two HashMap values,then print unique list value and print missed list value
   * 
   * @param expectedList - expected element list
   * @param actualList - actual element list
   * @return statusToBeReturned - returns true if both the lists are equal, else returns false
   */
  public static boolean compareTwoHashMap(HashMap<String, String> expectedList,
      HashMap<String, String> actualList) {
    List<String> missedkey = new ArrayList<String>();
    HashMap<String, String> missedvalue = new HashMap<String, String>();
    try {
      for (String k : expectedList.keySet()) {
        if (!(actualList.get(k).equals(expectedList.get(k)))) {
          missedvalue.put(k, actualList.get(k));
          Log.event("Missed Values:: " + missedvalue);
          return false;
        }
      }
      for (String y : actualList.keySet()) {
        if (!expectedList.containsKey(y)) {
          missedkey.add(y);
          Log.event("Missed keys:: " + missedkey);
          return false;
        }
      }
    } catch (NullPointerException np) {
      return false;
    }
    return true;
  }

  /**
   * To verify font of the webelement
   * 
   * 
   * @param locator - css selector of webelement
   * @param font - font type to verify
   * @return boolean
   * 
   */
  public static boolean verifyFont(WebDriver driver, String locator, String font) throws Exception {
    try {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      WebElement element = driver.findElement(By.cssSelector(locator));
      String fontWeight = (String) js.executeScript(
          "return getComputedStyle(arguments[0]).getPropertyValue('font-Weight');", element);
      if (fontWeight.trim().equals(font)) {
        return true;
      } else {
        return false;
      }
    } catch (Exception ex) {
      throw new Exception("Error while veriying whether the string is in bold" + ex);

    }
  }

  /**
   * To verify given string is displayed first
   * 
   * 
   * @param locator - css selector of webelement
   * @param font - font type to verify
   * @return boolean
   * 
   */
  public static boolean verifyWebElementStartWith(WebDriver driver, WebElement element,
      String substringToVerify) throws Exception {
    try {
      boolean firstString = false;
      String displayedPolicyHolderName = getTextOfWebElement(element, driver);
      if (displayedPolicyHolderName.startsWith(substringToVerify)) {
        firstString = true;
      }
      return firstString;

    } catch (Exception ex) {
      throw new Exception("Error while veriying whether the string is in bold" + ex);

    }
  }

  /**
   * To verify a list is in alphabetical order
   * 
   * @param listToCheck - what to check alpha order of
   * @return boolean
   */
  public static boolean verifyListInAlphabeticalOrder(List<WebElement> listToCheck) {
    boolean status = false;
    List<String> ActualList = new ArrayList<String>();
    List<String> Sortedlist = new ArrayList<String>();
    for (WebElement element : listToCheck) {
      ActualList.add(element.getText());
      Sortedlist.add(element.getText());
    }
    Collections.sort(Sortedlist);
    if (ActualList.equals(Sortedlist)) {
      status = true;
      Log.message("List is in alphabetical order: " + Sortedlist);
    } else {
      status = false;
    }
    return status;
  }

  /**
   * To generate Random characters
   * 
   * @param type
   * @param length
   * 
   */
  public static String getRandomCharacters(String type, int length) {
    String SALTCHARS = null;
    if ("ALPHANUMERIC".equalsIgnoreCase(type))
      SALTCHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
    else if ("ALPHA".equalsIgnoreCase(type))
      SALTCHARS = "abcdefghijklmnopqrstuvwxyz";
    else if ("NUMERIC".equalsIgnoreCase(type))
      SALTCHARS = "0123456789";

    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < length) {
      int index = (int) (rnd.nextFloat() * SALTCHARS.length());
      salt.append(SALTCHARS.charAt(index));
    }

    String saltStr = salt.toString();
    return saltStr;
  }

  /**
   * To set past or future date
   * 
   * @param days - future/past/current
   * @param dateFieldLocator - locator of the date field to set the date
   * @param nofOfDays - No of Days to add or Subtract
   * @param noOfYear - No of Years to add or subtract
   * @throws ParseException
   * 
   */
  public static String setDate(String days, WebElement dateFieldLocator, int nofOfDays,
      int noOfYear) throws ParseException {

    String pastAndFutureDate = null;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String stringDate = sdf.format(date);
    Date d = sdf.parse(stringDate);
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    if (days.equalsIgnoreCase("future")) {
      cal.add(Calendar.DATE, +nofOfDays); // number of days to add
      cal.add(Calendar.YEAR, +noOfYear);
      pastAndFutureDate = sdf.format(cal.getTime());
      dateFieldLocator.sendKeys(pastAndFutureDate);
    } else if (days.equalsIgnoreCase("past")) {
      cal.add(Calendar.DATE, -nofOfDays); // number of days to minus
      cal.add(Calendar.YEAR, -noOfYear);
      pastAndFutureDate = sdf.format(cal.getTime());
      dateFieldLocator.sendKeys(pastAndFutureDate);
    } else if (days.equalsIgnoreCase("current")) {
      dateFieldLocator.sendKeys(stringDate);
      pastAndFutureDate = stringDate;
    }
    return pastAndFutureDate;
  }

  /**
   * Returns a random integer number in given range.
   * 
   * @param min
   * @param max
   * @return the random integer number in given range
   */
  public static int getRandomNumberBetween(int min, int max) {
    return new Random().nextInt(max) + min;
  }

  /**
   * To verify String Present in Array
   * 
   * @param arr
   * @param targetValue
   * 
   */
  public static boolean verifyStringPresentInArray(String[] arr, String targetValue) {
    for (String s : arr) {
      if (s.equals(targetValue))
        return true;
    }
    return false;
  }

  /**
   * To check background color of given element
   * 
   * @param elementToCheck - WebElement that we are checking
   * @param desiredColor - hex value of a color
   * @return true if the desired color matches actual color
   * @throws Exception -
   */
  public static boolean checkBackgroundColor(WebElement elementToCheck, String desiredColor)
      throws Exception {
    boolean flag = false;
    try {
      String color = elementToCheck.getCssValue("background-color");
      String actualColor = convertColorFromRgbaToHex(color);

      flag = actualColor.equalsIgnoreCase(desiredColor);
    } catch (NoSuchElementException ex) {
      Log.exception(ex);
    }
    return flag;
  }

  /**
   * To check color of given element
   * 
   * @param elementToCheck - WebElement that we are checking
   * @param desiredColor - hex value of a color
   * @return true if the desired color matches actual color
   * @throws Exception -
   */
  public static boolean checkColor(WebElement elementToCheck, String desiredColor)
      throws Exception {
    boolean flag = false;
    try {
      String color = elementToCheck.getCssValue("background-color");
      String actualColor = convertColorFromRgbaToHex(color);
      flag = actualColor.equalsIgnoreCase(desiredColor);
    } catch (NoSuchElementException ex) {
      Log.exception(ex);
    }
    return flag;
  }

  /**
   * To convert color of an element from rgba to hex
   * 
   * @param color -
   * @return String of hex value
   */
  public static String convertColorFromRgbaToHex(String color) {
    String[] hexValue = color.replaceAll("[^,0-9]", "").split(",");

    int hexValue1 = Integer.parseInt(hexValue[0]);
    hexValue[1] = hexValue[1].trim();
    int hexValue2 = Integer.parseInt(hexValue[1]);
    hexValue[2] = hexValue[2].trim();
    int hexValue3 = Integer.parseInt(hexValue[2]);

    String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);

    return actualColor;
  }
  
  public static boolean validateCharFromStringValues(String charToValidate, char characterToValidate){
    char [] stringCharacters;
    boolean validator = false;
    stringCharacters = charToValidate.toCharArray();
    for(int i=0;i<stringCharacters.length;i++){
      if(stringCharacters[i] == characterToValidate){
        validator = true;
        break;
      }
    }
    return validator;
  }
  
}
  



