package com.demadev.repository;

import com.demadev.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findBySalonId(Long salonId);

}
