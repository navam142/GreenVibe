package com.app.greenvibe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponseDto {

    private Long cartId;
    private Long customerId;
    private BigDecimal totalAmount;
    private int totalItems;
    private List<CartItemResponseDto> cartItems;
}
