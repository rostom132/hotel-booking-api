package hotel.booking.api.domain.hotel.service;

import java.util.List;

import hotel.booking.api.domain.hotel.model.HotelParams;
import hotel.booking.api.domain.hotel.dto.HotelDto;

public interface HotelService {
    List<HotelDto> listHotels(HotelParams hotelFilter);
    HotelDto getById(Long hotelId);
}
