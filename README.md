# 🧪 Selenium Design Framework (Java)

This project is a Java-based Selenium test automation framework following the **Page Object Model (POM)** design pattern. 
It supports both **UI end-to-end testing**, and is organized for clarity, scalability, and maintainability.

---

## 📁 Project Structure

/project-root
│
├── src/
│   └── main/
│       └── java/
|           └── AbstractComponents/   
|               └── AbstractComponents.java  # Central class to instantiate and return page objects
│           └── pageObjects/               # POM classes for each web page
│               ├── ConfirmationPage.java  # Handles confirmation page locators and actions
│               ├── LandingPage.java    # Handles login page elements
│               ├── CartPage.java   # Logic for cart page elements
│               ├── OrderPage.java  # Checkout/order review page logic
│               ├── PaymentPage.java # payment page logic
|               └── ProductCataloguePage.java # product catalogue page logic
|            └── resources/               # utility files
│               ├── ExtentReporterNG.java  # Handles ExtentReports logic
│               └── GlobalData.Properties  # defined global properties (eg: browser, headless)
│
│   └── test/
│       └── java/
|           └── data/
│              ├── DataReader.java # handles logic to convert json into hashmap list
│              └── PurchaseOrder.json # defined json data for purchase order
|           └── testComponents/
│               ├── BaseTest.java # Central class to instantiate browser and other common tests (like AfterMethod , BeforeMethod)
│               ├── Listeners.java # listener for extent reporting
|               └── Retry.java # listerner for retry of execution of tests
│           └── tests/      # Test cases organized by feature or module
│               ├── ErrorValidationTest.java  # test for error validation negative scenarios
│               ├── StandaloneTest.java # Full order flow test
│               └── SubmitOrderTest.java # test for submitting order with POM concepts
|
├── testSuites/  # Test suite configuration file (TestNG)
|   ├── ErrorValidationTests.xml    # xml to run error validation group tests
│   ├── Purchase.xml # run purchase order group tests
│   └── testng.xml # common all tests execution
|
├── pom.xml    # Maven configuration (dependencies, build plugins)



---

## ✅ Key Components

### 🔹 `pageObjects/`
- Encapsulates all web page interactions using Page Object Model.
- Each class maps to a web page or screen and provides clear, reusable methods for UI interaction.

### 🔹 `AbstractComponents/`
- Contains abstract base classes like `AbstractComponents.java` which provide common utility methods such as wait handlers, click actions, etc., shared across all page objects.

### 🔹 `testComponents/`
- `BaseTest.java` sets up the WebDriver and common configurations for tests.
- `Listeners.java` hooks into the TestNG lifecycle for logging/reporting.
- `Retry.java` provides retry mechanisms for test flakiness.

### 🔹 `tests/`
- Organized test cases by feature (e.g., validation, checkout, standalone tests).
- Designed to be modular and data-driven.

### 🔹 `resources/`
- Configuration files like `GlobalData.properties` for environment variables.
- Extent report customization class (`ExtentReporterNG.java`).

### 🔹 `data/`
- Stores test data like `PurchaseOrder.json` and utilities to read them (e.g., `DataReader.java`).

### 🔹 `testSuites/`
- Includes specific and master TestNG XML suites for running grouped tests.

---

## 🛠 Tech Stack

- **Java 11+**
- **Selenium WebDriver**
- **TestNG**
- **Maven**
- **Extent Reports**
- **Jackson / Gson** (for JSON parsing)

---

## 🚀 Getting Started

### 1. Install Dependencies
```bash
mvn clean install

### 2. Run All tests
mvn test

### 3. Run Specific Suite
mvn test -P profileNameFromPom.xml (eg: mvn test -P Purchase)




