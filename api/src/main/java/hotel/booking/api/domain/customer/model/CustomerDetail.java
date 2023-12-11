package hotel.booking.api.domain.customer.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDetail {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
