package com.booking.context;

import com.booking.model.Booking;
import io.restassured.response.Response;

public class TestContext {

    private Booking request;
    private Response response;
    private String authToken;
    private Integer bookingId;
    private Response retrievedBookingResponse;

    public Booking getRequest() {
        return request;
    }

    public void setRequest(Booking request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Response getRetrievedBookingResponse() {
        return retrievedBookingResponse;
    }

    public void setRetrievedBookingResponse(Response retrievedBookingResponse) {
        this.retrievedBookingResponse = retrievedBookingResponse;
    }
}