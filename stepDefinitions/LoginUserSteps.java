package stepDefinitions;

import actions.LoginUserActions;
import io.cucumber.java.en.*;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import base.BaseUtil;


public class LoginUserSteps {

    @When("I verify Login to your account is visible")
    public void verifyLoginToAccountVisible() {
        LoginUserActions.verifyLoginToAccountVisible();
    }

    @When("I enter incorrect login credentials")
    public void enterIncorrectLoginCredentials() {
        BaseUtil.handlePopup();
        LoginUserActions.enterIncorrectLoginCredentials();
    }

    @When("I login with valid credentials")
    public void loginWithValidCredentials() {
        LoginUserActions.loginWithValidCredentials();
    }

    @Then("I verify error message is displayed")
    public void verifyErrorMessageDisplayed() {
        LoginUserActions.verifyErrorMessageDisplayed();
    }
}