package practiceSeleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practiceSeleniumFramework.abstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent{
	WebDriver driver;
	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css=".totalRow Button")
	WebElement checkoutButton;
	
	public List<WebElement> getCartItems(){
		return cartItems;
	}
	
	public boolean findIfItemInCart(String productName) {
		Boolean match =getCartItems().stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public PaymentPage clickCheckoutButton() {
		checkoutButton.click();
		PaymentPage payment =new PaymentPage(driver);
		return payment;
	}
	
	
}
