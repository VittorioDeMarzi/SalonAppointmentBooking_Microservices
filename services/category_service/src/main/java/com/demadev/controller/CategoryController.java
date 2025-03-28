package com.demadev.controller;

import com.demadev.model.Category;
import com.demadev.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{salonId")
    public ResponseEntity<Set<Category>> getCategoriesBySalon(@PathVariable Long salonId) throws Exception {
        Set<Category> categories = categoryService.getAllCategoriesBySalonId(salonId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId")
    public ResponseEntity<Category> getCategoriesById(@PathVariable Long categoryId) throws Exception {
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }
}
