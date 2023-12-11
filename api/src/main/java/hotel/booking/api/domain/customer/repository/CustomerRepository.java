package hotel.booking.api.domain.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hotel.booking.api.domain.customer.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query("SELECT u FROM CustomerEntity u WHERE u.email = :email")
    Optional<CustomerEntity> getCustomerByEmail(@Param("email") String email);
}
