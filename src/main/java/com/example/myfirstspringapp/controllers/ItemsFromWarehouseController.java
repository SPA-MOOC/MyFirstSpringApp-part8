package com.example.myfirstspringapp.controllers;

import com.example.myfirstspringapp.data.Category;
import com.example.myfirstspringapp.data.Item;
import com.example.myfirstspringapp.entities.CategoryEntity;
import com.example.myfirstspringapp.entities.ItemEntity;
import com.example.myfirstspringapp.exceptions.OperationNotAllowedException;
import com.example.myfirstspringapp.services.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    StoreService service;

    @GetMapping({"/", "/reset_filters"})
    public String getItems(Model model) {
        System.out.println("get items in controller "+this.service.getItemsFromRepository());
        model.addAttribute("items",this.service.getItemsFromRepository());
        return "items";
    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model) {
        System.out.println("get one item in controller "+id+" "+this.service.getItemFromRepository(id));
        model.addAttribute("index",id);
        model.addAttribute("item",this.service.getItemFromRepository(id));
        return "item";
    }

    @GetMapping("/category")
    public String getItems(@RequestParam("name") String category, Model model) {
        System.out.println("get items from category "+category+" in controller "+this.service.getItemsFromCategory(category));
        model.addAttribute("items",this.service.getItemsFromCategory(category));
        model.addAttribute("category",category);
        return "items";
    }

    @GetMapping("/manage/add")
    public String manage(Model model){
        model.addAttribute("categories",this.service.getCategoriesWithConstraints());//getItems().getCategories());
        model.addAttribute("newItem", new Item());
        //model.addAttribute("newItemCat", new Category());
        return "add_item";
    }

    @PostMapping("/manage/add")
    public String managePost(@Valid @ModelAttribute("newItem") Item newItem, /*Errors*/ BindingResult result, Model model) {
        System.out.println("resuult "+result.hasErrors());
        if (result.hasErrors()) {
            System.out.println(result.getErrorCount());
            result.getAllErrors().forEach(el-> System.out.println(el));
            model.addAttribute("categories",this.service.getCategoriesWithConstraints());//getItems().getCategories());
            return "add_item";
        }
        CategoryEntity categoryEntity=service.getCategoryFromRepository(newItem.getCategory().getCat_name());
        ItemEntity newItemEnt=new ItemEntity();newItemEnt.setName(newItem.getName());newItemEnt.setPrice(newItem.getPrice());newItemEnt.setCategory(categoryEntity);
        this.service.addItemToDB(newItemEnt);//getItems().addItem(newItem);
        return "redirect:/items/";

    }

    @GetMapping("/manage/edit/{id}")
    public String manageEdit(Model model, @PathVariable("id") int id){
        model.addAttribute("categories",this.service.getCategoriesWithConstraints());//getItems().getCategories());
        ItemEntity item4Edit=this.service.getItemFromRepository(id);//getItemFromRepository(id);
        Item item=new Item(item4Edit.getName(),item4Edit.getPrice(),new Category(item4Edit.getCategory().getName()));
        model.addAttribute("item4Edit", item);
        model.addAttribute("id",id);
        return "edit_item";
    }

    @PostMapping("/manage/edit/{id}")
    public String manageEditPost(@PathVariable("id") int id,@Valid @ModelAttribute("item4Edit") Item item, /*Errors*/ BindingResult result, Model model) {
        System.out.println("resuult "+result.hasErrors());
        if (result.hasErrors()) {
            System.out.println(result.getErrorCount());
            result.getAllErrors().forEach(el-> System.out.println(el));
            model.addAttribute("categories",this.service.getCategories());//getItems().getCategories());
            return "edit_item";
        }
        ItemEntity item4Edit=this.service.getItemFromRepository(id);
        item4Edit.setName(item.getName());item4Edit.setPrice(item.getPrice());item4Edit.setCategory(service.getCategoryFromRepository(item.getCategory().getCat_name()));
        this.service.editItemInDB(item4Edit);//getItems().editItem(item,id);
        return "redirect:/items/";
    }

    @GetMapping(value={"/manage/delete/{id}"})
    public String deleteItem(@PathVariable int id, Model model) {
        try {
            this.service.deleteItem(id);//getItems().removeItem(id);
            System.out.println("remove item in controller " + this.service.getItems());
        }catch(Exception e){
            try {
                throw new OperationNotAllowedException(String.valueOf(id));
            }
            catch (OperationNotAllowedException ex){
                System.out.println("Operation not allowed "+ex.getMessage());
                    model.addAttribute("errorMsg","Item cannot be deleted "+ex.getMessage());
                    return "error";
                }
        }
        return "redirect:/items/";
    }

    @GetMapping(value={"/filter_items"})
    public String filterItems(Model model,@RequestParam("name") Optional<String> name, @RequestParam("price_min") Optional<Float> price_min, @RequestParam("price_max") Optional<Float> price_max) {
        System.out.println("filter items in controller: name "+name.isPresent()+", price min "+price_min.isPresent()+", price max "+price_max.isPresent());
        model.addAttribute("items",this.service.getFilteredItems(name,price_min,price_max));//getItems().getFilteredItems(name,price_min,price_max));
        return "items";
    }


    @ExceptionHandler(OperationNotAllowedException.class)
    //@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No requested data")
    public String handleDataError(OperationNotAllowedException e) {
        System.out.println("ERROR "+e.getMessage() );
        return "error";
    }


}
