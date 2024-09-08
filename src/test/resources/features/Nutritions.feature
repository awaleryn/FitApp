Feature: Calculate nutrition needs

  Scenario: Get nutrition needs for a user with forbidden error
    When the user requests nutrition needs for weight 70.5 kg, height 175.0 cm, age 30 years, gender male, activity level moderately_active, and goal maintain without authorization
    Then the response status should be 403

  Scenario: Get nutrition needs for a user
    When the user requests nutrition needs for weight 70.5 kg, height 175.0 cm, age 30 years, gender male, activity level moderately_active, and goal maintain
    Then the response status should be 200
    And the nutrition needs should be calculated
