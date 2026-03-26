package com.booking.context;

import com.booking.model.Booking;
import io.restassured.response.Response;

public class TestContext {

    private Booking request;
    private Response response;

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
}