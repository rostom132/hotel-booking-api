package hotel.booking.api.domain.hotel.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RoomDto {
    private HotelDto hotel;
    private String roomNumber;
    private String roomType;
    private BigDecimal pricatePerNight; 
}
