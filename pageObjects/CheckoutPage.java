package pageObjects;

import org.openqa.selenium.By;

public class CheckoutPage {

    // Checkout Page
    public static final By ADDRESS_DETAILS_TEXT = By.xpath("//h2[text()='Address Details']");
    public static final By REVIEW_ORDER_TEXT = By.xpath("//h2[text()='Review Your Order']");
    public static final By COMMENT_TEXTAREA = By.name("message");
    public static final By PLACE_ORDER_BUTTON = By.linkText("Place Order");

}
