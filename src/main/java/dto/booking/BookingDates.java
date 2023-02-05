package dto.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookingDates {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("checkin")
    private String checkIn;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("checkout")
    private String checkOut;
}
