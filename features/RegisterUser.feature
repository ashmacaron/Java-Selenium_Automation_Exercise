Feature: User Registration

  @RegisterUser
  Scenario: Successfully register a new user
    Given I launch the browser and navigate to the website
    When I click on Signup Login button
    And I verify New User Signup is visible
    And I enter signup details and create account
    Then I verify account is created successfully
    And I verify user is logged in
    When I click Delete Account button
    Then I verify account is deleted successfully