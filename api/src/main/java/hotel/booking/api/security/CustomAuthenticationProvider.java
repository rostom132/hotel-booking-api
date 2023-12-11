package hotel.booking.api.security;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import hotel.booking.api.domain.common.utils.CustomerUtils;
import hotel.booking.api.domain.customer.entity.CustomerEntity;
import hotel.booking.api.domain.customer.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomerRepository customerRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomerEntity customerEntity = customerRepository.getCustomerByEmail(email)
            .orElseThrow(() -> new BadCredentialsException("system authentication failed"));

        if (customerEntity.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(CustomerUtils.customerEntityToCustomerDetail(customerEntity), null, null);
        }
        
        throw new BadCredentialsException("system authentication failed");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
