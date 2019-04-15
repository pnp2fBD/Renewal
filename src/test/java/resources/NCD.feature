@NCD
Feature: No Claims Discount Motor
 
  Scenario Outline: Create a Basic Motor Scheme 1 policy in Engagement Center
    Given Fetch new test case data for basic motor policy from the "TC_101"
    When Initiate a basic  motor policy quote in engagement centre and enter the vehicle details
    And Add ncd details with NCD used "<ncdUsed>" years
    And Add driver details with an option of adding other policy details and navigate to previous losses screen
    And Add "<claims>" previous losses to the policy
   
   Examples:
	 |ncdUsed|claims|
	 | 2     | 1    |
