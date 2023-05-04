package com.example.myfirstspringapp.entities.security;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;
    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;
    @Basic
    @Column(name = "ENABLED", nullable = false)
    private byte enabled;

}
