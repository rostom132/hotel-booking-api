package hotel.booking.api.domain.hotel.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelParams {
    @NotBlank
    private String queryString;
    private Integer pageSize = 50;
    private Integer pageNumber = 0;
}
