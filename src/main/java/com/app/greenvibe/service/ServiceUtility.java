package com.app.greenvibe.service;

import com.app.greenvibe.entity.Cart;
import com.app.greenvibe.entity.Customer;
import com.app.greenvibe.entity.Order;
import com.app.greenvibe.exception.ResourceNotFoundException;
import com.app.greenvibe.repository.CartRepository;
import com.app.greenvibe.repository.CustomerRepository;
import com.app.greenvibe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceUtility {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public @NonNull Customer getCustomerOrThrow(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
    }

    public @NonNull Cart getCartByCustomerIdOrThrow(Long customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "customerId", customerId));
    }



}
