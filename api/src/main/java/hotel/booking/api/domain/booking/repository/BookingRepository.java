package hotel.booking.api.domain.booking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hotel.booking.api.domain.booking.entity.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    @Query("select booking from BookingEntity booking " +
        " where booking.room.id = :roomId " + 
        " and booking.checkinDate <= :endDate and booking.checkoutDate >= :beginDate")
    List<BookingEntity> getBookingByRoomIdInDateRange(
        Long roomId,
        LocalDateTime beginDate,
        LocalDateTime endDate
    );

    @Query("select booking from BookingEntity booking where booking.customer.id = :customerId")
    List<BookingEntity> getBookingByCustomerId(Long customerId);
}
