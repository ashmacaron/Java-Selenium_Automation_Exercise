package pageObjects;

import org.openqa.selenium.By;

public class HomePage {

    // Home Page
    public static final By SIGNUP_LOGIN_BUTTON = By.linkText("Signup / Login");
    public static final By CONTACT_US_BUTTON = By.linkText("Contact us");
    public static final By CART_BUTTON = By.linkText("Cart");
    public static final By HOME_BUTTON = By.linkText("Home");
    
    public static final By LOGGED_IN_USER = By.xpath("//a[contains(text(),'Logged in as')]");
    public static final By DELETE_ACCOUNT_BUTTON = By.linkText("Delete Account");

}
