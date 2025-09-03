package actions;

import pageObjects.PageObjects;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.SignupLoginPage;

import io.qameta.allure.Attachment;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

public class LoginUserActions extends CommonActions {
    private static final Logger logger = LogManager.getLogger(LoginUserActions.class);
    private static JsonNode testData;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            testData = mapper.readTree(new File("src/test/resources/testdata.json"));
        } catch (Exception e) {
            logger.error("Failed to load test data in LoginUserActions: {}", e.getMessage(), e);
        }
    }

    public static void verifyLoginToAccountVisible() {
        try {
            logger.info("Verifying 'Login to your account' text is visible.");
            verifyElementVisible(SignupLoginPage.LOGIN_ACCOUNT_TEXT, "Login to your account");
        } catch (Exception e) {
            logger.error("Failed to verify 'Login to your account' text.", e);
        }
    }

    public static void enterIncorrectLoginCredentials() {
        try {
            logger.info("Entering incorrect login credentials.");
            JsonNode loginCreds = testData.get("loginCredentials");
            enterText(SignupLoginPage.LOGIN_EMAIL_INPUT, loginCreds.get("invalidEmail").asText());
            enterText(SignupLoginPage.LOGIN_PASSWORD_INPUT, loginCreds.get("invalidPassword").asText());
            clickElement(SignupLoginPage.LOGIN_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to enter incorrect login credentials.", e);
        }
    }


    public static void loginWithValidCredentials() {
        try {
            logger.info("Entering valid login credentials.");
            JsonNode loginCreds = testData.get("loginCredentials");
            enterText(SignupLoginPage.LOGIN_EMAIL_INPUT, loginCreds.get("validEmail").asText());
            enterText(SignupLoginPage.LOGIN_PASSWORD_INPUT, loginCreds.get("validPassword").asText());
            clickElement(SignupLoginPage.LOGIN_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to enter valid login credentials.", e);
        }
    }

    // Helper method in a utility class to take a screenshot for Allure
    @Attachment(value = "{name}", type = "image/png")
    public static byte[] saveScreenshot(String name) {
        // Assumes getDriver() returns your WebDriver instance
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static void verifyErrorMessageDisplayed() {
        try {
            logger.info("Verifying incorrect login error message is displayed.");
            verifyElementVisible(SignupLoginPage.LOGIN_ERROR_MESSAGE, "Your email or password is incorrect!");
        } catch (Exception e) {
            logger.error("Failed to verify the login error message.", e);
        }
    }
}