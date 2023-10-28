Feature: Sorting Products
  Background:
    Given User has logged in to Swag Labs

  @saucedemo @sorting
  Scenario Outline: User sorts products
    Given User is on Products page
    And see sorting field
    When User clicks on sorting field
    And choose <option> as sorting option
    Then products will be sorted and <product> will display first

    Examples:
      | option              | product |
      | Price (low to high) | Sauce Labs Onesie |
      | Price (high to low) | Sauce Labs Fleece Jacket |
      | Name (A to Z)       | Sauce Labs Backpack      |
      | Name (Z to A)       | Test.allTheThings() T-Shirt (Red) |

