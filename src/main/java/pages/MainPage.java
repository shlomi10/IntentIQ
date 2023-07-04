package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the main page
 *
 * @author Shlomi
 */

public class MainPage extends BasePageFunctions {

    // constructor
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final By gearMenu = By.id("ui-id-6");
    private final  By bagsElem = By.id("ui-id-25");
    private final  By pushItMessengerBag = By.cssSelector(".product.name.product-item-name>a[href*='push-it-messenger-bag']");
    private final  By addItemToCart = By.xpath("//div[@data-product-id='14']/..//div[@class='actions-primary']");
    private final  By logo = By.cssSelector(".logo");
    private final  By successMessage = By.xpath("//div[@class='messages']//a[@href]");

    // navigate to the site
    public Boolean getWebSite(String site) {
        return navigateToURL(site);
    }

    // read site to load
    public String readWebSite(String site) {
        return readProperties(site);
    }

    // navigate to bags
    public Boolean openBags() {
        waitForElementToBeVisible(gearMenu);
        mouseHooverFromElement(gearMenu);
        return waitForElementToBeClickableAndClickIt(bagsElem);
    }

    // add bag to cart
    public boolean addBagTOCart(){
        scrollToElement(pushItMessengerBag);
        mouseHooverFromElement(pushItMessengerBag);
        return waitForElementToBeClickableAndClickIt(addItemToCart);
    }

    // scroll up to header
    public boolean scrollToHeader(){
        return scrollToElement(logo);
    }

    // click on cart
    public boolean clickOnCart(){
        return waitForElementToBeClickableAndClickIt(successMessage);
    }

}
