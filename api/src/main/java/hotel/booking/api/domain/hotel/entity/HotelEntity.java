package hotel.booking.api.domain.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import hotel.booking.api.domain.common.entity.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class HotelEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(name="phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable =  false)
    private String address;

    @Column
    private String email;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "area_id", nullable = false)
    private AreaEntity area;

    @Builder
    public HotelEntity(Long id, String name, String phoneNumber, String address, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
}
