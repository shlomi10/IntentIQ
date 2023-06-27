package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.annotations.*;
import pages.CheckOutPage;
import pages.MainPage;
import pages.PaymentPage;
import pages.PurchasePage;

import java.util.HashMap;
import java.util.Map;

/**
 * this class represents the base of all tests
 * this will be before each test in the testNG xml
 *
 * @author Shlomi
 */

public class BaseTest implements ITestListener {

    WebDriver driver;
    MainPage mainPage;
    CheckOutPage checkOutPage;
    PaymentPage paymentPage;
    PurchasePage purchasePage;

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void setup(String browser, ITestContext context) {
        if(browser.equals("fireFox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 16_3_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.3 Mobile/15E148 Safari/604.1";
            firefoxOptions.addPreference("general.useragent.override", userAgent);
            firefoxOptions.addPreference("network.cookie.cookieBehavior", 1);
            driver = new FirefoxDriver(firefoxOptions);
        }
        else if(browser.equals("Chrome")){
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.cookie_controls_mode", 1);
            //prefs.put("profile.block_third_party_cookies", true);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);
        }
        // maximize the browser window
        driver.manage().window().maximize();

        // load main page
        mainPage = new MainPage(driver);
        // load checkOut page
        checkOutPage = new CheckOutPage(driver);
        // load payment page
        paymentPage = new PaymentPage(driver);
        // load purchase page
        purchasePage = new PurchasePage(driver);

        context.setAttribute("driver", driver);

    }

    @AfterTest(alwaysRun = true)
    public void close() {
        driver.quit();

    }

}