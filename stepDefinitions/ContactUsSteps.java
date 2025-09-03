package stepDefinitions;

import actions.ContactUsActions;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import base.BaseUtil;


public class ContactUsSteps {

    @When("I verify Get In Touch is visible")
    public void verifyGetInTouchVisible() {
        ContactUsActions.verifyGetInTouchVisible();
    }

    @When("I fill contact form details")
    public void fillContactFormDetails() {
        BaseUtil.handlePopup();
        ContactUsActions.fillContactFormDetails();
    }

    @When("I submit the contact form")
    public void submitContactForm() {
        ContactUsActions.submitContactForm();
    }

    @Then("I verify success message is displayed")
    public void verifySuccessMessageDisplayed() {
        ContactUsActions.verifySuccessMessageAndNavigateHome();
    }
}