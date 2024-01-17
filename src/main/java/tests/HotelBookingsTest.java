package tests;

import dto.booking.Booking;
import dto.booking.BookingDates;
import dto.booking.BookingIds;
import dto.booking.CreateBookingRs;
import org.junit.jupiter.api.Test;
import requests.HotelBookingBehavior;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

public class HotelBookingsTest extends BaseTest {
    public HotelBookingsTest() {
        bookingBehavior = new HotelBookingBehavior();
    }

    @Test
    void partialUpdateBooking() {
        CreateBookingRs booking = createBooking();
        Booking updatedBooking = bookingBehavior.partialUpdateBooking(token, booking.getBookingId(),
                prepareBodyForPartialUpdate("Updated " + booking.getBooking().getFirstName(), "Updated " + booking.getBooking().getLastName()));
        assertNotEquals(booking.getBooking().getFirstName(), updatedBooking.getFirstName(), "First name is updated");
        assertNotEquals(booking.getBooking().getLastName(), updatedBooking.getLastName(), "Last name is updated");
    }

    @Test
    void getBookingIdsTest() {
        CreateBookingRs addedBooking = createBooking();
        assertTrue(bookingBehavior.getBookingIds().stream().anyMatch(id -> id.getBookingId().equals(addedBooking.getBookingId())), "Added booking is in the list");
    }

    @Test
    void getBookingIdsWithParamsTest() {
        CreateBookingRs addedBooking = createBooking();
        CreateBookingRs customBooking = createCustomBooking("Johan", "Cruyff", 0, TRUE, null);
        List<BookingIds> bookings = bookingBehavior.getBookingIdsWithParams(customBooking.getBooking().getFirstName(),
                customBooking.getBooking().getLastName());
        List<Integer> ids = new ArrayList<>();
        bookings.forEach(booking -> ids.add(booking.getBookingId()));
        assertTrue(ids.contains(customBooking.bookingId), "Custom booking is in the filtered list");
        assertFalse(ids.contains(addedBooking.bookingId), "Added booking is not in the filtered list");
    }

    @Test
    void deleteBookingTest() {
        CreateBookingRs addedBooking = createBooking();
        assertTrue(getBookings().stream().anyMatch(id -> id.getBookingId().equals(addedBooking.getBookingId())), "Added booking is in the filtered list");
        deleteBooking(addedBooking.getBookingId());
        assertFalse(getBookings().stream().anyMatch(id -> id.getBookingId().equals(addedBooking.getBookingId())), "Added booking is in the filtered list");
    }

    public CreateBookingRs createBooking() {
        return bookingBehavior.createBooking(prepareBookingBody());
    }

    public void deleteBooking(Integer bookingId) {
        bookingBehavior.deleteBooking(token, bookingId);
    }

    public List<BookingIds> getBookings() {
        return bookingBehavior.getBookingIds();
    }

    public CreateBookingRs createCustomBooking(String firstName, String lastName, Integer totalPrice, Boolean depositPaid, String additionalNeeds) {
        return bookingBehavior.createBooking(prepareCustomBookingBody(firstName, lastName, totalPrice, depositPaid, additionalNeeds));
    }

    public Booking prepareBookingBody() {
        Booking body = new Booking();
        body.setFirstName("Test");
        body.setLastName("Testable");
        body.setTotalPrice(100);
        body.setDepositPaid(TRUE);
        body.setBookingDates(getBookingDates());
        body.setAdditionalNeeds("Lunch");
        return body;
    }

    public Booking prepareCustomBookingBody(String firstName, String lastName, Integer totalPrice, Boolean depositPaid, String additionalNeeds) {
        Booking body = new Booking();
        body.setFirstName(firstName);
        body.setLastName(lastName);
        body.setTotalPrice(totalPrice);
        body.setDepositPaid(depositPaid);
        body.setBookingDates(getBookingDates());
        body.setAdditionalNeeds(additionalNeeds);
        return body;
    }

    public Booking prepareBodyForPartialUpdate(String firstName, String lastName) {
        Booking body = new Booking();
        body.setFirstName(firstName);
        body.setLastName(lastName);
        return body;
    }

    public BookingDates getBookingDates() {
        BookingDates dates = new BookingDates();
        dates.setCheckIn(LocalDate.now().toString());
        dates.setCheckOut(LocalDate.now().plusWeeks(1).toString());
        return dates;
    }
}
