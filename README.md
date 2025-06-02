# 🧪 Ecommerce Automation Framework with Selenium Design Framework (Java, TestNG, Maven, Jenkins (CI/CD))


Overview
The Ecommerce Automation Framework is a Selenium-based test automation setup designed to test an ecommerce application. It includes end-to-end tests, API tests, validation tests, and reusable utilities. The framework is modular, scalable, and easy to use for both beginners and experienced testers.

This project is a Java-based Selenium test automation framework following the **Page Object Model (POM)** design pattern. The Framework is a Selenium based test automation setup designed to test an ecommerce application. It supports both **UI end-to-end testing**, and is organized for clarity, scalability, and maintainability.

---

## 📁 Project Structure

```text
/project-root
│
├── src/
│   └── main/
│       └── java/
│           ├── AbstractComponents/
│           │   └── AbstractComponents.java              # Base class to manage and return page objects
│           │
│           ├── pageObjects/                             # Page Object Model (POM) classes
│           │   ├── CartPage.java                         # Logic for cart page
│           │   ├── ConfirmationPage.java                 # Confirmation page locators/actions
│           │   ├── LandingPage.java                      # Login page elements
│           │   ├── OrderPage.java                        # Checkout/order review logic
│           │   ├── PaymentPage.java                      # Payment page handling
│           │   └── ProductCataloguePage.java             # Product catalog interaction
│           │
│           └── resources/                               # Utility & config files
│               ├── ExtentReporterNG.java                # ExtentReports configuration
│               └── GlobalData.properties                # Global settings (e.g., browser, URL)
│
│   └── test/
│       └── java/
│           ├── data/
│           │   ├── DataReader.java                      # Reads data from JSON to HashMap
│           │   └── PurchaseOrder.json                   # JSON test data for purchase flow
|
|           ├── cucumber/
│           │   ├── TestNGTestRunner.java                # TestRunner for cucumber feature files
│           │   └── ErrorValidation.feature              # feature file for error validation
│           │   └── SubmitOrder.feature                  # feature file for submit order
│
│           ├── stepDefinitions/
│           │   ├── ErrorValidationStepDefinition.java    # Step Definitions for Error Validation
│           │   ├── StepDefinitionImplementation.java     # Step Definition for Submit Order
|
│           ├── testComponents/
│           │   ├── BaseTest.java                        # WebDriver setup/teardown
│           │   ├── Listeners.java                       # ExtentReport TestNG listener
│           │   └── Retry.java                           # Retry logic for flaky tests
│
│           └── tests/                                   # Actual test classes
│               ├── ErrorValidationTest.java             # Tests for invalid scenarios
│               ├── StandaloneTest.java                  # Example standalone test
│               └── SubmitOrderTest.java                 # End-to-end purchase test
│
├── testSuites/                                          # TestNG suite XMLs
│   ├── ErrorValidationTests.xml                         # Runs validation tests
│   ├── Purchase.xml                                     # Runs order placement tests
│   └── testng.xml                                       # Master suite file
│
└── pom.xml                                              # Maven project config (dependencies, plugins)
```

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
- **Jenkins**(for CI/CD)

---

## 🚀 Getting Started

### 1. Install Dependencies
mvn clean install

### 2. Run All tests
mvn test

### 3. Run specific suite
mvn test -P ProfileNameFromPom (eg: mvn test -P Purchase)
