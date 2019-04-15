@test
Feature: 298 Renewal
#
#		#-----Create multiple policies with renewal in EC and validate renewal dashboard and documents in SS ---------------------------
#	
    #Scenario: Auto Renew is set on a newly created policy and Renew my policy option is available and renewal document must appear
#	    Given Fetch the test case data from the "TC_001"
#	    When Create a Multiple renewal policy and review it and validate only reviewed policy is displayed for Renewal
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS
#	    And Validate multiple policy is displayed
#	    And Validate renewal documents
#	    And Logout of SS
    #
    #
#		#When Create a Multiple renewal policy with Add on Covers and review it and validate only reviewed policy is displayed for Renewal
#		
#		#------------------Card payment scenarios for renewal in SS with validations for existing quote, contact and payment details---------------
#	   
    #Scenario: User validates policy data and performs renewal on Self Service applicatiton using annual card method with existing card details
#	    Given Fetch the test case data from the "TC_002"
#			When Create a new business quote without add on covers
#	    And Perform renewal save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS
#	    And Select policy URN to be renewed
#	    And Verify policy data on Your Quote page 
#	    And Accept Terms & Conditions and click on Buy button
#	    And Verify data on Contact Details page
#	    And Navigate to Payment page
#	    And Verify existing payment plan and payment method
#	    And Verify existing card details
#	    And Select the policy holder entry
#	    And Navigate to confimation page
#	    And Verify the payment status   
#	    And Navigate to dashboard from Confirmation Page
#	    And Logout of SS
     #
    #Scenario: User performs renewal on Self Service applicatiton using Annual card method with new card details
#	    Given Fetch the test case data from the "TC_003"
#	    When Create a policy in EC and perform renewal on it and save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS
#	    And Select policy URN to be renewed
#	    And Verify policy data on Your Quote page 
#	    And Accept Terms & Conditions and click on Buy button
#	    And Verify data on Contact Details page
#	    And Navigate to Payment page
#	    And Verify existing payment plan and payment method
#	    And Verify existing card details
#	    And Verify card add error message
#	    And Select the policy holder entry
#	    And Add a new card
#	    And Enter the card details and make payment
#	    And Verify the payment status
#	    And Navigate to dashboard from Confirmation Page
#	    And Logout of SS
    #
    #
    #----Create a renewal policy with Add on cover in EC and perform remove and add actions for add on cover in SS followed by existing card 
    #----payment and validation of audits in EC----------------------------------------------------------------------------------------------
    #
    #Scenario: User updates the Add On Covers while renewal in Self Service application
#	    Given Fetch the test case data from the "TC_004"
#	    When Create a policy with Add on covers in EC and perform renewal on it and save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS
#	    And Select policy URN to be renewed
#	    And Verify the existing addon covers
#	    And Add a new addon cover and Recalculate
#	    And Remove the existing addon cover and Recalculate
#	    And Accept Terms & Conditions and click on Buy button
#	    And Navigate to Payment page
#	    And Verify existing payment plan and payment method
#	    And Verify existing card details
#	    And Select the policy holder entry
#	    And Navigate to confimation page
#	    And Verify the payment status
#	    And Navigate to dashboard from Confirmation Page
#	    And Logout of SS
#	    And Verify audit creation in EC for the changes in SS
    #
    #
    #-----Perform and unsucessful renewal card payment in SS followed by a successful payment---
    #
    #Scenario: User performs an unsucessful renewal on Self Service applicatiton
#	    Given Fetch the test case data from the "TC_005"
#	    When Create a policy in EC and perform renewal on it and save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS
#	    And Select policy URN to be renewed
#	    And Accept Terms & Conditions and click on Buy button
#	    And Navigate to Payment page
#	    And Verify existing payment plan and payment method
#	    And Select the policy holder entry
#	    And Add a new card
#	    And Enter the card details and make payment
#	    And Validate unsucessful payment screen
#	    And Add a new card
#	    And Enter the card details and make payment
#	    And Verify the payment status
#	    And Navigate to dashboard from Confirmation Page
#	    And Logout of SS
#
#
    #------------ Terms and Conditions validation in EC & SS------------------------------------------------------------------
    #
    #Scenario: Create a renewal quote and validate addition and updation of freestyle T&C in both EC and SS
#	    Given Fetch the test case data from the "TC_006"
#	    When Create a new business quote without add on covers
#	    And Perform renewal with addition of two T&C and save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS
#	    And Select policy URN to be renewed
#	    And Verify that added and edited T&C are present in Self Service
#	    And Logout from Your Quote page
   #
   #
    #Scenario: Create a renewal quote and validate addition and updation of system T&C in both EC and SS
#		  Given Fetch the test case data from the "TC_007"
#		  When Create a new business quote without add on covers
#		  And Perform renewal with addition of two T&C and save the policy in reviewed status
#		  And Create a username and password for SS user in UIRefresh
#		  Then Login to SS
#		  And Select policy URN to be renewed
#		  And Verify that added and edited T&C are present in Self Service
#		  And Logout from Your Quote page
    #
    #
    #----Perform renewal in SS and updates contact details, followed by audits verification in EC----------------------------------  
    #
    #Scenario: User performs renewal in EC followed by updation of mobile number in SS and verification of audit for the same in EC
#	    Given Fetch the test case data from the "TC_008"
#	    When Create a new business quote without add on covers
#	    And Perform renewal save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS
#	    And Select policy URN to be renewed
#	    And Accept Terms & Conditions and click on Buy button
#	    And Update contact name and click on cancel button
#	    And Select policy URN to be renewed
#	    And Accept Terms & Conditions and click on Buy button
#	    And Verify that name is not updated
#	    And Update mobile number and click save on Contact Details page
#	    And Verify that mobile number is updated
#	    And Navigate to Payment page
#	    And Logout from Payment page
#	    And Verify audit creation in EC for the changes in SS
    #
    #
    #------------------DD payment scenarios for renewal in SS with validations for existing quote, contact and payment details---------------
    #
    #Scenario: User validates policy data and performs renewal on Self Service applicatiton using monthly direct debit method with existing bank account details
#	    Given Fetch the test case data from the "TC_009"
#	    When Create a new business quote without add on covers
#	    And Perform renewal save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS
#	    And Select policy URN to be renewed
#	    And Verify policy data on Your Quote page 
#	    And Accept Terms & Conditions and click on Buy button
#	    And Verify data on Contact Details page
#	    And Navigate to Payment page
#	    And Verify existing payment plan and payment method
#	    And Verify existing bank account details
#	    And Select the policy holder entry
#	    And Accept Direct Debit T&C
#	    And Navigate to confimation page
#	    And Verify the payment status   
#	    And Navigate to dashboard from Confirmation Page
#	    And Logout of SS
    #
    #
    #Scenario: User validates policy data and performs renewal on Self Service applicatiton using monthly direct debit method with new bank account details
#	    Given Fetch the test case data from the "TC_010"
#	    When Create a new business quote without add on covers
#	    And Perform renewal save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS
#	    And Select policy URN to be renewed
#	    And Verify policy data on Your Quote page 
#	    And Accept Terms & Conditions and click on Buy button
#	    And Verify data on Contact Details page
#	    And Navigate to Payment page
#	    And Verify existing payment plan and payment method
#	    And Add new bank account details and make payment
#	    And Select the policy holder entry
#	    And Accept Direct Debit T&C
#	    And Navigate to confimation page
#	    And Verify the payment status   
#	    And Navigate to dashboard from Confirmation Page
#	    And Logout of SS
    #
    #
    #------------------Date message validations in SS for renewals-----------------------------------
    #
    #Scenario Outline: User verifies policy renewal messages on Self Service for different date input
#	    Given Fetch the test case data from the "TC_011" 
#	  	When Create a policy in EC using different "<date>" and perform renewal with reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	   	Then Login to SS
#	   	And Verify policy "<messages>" on customer dashboard 
#	   	And Logout of SS
#	   	
#	   	Examples:
#	   	| date | messages |
#			| 0		 |Your Household Standard policy is now due for renewal.|
#			| 10 	 |Your Household Standard policy is due for renewal in 9 days.|
#
#
#		#------------------Past date message validations in SS for renewals------------------------------------------------
#
    #Scenario Outline: User verifies policy renewal message on Self Service when renewal is not possible in SS
#	    Given Fetch the test case data from the "TC_013"
#	  	When Create a policy in EC using different "<date>" and perform renewal with reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	   	Then Login to SS
#	   	And Verify policy "<message>" on customer dashboard when renewal is not possible in SS
#	   	And Logout of SS
#	   	
#	   	Examples:
#	   	| date | message |
#			|-35	 |Your policy is now due for renewal. It is no longer possible to renew your Household Standard policy online. Please contact our Call Centre to renew your policy.| 		
#	
#		
#		#------------------Renewal in SS with change of payment plan and after renewal message validation------------------------------------------------
#		
#		Scenario Outline: User validates policy data and performs renewal on Self Service applicatiton using new payment plan and method
#	    Given Fetch the test case data from the "TC_012"
#	    When Create a policy in EC using different "<date>" and perform renewal with reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS
#	    And Select policy URN to be renewed 
#	    And Accept Terms & Conditions and click on Buy button
#	    And Navigate to Payment page
#	    And Verify existing payment plan and payment method
#	    And Change payment plan and payment method
#	    And Add new bank account details and make payment
#	    And Select the policy holder entry
#	    And Accept Direct Debit T&C
#	    And Navigate to confimation page
#	    And Verify the payment status   
#	    And Navigate to dashboard from Confirmation Page
#	    And Verify renewal "<message>" with and status
#	    And Logout of SS
    #
    #Examples:
   #	| date | message |
#		|  5 	 |Your Household Standard policy has been renewed and will become effective on|
#
#
#		#---------------------------------------------Renewal using Pay Later functionality------------------------------------------------ 
#		 
#		Scenario: Renew the Policy using Pay Later option in SS
#	    Given Fetch the test case data from the "TC_014"
#			When Create a new business quote without add on covers
#	    And Perform renewal save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS  
  #		And Select policy URN to be renewed 
      #And Accept Terms & Conditions and click on Buy button
      #And Navigate to Payment page
      #And Verify existing payment plan and payment method
      #And Change payment plan and payment method
    #	And Make payment using pay later option
    #	And Verify the payment status   
    #	And Navigate to dashboard from Confirmation Page
    #	And Logout of SS
    #
    #---------------------------------------------Required fields validations------------------------------------------------ 
#		 
#		Scenario: Validate the required fields in renewal quote on SS
#	    Given Fetch the test case data from the "TC_015"
#			When Create a new business quote without add on covers
#	    And Perform renewal save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS  
  #		And Select policy URN to be renewed 
      #And Accept Terms & Conditions and click on Buy button
      #And Verify data on Contact Details page
      #And Remove contact name fields
      #And Optin to maketing preference however no option should be selected for the same
      #And Click on save and validate required fields
      #And Update contact details and marketing preference
      #And Navigate to Payment page
      #And Validate required fields on payment page
      #
      #
    #---------------------------------------------Policy Data screen validations------------------------------------------------ 
#		 
#		Scenario: Validate the policy data screen fields in renewal quote on SS
#	    Given Fetch the test case data from the "TC_016"
#			When Create a new business quote without add on covers
#	    And Perform renewal save the policy in reviewed status
#	    And Create a username and password for SS user in UIRefresh
#	    Then Login to SS  
#	    And Select policy URN to be renewed
#	    And Navigate to View my insurance details page
#	    And Validate all the added cover details on these pages and finally naviagte to YourQuote page
#	    And Logout from Your Quote page 
      #
  	#---------------------------------------------GDPR Scenario----------------------------------------------------------------- 
  	
  	Scenario: Validate that user cannot perform renewal when the user is suspended with GDPR reason
	    Given Fetch the test case data from the "TC_017"
			When Create a new business quote without add on covers
	    And Perform renewal save the policy in reviewed status
	    And Create a username and password for SS user in UIRefresh
	    And Suspend the user with GDPR
	    Then Login to SS
	    And Validate that Suspended user is not able to Renew the policy
	    And Logout of SS