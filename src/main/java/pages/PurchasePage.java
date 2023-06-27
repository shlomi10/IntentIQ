package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the purchase page
 *
 * @author Shlomi
 */

public class PurchasePage extends BasePageFunctions {

    // constructor
    public PurchasePage(WebDriver driver) {
        super(driver);
    }

    private final  By orderId = By.xpath("//div[@class='checkout-success']//p//span");

    // get order id
    public Boolean getOrderID() {
        return writeToFile(getTextFromElement(orderId));
    }

}
