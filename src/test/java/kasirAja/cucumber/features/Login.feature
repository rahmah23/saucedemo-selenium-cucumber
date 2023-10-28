Feature: Login to Kasir Aja

  @Regression @Positive
  Scenario: Success Login
    Given Kasir Aja Login page
    When input email
    And input password
    And click Login button
    Then User is on Dashboard page

  @Regression @Negative
  Scenario: Failed Login
    Given Kasir Aja Login page
    When input email
    And input wrong password
    And click Login button
    Then User gets an error message

  @DDT
  Scenario Outline: User login to kasir Aja
    Given Kasir Aja Login page
    When input <email> as email
    And input <password> as password
    And click Login button
    Then verify <status> login result

    Examples:
      | email              | password | status  |
      | rarashop@gmail.com | password | success |
      | rarashop@gmail.com | 123456   | failed  |