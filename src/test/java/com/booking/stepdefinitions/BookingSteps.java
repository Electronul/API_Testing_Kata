package com.booking.stepdefinitions;

import com.booking.client.BookingClient;
import com.booking.context.TestContext;
import com.booking.model.Booking;
import com.booking.model.BookingDates;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingSteps {

    private final TestContext context;
    private final BookingClient bookingClient = new BookingClient();

    public BookingSteps(TestContext context) {
        this.context = context;
    }

    @Given("a valid booking payload")
    public void aValidBookingPayload() {
        Booking booking = new Booking();
        booking.setRoomid(1);
        booking.setFirstname("John");
        booking.setLastname("Doe");
        booking.setDepositpaid(true);
        booking.setBookingdates(new BookingDates(
                LocalDate.now().plusDays(3).toString(),
                LocalDate.now().plusDays(5).toString()
        ));
        booking.setEmail("candidate+" + UUID.randomUUID().toString().substring(0, 8) + "@mail.com");
        booking.setPhone("01234567890");

        context.setRequest(booking);
    }

    @When("I create the booking")
    public void iCreateTheBooking() {
        context.setResponse(bookingClient.createBooking(context.getRequest()));
    }

    @Then("the booking should be created successfully")
    public void theBookingShouldBeCreatedSuccessfully() {
        assertEquals(200, context.getResponse().statusCode());
    }
}