Feature: User API

  Scenario: Fetch user by ID
    When I request user data for user id "2"
    Then I should receive user details with id "2"