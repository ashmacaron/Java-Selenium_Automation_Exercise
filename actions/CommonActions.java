package actions;

import base.BaseUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageObjects.AccountInfoPage;
import pageObjects.HomePage;
import pageObjects.PageObjects;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.ProductCartPage;

public class CommonActions extends BaseUtil {
    private static final Logger logger = LogManager.getLogger(CommonActions.class);
    private static JsonNode testData;


    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            testData = mapper.readTree(new File("src/test/resources/testdata.json"));
            logger.info("Test data loaded successfully from src/test/resources/testdata.json");
        } catch (Exception e) {
            logger.error("Failed to load test data: {}", e.getMessage(), e);
        }
    }

    public static void clickElement(By locator) {
        try {
            WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked element: {}", locator);
        } catch (Exception e) {
            logger.error("Failed to click element: {}", locator, e);
        }
    }

    public static void enterText(By locator, String text) {
        try {
            WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
            logger.info("Entered text '{}' into element: {}", text, locator);
        } catch (Exception e) {
            logger.error("Failed to enter text '{}' into element: {}", text, locator, e);
        }
    }

    public static void verifyElementVisible(By locator, String expectedText) {
        try {
            WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            String actualText = element.getText();
            logger.info("Verifying element visibility. Expected text: '{}', Actual text: '{}'", expectedText, actualText);
            Assert.assertTrue(actualText.contains(expectedText),
                    "Expected text '" + expectedText + "' not found. Actual: '" + actualText + "'");

            BaseUtil.handlePopup();

        } catch (Exception e) {
            logger.error("Element verification failed for locator: {}", locator, e);
            Assert.fail("Element not visible or verification failed: " + e.getMessage());
        }
    }

    public static void verifyPageTitle(String expectedTitle) {
        try {
            String actualTitle = getDriver().getTitle();
            logger.info("Verifying page title. Expected: '{}', Actual: '{}'", expectedTitle, actualTitle);
            Assert.assertTrue(actualTitle.contains(expectedTitle),
                    "Page title verification failed. Expected: '" + expectedTitle + "', Actual: '" + actualTitle + "'");
        } catch (Exception e) {
            logger.error("Page title verification failed: {}", e.getMessage(), e);
            Assert.fail("Page title verification failed: " + e.getMessage());
        }
    }

    public static void selectDropdownByText(By locator, String text) {
        try {
            WebElement dropdown = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            Select select = new Select(dropdown);
            select.selectByVisibleText(text);
            logger.info("Selected dropdown option '{}' for element: {}", text, locator);
        } catch (Exception e) {
            logger.error("Failed to select dropdown option '{}' for element: {}", text, locator, e);
        }
    }

    public static void checkCheckbox(By locator) {
        try {
            WebElement checkbox = getWait().until(ExpectedConditions.elementToBeClickable(locator));
            if (!checkbox.isSelected()) {
                checkbox.click();
                logger.info("Checked checkbox: {}", locator);
            }
        } catch (Exception e) {
            logger.error("Failed to check checkbox: {}", locator, e);
        }
    }

    // New action methods moved from CommonSteps

    public static void launchBrowserAndNavigate() {
        try {
            logger.info("Launching browser and navigating to URL.");
            BaseUtil.initializeDriver(testData.get("browser").asText());
            BaseUtil.navigateToUrl(testData.get("baseUrl").asText());
            verifyPageTitle("Automation Exercise");

            BaseUtil.handlePopup();

        } catch (Exception e) {
            logger.error("Failed to launch browser and navigate: {}", e.getMessage(), e);
        }
    }

    public static void clickSignupLogin() {
        try {
            logger.info("Clicking on Signup/Login button.");
            clickElement(HomePage.SIGNUP_LOGIN_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to click signup/login button.", e);
        }
    }

    public static void clickContactUs() {
        try {
            logger.info("Clicking on Contact Us button.");
            clickElement(HomePage.CONTACT_US_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to click Contact Us button.", e);
        }
    }

    public static void addProductsToCart() {
        try {
            logger.info("Adding products to cart.");
            BaseUtil.handlePopup();
            clickElement(ProductCartPage.FIRST_PRODUCT_ADD_CART);
            clickElement(ProductCartPage.CONTINUE_SHOPPING_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to add products to cart.", e);
        }
    }

    public static void clickCartButton() {
        try {
            logger.info("Clicking on Cart button.");
            clickElement(HomePage.CART_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to click Cart button.", e);
        }
    }

    public static void clickDeleteAccount() {
        try {
            logger.info("Clicking on Delete Account button.");
            clickElement(HomePage.DELETE_ACCOUNT_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to click Delete Account button.", e);
        }
    }

    public static void verifyAccountDeleted() {
        try {
            logger.info("Verifying account deletion.");
            Thread.sleep(3000); // Wait for page to load
            verifyElementVisible(AccountInfoPage.ACCOUNT_DELETED_TEXT, "ACCOUNT DELETED!");
            clickElement(AccountInfoPage.CONTINUE_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to verify account deletion.", e);
            Assert.fail("Failed to verify account deletion: " + e.getMessage());
        }
    }

}
