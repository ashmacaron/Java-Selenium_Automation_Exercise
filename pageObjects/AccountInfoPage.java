package pageObjects;

import org.openqa.selenium.By;

public class AccountInfoPage {

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

}
