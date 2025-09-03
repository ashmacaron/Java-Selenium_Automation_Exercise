package pageObjects;

import org.openqa.selenium.By;

public class SignupLoginPage {

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

}
