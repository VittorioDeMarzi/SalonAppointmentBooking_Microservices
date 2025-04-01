package com.demadev.service;

import com.demadev.dto.SalonDto;
import com.demadev.model.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    Category saveCategory(Category category, SalonDto salonDto);
    Category getCategoryById(Long id);
    Set<Category> getAllCategoriesBySalonId(Long SalonId);
    void deleteCategory(Long categoryId, Long salonId) throws Exception;

}
