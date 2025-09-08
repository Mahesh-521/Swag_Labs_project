package com.swaglabs.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.swaglabs.base.BaseTest;
import com.swaglabs.listeners.TestListener;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.CheckoutCompletePage;
import com.swaglabs.pages.CheckoutOverviewPage;
import com.swaglabs.pages.CheckoutPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductPage;
import com.swaglabs.utilites.ExcelUtiles;
import com.swaglabs.utilites.ScreenShots;

@Listeners(TestListener.class)
public class EndToEndTest extends BaseTest {

	LoginPage loginPage;
	ProductPage productsPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	CheckoutOverviewPage overviewPage;
	CheckoutCompletePage completePage;
	
	static String projectpath = System.getProperty("user.dir");

	@BeforeMethod
	public void init() {
		// Initialize page objects before each test method
		loginPage = new LoginPage(driver);
		productsPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckoutPage(driver);
		overviewPage = new CheckoutOverviewPage(driver);
		completePage = new CheckoutCompletePage(driver);
	}
	
	@DataProvider
	public Object[][] logindata() throws IOException {
		// Load login credentials from Excel file
		String excelpath = projectpath + "\\src\\test\\resources\\Testdata\\login.xlsx";
		return ExcelUtiles.getdata(excelpath, "Sheet1");
	}

	@Test(priority = 1,dataProvider = "logindata")
	public void purchaseSingleProduct(String username,String password) throws InterruptedException, IOException {
		// Test case: Purchase a single product end-to-end

		test = extent.createTest("End to End Purchase Single Product");

		// Step 1: Login with provided credentials
		loginPage.login(username, password);
		Thread.sleep(1000);

		// Step 2: Verify the products page is displayed
		Assert.assertTrue(productsPage.isPageDisplayed());
		Thread.sleep(1000);

		// Step 3: Add 'Sauce Labs Backpack' to the cart
		productsPage.addBackpackToCart();
		Thread.sleep(1000);

		// Step 4: Navigate to cart
		productsPage.goToCart();
		Thread.sleep(1000);

		// Step 5: Verify that 'Sauce Labs Backpack' is present in the cart
		Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));
		Thread.sleep(1000);

		// Step 6: Click checkout button
		cartPage.clickCheckout();
		Thread.sleep(1000);

		// Step 7: Enter checkout information
		checkoutPage.enterFirstName("John");
		checkoutPage.enterLastName("Doe");
		checkoutPage.enterPostalCode("12345");

		// Step 8: Continue to overview page
		checkoutPage.clickContinue();
		Thread.sleep(1000);

		// Step 9: Verify the product is listed in the checkout overview
		Assert.assertTrue(overviewPage.isProductDisplayed("Sauce Labs Backpack"));

		// Step 10: Click Finish to complete the order
		overviewPage.clickFinish();
		Thread.sleep(1000);
		
		// Step 11: Validate that the order is complete, else capture screenshot
		if(completePage.isOrderComplete()) {
			test.pass("Single product Oredered successfully");
		}else {
			String screenpath = ScreenShots.Capture(driver, "Single product Ordered Failed");
			test.fail("Single product Ordered Failed")
					.addScreenCaptureFromPath(screenpath);
		}

		// Step 12: Navigate back to home page
		completePage.clickBackHome();
		Thread.sleep(1000);

		// Step 13: Logout from the application
		productsPage.logout();
		Thread.sleep(1000);
	}

    @Test(priority = 2,dataProvider = "logindata")
    public void purchaseMultipleProducts(String username,String password) throws InterruptedException, IOException {
        // Test case: Purchase multiple products end-to-end

        test = extent.createTest("End to End Purchase Multiple Products");

        // Step 1: Login with provided credentials
        loginPage.login(username, password);
		Thread.sleep(1000);

        // Step 2: Verify products page is displayed
        Assert.assertTrue(productsPage.isPageDisplayed());

        // Step 3: Add 'Sauce Labs Backpack' and 'Sauce Labs Bike Light' to the cart
        productsPage.addBackpackToCart();
        productsPage.addBikeLightToCart();
        Thread.sleep(1000);

        // Step 4: Go to the cart
        productsPage.goToCart();
        Thread.sleep(1000);

        // Step 5: Verify both products are present in the cart
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Bike Light"));

        // Step 6: Click checkout button
        cartPage.clickCheckout();
        Thread.sleep(1000);

        // Step 7: Enter checkout information
        checkoutPage.enterFirstName("Jane");
        checkoutPage.enterLastName("Smith");
        checkoutPage.enterPostalCode("54321");

        // Step 8: Continue to overview page
        checkoutPage.clickContinue();
        Thread.sleep(1000);

        // Step 9: Verify both products are listed in the overview
        Assert.assertTrue(overviewPage.isProductDisplayed("Sauce Labs Backpack"));
        Assert.assertTrue(overviewPage.isProductDisplayed("Sauce Labs Bike Light"));

        // Step 10: Finish checkout
        overviewPage.clickFinish();
        Thread.sleep(1000);

        // Step 11: Validate that order is complete or capture screenshot
        if(completePage.isOrderComplete()) {
			test.pass("Multiple product Oredered successfully");
		}else {
			String screenpath = ScreenShots.Capture(driver, "Multiple product Ordered Failed");
			test.fail("Multiple product Ordered Failed")
					.addScreenCaptureFromPath(screenpath);
		}

        // Step 12: Navigate back to home page
        completePage.clickBackHome();
        Thread.sleep(1000);

        // Step 13: Logout from the application
        productsPage.logout();

        test.pass("Multiple product checkout flow completed successfully");
    }
    
    @Test(priority = 3,dataProvider = "logindata")
    public void sortAndCheckoutWithPriceValidation(String username,String password) throws InterruptedException, IOException {
        // Test case: Sort products by price, add top 3, remove one, and validate pricing

        test = extent.createTest("End-to-End Sort and Checkout with Price Validation");

        // Step 1: Login with provided credentials
        loginPage.login(username, password);
        Thread.sleep(1000);

        // Step 2: Verify products page is displayed
        Assert.assertTrue(productsPage.isPageDisplayed());

        // Step 3: Sort products by price high to low
        productsPage.sortByPriceHighToLow();
        Thread.sleep(1000);

        // Step 4: Add top 3 products to cart
        String product1 = productsPage.getProductNameByIndex(0);
        String product2 = productsPage.getProductNameByIndex(1);
        String product3 = productsPage.getProductNameByIndex(2);
        productsPage.addProductToCart(product1);
        productsPage.addProductToCart(product2);
        productsPage.addProductToCart(product3);
        Thread.sleep(1000);

        // Step 5: Go to cart and verify all 3 products are listed
        productsPage.goToCart();
        Thread.sleep(1000);
        Assert.assertTrue(cartPage.isProductInCart(product1));
        Assert.assertTrue(cartPage.isProductInCart(product2));
        Assert.assertTrue(cartPage.isProductInCart(product3));

        // Step 6: Remove the third product from cart
        cartPage.removeProduct(product3);
        Thread.sleep(1000);
        Assert.assertTrue(cartPage.isProductInCart(product1));
        Assert.assertTrue(cartPage.isProductInCart(product2));
        Assert.assertFalse(cartPage.isProductInCart(product3));

        // Step 7: Checkout and enter information
        cartPage.clickCheckout();
        Thread.sleep(1000);
        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("12345");
        checkoutPage.clickContinue();
        Thread.sleep(1000);

        // Step 8: Validate subtotal, tax, and total prices
        double price1 = overviewPage.getProductPrice("Sauce Labs Fleece Jacket");
        double price2 = overviewPage.getProductPrice("Sauce Labs Backpack");
        double expectedSubtotal = price1 + price2;
        double actualSubtotal = overviewPage.getSubtotalPrice();
        Assert.assertEquals(actualSubtotal, expectedSubtotal, 0.01, "Subtotal mismatch");

        double expectedTax = Math.round(expectedSubtotal * 0.08 * 100.0) / 100.0;
        double actualTax = overviewPage.getTaxPrice();
        Assert.assertEquals(actualTax, expectedTax, 0.01, "Tax mismatch");

        double expectedTotal = expectedSubtotal + expectedTax;
        double actualTotal = overviewPage.getTotalPrice();
        Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Total price mismatch");
        
        if (Math.abs(actualTotal - expectedTotal) < 0.01) {
        	test.pass("Sort and checkout with price validation completed successfully");
		}else {
			String screenpath = ScreenShots.Capture(driver, "Sort and checkout with price validation completed Failedy");
			test.fail("Sort and checkout with price validation completed Failed")
					.addScreenCaptureFromPath(screenpath);
		}

        // Step 9: Finish checkout
        overviewPage.clickFinish();
        Thread.sleep(1000);

        // Step 10: Validate that order is complete
        Assert.assertTrue(completePage.isOrderComplete());

        // Step 11: Navigate back to home
        completePage.clickBackHome();
        Thread.sleep(1000);

        // Step 12: Logout
        productsPage.logout();
    }
    
    @Test(priority = 4,dataProvider = "logindata")
    public void cartPersistenceAcrossLogoutAndLogin(String username,String password) throws InterruptedException, IOException {
        // Test case: Verify that cart persists after logout and login

        test = extent.createTest("EndToend Cart Persistence Across Logout and Login");

        // Step 1: Login with provided credentials
        loginPage.login(username, password);
        Thread.sleep(1000);
        Assert.assertTrue(productsPage.isPageDisplayed(), "Login failed");

        // Step 2: Add 'Sauce Labs Backpack' to cart
        productsPage.addProductToCart("Sauce Labs Backpack");
        Thread.sleep(1000);

        // Step 3: Logout from application
        productsPage.logout();
        Thread.sleep(1000);

        // Step 4: Login again using standard credentials
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(1000);
        Assert.assertTrue(productsPage.isPageDisplayed(), "Login after logout failed");

        // Step 5: Navigate to cart and verify 'Sauce Labs Backpack' is still present
        productsPage.goToCart();
        Thread.sleep(1000);
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "Product not present in cart after relogin");

        if(cartPage.isProductInCart("Sauce Labs Backpack")) {
        	test.pass("Cart Persisted Across Logout and Login");
		}else {
			String screenpath = ScreenShots.Capture(driver, "Product not present in cart after relogin");
			test.fail("Cart not Persisted Across Logout and Login")
					.addScreenCaptureFromPath(screenpath);
		}
    }

    @Test(priority = 5,dataProvider = "logindata")
    public void resetAppStateClearsCart(String username,String password) throws InterruptedException, IOException {
        // Test case: Verify that resetting app state clears the cart

        test = extent.createTest("EndToend Reset App State Clears Cart");

        // Step 1: Login with provided credentials
        loginPage.login(username, password);
        Thread.sleep(1000);
        Assert.assertTrue(productsPage.isPageDisplayed(), "Login failed");

        // Step 2: Add 4 products to the cart
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.addProductToCart("Sauce Labs Bolt T-Shirt");
        productsPage.addProductToCart("Sauce Labs Fleece Jacket");
        Thread.sleep(1000);

        // Step 3: Verify 4 products are added in the cart
        productsPage.goToCart();
        Thread.sleep(1000);
        Assert.assertEquals(cartPage.getCartItemCount(), 4, "Products not added to cart");

        // Step 4: Open sidebar and click 'Reset App State'
        Thread.sleep(1000);
        productsPage.goToSidebar();
        Thread.sleep(1000);
        productsPage.clickResetAppState();
        Thread.sleep(1000);

        // Step 5: Refresh the page
        driver.navigate().refresh();
        Thread.sleep(1000);

        // Step 6: Navigate to cart and verify it's empty
        productsPage.goToCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 0, "Cart is not empty after resetting app state");

        if(cartPage.getCartItemCount()==0) {
        	test.pass("Reset app state cleared the cart successfully");
		}else {
			String screenpath = ScreenShots.Capture(driver, "Cart is not empty after resetting app state");
			test.fail("Reset app state cleared the cart Failed")
					.addScreenCaptureFromPath(screenpath);
		}
    }  
}
