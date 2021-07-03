package tests;

import dataprovider.ConfigFileReader;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import pageObjects.Cart;
import pageObjects.Home;
import pageObjects.Product;

import java.net.MalformedURLException;
import java.net.URL;


public class BaseTest {

    public static AndroidDriver driver;
    protected static Home home;
    protected static Product product;
    protected static Cart cart;

    ConfigFileReader configFileReader = new ConfigFileReader();

    public static final String USERNAME_INPUT = "test-Username";
    public static final String PASSWORD_INPUT = "test-Password";

    private MobileElement loginButton() {
        return (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");
    }

    @BeforeClass
    public static void launchApplication() {
        try {
            setDriver();
            home = new Home(driver);
            product = new Product(driver);
            cart = new Cart(driver);
        } catch (Exception e) {
            System.out.println("Could not initialise driver");
        }
    }

    public static AndroidDriver getAndroidDriver() {
        return driver;
    }


    public static void setDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20000");

        //Note:- To install app from local
        //capabilities.setCapability(MobileCapabilityType.APP, "/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

        capabilities.setCapability("appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
        //adb shell am start -n com.swaglabsmobileapp/com.swaglabsmobileapp.SplashActivity

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url, capabilities);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterTest
    public void exitDriver() {
        driver.resetApp();
    }

    public void scrollAndClick(String visibleText) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))").click();
    }

    private void enterCredentials() {
        String userid = configFileReader.getPropertyValue("id");
        String password = configFileReader.getPropertyValue("password");
        driver.findElementByAccessibilityId(USERNAME_INPUT).sendKeys(userid);
        driver.findElementByAccessibilityId(PASSWORD_INPUT).sendKeys(password);
    }

    public void loginToApp() {
        enterCredentials();
        loginButton().click();
    }

    public void pressBack() {
        driver.navigate().back();
    }

    public void waitUntilTextIsPresent(String text) {
        MobileElement element = (MobileElement) driver.findElement(By.linkText(text));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(text)));
    }

}

