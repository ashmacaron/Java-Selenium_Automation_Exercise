package stepDefinitions;

import actions.CommonActions;
import base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.cucumber.java.AfterStep;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.ByteArrayInputStream;


public class CommonSteps {

    @Before
    public void beforeScenario() {
        BaseUtil.handlePopup();
    }

    @Given("I launch the browser and navigate to the website")
    public void launchBrowserAndNavigate() {
        BaseUtil.handlePopup();
        CommonActions.launchBrowserAndNavigate();
    }

    @When("I click on Signup Login button")
    public void clickSignupLogin() {
        CommonActions.clickSignupLogin();
    }

    @When("I click on Contact Us button")
    public void clickContactUs() {
        CommonActions.clickContactUs();
    }

    @When("I add products to cart")
    public void addProductsToCart() {
        CommonActions.addProductsToCart();
    }

    @When("I click on Cart button")
    public void clickCartButton() {
        CommonActions.clickCartButton();
    }

    @When("I click Delete Account button")
    public void clickDeleteAccount() {
        CommonActions.clickDeleteAccount();
    }

    @Then("I verify account is deleted successfully")
    public void verifyAccountDeleted() {
        CommonActions.verifyAccountDeleted();
    }

    @AfterStep
    public void takeScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) BaseUtil.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        BaseUtil.closeDriver();
    }
}
