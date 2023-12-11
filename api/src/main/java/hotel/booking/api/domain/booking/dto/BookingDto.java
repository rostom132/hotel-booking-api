package hotel.booking.api.domain.booking.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class BookingDto {
    private Long customerId;

    @NotNull
    private Long roomId;

    @NotNull
    private LocalDateTime checkinDate;

    @NotNull
    private LocalDateTime checkoutDate;
    
    private BigDecimal totalAmount;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleBooking {
        BookingDto booking;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultipleBooking {
        BookingDto bookings;
    }
}
