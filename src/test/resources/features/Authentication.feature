Feature: Authentication/registration of user

  Scenario: User registers, tries to log in but fails, logs out and after that correctly logs in, authenticates and wants to refresh token

    Given a user with the first name "registerNameTest", last name "registerLastNameTest", email "registerTest@gmail.com", and password "registerTestPassword" registers successfully
    Then the response status should be 200

    When the user tries to authenticate with email "registerTest@gmail.com" and password "registerTestPassword"
    Then the response status should be 200

    When the user tries to log in with email "registerTest@gmail.com" and password "registerTestPassword"
    Then the response status should be 400

    When the user logs out
    Then the response status should be 200

    When the user tries to log in with email "registerTest@gmail.com" and password "registerTestPassword"
    Then the response status should be 200

    When the user wants to refresh token
    Then the response status should be 200