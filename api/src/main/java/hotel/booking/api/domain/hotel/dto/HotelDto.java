package hotel.booking.api.domain.hotel.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class HotelDto {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String areaName;
    private String cityName;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleHotel {
        HotelDto hotel;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultipleHotel {
        List<HotelDto> hotels;
    }
}
