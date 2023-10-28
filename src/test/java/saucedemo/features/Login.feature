Feature: Login to Swag Labs

  @saucedemo
  Scenario Outline: User login to Swag Labs
    Given Swag Labs Login page
    When input <username> as username in login form
    And input <password> as password in login form
    And click on Login button
    Then verify login result <status> and <message>

    Examples:
      | username      | password     | status  | message |
      | standard_user | secret_sauce | success | null    |
      | standard_user | secret_12345 | failed  | Epic sadface: Username and password do not match any user in this service|
      | standard_user |              | failed  | Epic sadface: Password is required                                       |