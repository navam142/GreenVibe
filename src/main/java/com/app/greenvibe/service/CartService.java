package com.app.greenvibe.service;

import com.app.greenvibe.dto.response.CartResponseDto;
import com.app.greenvibe.entity.Cart;
import com.app.greenvibe.entity.CartItem;
import com.app.greenvibe.entity.Customer;
import com.app.greenvibe.entity.Product;
import com.app.greenvibe.exception.OutOfStockException;
import com.app.greenvibe.exception.ResourceNotFoundException;
import com.app.greenvibe.mapper.CartMapper;
import com.app.greenvibe.repository.CartItemRepository;
import com.app.greenvibe.repository.CartRepository;
import com.app.greenvibe.repository.CustomerRepository;
import com.app.greenvibe.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CartMapper cartMapper;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CartResponseDto getOrCreateCart(Long customerId) {
        Customer customer = getCustomerOrThrow(customerId);
        Cart cart = cartRepository.findByCustomerId(customerId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setCustomer(customer);
            return cartRepository.save(newCart);
        });
        return cartMapper.toDto(cart);
    }

    public CartResponseDto addItem(Long customerId, Long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        Customer customer = getCustomerOrThrow(customerId);
        Cart cart = cartRepository.findByCustomerId(customerId).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setCustomer(customer);
            return cartRepository.save(newCart);
        });
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId).orElseGet(() -> {
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(0);
            return newCartItem;
        });

        int newQty = cartItem.getQuantity() + quantity;
        if (newQty > product.getStockQuantity()) {
            throw new OutOfStockException(product.getName(), newQty, product.getStockQuantity());
        }

        cartItem.setQuantity(newQty);
        cartItemRepository.save(cartItem);

        cart.updateTotalAmount();
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDto(savedCart);
    }

    public CartResponseDto getCart(Long customerId) {
        Cart cart = getCartByCustomerIdOrThrow(customerId);
        return cartMapper.toDto(cart);
    }

    public String removeItem(Long customerId, Long productId) {

        Cart cart = getCartByCustomerIdOrThrow(customerId);

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "productId", productId));

        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        return "Item " + cartItem.getProduct().getName() + " removed from cart";
    }

    public CartResponseDto updateItem(Long customerId, Long productId, int quantity) {
        Cart cart = getCartByCustomerIdOrThrow(customerId);

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "productId", productId));

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        int newQty = quantity;
        if (newQty > product.getStockQuantity()) {
            throw new OutOfStockException(product.getName(), newQty, product.getStockQuantity());
        }
        cartItem.setQuantity(newQty);
        cartItemRepository.save(cartItem);
        cart.updateTotalAmount();
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDto(savedCart);
    }

    private @NonNull Customer getCustomerOrThrow(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
    }

    private @NonNull Cart getCartByCustomerIdOrThrow(Long customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "customerId", customerId));
    }

    public String clearCart(Long customerId) {
        Cart cart = getCartByCustomerIdOrThrow(customerId);
        cart.getCartItems().clear();
        cartItemRepository.deleteAll(cart.getCartItems());
        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);
        return "Cart cleared";
    }
}

