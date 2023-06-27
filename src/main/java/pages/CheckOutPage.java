package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.BasePageFunctions;

/**
 * this class represents the check-out page
 *
 * @author Shlomi
 */

public class CheckOutPage extends BasePageFunctions {

    // constructor
    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    private final  String israelValue = "IL";
    private final  By emailField = By.xpath("//div[@class='field required']//input[@type='email']");
    private final  By firstNameField = By.xpath("//input[@name='firstname']");
    private final  By lastNameField = By.xpath("//input[@name='lastname']");
    private final  By streetField = By.xpath("//input[@name='street[0]']");
    private final  By cityField = By.xpath("//input[@name='city']");
    private final  By countryField = By.xpath("//select[@name='country_id']");
    private final  By zipField = By.xpath("//input[@name='postcode']");
    private final  By phoneField = By.xpath("//input[@name='telephone']");
    private Select country;
    private final  By nextBTN = By.xpath("//button[@data-role='opc-continue']");
    private final  By checkOutBTN = By.cssSelector(".checkout.methods.items.checkout-methods-items");
    private final  By spinner = By.xpath("//img[@alt='Loading...']");
    private final  By tableRate = By.xpath("//tr[@class='row']//td[@id='label_method_bestway_tablerate']");
    private final  By orderTotalField = By.xpath("//td[@data-th='Order Total']//span[@class='price']");

    // click on checkout
    public Boolean clickCheckOut() {
        waitForElementToBeClickableAndClickIt(orderTotalField);
        String price = getTextFromElement(orderTotalField).split("\\$")[1];
        int totalPrice = Integer.parseInt(price.split("\\.")[0]);
        if(totalPrice > 10){
            return waitForElementToBeClickableAndClickIt(checkOutBTN);
        }
        return false;
    }

    // fill details
    public Boolean fillDetails() {
        waitForElementToBeVisible(emailField);
        clearAndTypeTextToElem(emailField,readProperties("email"));
        clearAndTypeTextToElem(firstNameField,readProperties("firstName"));
        clearAndTypeTextToElem(lastNameField,readProperties("lastName"));
        clearAndTypeTextToElem(streetField,readProperties("street"));
        waitForElementToBeVisible(cityField);
        clearAndTypeTextToElem(cityField,readProperties("city"));
        waitForElementNotToBeVisible(spinner);
        scrollToElement(nextBTN);
        country = new Select(getWebElement(countryField));
        country.selectByValue(israelValue);
        clearAndTypeTextToElem(zipField,readProperties("zip"));
        clearAndTypeTextToElem(phoneField,readProperties("phone"));
        scrollToElement(nextBTN);
        waitForElementNotToBeVisible(tableRate);
        return clickOnElement(nextBTN);
    }

}
