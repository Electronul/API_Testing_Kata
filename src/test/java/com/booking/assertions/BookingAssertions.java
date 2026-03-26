package com.booking.assertions;

import com.booking.model.Booking;
import com.booking.model.CreateBookingResponse;
import com.booking.model.ErrorResponse;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class BookingAssertions {

    private BookingAssertions() {
    }

    public static void assertBookingCreated(Response response, Booking expected) {
        assertEquals(200, response.statusCode(), "Unexpected status code");

        CreateBookingResponse body = response.as(CreateBookingResponse.class);
        assertNotNull(body.getBookingid(), "bookingid should be returned");
        assertTrue(body.getBookingid() > 0, "bookingid should be positive");
        assertNotNull(body.getBooking(), "booking object should be present");

        Booking actual = body.getBooking();
        assertEquals(expected.getRoomid(), actual.getRoomid(), "roomid mismatch");
        assertEquals(expected.getFirstname(), actual.getFirstname(), "firstname mismatch");
        assertEquals(expected.getLastname(), actual.getLastname(), "lastname mismatch");
        assertEquals(expected.getDepositpaid(), actual.getDepositpaid(), "depositpaid mismatch");
        assertEquals(expected.getEmail(), actual.getEmail(), "email mismatch");
        assertEquals(expected.getPhone(), actual.getPhone(), "phone mismatch");

        assertNotNull(actual.getBookingdates(), "bookingdates should be returned");
        assertEquals(expected.getBookingdates().getCheckin(), actual.getBookingdates().getCheckin(), "checkin mismatch");
        assertEquals(expected.getBookingdates().getCheckout(), actual.getBookingdates().getCheckout(), "checkout mismatch");
    }

    public static void assertValidationError(Response response, String expectedErrorFragment) {
        assertEquals(400, response.statusCode(), "Unexpected status code");

        ErrorResponse errorResponse = response.as(ErrorResponse.class);
        assertNotNull(errorResponse.getErrors(), "errors list should be present");
        assertFalse(errorResponse.getErrors().isEmpty(), "errors list should not be empty");
        assertTrue(
                errorResponse.getErrors().stream().anyMatch(error -> error.contains(expectedErrorFragment)),
                "Expected error fragment not found: " + expectedErrorFragment
        );
    }
}