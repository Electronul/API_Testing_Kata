package com.booking.builder;

import com.booking.model.Booking;
import com.booking.model.BookingDates;

import java.time.LocalDate;
import java.util.UUID;

public class BookingBuilder {

    private Integer roomid = 1;
    private String firstname = "John";
    private String lastname = "Doe";
    private Boolean depositpaid = true;
    private String checkin = LocalDate.now().plusDays(3).toString();
    private String checkout = LocalDate.now().plusDays(5).toString();
    private String email = uniqueEmail();
    private String phone = "01234567890";

    public BookingBuilder withRoomId(Integer roomid) {
        this.roomid = roomid;
        return this;
    }

    public BookingBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public BookingBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public BookingBuilder withDepositPaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
        return this;
    }

    public BookingBuilder withCheckin(String checkin) {
        this.checkin = checkin;
        return this;
    }

    public BookingBuilder withCheckout(String checkout) {
        this.checkout = checkout;
        return this;
    }

    public BookingBuilder withDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
        return this;
    }

    public BookingBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public BookingBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Booking build() {
        Booking booking = new Booking();
        booking.setRoomid(roomid);
        booking.setFirstname(firstname);
        booking.setLastname(lastname);
        booking.setDepositpaid(depositpaid);
        booking.setBookingdates(new BookingDates(checkin, checkout));
        booking.setEmail(email);
        booking.setPhone(phone);
        return booking;
    }

    private static String uniqueEmail() {
        return "candidate+" + UUID.randomUUID().toString().substring(0, 8) + "@mail.com";
    }
}