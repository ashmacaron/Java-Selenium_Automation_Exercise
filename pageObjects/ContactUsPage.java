package pageObjects;

import org.openqa.selenium.By;

public class ContactUsPage {

    // Contact Us Page
    public static final By GET_IN_TOUCH_TEXT = By.xpath("//div[@class='contact-form']//h2[contains(text(),'Get In Touch')]");
    public static final By CONTACT_NAME_INPUT = By.xpath("//input[@data-qa='name']");
    public static final By CONTACT_EMAIL_INPUT = By.xpath("//input[@data-qa='email']");
    public static final By CONTACT_SUBJECT_INPUT = By.xpath("//input[@data-qa='subject']");
    public static final By CONTACT_MESSAGE_INPUT = By.xpath("//textarea[@data-qa='message']");
    public static final By CONTACT_FILE_INPUT = By.name("upload_file");
    public static final By CONTACT_SUBMIT_BUTTON = By.xpath("//input[@data-qa='submit-button']");
    public static final By SUCCESS_MESSAGE = By.xpath("//div[contains(@class,'status alert alert-success') and contains(text(),'Success! Your details have been submitted successfully.')]");

}
