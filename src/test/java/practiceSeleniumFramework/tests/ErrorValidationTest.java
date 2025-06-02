package practiceSeleniumFramework.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import practiceSeleniumFramework.pageObjects.MyCartPage;
import practiceSeleniumFramework.pageObjects.ProductCataloguePage;
import practiceSeleniumFramework.testComponents.BaseTest;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		String countryName = "India";

		// object for landing page, passing driver as parameter
		landingPage.loginApplication("sravanisravs1410@gmail.com123", "Sravani@1410");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}

	@Test(groups= {"ErrorHandling"})
	public void productErrorValidation() {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3aa";

		// object for landing page, passing driver as parameter
		ProductCataloguePage cataloguePage = landingPage.loginApplication("sravanisravs1410@gmail.com", "Sravani@1410");

		List<WebElement> products = cataloguePage.getProductList();
		cataloguePage.addProductToCart(productName);

		MyCartPage cartPage = cataloguePage.goToCartPage();
		Boolean match = cartPage.findIfItemInCart(productName);
		Assert.assertFalse(match, "Item found");
	}
}
