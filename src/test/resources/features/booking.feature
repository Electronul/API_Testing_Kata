Feature: Create a booking

  Scenario: Create a booking with valid data
    Given a valid booking payload
    When I create the booking
    Then the booking should be created successfully

  Scenario: Reject a booking with invalid email format
    Given a booking payload with invalid email
    When I create the booking
    Then the API should return a validation error containing "well-formed email"

  Scenario: Reject a booking with firstname shorter than allowed
    Given a booking payload with firstname shorter than allowed
    When I create the booking
    Then the API should return a validation error containing "size must be between 3 and 18"