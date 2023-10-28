Feature: Product Detail page
  Background:
    Given User has logged in to Swag Labs to see detail

  @saucedemo @detailpage
  Scenario: User see the detail of registered product item
    Given User is on Products page to see detail
    When User clicks on product item
    Then User will be redirected to product detail page

  @saucedemo @detailpage
  Scenario: User see unregistered inventory item
    Given User is on Products page to see detail
    When User clicks on product item
    And User changes product Id on url with invalid Id
    Then Item not Found message appears