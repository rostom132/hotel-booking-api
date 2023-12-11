package hotel.booking.api.domain.hotel.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import hotel.booking.api.domain.hotel.entity.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<RoomEntity> findWithLockById(Long id);
}
