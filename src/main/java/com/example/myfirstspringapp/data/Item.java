package com.example.myfirstspringapp.data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Item {
    @Size(min=3, max=10, message = "{err.string.length}")
    private String name;
    @DecimalMin(value = "0.1", message = "{err.float.value}")
    private float price;
    @Valid
    private Category category;
}
