Feature: Find Region

  Scenario: FINDING CUSTOMER BY ID

    Given The following region exists and Admin wants to find the region by ID
      | regionId  | name       | city  | state  | country | storeList  | tax  |
    	| 12        |  RVY-Delhi | delhi | delhi  | india   |    null    | null |
    	| 45        | RVY-Jaipur |jaipur | rajasthan| india | null       | null |
    When admin wants to find the region with ID '45'
    Then the region is found
