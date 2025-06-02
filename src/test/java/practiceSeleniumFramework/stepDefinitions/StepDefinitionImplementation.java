package practiceSeleniumFramework.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import practiceSeleniumFramework.pageObjects.ConfirmationPage;
import practiceSeleniumFramework.pageObjects.LandingPage;
import practiceSeleniumFramework.pageObjects.MyCartPage;
import practiceSeleniumFramework.pageObjects.PaymentPage;
import practiceSeleniumFramework.pageObjects.ProductCataloguePage;
import practiceSeleniumFramework.testComponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest{
	
	
	public LandingPage landingPage;
	public ProductCataloguePage cataloguePage;
	public MyCartPage cartPage;
	public PaymentPage payment;
	public ConfirmationPage confirmation;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}
	
	/*@Before
    public void setUp() throws IOException {
        landingPage = launchApplication();  // initializes driver and landingPage
    }*/
	
	//@Given("^Logged in with user name (.+) and password (.+)$")
	@Given("Logged in with username {string} and password {string}")
	public void logged_in_username_and_password(String userName, String password) {
		cataloguePage = landingPage.loginApplication(userName, password);
	}
	
	@When("I add product {string} to Cart")
	public void I_add_product_to_cart(String productName) {
		cataloguePage.addProductToCart(productName);
		cataloguePage.goToCartPage();
	}
	
	@When("Checkout {string} and submit the order")
	public void checkout_and_submit_the_order(String productName) {
		// Verify product is in cart
		cartPage = cataloguePage.goToCartPage();
		Boolean match = cartPage.findIfItemInCart(productName);
		Assert.assertTrue(match, "Item not found");

		// Proceed to checkout and select country
		payment = cartPage.clickCheckoutButton();
		payment.selectCountryName("India");
		confirmation = payment.clickPlaceOrderButton();
	}		
	
	@Then("{string} message is displayed on confirmationPage")
	public void check_confirmation_message(String string) {
		String message = confirmation.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase(string));
		driver.close();
	}

}
