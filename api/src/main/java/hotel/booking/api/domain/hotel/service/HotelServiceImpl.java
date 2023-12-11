package hotel.booking.api.domain.hotel.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHitSupport;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import hotel.booking.api.domain.hotel.model.HotelParams;
import hotel.booking.api.domain.hotel.dto.HotelDto;
import hotel.booking.api.domain.hotel.entity.HotelESEntity;
import hotel.booking.api.domain.hotel.entity.HotelEntity;
import hotel.booking.api.domain.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<HotelDto> listHotels(HotelParams hotelFilter) {
        Pageable pageable = PageRequest.of(hotelFilter.getPageNumber(), hotelFilter.getPageSize());
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(
            hotelFilter.getQueryString(),
            "name",
            "areaName",
            "cityName"    
        );

        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(queryBuilder)
            .withPageable(pageable)
            .build();

        SearchHits<HotelESEntity> hotelHits = elasticsearchOperations.search(query, HotelESEntity.class);
        SearchPage<HotelESEntity> hotelPage = SearchHitSupport.searchPageFor(hotelHits, pageable);
        List<Long> hotelIds = hotelPage.get().map(entity -> entity.getContent().getId())
            .collect(Collectors.toList());

        List<HotelEntity> lstHotelEntities  = hotelRepository.getListHotelsByIds(hotelIds);
        return lstHotelEntities.stream().map(entity -> convertToHotel(entity)).collect(Collectors.toList());
    }

    @Override
    public HotelDto getById(Long hotelId) {
        return convertToHotel(hotelRepository.getReferenceById(hotelId));
    }
        
    private HotelDto convertToHotel(HotelEntity hotelEntity) {
        if (Objects.isNull(hotelEntity)) {
            return null;
        }

        return HotelDto.builder()
            .name(hotelEntity.getName())
            .address(hotelEntity.getAddress())
            .email(hotelEntity.getEmail())
            .phoneNumber(hotelEntity.getPhoneNumber())
            .areaName(hotelEntity.getArea().getName())
            .cityName(hotelEntity.getArea().getCity().getName())
            .build();
    }
}
