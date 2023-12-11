package hotel.booking.api.domain.hotel.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Document(indexName = "hotel")
public class HotelESEntity {
    @Id
    private Long id;

    private String name;
    private String areaName;
    private String cityName;
}
