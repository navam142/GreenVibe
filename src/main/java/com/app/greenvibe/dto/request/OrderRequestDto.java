package com.app.greenvibe.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    @NotNull(message = "Customer id is required")
    private Long customerId;

    private String shippingAddress;
    private String city;
    private String pincode;
}
