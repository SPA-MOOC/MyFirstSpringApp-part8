package com.example.myfirstspringapp.controllers;

import com.example.myfirstspringapp.data.Category;
import com.example.myfirstspringapp.data.Item;
import com.example.myfirstspringapp.entities.CategoryEntity;
import com.example.myfirstspringapp.entities.ItemEntity;
import com.example.myfirstspringapp.exceptions.OperationNotAllowedException;
import com.example.myfirstspringapp.services.StoreService;
import com.example.myfirstspringapp.services.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/warehouse")
public class ItemsFromWarehouseController {
    @Autowired
    WarehouseService service;

    @GetMapping("/")
    public String getItems(Model model) {
        System.out.println("get items from warehouse "+this.service.getItemsFromWarehouse());
        model.addAttribute("items",this.service.getItemsFromWarehouse());
        model.addAttribute("order",this.service.getOrder());
        return "order_items";
    }

    @GetMapping("/addToOrder/{name}")
    public String addToOrder(@PathVariable String name){
        System.out.println("Order "+service.addToOrder(name));
        return "redirect:/warehouse/";
    }

    @GetMapping("/order")
    public String makeOrder(Model model){
        model.addAttribute("ordered",this.service.getOrder().size());
        System.out.println("Order successful? "+this.service.makeOrder());
        model.addAttribute("items",this.service.getItemsFromWarehouse());
        model.addAttribute("order",this.service.getOrder());
        return "order_items";
        //return "redirect:/warehouse/";
    }




    @ExceptionHandler(OperationNotAllowedException.class)
    //@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No requested data")
    public String handleDataError(OperationNotAllowedException e) {
        System.out.println("ERROR "+e.getMessage() );
        return "error";
    }


}
