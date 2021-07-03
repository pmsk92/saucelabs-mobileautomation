package pageObjects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class Product extends BasePage {

    protected AndroidDriver driver;

    public Product(AndroidDriver driver) {
        this.driver = driver;
    }

    private MobileElement productText() {
        return (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]");
    }

    private MobileElement cartButton() {
        return (MobileElement) driver.findElementByAccessibilityId("test-ADD TO CART");
    }

    private MobileElement removeButton() {
        return (MobileElement) driver.findElementByAccessibilityId("test-REMOVE");
    }

    private MobileElement productPrice() {
        return (MobileElement) driver.findElementByAccessibilityId("test-Price");
    }

    public String getProductPrice() {
        return productPrice().getText();
    }

    public String getProductText() {
        return productText().getText();
    }

    public boolean isRemoveButtonPresent(){
        return removeButton().isDisplayed();
    }

    public void selectRemove(){
        removeButton().click();
    }

    public void waitForProductScreenLoaded() {
        Wait wait = new FluentWait(driver)
                .withTimeout(DURATION)
                .pollingEvery(POLL)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);

        wait.until(ExpectedConditions.visibilityOf(removeButton()));
    }
}
