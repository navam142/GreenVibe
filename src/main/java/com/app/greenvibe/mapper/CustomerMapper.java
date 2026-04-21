package com.app.greenvibe.mapper;

import com.app.greenvibe.dto.request.CustomerRegisterRequestDto;
import com.app.greenvibe.dto.response.CustomerResponseDto;
import com.app.greenvibe.entity.Customer;
import org.springframework.stereotype.Component;

/**
 * Maps between Customer entity and its DTOs.
 * Password encoding is intentionally excluded here — it must be
 * handled in the service layer via PasswordEncoder before persisting.
 */
@Component
public class CustomerMapper {

    /**
     * Converts a registration request DTO into a Customer entity.
     * Note: password is stored as-is here; the service layer must
     * encode it before calling save().
     */
    public Customer toEntity(CustomerRegisterRequestDto dto) {
        Customer customer = new Customer();
        customer.setFullName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword()); // encode in service before save
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setAddress(dto.getAddress());
        customer.setCity(dto.getCity());
        customer.setPincode(dto.getPincode());
        return customer;
    }

    /**
     * Converts a Customer entity into a response DTO.
     * Sensitive fields (password, role) are never exposed.
     */
    public CustomerResponseDto toDto(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .city(customer.getCity())
                .pincode(customer.getPincode())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}