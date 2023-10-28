Feature: Add Product to Cart

  Background:
    Given User has logged in to Swag Labs to add to cart

  @saucedemo @addtocart
  Scenario: User adds a product to the cart
    Given User is on Products page to add to cart
    When User clicks on Add to cart button
    And click on Cart icon
    Then The product will be displayed on cart page
    And cart badge will increase

  @saucedemo @addtocart
  Scenario: User removes product from the cart
    Given User is on Products page to add to cart
    And User has added two products to the cart
    And click on Cart icon
    When User clicks on Remove button
    Then The product is no longer display in the cart page
    And cart badge will decrease