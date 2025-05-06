package practiceSeleniumFramework.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import practiceSeleniumFramework.pageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		
		//object for landing page, passing driver as parameter
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("sravanisravs1410@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sravani@1410");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));
		
		List<WebElement> products = driver.findElements(By.className("mb-3"));
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match =cartItems.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match, "Item not found");
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		String countryName = "India";
		
		//using send keys
		/*driver.findElement(By.cssSelector(".form-group input")).sendKeys(countryName);
		List<WebElement> countryNames = driver.findElements(By.cssSelector(".list-group button"));
		WebElement ele =countryNames.stream().filter(item->item.findElement(By.cssSelector("span")).getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		ele.click();*/
		
		//using actions
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), countryName).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
		List<WebElement> countryNames = driver.findElements(By.cssSelector(".list-group button"));
		WebElement ele =countryNames.stream().filter(item->item.findElement(By.cssSelector("span")).getText().equalsIgnoreCase(countryName)).findFirst().orElse(null);
		ele.click();
		
		driver.findElement(By.cssSelector(".actions a")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}

}
