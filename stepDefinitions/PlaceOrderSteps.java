package stepDefinitions;

import actions.PlaceOrderActions;
import io.cucumber.java.en.*;


public class PlaceOrderSteps {

    @When("I proceed to checkout and place order")
    public void proceedToCheckoutAndPlaceOrder() {
        PlaceOrderActions.proceedToCheckoutAndPlaceOrder();
    }

    @Then("I verify order is placed successfully")
    public void verifyOrderPlacedSuccessfully() {
        PlaceOrderActions.verifyOrderPlacedSuccessfully();
    }
}