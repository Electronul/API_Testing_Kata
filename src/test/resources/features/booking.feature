Feature: Create a booking

  Scenario: Create a booking with valid data
    Given a valid booking payload
    When I create the booking
    Then the booking should be created successfully