package com.example.myfirstspringapp;

import com.example.myfirstspringapp.repositories.db.ItemEntityRepository;
import com.example.myfirstspringapp.services.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyFirstSpringAppApplicationTests {

    @Autowired
    ItemEntityRepository repository;

    @Autowired
    StoreService service;

    @Test
    void contextLoads() {
        assertThat(repository).isNotNull();
        assertThat(service).isNotNull();
    }

}
