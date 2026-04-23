package com.app.greenvibe.controller;

import com.app.greenvibe.dto.response.CartResponseDto;
import com.app.greenvibe.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.getOrCreateCart(customerId));
    }

    @PostMapping("/{customerId}/add")
    public ResponseEntity<CartResponseDto> addItem(
            @PathVariable Long customerId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addItem(customerId, productId, quantity));
    }

    @PutMapping("/{customerId}/update")
    public ResponseEntity<CartResponseDto> updateItem(
            @PathVariable Long customerId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateItem(customerId, productId, quantity));
    }

    @DeleteMapping("/{customerId}/remove/{productId}")
    public ResponseEntity<String> removeItem(
            @PathVariable Long customerId,
            @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeItem(customerId, productId));
    }

    @DeleteMapping("/{customerId}/clear")
    public ResponseEntity<String> clearCart(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.clearCart(customerId));
    }
}
