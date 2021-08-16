Feature: Find store

  Scenario: FINDING STORE BY ID

    Given The following store exists and Admin wants to find the store by ID
      | storeId  | name             |  address              | mobileNumber | email              | region  | userList |
    	| 12       |  RVY Supermarket | sec 23, malviya nagar | 7890967890   | rvy_jaipur@rvy.com |   null  | null |
    	| 90       |  RVY Supermarket | sec 67, tilak nagar   | 7890967891   | rvy_delhi@rvy.com  |   null  | null |
    When admin wants to find the store with ID '12'
    Then the store is found
