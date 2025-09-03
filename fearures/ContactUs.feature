Feature: Contact Us Form

  @ContactUs
  Scenario: Submit contact us form successfully
    Given I launch the browser and navigate to the website
    When I click on Contact Us button
    And I fill contact form details
    And I submit the contact form
    Then I verify success message is displayed
