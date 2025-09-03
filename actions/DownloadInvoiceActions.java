package actions;

import base.BaseUtil;
import pageObjects.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DownloadInvoiceActions extends CommonActions {
    private static final Logger logger = LogManager.getLogger(DownloadInvoiceActions.class);
    private static JsonNode testData;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            testData = mapper.readTree(new File("src/test/resources/testdata.json"));
        } catch (Exception e) {
            logger.error("Failed to load test data in DownloadInvoiceActions: {}", e.getMessage(), e);
        }
    }

    public static void proceedToCheckoutAsGuestUser() {
        try {
            logger.info("Starting checkout process as a guest user.");
            clickElement(ProductCartPage.PROCEED_CHECKOUT_BUTTON);
            clickElement(ProductCartPage.REGISTER_LOGIN_BUTTON);

            registerGuestUserForCheckout();

        } catch (Exception e) {
            logger.error("Failed during the guest checkout process.", e);
        }
    }

    private static void registerGuestUserForCheckout() {
        try {
            logger.info("Registering a temporary guest user for checkout.");
            JsonNode userReg = testData.get("userRegistration");

            // Create unique email for guest user
            String uniqueEmail = "guest" + System.currentTimeMillis() + "@test.com";
            String guestName = "Guest " + userReg.get("firstName").asText();

            Thread.sleep(2000); // Wait for page to load

            BaseUtil.handlePopup();

            // Quick registration for guest checkout using JSON data
            enterText(SignupLoginPage.SIGNUP_NAME_INPUT, guestName);
            enterText(SignupLoginPage.SIGNUP_EMAIL_INPUT, uniqueEmail);
            clickElement(SignupLoginPage.SIGNUP_BUTTON);
            logger.info("Guest user signed up with name '{}' and email: {}", guestName, uniqueEmail);

            // Wait and verify account information page
            Thread.sleep(3000);
            verifyElementVisible(AccountInfoPage.ACCOUNT_INFO_TEXT, "ENTER ACCOUNT INFORMATION");

            BaseUtil.handlePopup();

            // Quick account creation using JSON data
            fillBasicAccountInfo(userReg);
            fillBasicAddressInfo(userReg);

            clickElement(AccountInfoPage.CREATE_ACCOUNT_BUTTON);
            clickElement(AccountInfoPage.CONTINUE_BUTTON);

        } catch (Exception e) {
            logger.error("Failed to register the guest user.", e);
        }
    }

    private static void fillBasicAccountInfo(JsonNode userReg) {
        try {
            logger.debug("Filling basic account info for guest using JSON data.");
            Thread.sleep(1000);

            // Use title from JSON data
            if (userReg.get("title").asText().equals("Mr")) {
                clickElement(AccountInfoPage.TITLE_MR_RADIO);
            }

            enterText(AccountInfoPage.PASSWORD_INPUT, userReg.get("password").asText());

            Thread.sleep(1000);
            selectDropdownByText(AccountInfoPage.BIRTH_DAY_DROPDOWN, "15");
            selectDropdownByText(AccountInfoPage.BIRTH_MONTH_DROPDOWN, "January");
            selectDropdownByText(AccountInfoPage.BIRTH_YEAR_DROPDOWN, "1990");

        } catch (Exception e) {
            logger.error("Failed while filling basic account info.", e);
        }
    }

    private static void fillBasicAddressInfo(JsonNode userReg) {
        try {
            logger.debug("Filling basic address info for guest using JSON data.");

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
            logger.error("Failed while filling basic address info.", e);
        }
    }

    public static void completePurchaseProcessWithInvoice() {
        try {
            logger.info("Starting the complete purchase and invoice download process.");
            navigateToCartAndCheckout();
            placeOrderWithComment();
            processPayment();
            handleInvoiceDownloadAndCleanup();

        } catch (Exception e) {
            logger.error("Failed to complete the purchase process.", e);
        }
    }

    private static void navigateToCartAndCheckout() {
        try {
            logger.info("Navigating from cart to checkout.");
            clickElement(HomePage.CART_BUTTON);
            clickElement(ProductCartPage.PROCEED_CHECKOUT_BUTTON);
        } catch (Exception e) {
            logger.error("Failed during navigation to cart and checkout.", e);
        }
    }

    private static void placeOrderWithComment() {
        try {
            logger.info("Placing order with a comment.");
            JsonNode orderData = testData.get("orderData");
            String comment = orderData != null && orderData.has("comment") ?
                    orderData.get("comment").asText() : "Invoice download test";

            enterText(CheckoutPage.COMMENT_TEXTAREA, comment);
            clickElement(CheckoutPage.PLACE_ORDER_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to place order with comment.", e);
        }
    }

    private static void processPayment() {
        try {
            logger.info("Processing payment details from JSON data.");
            JsonNode paymentDetails = testData.get("paymentDetails");
            enterText(PaymentPage.NAME_ON_CARD_INPUT, paymentDetails.get("nameOnCard").asText());
            enterText(PaymentPage.CARD_NUMBER_INPUT, paymentDetails.get("cardNumber").asText());
            enterText(PaymentPage.CVC_INPUT, paymentDetails.get("cvc").asText());
            enterText(PaymentPage.EXPIRY_MONTH_INPUT, paymentDetails.get("expirationMonth").asText());
            enterText(PaymentPage.EXPIRY_YEAR_INPUT, paymentDetails.get("expirationYear").asText());

            clickElement(PaymentPage.PAY_CONFIRM_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to process payment.", e);
        }
    }

    private static void handleInvoiceDownloadAndCleanup() {
        try {
            logger.info("Handling post-order tasks: invoice download and account deletion.");
            // Verify order success and download invoice
            verifyElementVisible(PaymentPage.ORDER_SUCCESS_MESSAGE, "Congratulations! Your order has been confirmed!");
            clickElement(PaymentPage.DOWNLOAD_INVOICE_BUTTON);
            clickElement(AccountInfoPage.CONTINUE_BUTTON);

            // Delete account for cleanup (Delete Account!)
            logger.info("Deleting temporary account for cleanup.");
            clickElement(HomePage.DELETE_ACCOUNT_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to handle invoice download and cleanup.", e);
        }
    }
}