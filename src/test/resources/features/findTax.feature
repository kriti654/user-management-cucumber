Feature: Find Customer

  Scenario: FINDING CUSTOMER BY ID

    Given The following tax exists and Admin wants to find the tax by ID
      | taxId  | code | name    | taxAmount   | effectiveDate  | regionList | 
    	| 12     | 1001 | GST     | 13          | 2020-08-09     |    null    |
    	|13   | 4567 | SGST    | 12          |	2020-08-08     | null       |
    When admin wants to find the tax with ID '12'
    Then the tax is found
