package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebDriver;


public class BaseUtil {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static final int TIMEOUT = 10;

    public static void initializeDriver(String browserName) {
        try {
            switch (browserName.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-popup-blocking");
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + browserName);
            }

            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

        } catch (Exception e) {
            System.err.println("Failed to initialize driver: " + e.getMessage());
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static void navigateToUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            System.err.println("Failed to navigate to URL: " + e.getMessage());
        }
    }

    public static void closeDriver() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            System.err.println("Failed to close driver: " + e.getMessage());
        }
    }

    public static void handlePopup() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Nahspush
            try {
                WebElement popupCloseBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("svg path[fill='#FAFAFA'], button[class*='close'], div[class*='popup'] svg")
                ));
                popupCloseBtn.click();
                System.out.println("Nashpush popup closed successfully.");
            } catch (Exception e) {
                System.out.println("No Nashpush popup found.");
            }

            // Google Ads
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(
                        "document.querySelectorAll('ins.adsbygoogle, iframe[id^=\"aswift_\"]').forEach(e => e.remove());"
                );
                System.out.println("Google ads removed successfully.");
            } catch (Exception e) {
                System.out.println("No ads to remove or failed.");
            }

        } catch (Exception e) {
            System.out.println("Error handling popups/ads: " + e.getMessage());
        }
    }

}
