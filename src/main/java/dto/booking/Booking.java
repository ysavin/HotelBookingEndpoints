package dto.booking;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Booking {
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("totalprice")
    private Integer totalPrice;
    @JsonProperty("depositpaid")
    private Boolean depositPaid;
    @JsonProperty("bookingdates")
    private BookingDates bookingDates;
    @JsonProperty("additionalneeds")
    private String additionalNeeds;
}
