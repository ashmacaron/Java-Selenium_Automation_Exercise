package pageObjects;

import org.openqa.selenium.By;

public class ProductCartPage {

    // Products and Cart
    public static final By FIRST_PRODUCT_ADD_CART = By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]");
    public static final By CONTINUE_SHOPPING_BUTTON = By.xpath("//button[text()='Continue Shopping']");
    public static final By PROCEED_CHECKOUT_BUTTON = By.linkText("Proceed To Checkout");
    public static final By REGISTER_LOGIN_BUTTON = By.linkText("Register / Login");

}
