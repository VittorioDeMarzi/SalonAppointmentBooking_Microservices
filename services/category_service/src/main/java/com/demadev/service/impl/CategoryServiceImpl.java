package com.demadev.service.impl;

import com.demadev.dto.SalonDto;
import com.demadev.model.Category;
import com.demadev.repository.CategoryRepository;
import com.demadev.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public Category saveCategory(Category category, SalonDto salonDto) {

        Category newCategory = new Category();
        newCategory.setName(category.getName());
        newCategory.setSalonId(salonDto.getId());
        newCategory.setImage(category.getImage());
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category getCategoryById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElse(null);

        if (category == null) {
            throw new RuntimeException("Category not found for ID: " + categoryId);
        }

        return category;
    }

    @Override
    public Set<Category> getAllCategoriesBySalonId(Long salonId) {
        return categoryRepository.findBySalonId(salonId);
    }

    @Override
    public void deleteCategory(Long categoryId, Long salonId) throws Exception {
        Category category = getCategoryById(categoryId);
        if(category.getSalonId().equals(salonId)) {
            throw new Exception("You don't have permission to delete this category");
        }
        categoryRepository.deleteById(categoryId);
    }
}
