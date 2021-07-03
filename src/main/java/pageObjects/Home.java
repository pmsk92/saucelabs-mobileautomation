package pageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


public class Home extends BasePage {

    protected AndroidDriver driver;

    public Home(AndroidDriver driver) {
        this.driver = driver;
    }

    private MobileElement loginButton() {
        return (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");
    }

    private MobileElement cartLogoButton() {
        return (MobileElement) driver.findElementByAccessibilityId("test-Cart");
    }

    private MobileElement productsList() {
        return (MobileElement) driver.findElementByAccessibilityId("test-PRODUCTS");
    }

    private MobileElement productsInCart() {
        return (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.TextView");
    }

    public boolean isAppOpened() {
        return loginButton().isDisplayed();
    }

    public void waitForHomeLoaded() {
        Wait wait = new FluentWait(driver)
                .withTimeout(DURATION)
                .pollingEvery(POLL)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.id("com.swaglabsmobileapp:id/action_bar_root")));
    }

    public boolean isProductsVisible() {
        return productsList().isDisplayed();
    }

    public boolean isCartVisible() {
        return cartLogoButton().isDisplayed();
    }

    public int validateProductsInCart() {
        return Integer.parseInt(productsInCart().getText());
    }

    public void selectCart() {
        cartLogoButton().click();
    }
}

