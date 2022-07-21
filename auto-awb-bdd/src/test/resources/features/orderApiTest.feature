Feature: Order test
  Test the Order Creation successful

  Scenario: Create Order successful
    Given Login to get token
    When Create a new order
    Then Order is created successful