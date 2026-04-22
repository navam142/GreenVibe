package com.app.greenvibe.controller;

import com.app.greenvibe.dto.request.ProductRequestDto;
import com.app.greenvibe.dto.response.ProductResponseDto;
import com.app.greenvibe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<ProductResponseDto> addProduct(@RequestPart("product") ProductRequestDto productRequestDto, @RequestPart("image") MultipartFile imageFile ) {
        return ResponseEntity.status(201).body(productService.create(productRequestDto, imageFile));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteById(id));
    }

}
