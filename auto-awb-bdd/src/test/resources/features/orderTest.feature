Feature: Order test
  Test the Print AWB

  Scenario: Print airway bill
    Given Given Shipper opened the Ninja Dashboard login page at https://dashboard-qa.ninjavan.co/login-v2
    And shipper had an order created under his account
    When shipper login by using credentials challenge2@ninjavan.co and Ninjavan1234
    Then shipper arrived at landing page
    When shipper go to the tracking page
    And shipper click search button
    And shipper select the first tracking ID in the table
    Then QA verify that the Order Details page is shown
    When shipper click Print Airway Bill button
    And shipper select 1 bills per page in Print Airway Bills dialog
    And shipper click print button in Print Airway Bills dialog
    Then QA verify that the tracking ID text in the pdf is the same as in the step 7