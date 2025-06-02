package practiceSeleniumFramework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import practiceSeleniumFramework.pageObjects.*;
import practiceSeleniumFramework.testComponents.BaseTest;
import practiceSeleniumFramework.testComponents.Retry;

/**
 * Test class for submitting an order and verifying order history.
 * Uses data-driven approach with TestNG DataProvider.
 */
public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	String countryName = "India";

	/**
	 * Test to register a new user, place an order, and verify confirmation.
	 * @param input HashMap containing user and order details from DataProvider
	 * @throws IOException if data file is not found
	 */
	@Test(dataProvider = "getData", groups = { "Purchase" }, retryAnalyzer = Retry.class)
	public void SubmitOrder(HashMap<String, String> input) throws IOException {
		// Register a new user before login (if needed)
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();
		registrationPage.setFirstName(input.get("firstName"));
		registrationPage.setLastName(input.get("lastName"));
		registrationPage.setEmail(input.get("email"));
		registrationPage.setPhoneNumber(input.get("phone"));
		registrationPage.selectOccupation(input.get("occupation"));
		registrationPage.selectGender(input.get("gender"));
		registrationPage.setPassword(input.get("password"));
		registrationPage.setConfirmPassword(input.get("password"));
		registrationPage.checkAgeConfirmation();
		registrationPage.clickRegister();

		// Login and navigate to product catalogue
		ProductCataloguePage cataloguePage = landingPage.loginApplication(input.get("email"), input.get("password"));

		// Add product to cart
		cataloguePage.addProductToCart(input.get("productName"));
		cataloguePage.goToCartPage();

		// Verify product is in cart
		MyCartPage cartPage = cataloguePage.goToCartPage();
		Boolean match = cartPage.findIfItemInCart(input.get("productName"));
		Assert.assertTrue(match, "Item not found");

		// Proceed to checkout and select country
		PaymentPage payment = cartPage.clickCheckoutButton();
		payment.selectCountryName(countryName);

		// Place order and verify confirmation message
		ConfirmationPage confirmation = payment.clickPlaceOrderButton();
		String message = confirmation.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	/**
	 * Test to verify that the order appears in the order history.
	 * Depends on successful execution of SubmitOrder test.
	 */
	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() {
		// Login and navigate to order history page
		ProductCataloguePage cataloguePage = landingPage.loginApplication("sravanisravs1410@gmail.com", "Sravani@1410");
		OrderPage orderpage = cataloguePage.goToOrderPage();
		// Assert that the product is present in order history
		Assert.assertTrue(orderpage.verifyOrderAdded(productName));
	}

	/**
	 * DataProvider for test data.
	 * Reads user and order details from JSON file and returns as Object array.
	 * @return Object[][] containing test data
	 * @throws IOException if data file is not found
	 */
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\practiceSeleniumFramework\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}

