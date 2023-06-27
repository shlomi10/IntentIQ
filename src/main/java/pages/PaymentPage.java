package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the payment page
 *
 * @author Shlomi
 */

public class PaymentPage extends BasePageFunctions {

    // constructor
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    private final  By placeOrderBTN = By.cssSelector(".action.primary.checkout");
    private final  By spinner = By.xpath("//img[@alt='Loading...']");

    // clickPlaceOrder
    public Boolean clickPlaceOrder() {
        waitForElementNotToBeVisible(spinner);
        waitForElementToBeClickableAndClickIt(placeOrderBTN);
        return waitForElementNotToBeVisible(spinner);
    }

}
