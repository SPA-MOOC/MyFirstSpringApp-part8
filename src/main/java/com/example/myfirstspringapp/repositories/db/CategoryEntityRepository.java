package com.example.myfirstspringapp.repositories.db;

import com.example.myfirstspringapp.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByNameIgnoreCase(String name);

}