package hotel.booking.api.domain.booking.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import hotel.booking.api.domain.booking.dto.BookingDetailDto;
import hotel.booking.api.domain.booking.dto.BookingDto;
import hotel.booking.api.domain.booking.entity.BookingEntity;
import hotel.booking.api.domain.booking.repository.BookingRepository;
import hotel.booking.api.domain.common.utils.CustomerUtils;
import hotel.booking.api.domain.customer.entity.CustomerEntity;
import hotel.booking.api.domain.customer.model.CustomerDetail;
import hotel.booking.api.domain.hotel.dto.HotelDto;
import hotel.booking.api.domain.hotel.dto.RoomDto;
import hotel.booking.api.domain.hotel.entity.RoomEntity;
import hotel.booking.api.domain.hotel.repository.RoomRepository;
import hotel.booking.api.exception.AppException;
import hotel.booking.api.exception.AppError;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    
    @Transactional
    @Override
    public BookingDto bookRoom(BookingDto bookingDto, CustomerDetail customerDetail) {
        try {
            // Validate booking infomation
            if (bookingDto.getCheckinDate().isAfter(bookingDto.getCheckoutDate())) {
                throw new AppException(AppError.INVALID_BOOKING_DATE);
            }

            CustomerEntity customer = CustomerUtils.customerDetailToCustomerEntity(customerDetail);

            // Get room to book (use optimistic lock to avoid race condition)
            RoomEntity room = roomRepository.findWithLockById(bookingDto.getRoomId())
                .orElseThrow(() -> new AppException(AppError.ROOM_NOT_FOUND));
            
            // Check if there is any colapse booking for the room
            List<BookingEntity> colapsedBookings = bookingRepository.getBookingByRoomIdInDateRange(
                room.getId(),
                bookingDto.getCheckinDate(),
                bookingDto.getCheckoutDate()
            );

            if (Objects.nonNull(colapsedBookings) && !colapsedBookings.isEmpty()) {
                throw new AppException(AppError.OVERLAPSE_BOOKING_TIME);
            }

            // Get price
            Duration duration = Duration.between(bookingDto.getCheckinDate(), bookingDto.getCheckoutDate());
            BigDecimal totalAmount = BigDecimal.valueOf(duration.toDays()).multiply(room.getPricePerNight());

            BookingEntity bookingEntity = BookingEntity.builder()
                .room(room)
                .customer(customer)
                .totalAmount(totalAmount)
                .checkinDate(bookingDto.getCheckinDate())
                .checkoutDate(bookingDto.getCheckoutDate())
                .build();

            bookingRepository.save(bookingEntity);

            bookingDto.setCustomerId(customer.getId());
            bookingDto.setTotalAmount(totalAmount);

            return bookingDto;
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new AppException(AppError.BOOKING_GOT_BLOCKED);
        }
    }

    @Override
    public List<BookingDetailDto> listBookingForCustomer(CustomerDetail customerDetail) {
        List<BookingEntity> bookingEntities = bookingRepository.getBookingByCustomerId(customerDetail.getId());
        return bookingEntities.stream()
            .map(entity -> bookingEntityToBookingDetailDtoWithCustomer(entity, customerDetail))
            .collect(Collectors.toList());
    }

    private BookingDetailDto bookingEntityToBookingDetailDtoWithCustomer(BookingEntity bookingEntity, CustomerDetail customerDetail) {
        RoomDto roomDto = RoomDto.builder()
            .roomNumber(bookingEntity.getRoom().getRoomNumber())
            .roomType(bookingEntity.getRoom().getRoomType())
            .pricatePerNight(bookingEntity.getRoom().getPricePerNight())
            .hotel(
                HotelDto.builder()
                    .address(bookingEntity.getRoom().getHotel().getAddress())
                    .email(bookingEntity.getRoom().getHotel().getEmail())
                    .name(bookingEntity.getRoom().getHotel().getName())
                    .phoneNumber(bookingEntity.getRoom().getHotel().getPhoneNumber())
                    .build()
            )
            .build();

        return BookingDetailDto.builder()
            .customer(CustomerUtils.customerDetailToCustomerDto(customerDetail))
            .checkinDate(bookingEntity.getCheckinDate())
            .checkoutDate(bookingEntity.getCheckoutDate())
            .totalAmount(bookingEntity.getTotalAmount())
            .room(roomDto)
            .build();
    }
}
