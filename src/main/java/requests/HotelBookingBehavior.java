package requests;


import dto.booking.Booking;
import dto.booking.BookingIds;
import dto.booking.CreateBookingRs;
import dto.token.CreateToken;
import dto.token.CreateTokenRs;
import io.restassured.response.ValidatableResponse;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class HotelBookingBehavior {
    public Booking getBookingById(String id) {
        ValidatableResponse response = new Requests().getBookingById(id);
        response.assertThat().statusCode(SC_OK);
        return response.extract().as(Booking.class);
    }

    public List<BookingIds> getBookingIds() {
        ValidatableResponse response = new Requests().getBookingIds();
        response.assertThat().statusCode(SC_OK);
        return response.extract().jsonPath().getList(".", BookingIds.class);
    }

    public List<BookingIds> getBookingIdsWithParams(String firstName, String lastName) {
        ValidatableResponse response = new Requests().getBookingIds(firstName, lastName);
        response.assertThat().statusCode(SC_OK);
        return response.extract().jsonPath().getList(".", BookingIds.class);
    }

    public CreateTokenRs createToken(CreateToken body) {
        ValidatableResponse response = new Requests().createToken(body);
        response.assertThat().statusCode(SC_OK);
        return response.extract().as(CreateTokenRs.class);
    }

    public CreateBookingRs createBooking(Booking body) {
        ValidatableResponse response = new Requests().createBooking(body);
        response.assertThat().statusCode(SC_OK);
        return response.extract().as(CreateBookingRs.class);
    }

    public Booking partialUpdateBooking(String token, Integer id, Booking body) {
        ValidatableResponse response = new Requests(token).partialUpdateBooking(id, body);
        response.assertThat().statusCode(SC_OK);
        return response.extract().as(Booking.class);
    }

    public void deleteBooking(String token, Integer id) {
        ValidatableResponse response = new Requests(token).deleteBooking(id);
        response.assertThat().statusCode(SC_CREATED);
    }

}
