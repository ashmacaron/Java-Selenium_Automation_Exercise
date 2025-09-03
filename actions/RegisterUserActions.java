package actions;

import base.BaseUtil;
import pageObjects.AccountInfoPage;
import pageObjects.HomePage;
import pageObjects.PageObjects;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.SignupLoginPage;

public class RegisterUserActions extends CommonActions {
    private static final Logger logger = LogManager.getLogger(RegisterUserActions.class);
    private static JsonNode testData;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            testData = mapper.readTree(new File("src/test/resources/testdata.json"));
        } catch (Exception e) {
            // Using the logger to report the error
            logger.error("Failed to load test data in RegisterUserActions: {}", e.getMessage(), e);
        }
    }

    public static void verifyNewUserSignupVisible() {
        try {
            logger.info("Verifying 'New User Signup!' text is visible.");
            verifyElementVisible(SignupLoginPage.NEW_USER_SIGNUP_TEXT, "New User Signup!");
        } catch (Exception e) {
            logger.error("Failed to verify 'New User Signup!' text.", e);
        }
    }

    public static void fillSignupFormAndCreateAccount() {
        try {
            logger.info("Starting user signup and account creation process.");
            JsonNode userReg = testData.get("userRegistration");

            Thread.sleep(2000); // Wait for page to load

            BaseUtil.handlePopup();

            // Enter signup details with unique email (for easy testing no duplication)
            String uniqueEmail = userReg.get("email").asText().replace("@", System.currentTimeMillis() + "@");
            logger.info("Using name '{}' and unique email '{}' for signup.", userReg.get("name").asText(), uniqueEmail);
            enterText(SignupLoginPage.SIGNUP_NAME_INPUT, userReg.get("name").asText());
            enterText(SignupLoginPage.SIGNUP_EMAIL_INPUT, uniqueEmail);
            clickElement(SignupLoginPage.SIGNUP_BUTTON);

            // Wait and verify account information page (Enter Account Information)
            Thread.sleep(3000);
            verifyElementVisible(AccountInfoPage.ACCOUNT_INFO_TEXT, "ENTER ACCOUNT INFORMATION");

            BaseUtil.handlePopup();

            fillAccountInformation(userReg);


            // Create account
            logger.info("Submitting the form to create an account.");
            clickElement(AccountInfoPage.CREATE_ACCOUNT_BUTTON);

        } catch (Exception e) {
            logger.error("An error occurred during the signup form process.", e);
        }
    }

    private static void fillAccountInformation(JsonNode userReg) {
        try {
            logger.info("Filling account information details.");
            // Fill account information (wait for action)
            Thread.sleep(1000);
            if (userReg.get("title").asText().equals("Mr")) {
                clickElement(AccountInfoPage.TITLE_MR_RADIO);
            }

            enterText(AccountInfoPage.PASSWORD_INPUT, userReg.get("password").asText());

            // Handle dropdowns with wait (dropdown hard-coded)
            Thread.sleep(1000);
            selectDropdownByText(AccountInfoPage.BIRTH_DAY_DROPDOWN, "15");
            selectDropdownByText(AccountInfoPage.BIRTH_MONTH_DROPDOWN, "January");
            selectDropdownByText(AccountInfoPage.BIRTH_YEAR_DROPDOWN, "1990");

            // Check checkboxes
            checkCheckbox(AccountInfoPage.NEWSLETTER_CHECKBOX);
            checkCheckbox(AccountInfoPage.OFFERS_CHECKBOX);

            // Fill address details
            enterText(AccountInfoPage.FIRST_NAME_INPUT, userReg.get("firstName").asText());
            enterText(AccountInfoPage.LAST_NAME_INPUT, userReg.get("lastName").asText());
            enterText(AccountInfoPage.COMPANY_INPUT, userReg.get("company").asText());
            enterText(AccountInfoPage.ADDRESS_INPUT, userReg.get("address").asText());
            enterText(AccountInfoPage.ADDRESS2_INPUT, userReg.get("address2").asText());

            Thread.sleep(1000);
            selectDropdownByText(AccountInfoPage.COUNTRY_DROPDOWN, userReg.get("country").asText());
            enterText(AccountInfoPage.STATE_INPUT, userReg.get("state").asText());
            enterText(AccountInfoPage.CITY_INPUT, userReg.get("city").asText());
            enterText(AccountInfoPage.ZIPCODE_INPUT, userReg.get("zipcode").asText());
            enterText(AccountInfoPage.MOBILE_INPUT, userReg.get("mobile").asText());

        } catch (Exception e) {
            logger.error("Failed to fill account information details.", e);
        }
    }

    public static void verifyAccountCreatedAndContinue() {
        try {
            // (Account Created!)
            logger.info("Verifying 'ACCOUNT CREATED!' message.");
            verifyElementVisible(AccountInfoPage.ACCOUNT_CREATED_TEXT, "ACCOUNT CREATED!");
            clickElement(AccountInfoPage.CONTINUE_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to verify 'ACCOUNT CREATED!' message or click continue.", e);
        }
    }

    public static void verifyUserLoggedIn() {
        try {
            logger.info("Verifying that user is logged in.");
            verifyElementVisible(HomePage.LOGGED_IN_USER, "Logged in as");
        } catch (Exception e) {
            logger.error("Failed to verify user is logged in.", e);
        }
    }
}