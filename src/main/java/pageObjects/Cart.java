package pageObjects;

import dataprovider.ConfigFileReader;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


public class Cart extends BasePage {

    protected AndroidDriver driver;

    public Cart(AndroidDriver driver) {
        this.driver = driver;
    }

    ConfigFileReader configFileReader = new ConfigFileReader();

    private static final String CART = "YOUR CART";
    private static final String CHECKOUT_INFO = "Ctest-Checkout: Your Info";
    private static final String CANCEL_BUTTON = "test-CANCEL";
    private static final String CONTINUE_BUTTON = "test-CONTINUE";
    private static final String CHECKOUT_OVERVIEW = "test-CHECKOUT: OVERVIEW";
    private static final String CHECKOUT_COMPLETE = "//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]";

    private MobileElement productText() {
        return (MobileElement) driver.findElementByLinkText(CART);
    }

    private MobileElement continueButton() {
        return (MobileElement) driver.findElementByAccessibilityId(CONTINUE_BUTTON);
    }

    private MobileElement cancelButton() {
        return (MobileElement) driver.findElementByAccessibilityId(CANCEL_BUTTON);
    }

    private MobileElement continueShoppingButton() {
        return (MobileElement) driver.findElementByAccessibilityId("test-CONTINUE SHOPPING");
    }

    private MobileElement checkoutButton() {
        return (MobileElement) driver.findElementByAccessibilityId("test-CHECKOUT");
    }

    private MobileElement checkoutInfo() {
        return (MobileElement) driver.findElementByLinkText(CHECKOUT_INFO);
    }

    public MobileElement checkoutOverview() {
        return (MobileElement) driver.findElementByAccessibilityId(CHECKOUT_OVERVIEW);
    }

    private MobileElement checkoutCompleteHeader() {
        return (MobileElement) driver.findElementByXPath(CHECKOUT_COMPLETE);
    }


    public boolean isPageOpened(){
        return continueShoppingButton().isDisplayed() && checkoutButton().isDisplayed();
    }

    public void selectCheckoutButton(){
        checkoutButton().click();
    }

    public boolean isCheckoutPageOpened(){
        return continueButton().isDisplayed() && cancelButton().isDisplayed();
    }

    public boolean isCheckoutCompleteTextPresent(){
        return checkoutCompleteHeader().isDisplayed();
    }

    public void enterCheckoutInfo(){
        String firstName= configFileReader.getPropertyValue("firstname");
        String lastName= configFileReader.getPropertyValue("lastname");
        String postalCode= configFileReader.getPropertyValue("postalCode");
        driver.findElementByAccessibilityId("test-First Name").sendKeys(firstName);
        driver.findElementByAccessibilityId("test-Last Name").sendKeys(lastName);
        driver.findElementByAccessibilityId("test-Zip/Postal Code").sendKeys(postalCode);
    }

    public void selectContinue(){
        if(continueButton().isEnabled())
        {
            TouchAction action = new TouchAction(driver);
            action.tap(PointOption.point(continueButton().getCenter()));
            action.perform();
        }
    }

    public void waitUntilScreenLoaded() {
        Wait wait = new FluentWait(driver)
                .withTimeout(DURATION)
                .pollingEvery(POLL)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        wait.until(ExpectedConditions.visibilityOf(continueButton()));
    }
}
