package pageObjects;

import org.openqa.selenium.By;

public class PaymentPage {

    // Payment Page
    public static final By NAME_ON_CARD_INPUT = By.name("name_on_card");
    public static final By CARD_NUMBER_INPUT = By.name("card_number");
    public static final By CVC_INPUT = By.name("cvc");
    public static final By EXPIRY_MONTH_INPUT = By.name("expiry_month");
    public static final By EXPIRY_YEAR_INPUT = By.name("expiry_year");
    public static final By PAY_CONFIRM_BUTTON = By.id("submit");
    public static final By ORDER_SUCCESS_MESSAGE = By.xpath("//p[text()='Congratulations! Your order has been confirmed!']");
    public static final By DOWNLOAD_INVOICE_BUTTON = By.linkText("Download Invoice");

}
