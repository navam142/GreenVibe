package com.app.greenvibe.mapper;

import com.app.greenvibe.dto.response.CartItemResponseDto;
import com.app.greenvibe.dto.response.CartResponseDto;
import com.app.greenvibe.entity.Cart;
import com.app.greenvibe.entity.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Maps between Cart / CartItem entities and their response DTOs.
 */
@Component
public class CartMapper {

    public CartResponseDto toDto(Cart cart) {
        List<CartItemResponseDto> itemDtos = cart.getCartItems()
                .stream()
                .map(this::toItemDto)
                .toList();

        int totalItems = cart.getCartItems()
                .stream()
                .mapToInt(CartItem::getQuantity)
                .sum();

        return CartResponseDto.builder()
                .cartId(cart.getId())
                .customerId(cart.getCustomer().getId())
                .totalAmount(cart.getTotalAmount())
                .totalItems(totalItems)
                .cartItems(itemDtos)
                .build();
    }

    private CartItemResponseDto toItemDto(CartItem item) {
        BigDecimal price    = item.getProduct().getPrice();
        BigDecimal subtotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));

        return CartItemResponseDto.builder()
                .cartItemId(item.getId())
                .productId(item.getProduct().getId())
                .productName(item.getProduct().getName())
                .imageUrl(item.getProduct().getImageUrl())
                .price(price)
                .quantity(item.getQuantity())
                .subtotal(subtotal)
                .build();
    }
}