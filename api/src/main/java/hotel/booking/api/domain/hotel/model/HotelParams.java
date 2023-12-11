package hotel.booking.api.domain.hotel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelParams {
    private String queryString;
    private Integer pageSize;
    private Integer pageNumber;
}
