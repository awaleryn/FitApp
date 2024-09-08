Feature: Change daily intake based on added product

  Scenario: Get nutrition needs for a user
    When the user has assigned daily needs
    And adds new product to daily intake with "testProduct" product name
    Then the response status should be 200