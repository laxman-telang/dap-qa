
Feature: SauceDemo Login

  Scenario: Successful login with valid credentials
    Given I open the SauceDemo login page
    When I enter valid username and password
    And I click the login button
    Then I should be redirected to the inventory page
