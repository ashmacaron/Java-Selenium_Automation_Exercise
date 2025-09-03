package stepDefinitions;

import actions.RegisterUserActions;
import io.cucumber.java.en.*;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import base.BaseUtil;


public class RegisterUserSteps {

    @When("I verify New User Signup is visible")
    public void verifyNewUserSignupVisible() {
        RegisterUserActions.verifyNewUserSignupVisible();
    }

    @When("I enter signup details and create account")
    public void enterSignupDetailsAndCreateAccount() {
        RegisterUserActions.fillSignupFormAndCreateAccount();
    }

    @Then("I verify account is created successfully")
    public void verifyAccountCreated() {
        RegisterUserActions.verifyAccountCreatedAndContinue();
    }

    @Then("I verify user is logged in")
    public void verifyUserLoggedIn() {
        BaseUtil.handlePopup();
        RegisterUserActions.verifyUserLoggedIn();
    }
}