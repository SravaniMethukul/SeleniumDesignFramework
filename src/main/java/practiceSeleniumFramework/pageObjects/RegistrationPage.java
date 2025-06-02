package practiceSeleniumFramework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import practiceSeleniumFramework.abstractComponents.AbstractComponent;

public class RegistrationPage extends AbstractComponent {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // First Name
    @FindBy(id = "firstName")
    WebElement firstNameInput;

    // Last Name
    @FindBy(id = "lastName")
    WebElement lastNameInput;

    // Email
    @FindBy(id = "userEmail")
    WebElement emailInput;

    // Phone Number
    @FindBy(id = "userMobile")
    WebElement phoneInput;

    // Occupation Dropdown
    @FindBy(css = "select[formcontrolname='occupation']")
    WebElement occupationDropdown;

    // Gender Radio Buttons
    @FindBy(css = "input[type='radio'][formcontrolname='gender'][value='Male']")
    WebElement maleRadio;

    @FindBy(css = "input[type='radio'][formcontrolname='gender'][value='Female']")
    WebElement femaleRadio;

    // Password
    @FindBy(id = "userPassword")
    WebElement passwordInput;

    // Confirm Password
    @FindBy(id = "confirmPassword")
    WebElement confirmPasswordInput;

    // Age Confirmation Checkbox
    @FindBy(css = "input[type='checkbox'][formcontrolname='required']")
    WebElement ageCheckbox;

    // Register Button
    @FindBy(id = "login")
    WebElement registerButton;

    // Registration Message
    @FindBy(css = "[class*='flyInOut']")
    WebElement registrationMessage;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client/register");
    }

    public void setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void setPhoneNumber(String phone) {
        phoneInput.sendKeys(phone);
    }

    public void selectOccupation(String occupation) {
        new Select(occupationDropdown).selectByVisibleText(occupation);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            maleRadio.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            femaleRadio.click();
        }
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword) {
        confirmPasswordInput.sendKeys(confirmPassword);
    }

    public void checkAgeConfirmation() {
        if (!ageCheckbox.isSelected()) {
            ageCheckbox.click();
        }
    }

    public void clickRegister() {
        registerButton.click();
    }

    public String getRegistrationMessage() {
        waitForElementToAppear(registrationMessage);
        return registrationMessage.getText();
    }
}