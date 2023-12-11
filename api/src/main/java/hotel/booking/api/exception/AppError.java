package hotel.booking.api.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum AppError {
    // Customer exceptions
    CUSTOMER_NOT_FOUND("customer not found", HttpStatus.NOT_FOUND),
    // Room exceptions
    ROOM_NOT_FOUND("room not found", HttpStatus.NOT_FOUND),
    // Business exceptions
    BOOKING_GOT_BLOCKED("this room is booked or being booking", HttpStatus.UNPROCESSABLE_ENTITY),
    OVERLAPSE_BOOKING_TIME("the booking date is overlapsed with other booking",  HttpStatus.UNPROCESSABLE_ENTITY),
    INVALID_BOOKING_DATE("the check-in or check-out date is invalid", HttpStatus.UNPROCESSABLE_ENTITY)
    ;

    private final String message;
    private final HttpStatus status;

    AppError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}

