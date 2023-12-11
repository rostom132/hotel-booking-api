package hotel.booking.api.domain.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import hotel.booking.api.domain.common.entity.BaseEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "area")
public class AreaEntity extends BaseEntity {
    @Column(name="name_text")
    private String name;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "city_id", nullable = false)
    private CityEntity city;
}
