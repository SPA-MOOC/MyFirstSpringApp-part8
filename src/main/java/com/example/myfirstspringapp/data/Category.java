package com.example.myfirstspringapp.data;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @NotBlank(message = "{err.required}")
    private String cat_name;
}
