package tests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class EmptyItemCheckoutTest extends BaseTest {

    @Test
    private void validateEmptyItemCheckoutTest() throws InterruptedException {
        System.out.println("Login to App");
        Assert.assertTrue(home.isAppOpened(), "App was not opened");
        loginToApp();
        home.waitForHomeLoaded();

        System.out.println("Select Cart & Proceed to checkout");
        Assert.assertTrue(home.isCartVisible(), "Cart button is not visible");
        home.selectCart();
        Assert.assertTrue(cart.isPageOpened(), "Your Cart page was not opened");
        cart.selectCheckoutButton();

        System.out.println("Enter the details in checkout info page");
        cart.waitUntilScreenLoaded();
        //Thread.sleep(3000);
        Assert.assertTrue(cart.isCheckoutPageOpened(), "Checkout page was not seen");
        cart.enterCheckoutInfo();
        cart.selectContinue();

        System.out.println("Press Submit and complete");
        Assert.assertTrue(cart.checkoutOverview().isDisplayed(), "Checkout Overview not seen");
        scrollAndClick("FINISH");
        Thread.sleep(2000);
        Assert.assertFalse(cart.isCheckoutCompleteTextPresent(), "Test case failed as checkout successful");

    }

}
