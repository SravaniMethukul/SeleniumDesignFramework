@tag
Feature: Error Validation

  @ErrorValidation
  Scenario Outline: Error validation for login
    Given landed on Ecommerce page
    When Logged into website with username "<username>" and password "<password>"
    Then "Incorrect email or password." error message is displayed.

    Examples: 
      | username      | password |   
      | abc@gmail.com | abcQ12   |
