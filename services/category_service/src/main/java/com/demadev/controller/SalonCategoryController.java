package com.demadev.controller;

import com.demadev.dto.SalonDto;
import com.demadev.model.Category;
import com.demadev.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/categories/salon-owner")
public class SalonCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category) {
        SalonDto salonDto =new SalonDto();
        salonDto.setId(1L);
        Category newCategory = categoryService.saveCategory(category, salonDto);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long categoryId) throws Exception {
        SalonDto salonDto =new SalonDto();
        salonDto.setId(1L);

        categoryService.deleteCategory(categoryId, salonDto.getId());
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }

}
