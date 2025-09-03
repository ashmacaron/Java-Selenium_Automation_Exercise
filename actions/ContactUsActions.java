package actions;

import base.BaseUtil;
import pageObjects.ContactUsPage;
import pageObjects.HomePage;
import pageObjects.PageObjects;
import org.openqa.selenium.Alert;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContactUsActions extends CommonActions {
    private static final Logger logger = LogManager.getLogger(ContactUsActions.class);
    private static JsonNode testData;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            testData = mapper.readTree(new File("src/test/resources/testdata.json"));
        } catch (Exception e) {
            logger.error("Failed to load test data in ContactUsActions: {}", e.getMessage(), e);
        }
    }

    public static void verifyGetInTouchVisible() {
        try {
            logger.info("Verifying 'Get In Touch' text is visible.");
            Thread.sleep(2000); // Wait for page to load
            verifyElementVisible(ContactUsPage.GET_IN_TOUCH_TEXT, "Get In Touch");

            BaseUtil.handlePopup();

        } catch (Exception e) {
            logger.error("Failed to verify 'Get In Touch' text.", e);
        }
    }

    public static void fillContactFormDetails() {
        try {
            logger.info("Filling out the contact form details.");
            JsonNode contactForm = testData.get("contactForm");

            Thread.sleep(1000);
            enterText(ContactUsPage.CONTACT_NAME_INPUT, contactForm.get("name").asText());
            enterText(ContactUsPage.CONTACT_EMAIL_INPUT, contactForm.get("email").asText());
            enterText(ContactUsPage.CONTACT_SUBJECT_INPUT, contactForm.get("subject").asText());
            enterText(ContactUsPage.CONTACT_MESSAGE_INPUT, contactForm.get("message").asText());

            // Upload file - create a simple text file if cat.jpg doesn't exist
            uploadContactFile(contactForm);

        } catch (Exception e) {
            logger.error("Failed to fill the contact form.", e);
        }
    }

    private static void uploadContactFile(JsonNode contactForm) {
        try {
            String filePath = System.getProperty("user.dir") + "/src/test/resources/" + contactForm.get("uploadFile").asText();
            java.io.File file = new java.io.File(filePath);
            if (!file.exists()) {
                // Create a dummy file for testing
                filePath = System.getProperty("user.dir") + "/src/test/resources/cat.jpg";
                try (java.io.FileWriter writer = new java.io.FileWriter(filePath)) {
                    writer.write("This is a test file for upload");
                }
            }
            logger.info("Uploading file from path: {}", filePath);
            enterText(ContactUsPage.CONTACT_FILE_INPUT, filePath);
        } catch (Exception fileException) {
            logger.error("File upload failed, continuing without file.", fileException);
        }
    }

    public static void submitContactForm() {
        try {
            logger.info("Submitting the contact form.");
            clickElement(ContactUsPage.CONTACT_SUBMIT_BUTTON);

            // Handle alert if present
            handleAlertIfPresent();

        } catch (Exception e) {
            logger.error("Failed to submit the contact form.", e);
        }
    }

    private static void handleAlertIfPresent() {
        try {
            Alert alert = BaseUtil.getDriver().switchTo().alert();
            logger.info("Alert detected with text: '{}'. Accepting it.", alert.getText());
            alert.accept();
        } catch (Exception alertException) {
            // Alert might not be present, continue execution
            logger.debug("No alert was present, continuing execution.");
        }
    }

    public static void verifySuccessMessageAndNavigateHome() {
        try {
            logger.info("Verifying success message and navigating to the home page.");
            verifyElementVisible(ContactUsPage.SUCCESS_MESSAGE, "Success! Your details have been submitted successfully.");
            clickElement(HomePage.HOME_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to verify success message or navigate home.", e);
        }
    }
}