
Feature: Create region
  I want to use this template for region

  Scenario: WITH ALL REQUIRED FIELDS IS SUCCESSFUL

    Given Admin wants to create a region with the following attributes
      | regionId  | name       | city  | state  | country | storeList  | tax  |
    	| 12        |  RVY-Delhi | delhi | delhi  | india   |    null    | null |
    When admin saves the new region 'WITH ALL REQUIRED FIELDS'
    Then the save 'IS SUCCESSFUL'
