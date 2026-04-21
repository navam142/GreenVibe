package com.app.greenvibe.dto.response;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemResponseDto {
    private Long cartItemId;
    private Long productId;
    private String productName;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;
    private BigDecimal subtotal;
}
