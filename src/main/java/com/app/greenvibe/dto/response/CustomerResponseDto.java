package com.app.greenvibe.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String pincode;
    private LocalDateTime createdAt;
}
