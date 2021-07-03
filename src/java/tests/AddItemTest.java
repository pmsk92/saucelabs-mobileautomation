package tests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class AddItemTest extends BaseTest {

    private static final String PRODUCT = "Sauce Labs Fleece Jacket";

    @Test (priority = 1)
    private void validateAddProduct() throws InterruptedException {
        System.out.println("Login to App");
        Assert.assertTrue(home.isAppOpened(),"App was not opened");
        loginToApp();
        home.waitForHomeLoaded();
        //Thread.sleep(2000);
        Assert.assertTrue(home.isProductsVisible(),"Products are not displayed");

        System.out.println("Add the Product to cart");
        scrollAndClick(PRODUCT);
        System.out.println("The Price of the Product is: "+product.getProductPrice());
        Assert.assertEquals(PRODUCT,product.getProductText(),"The Product names do not match");
        scrollAndClick("ADD TO CART");
    }

    @Test (dependsOnMethods = {"validateAddProduct"})
    private void validateDeleteProduct() throws InterruptedException {
        System.out.println("Proceed to remove the same product from cart");
        product.waitForProductScreenLoaded(); //Thread.sleep(2000);
        Assert.assertTrue(product.isRemoveButtonPresent(),"Remove button was not present");
        pressBack();
        Assert.assertTrue(home.isCartVisible(), "Cart button is not visible");
        Assert.assertTrue(home.validateProductsInCart() > 0, "Cart is empty");
        System.out.println("Number of Items in cart "+home.validateProductsInCart());
        home.selectCart();

        System.out.println("Verify product is removed");
        Assert.assertTrue(product.isRemoveButtonPresent(), "Remove button is not present");
        product.selectRemove();
        Assert.assertTrue(cart.isPageOpened(),"Your Cart page was not opened");
    }
}

