package practiceSeleniumFramework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import practiceSeleniumFramework.abstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {
	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryNameInput;
	
	@FindBy(css=".list-group button")
	List<WebElement> countryNamesList;
	
	@FindBy(css=".actions a")
	WebElement placeOrderButton;
	
	By listOfCountries = By.cssSelector(".list-group");
	
	public List<WebElement> getCountriesList(){
		return countryNamesList;
	}
	
	public void selectCountryName(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(countryNameInput, countryName).build().perform();
		waitForElementToAppear(listOfCountries);
		WebElement ele=countryNamesList.stream().filter(item -> item.findElement(By.cssSelector("span")).getText().equalsIgnoreCase(countryName))
		.findFirst().orElse(null);
		ele.click();
	}
	
	public ConfirmationPage clickPlaceOrderButton() {
		placeOrderButton.click();
		ConfirmationPage confirmation =new ConfirmationPage(driver);
		return confirmation;
	}
}
