package utilities;

import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * this class represents the main functions of all pages
 *
 * @author Shlomi
 */


public abstract class BasePageFunctions {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;

    // constructor
    public BasePageFunctions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // get webDriver
    public WebDriver getDriver() {
        return this.driver;
    }

    // read from properties
    public String readProperties(String whatToRead){
        try {
            // load properties
            Properties props = new Properties();

            String propFileName = "config.properties";
            // get the config properties file
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            return props.getProperty(whatToRead);
        } catch (Exception e) {
            System.out.println("There was problem load the properties file");
            return "There was problem load the properties file";
        }
    }

    // write to a file
    public Boolean writeToFile (String textToWrite){
        try {
            FileWriter myWriter = new FileWriter("test_data.txt");
            myWriter.write("Order id is: "+textToWrite);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    // navigate to URL
    public Boolean navigateToURL(String URL) {
        try {
            driver.navigate().to(URL);
            return true;
        } catch (Exception e) {
            System.out.println("Site was not loaded");
            return false;
        }
    }

    // get back webElement
    public WebElement getWebElement(By elem) {
        return driver.findElement(elem);
    }

    // click on element
    public Boolean clickOnElement(By elem) {
        try {
            getWebElement(elem).click();
            return true;
        } catch (Exception e) {
            System.out.println("Element " + elem + " was not clicked");
            return false;
        }
    }

    // clear field and then type text
    public Boolean clearAndTypeTextToElem(By elem, String text) {
        try {
            WebElement textField = getWebElement(elem);
            textField.clear();
            textField.sendKeys(text);
            return true;
        } catch (Exception e) {
            System.out.println("Element " + elem + " was not clear and text was not sent to");
            return false;
        }
    }

    // element to be clickable and click it
    public Boolean waitForElementToBeClickableAndClickIt(By elem) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elem)).click();
            return true;
        } catch (Exception e) {
            System.out.println("Wait for element to be clickable was not worked correct");
            return false;
        }
    }

    // wait for element to be visible
    public Boolean waitForElementToBeVisible(By elem) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
            return true;
        } catch (Exception e) {
            System.out.println("Wait for element to be visible was not worked correct");
            return false;
        }
    }

    // wait for element not to be visible
    public Boolean waitForElementNotToBeVisible(By elem) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(elem));
            return true;
        } catch (Exception e) {
            System.out.println("Wait for element to be in visible was not worked correct");
            return false;
        }
    }

    // wait for element to be presented
    public Boolean waitForElementToBePresented(By elem) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(elem));
            return true;
        } catch (Exception e) {
            System.out.println("Wait for element to be present was not worked correct");
            return false;
        }
    }

    // get text from element
    public String getTextFromElement(By elem) {
        return getWebElement(elem).getText();
    }

    // move courser to element
    public boolean mouseHooverFromElement(By elem) {
        action = new Actions(driver);
        try {
            action.moveToElement(getWebElement(elem)).build().perform();
            return true;
        } catch (Exception e) {
            System.out.println("mouse hover was not worked properly");
            return false;
        }
    }

    // move courser to element and click on him
    public boolean mouseHooverFromElementToElementAndClick(By elem) {
        action = new Actions(driver);
        try {
            action.moveToElement(getWebElement(elem)).click().build().perform();
            return true;
        } catch (Exception e) {
            System.out.println("mouse hover and click was not worked properly");
            return false;
        }
    }

    // scroll to element
    public boolean scrollToElement(By elemToScroll) {
        try {
            WebElement element1 = getWebElement(elemToScroll);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
            return true;
        } catch (Exception e) {
            System.out.println("Scroll to element was not worked correct");
            return false;
        }
    }

    // take screenshot
    public Boolean takeScreenShot() {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            //Convert web driver object to TakeScreenshot
            TakesScreenshot scrShot = ((TakesScreenshot) getDriver());
            //Call getScreenshotAs method to create image file
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            // take the tile and path
            String path = System.getProperty("user.dir") + File.separator + "Pictures" + File.separator + timestamp.getTime() + ".png";
            //Move image file to new destination
            File DestFile = new File(path);
            //Copy file at destination
            FileUtils.copyFile(SrcFile, DestFile);
            return true;
        } catch (Exception e) {
            System.out.println("No screenshot was made");
            return false;
        }
    }

}
