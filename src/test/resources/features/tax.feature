Feature: Create Tax

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given Admin wants to create a tax with the following attributes
      | taxId  | code | name    | taxAmount   | effectiveDate  | regionList | 
    	| 12     | 100  | GST     | 13          | 2020-08-12      |    null    |
    When admin saves the new tax 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'
