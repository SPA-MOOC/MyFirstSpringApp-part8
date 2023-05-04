package com.example.myfirstspringapp.entities.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {
    List<Authorities> findByUsername(String username);
}