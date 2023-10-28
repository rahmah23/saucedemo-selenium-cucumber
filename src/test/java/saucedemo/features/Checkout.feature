Feature: Checkout Products

  Background:
    Given User has logged in to Swag Labs to checkout products
    And User has added products to the cart

  @saucedemo @checkout
  Scenario: User checkouts some products
    Given User has some products on the cart
    When User clicks on Cart icon
    And clicks on checkout button
    Then User will see receiver information form
    When User input First Name, Last Name, and Postal Code
    And clicks on Continue button
    Then User will see Payment Information, Shipping Information, and Price Total