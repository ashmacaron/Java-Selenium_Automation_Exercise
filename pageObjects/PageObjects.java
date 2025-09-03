package pageObjects;

import org.openqa.selenium.By;

public class PageObjects {

    // Home Page
    public static final By SIGNUP_LOGIN_BUTTON = By.linkText("Signup / Login");
    public static final By CONTACT_US_BUTTON = By.linkText("Contact us");
    public static final By CART_BUTTON = By.linkText("Cart");
    public static final By HOME_BUTTON = By.linkText("Home");
    public static final By LOGGED_IN_USER = By.xpath("//a[contains(text(),'Logged in as')]");
    public static final By DELETE_ACCOUNT_BUTTON = By.linkText("Delete Account");

    // Signup/Login Page
    public static final By NEW_USER_SIGNUP_TEXT = By.xpath("//h2[text()='New User Signup!']");
    public static final By LOGIN_ACCOUNT_TEXT = By.xpath("//h2[text()='Login to your account']");
    public static final By SIGNUP_NAME_INPUT = By.name("name");
    public static final By SIGNUP_EMAIL_INPUT = By.xpath("//input[@data-qa='signup-email']");
    public static final By SIGNUP_BUTTON = By.xpath("//button[@data-qa='signup-button']");
    public static final By LOGIN_EMAIL_INPUT = By.xpath("//input[@data-qa='login-email']");
    public static final By LOGIN_PASSWORD_INPUT = By.xpath("//input[@data-qa='login-password']");
    public static final By LOGIN_BUTTON = By.xpath("//button[@data-qa='login-button']");
    public static final By LOGIN_ERROR_MESSAGE = By.xpath("//p[text()='Your email or password is incorrect!']");

    // Account Information Page
    public static final By ACCOUNT_INFO_TEXT = By.xpath("//b[text()='Enter Account Information']");
    public static final By TITLE_MR_RADIO = By.id("id_gender1");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By BIRTH_DAY_DROPDOWN = By.id("days");
    public static final By BIRTH_MONTH_DROPDOWN = By.id("months");
    public static final By BIRTH_YEAR_DROPDOWN = By.id("years");
    public static final By NEWSLETTER_CHECKBOX = By.id("newsletter");
    public static final By OFFERS_CHECKBOX = By.id("optin");
    public static final By FIRST_NAME_INPUT = By.id("first_name");
    public static final By LAST_NAME_INPUT = By.id("last_name");
    public static final By COMPANY_INPUT = By.id("company");
    public static final By ADDRESS_INPUT = By.id("address1");
    public static final By ADDRESS2_INPUT = By.id("address2");
    public static final By COUNTRY_DROPDOWN = By.id("country");
    public static final By STATE_INPUT = By.id("state");
    public static final By CITY_INPUT = By.id("city");
    public static final By ZIPCODE_INPUT = By.id("zipcode");
    public static final By MOBILE_INPUT = By.id("mobile_number");
    public static final By CREATE_ACCOUNT_BUTTON = By.xpath("//button[@data-qa='create-account']");

    // Account Created Page
    public static final By ACCOUNT_CREATED_TEXT = By.xpath("//b[contains(text(),'Account Created!')]");
    public static final By ACCOUNT_DELETED_TEXT = By.xpath("//b[contains(text(),'Account Deleted!')]");
    public static final By CONTINUE_BUTTON = By.xpath("//a[@data-qa='continue-button']");

    // Contact Us Page
    public static final By GET_IN_TOUCH_TEXT = By.xpath("//div[@class='contact-form']//h2[contains(text(),'Get In Touch')]");
    public static final By CONTACT_NAME_INPUT = By.xpath("//input[@data-qa='name']");
    public static final By CONTACT_EMAIL_INPUT = By.xpath("//input[@data-qa='email']");
    public static final By CONTACT_SUBJECT_INPUT = By.xpath("//input[@data-qa='subject']");
    public static final By CONTACT_MESSAGE_INPUT = By.xpath("//textarea[@data-qa='message']");
    public static final By CONTACT_FILE_INPUT = By.name("upload_file");
    public static final By CONTACT_SUBMIT_BUTTON = By.xpath("//input[@data-qa='submit-button']");
    public static final By SUCCESS_MESSAGE = By.xpath("//div[contains(@class,'status alert alert-success') and contains(text(),'Success! Your details have been submitted successfully.')]");

    // Products and Cart
    public static final By FIRST_PRODUCT_ADD_CART = By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]");
    public static final By CONTINUE_SHOPPING_BUTTON = By.xpath("//button[text()='Continue Shopping']");
    public static final By PROCEED_CHECKOUT_BUTTON = By.linkText("Proceed To Checkout");
    public static final By REGISTER_LOGIN_BUTTON = By.linkText("Register / Login");

    // Checkout Page
    public static final By ADDRESS_DETAILS_TEXT = By.xpath("//h2[text()='Address Details']");
    public static final By REVIEW_ORDER_TEXT = By.xpath("//h2[text()='Review Your Order']");
    public static final By COMMENT_TEXTAREA = By.name("message");
    public static final By PLACE_ORDER_BUTTON = By.linkText("Place Order");

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