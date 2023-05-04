package com.example.myfirstspringapp.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class WarehouseItem {
    private String name;
    private Boolean available;
}
