package dto.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateBookingRs {
    @JsonProperty("bookingid")
    public Integer bookingId;
    public Booking booking;

}
