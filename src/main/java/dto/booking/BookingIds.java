package dto.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookingIds {
    @JsonProperty("bookingid")
    private Integer bookingId;
}
