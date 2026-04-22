package com.app.greenvibe.controller;

import com.app.greenvibe.dto.request.CategoryRequestDto;
import com.app.greenvibe.dto.response.CategoryResponseDto;
import com.app.greenvibe.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @RequestMapping("/create")
    public ResponseEntity<CategoryResponseDto> create(@RequestBody CategoryRequestDto request) {
        return ResponseEntity.status(201).body(categoryService.create(request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<CategoryResponseDto>> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deleteById(id));
    }
}
