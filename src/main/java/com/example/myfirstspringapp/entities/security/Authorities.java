package com.example.myfirstspringapp.entities.security;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Authorities {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;
    @Basic
    @Column(name = "AUTHORITY", nullable = false, length = 50)
    private String authority;


}
