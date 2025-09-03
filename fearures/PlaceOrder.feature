Feature: Place Order

  @PlaceOrder
  Scenario: Place order after login
    Given I launch the browser and navigate to the website
    When I click on Signup Login button
    And I login with valid credentials
    And I add products to cart
    And I proceed to checkout and place order
    Then I verify order is placed successfully