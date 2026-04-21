package com.app.greenvibe.mapper;

import com.app.greenvibe.dto.response.OrderItemResponseDto;
import com.app.greenvibe.dto.response.OrderResponseDto;
import com.app.greenvibe.entity.Order;
import com.app.greenvibe.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Maps between Order / OrderItem entities and their response DTOs.
 * Order creation logic (building OrderItems from cart) stays in the
 * service layer — the mapper only handles the entity-to-DTO direction.
 */
@Component
public class OrderMapper {

    public OrderResponseDto toDto(Order order) {
        List<OrderItemResponseDto> itemDtos = order.getOrderItems()
                .stream()
                .map(this::toItemDto)
                .toList(); // Java 16+; use .collect(Collectors.toList()) if on Java 11

        return OrderResponseDto.builder()
                .orderId(order.getId())
                .customerId(order.getCustomer().getId())
                .customerName(order.getCustomer().getFullName())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .orderStatus(order.getOrderStatus())
                .orderItems(itemDtos)
                .build();
    }

    private OrderItemResponseDto toItemDto(OrderItem item) {
        return OrderItemResponseDto.builder()
                .productId(item.getProduct().getId())
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .subtotal(item.getSubtotal()) // uses @Transient getter
                .build();
    }
}