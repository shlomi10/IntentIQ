package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import java.io.InputStream;
import java.util.Properties;

/**
 * this class represents the test runner
 *
 * @author Shlomi
 */

public class MainRunnerTest extends BaseTest {

    private String siteURL;

    @BeforeMethod
    public void beforeMethod() {
        try {
            // load properties
            Properties props = new Properties();

            String propFileName = "config.properties";
            // get the config properties file
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            siteURL = props.getProperty("siteURL");
        } catch (Exception e) {
            System.out.println("There was problem load the properties file");
        }
    }

    @Test(priority = 1, description = "add bag to cart test")
    public void addBagTOCartFlow() {
        Assert.assertTrue(mainPage.getWebSite(siteURL), "site was not loaded");
        Assert.assertTrue(mainPage.openBags(), "bags menu was not opened");
        Assert.assertTrue(mainPage.addBagTOCart(),"item was not added to the cart");
        Assert.assertTrue(mainPage.scrollToHeader(), "page was not scrolled up to header");
        Assert.assertTrue(mainPage.clickOnCart(), "cart was not clicked");
    }

    @Test(priority = 2, description = "proceed to check out")
    public void proceedToCheckOut() {
        Assert.assertTrue(checkOutPage.clickCheckOut(), "checkOut was not clicked");
    }

    @Test(priority = 3, description = "check out")
    public void checkOut() {
        Assert.assertTrue(checkOutPage.fillDetails(), "details was not filled");
        Assert.assertTrue(paymentPage.clickPlaceOrder(), "place order was not clicked");
        Assert.assertTrue(purchasePage.getOrderID(), "place order was not clicked");
    }

}
