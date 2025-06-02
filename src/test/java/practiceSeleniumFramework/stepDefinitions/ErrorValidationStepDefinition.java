package practiceSeleniumFramework.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import practiceSeleniumFramework.pageObjects.LandingPage;
import practiceSeleniumFramework.pageObjects.ProductCataloguePage;
import practiceSeleniumFramework.testComponents.BaseTest;

public class ErrorValidationStepDefinition extends BaseTest{

	public LandingPage landingPage;
	public ProductCataloguePage cataloguePage;
	
	@Given("landed on Ecommerce page")
	public void landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}
	
	/*@Before
    public void setUp() throws IOException {
        landingPage = launchApplication();  // initializes driver and landingPage
    }*/
	
	//@Given("^Logged in with user name (.+) and password (.+)$")
	@When("Logged into website with username {string} and password {string}")
	public void logged_into_website_with_username_and_password(String userName, String password) {
		cataloguePage = landingPage.loginApplication(userName, password);
	}
	

	@Then("{string} error message is displayed.")
	public void check_error_message(String string) {
		Assert.assertEquals(landingPage.getErrorMessage(), string);
	}

}
