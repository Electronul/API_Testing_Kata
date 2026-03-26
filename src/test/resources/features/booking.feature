Feature: Create a booking

  Scenario: Create a booking with valid data
    Given a valid booking payload
    When I create the booking
    Then the booking should be created successfully

  Scenario: Reject a booking with invalid email format
    Given a booking payload with invalid email
    When I create the booking
    Then the API should return a validation error containing "well-formed email"