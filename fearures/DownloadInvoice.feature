Feature: Download Invoice

  @DownloadInvoice
  Scenario: Download invoice after purchase
    Given I launch the browser and navigate to the website
    When I add products to cart
    And I click on Cart button
    And I proceed to checkout as guest user
    And I complete the purchase process
    Then I verify account is deleted successfully