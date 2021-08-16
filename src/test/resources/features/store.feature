
Feature: Create store
  I want to use this template for store

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given Admin wants to create a store with the following attributes
      | storeId  | name             |  address              | mobileNumber | email              | region  | userList |
    	| 12       |  RVY Supermarket | sec 23, maviya nagar  | 7890967890   | rvy_jaipur@rvy.com |   null  | null |
    When admin saves the new store 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'
