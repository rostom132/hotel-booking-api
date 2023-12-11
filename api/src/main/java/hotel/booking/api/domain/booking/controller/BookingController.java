package hotel.booking.api.domain.booking.controller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hotel.booking.api.domain.booking.dto.BookingDetailDto;
import hotel.booking.api.domain.booking.dto.BookingDto;
import hotel.booking.api.domain.booking.service.BookingService;
import hotel.booking.api.domain.customer.model.CustomerDetail;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingDto.SingleBooking bookRoom(@Valid @RequestBody BookingDto booking, @AuthenticationPrincipal CustomerDetail customerDetail) {
        return new BookingDto.SingleBooking(bookingService.bookRoom(booking, customerDetail));
    }

    @GetMapping
    public BookingDetailDto.MultipleBookingDetail listBookingForCustomer(@AuthenticationPrincipal CustomerDetail customerDetail) {
        return new BookingDetailDto.MultipleBookingDetail(bookingService.listBookingForCustomer(customerDetail));
    }
}
