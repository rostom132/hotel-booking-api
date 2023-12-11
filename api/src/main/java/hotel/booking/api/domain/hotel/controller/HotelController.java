package hotel.booking.api.domain.hotel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotel.booking.api.domain.hotel.model.HotelParams;
import hotel.booking.api.domain.hotel.dto.HotelDto;
import hotel.booking.api.domain.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    public HotelDto.MultipleHotel listHotels(@ModelAttribute HotelParams hotelQueryParam) {
        return new HotelDto.MultipleHotel(hotelService.listHotels(hotelQueryParam));
    }

    @GetMapping("/{hotelId}")
    public HotelDto.SingleHotel getHotel(@PathVariable Long hotelId) {

        return new HotelDto.SingleHotel(hotelService.getById(hotelId));
    }
}
