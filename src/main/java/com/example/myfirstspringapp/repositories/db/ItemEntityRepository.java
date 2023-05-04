package com.example.myfirstspringapp.repositories.db;

import com.example.myfirstspringapp.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Integer> {
    ItemEntity findByName(String name);

    List<ItemEntity> findByCategory_Name(String name);
    @Override
    Optional<ItemEntity> findById(Integer integer);

    @Override
    void deleteById(Integer integer);



}