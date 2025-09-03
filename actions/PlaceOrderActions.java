package actions;

import base.BaseUtil;
import pageObjects.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlaceOrderActions extends CommonActions {
    private static final Logger logger = LogManager.getLogger(PlaceOrderActions.class);
    private static JsonNode testData;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            testData = mapper.readTree(new File("src/test/resources/testdata.json"));
        } catch (Exception e) {
            // Using the logger to report the error
            logger.error("Failed to load test data in PlaceOrderActions: {}", e.getMessage(), e);
        }
    }

    public static void proceedToCheckoutAndPlaceOrder() {
        try {
            logger.info("Starting the full order placement process.");
            BaseUtil.handlePopup();
            navigateToCartAndCheckout();
            verifyCheckoutDetails();
            addOrderCommentAndProceed();
            fillPaymentDetailsAndConfirm();

        } catch (Exception e) {
            logger.error("The order placement process failed at a high level.", e);
        }
    }

    private static void navigateToCartAndCheckout() {
        try {
            logger.info("Navigating from cart page to checkout.");
            clickElement(HomePage.CART_BUTTON);
            clickElement(ProductCartPage.PROCEED_CHECKOUT_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to navigate to the checkout page.", e);
        }
    }

    private static void verifyCheckoutDetails() {
        try {
            logger.info("Verifying address details and order review sections.");
            verifyElementVisible(CheckoutPage.ADDRESS_DETAILS_TEXT, "Address Details");
            verifyElementVisible(CheckoutPage.REVIEW_ORDER_TEXT, "Review Your Order");
        } catch (Exception e) {
            logger.error("Failed to verify checkout details.", e);
        }
    }

    private static void addOrderCommentAndProceed() {
        try {
            logger.info("Adding order comment and clicking 'Place Order'.");
            enterText(CheckoutPage.COMMENT_TEXTAREA, "Test order comment");
            clickElement(CheckoutPage.PLACE_ORDER_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to add comment or click the place order button.", e);
        }
    }

    private static void fillPaymentDetailsAndConfirm() {
        try {
            logger.info("Filling payment details.");
            JsonNode paymentDetails = testData.get("paymentDetails");
            enterText(PaymentPage.NAME_ON_CARD_INPUT, paymentDetails.get("nameOnCard").asText());
            enterText(PaymentPage.CARD_NUMBER_INPUT, paymentDetails.get("cardNumber").asText());
            enterText(PaymentPage.CVC_INPUT, paymentDetails.get("cvc").asText());
            enterText(PaymentPage.EXPIRY_MONTH_INPUT, paymentDetails.get("expirationMonth").asText());
            enterText(PaymentPage.EXPIRY_YEAR_INPUT, paymentDetails.get("expirationYear").asText());

            logger.info("Submitting payment and confirming order.");
            clickElement(PaymentPage.PAY_CONFIRM_BUTTON);
        } catch (Exception e) {
            logger.error("Failed to fill payment details or confirm the order.", e);
        }
    }

    public static void verifyOrderPlacedSuccessfully() {
        try {
            logger.info("Verifying the final order confirmation message.");
            verifyElementVisible(PaymentPage.ORDER_SUCCESS_MESSAGE, "Congratulations! Your order has been confirmed!");
        } catch (Exception e) {
            logger.error("Failed to verify the order success message.", e);
        }
    }
}