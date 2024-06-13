
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Posttive step to submit the order
    Given login with USername <name> and Password <password>
    When I add product <productName> to cart 
    And Checkout  <productName> and submit the Order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation Page
    
    Examples: 
      | name                       | password | productName |
      | corona-rebid-0d@icloud.com | Asdf@123 |ZARA COAT 3|
  
