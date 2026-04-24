package com.app.greenvibe.service;

import com.app.greenvibe.dto.response.OrderResponseDto;
import com.app.greenvibe.entity.*;
import com.app.greenvibe.exception.GreenVibeException;
import com.app.greenvibe.exception.OutOfStockException;
import com.app.greenvibe.exception.ResourceNotFoundException;
import com.app.greenvibe.mapper.OrderMapper;
import com.app.greenvibe.repository.CustomerRepository;
import com.app.greenvibe.repository.OrderRepository;
import com.app.greenvibe.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final CartService cartService;
    private final ServiceUtility serviceUtility;
    private final CustomerRepository customerRepository;

    @Transactional
    public OrderResponseDto placeOrder() {
        Long customerId = getAuthenticatedCustomerId();
        Cart cart = serviceUtility.getCartByCustomerIdOrThrow(customerId);

        if (cart.getCartItems().isEmpty()) {
            throw new GreenVibeException("Cannot place order with an empty cart", HttpStatus.BAD_REQUEST);
        }

        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(cart.getTotalAmount());
        order.setOrderStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();

            // Re-verify stock before finalizing order
            if (product.getStockQuantity() < cartItem.getQuantity()) {
                throw new OutOfStockException(product.getName(), cartItem.getQuantity(), product.getStockQuantity());
            }

            // Deduct stock
            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice());
            return orderItem;
        }).toList();

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);

        // Clear the cart after successful order placement
        cartService.clearCart(customerId);

        return orderMapper.toDto(savedOrder);
    }

    @Transactional
    public OrderResponseDto getOrderById(Long orderId) {
        Long customerId = getAuthenticatedCustomerId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        // Ensure the order actually belongs to the requesting customer
        if (!order.getCustomer().getId().equals(customerId)) {
            throw new ResourceNotFoundException("Order", "id", orderId);
        }

        return orderMapper.toDto(order);
    }

    @Transactional
    public OrderResponseDto cancelOrder(Long orderId) {
        Long customerId = getAuthenticatedCustomerId();
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        // Ensure the order actually belongs to the requesting customer
        if (!order.getCustomer().getId().equals(customerId)) {
            throw new ResourceNotFoundException("Order", "id", orderId);
        }

        if (order.getOrderStatus() != OrderStatus.PENDING) {
            throw new GreenVibeException("Only pending orders can be cancelled", HttpStatus.BAD_REQUEST);
        }

        // Restock products
        order.getOrderItems().forEach(item -> {
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
            productRepository.save(product);
        });

        order.setOrderStatus(OrderStatus.CANCELLED);
        Order cancelledOrder = orderRepository.save(order);
        return orderMapper.toDto(cancelledOrder);
    }

    @Transactional
    public String updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        return "Order status updated to " + orderStatus;
    }

    private Long getAuthenticatedCustomerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            throw new GreenVibeException("Unauthenticated request", HttpStatus.UNAUTHORIZED);
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof Customer customer) {
            return customer.getId();
        }

        if (principal instanceof UserDetails userDetails) {
            return customerRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "email", userDetails.getUsername()))
                    .getId();
        }

        if (principal instanceof String username && !"anonymousUser".equals(username)) {
            return customerRepository.findByEmail(username)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "email", username))
                    .getId();
        }

        throw new GreenVibeException("Unauthenticated request", HttpStatus.UNAUTHORIZED);
    }

}
