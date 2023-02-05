package dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateToken {
    @JsonProperty("username")
    private String userName;
    private String password;
}
