package com.example.myfirstspringapprest.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class WarehouseItem {
    private String name;
    private Boolean available;
}
