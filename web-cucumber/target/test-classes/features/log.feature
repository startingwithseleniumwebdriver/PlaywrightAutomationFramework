Feature: Web Login

  Scenario: Valid login
    Given I open the login page
    When I login with username "admin" and password "admin123"
    Then I should see the login status as "Welcome"