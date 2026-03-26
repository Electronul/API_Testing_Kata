package com.booking.stepdefinitions;

import com.booking.assertions.BookingAssertions;
import com.booking.builder.BookingBuilder;
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

    @Given("a booking payload with invalid email")
    public void aBookingPayloadWithInvalidEmail() {
        context.setRequest(new BookingBuilder().withEmail("invalid-email").build());
    }

    @Given("a booking payload with firstname shorter than allowed")
    public void aBookingPayloadWithShortFirstname() {
        context.setRequest(new BookingBuilder().withFirstname("Jo").build());
    }

    @Given("a booking payload with phone {string}")
    public void aBookingPayloadWithPhone(String phone) {
        context.setRequest(new BookingBuilder().withPhone(phone).build());
    }

    @Given("a booking payload with checkout before checkin")
    public void aBookingPayloadWithCheckoutBeforeCheckin() {
        LocalDate checkin = LocalDate.now().plusDays(5);
        LocalDate checkout = checkin.minusDays(1);

        context.setRequest(
                new BookingBuilder()
                        .withDates(checkin.toString(), checkout.toString())
                        .build()
        );
    }

    @When("I create the booking")
    public void iCreateTheBooking() {
        context.setResponse(bookingClient.createBooking(context.getRequest()));
    }

    @Then("the booking should be created successfully")
    public void theBookingShouldBeCreatedSuccessfully() {
        BookingAssertions.assertBookingCreated(context.getResponse(), context.getRequest());
    }

    @Then("the API should return a validation error containing {string}")
    public void theApiShouldReturnAValidationErrorContaining(String expectedErrorMessage) {
        BookingAssertions.assertValidationError(context.getResponse(), expectedErrorMessage);
    }
}