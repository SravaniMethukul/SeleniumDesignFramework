package practiceSeleniumFramework.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import practiceSeleniumFramework.pageObjects.ConfirmationPage;
import practiceSeleniumFramework.pageObjects.MyCartPage;
import practiceSeleniumFramework.pageObjects.OrderPage;
import practiceSeleniumFramework.pageObjects.PaymentPage;
import practiceSeleniumFramework.pageObjects.ProductCataloguePage;
import practiceSeleniumFramework.testComponents.BaseTest;
import practiceSeleniumFramework.testComponents.Retry;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	String countryName = "India";

	@Test(dataProvider = "getData", groups = { "Purchase" }, retryAnalyzer=Retry.class)
	public void SubmitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub
		// object for landing page, passing driver as parameter
		ProductCataloguePage cataloguePage = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = cataloguePage.getProductList();
		cataloguePage.addProductToCart(input.get("productName"));
		cataloguePage.goToCartPage();

		MyCartPage cartPage = cataloguePage.goToCartPage();
		Boolean match = cartPage.findIfItemInCart(input.get("productName"));
		Assert.assertTrue(match, "Item not found");

		PaymentPage payment = cartPage.clickCheckoutButton();
		payment.selectCountryName(countryName);

		ConfirmationPage confirmation = payment.clickPlaceOrderButton();
		String message = confirmation.getConfirmationMessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() {
		ProductCataloguePage cataloguePage = landingPage.loginApplication("sravanisravs1410@gmail.com", "Sravani@1410");
		OrderPage orderpage = cataloguePage.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderAdded(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\practiceSeleniumFramework\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	};
}

// passing data as object
/*
 * return new Object[][] { {"sravanisravs1410@gmail.com", "Sravani@1410",
 * "ZARA COAT 3"}, {"methukulasravani@gmail.com", "Sravanisai@22",
 * "ADIDAS ORIGINAL"}, };
 */

// usage of hashmap object
/*
 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
 * "sravanisravs1410@gmail.com"); map.put("password", "Sravani@1410");
 * map.put("productName", "ZARA COAT 3");
 * 
 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
 * "methukulasravani@gmail.com"); map1.put("password", "Sravanisai@22");
 * map1.put("productName", "ADIDAS ORIGINAL"); return new Object[][]
 * {{map},{map1}};
 */
