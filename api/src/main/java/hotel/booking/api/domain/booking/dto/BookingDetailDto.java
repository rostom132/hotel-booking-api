package hotel.booking.api.domain.booking.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import hotel.booking.api.domain.customer.dto.CustomerDto;
import hotel.booking.api.domain.hotel.dto.RoomDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BookingDetailDto {
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime checkinDate;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime checkoutDate;
    
    private BigDecimal totalAmount;

    private CustomerDto customer;

    private RoomDto room;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SingleBookingDetail {
        BookingDetailDto bookingDetail;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MultipleBookingDetail {
        List<BookingDetailDto> bookingDetails;
    }
}
