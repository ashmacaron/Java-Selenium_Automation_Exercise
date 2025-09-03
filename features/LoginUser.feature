Feature: User Login

  @LoginUser
  Scenario: Login with incorrect credentials
    Given I launch the browser and navigate to the website
    When I click on Signup Login button
    And I verify Login to your account is visible
    And I enter incorrect login credentials
    Then I verify error message is displayed