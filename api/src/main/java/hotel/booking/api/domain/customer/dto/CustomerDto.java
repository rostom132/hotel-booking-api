package hotel.booking.api.domain.customer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
