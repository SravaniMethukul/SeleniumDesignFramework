# 🧪 Selenium Design Framework (Java)

This project is a Java-based Selenium test automation framework following the **Page Object Model (POM)** design pattern. 
It supports both **UI end-to-end testing**, and is organized for clarity, scalability, and maintainability.

---

## 📁 Project Structure

<pre lang="markdown"> <code>```plaintext /project-root │ ├── src/ │ └── main/ │ └── java/ │ ├── AbstractComponents/ │ │ └── AbstractComponents.java # Central class to instantiate and return page objects │ │ │ ├── pageObjects/ # POM classes for each web page │ │ ├── ConfirmationPage.java # Handles confirmation page locators and actions │ │ ├── LandingPage.java # Handles login page elements │ │ ├── CartPage.java # Logic for cart page elements │ │ ├── OrderPage.java # Checkout/order review page logic │ │ ├── PaymentPage.java # Payment page logic │ │ └── ProductCataloguePage.java # Product catalogue page logic │ │ └── resources/ # Utility files │ ├── ExtentReporterNG.java # ExtentReports setup │ └── GlobalData.properties # Global config (e.g., browser type) │ │ └── test/ │ └── java/ │ ├── data/ │ │ ├── DataReader.java # Converts JSON into HashMap list │ │ └── PurchaseOrder.json # Test data for purchase order │ │ ├── testComponents/ │ │ ├── BaseTest.java # WebDriver setup, hooks │ │ ├── Listeners.java # Extent listener │ │ └── Retry.java # Retry logic for failed tests │ │ └── tests/ # Test classes │ ├── ErrorValidationTest.java # Negative scenario validation │ ├── StandaloneTest.java # Demo/test example │ └── SubmitOrderTest.java # Full order test flow │ ├── testSuites/ # TestNG suite configurations │ ├── ErrorValidationTests.xml │ ├── Purchase.xml │ └── testng.xml │ └── pom.xml # Maven project file ```</code> </pre>



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




