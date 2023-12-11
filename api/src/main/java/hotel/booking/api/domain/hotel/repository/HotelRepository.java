package hotel.booking.api.domain.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hotel.booking.api.domain.hotel.entity.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    @Query("select hotel from HotelEntity hotel where hotel.id in :hotelIds")
    List<HotelEntity> getListHotelsByIds(@Param("hotelIds") List<Long> hotelIds);
}
