package hotel.booking.api.domain.common.utils;

import java.util.Objects;

import hotel.booking.api.domain.customer.dto.CustomerDto;
import hotel.booking.api.domain.customer.entity.CustomerEntity;
import hotel.booking.api.domain.customer.model.CustomerDetail;

public class CustomerUtils {
    public static CustomerDetail customerEntityToCustomerDetail(CustomerEntity customerEntity) {
        if (Objects.isNull(customerEntity)) {
            return null;
        }

        return CustomerDetail.builder()
            .id(customerEntity.getId())
            .firstName(customerEntity.getFirstName())
            .lastName(customerEntity.getLastName())
            .email(customerEntity.getEmail())
            .phoneNumber(customerEntity.getPhoneNumber())
            .build();
    }

    public static CustomerEntity customerDetailToCustomerEntity(CustomerDetail customerDetail) {
        if (Objects.isNull(customerDetail)) {
            return null;
        }

        CustomerEntity entity = CustomerEntity.builder()
            .firstName(customerDetail.getFirstName())
            .lastName(customerDetail.getLastName())
            .email(customerDetail.getEmail())
            .phoneNumber(customerDetail.getPhoneNumber())
            .build();
        
        entity.setId(customerDetail.getId());
        return entity;
    }

    public static CustomerDto customerDetailToCustomerDto(CustomerDetail customerDetail) {
        if (Objects.isNull(customerDetail)) {
            return null;
        }

        return CustomerDto.builder()
            .email(customerDetail.getEmail())
            .firstName(customerDetail.getFirstName())
            .lastName(customerDetail.getLastName())
            .phoneNumber(customerDetail.getPhoneNumber())
            .build();
    }
}
