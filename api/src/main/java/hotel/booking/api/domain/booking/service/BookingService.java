package hotel.booking.api.domain.booking.service;

import java.util.List;

import hotel.booking.api.domain.booking.dto.BookingDetailDto;
import hotel.booking.api.domain.booking.dto.BookingDto;
import hotel.booking.api.domain.customer.model.CustomerDetail;

public interface BookingService {
    BookingDto bookRoom(BookingDto bookingDto, CustomerDetail customerDetail);
    List<BookingDetailDto> listBookingForCustomer(CustomerDetail customerDetail);
}
