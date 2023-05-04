package com.example.myfirstspringapp.services;

import com.example.myfirstspringapp.data.Category;
import com.example.myfirstspringapp.data.Item;
import com.example.myfirstspringapp.entities.CategoryEntity;
import com.example.myfirstspringapp.entities.ItemEntity;
import com.example.myfirstspringapp.repositories.Cart;
import com.example.myfirstspringapp.repositories.ItemRepository;
import com.example.myfirstspringapp.repositories.db.CategoryEntityRepository;
import com.example.myfirstspringapp.repositories.db.ItemEntityRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Getter
public class StoreService {
    @Autowired
   // ItemRepository items;
    ItemEntityRepository items;
    @Autowired
    Cart cart;
    @Autowired
    CategoryEntityRepository categories;

//    public List<Item> getItemsFromRepository() {return this.items.getItems();}
//    public List<Item> getCartItems() {return cart.getItems();}
//    public Item getItemFromRepository(int id){
//        return  this.items.getItem(id);
//    }
//    public Category getCategoryFromRepository(String name){
//        return this.items.getCategory(name);
//    }
//    public List<Item> getItemsFromCategory(String category){
//        return this.items.getItemsFromCategory(category);
//    }
//    public void addItemToCart(int id){
//        Item item=this.items.getItem(id);
//        if(item!=null) this.cart.addItem(item);
//    }
public List<ItemEntity> getItemsFromRepository() {return this.items.findAll();}
    public List<ItemEntity> getCartItems() {return cart.getItems();}
    public ItemEntity getItemFromRepository(int id){
        return  this.items.findById(id).isEmpty()?null:this.items.findById(id).get();
    }
    public CategoryEntity getCategoryFromRepository(String name){
        return categories.findByNameIgnoreCase(name);
    }
    public List<ItemEntity> getItemsFromCategory(String category){
        return items.findByCategory_Name(category);
    }
    public void addItemToCart(int id){
        ItemEntity item=this.getItemFromRepository(id);
        if(item!=null) this.cart.addItem(item);
    }

    public void addItemToDB(ItemEntity item){
        this.items.save(item);
    }



    public void editItemInDB(ItemEntity editItem){
        this.items.save(editItem);
    }

    public void deleteItem(int id){
    this.items.deleteById(id);
    }

    public List<ItemEntity> getFilteredItems(Optional<String> name, Optional<Float> price_min, Optional<Float> price_max){
        List<ItemEntity> filteredItems=this.getItemsFromRepository();
        if(name.isPresent()){
            filteredItems=filteredItems.stream().filter(item-> item.getName().contains(name.get()))
                    .collect(Collectors.toList());
        }
        if(price_min.isPresent()){
            filteredItems=filteredItems.stream().filter(item-> item.getPrice()>=price_min.get())
                    .collect(Collectors.toList());
        }
        if(price_max.isPresent()){
            filteredItems=filteredItems.stream().filter(item-> item.getPrice()<=price_max.get())
                    .collect(Collectors.toList());
        }
        return filteredItems;
    }

    public List<Category> getCategoriesWithConstraints(){
        List<CategoryEntity> categoryEntities=categories.findAll();
        List<Category> categoryList=null;
        categoryList=categoryEntities.stream().map(categoryEntity -> new Category(categoryEntity.getName())).toList();
        return categoryList;
    }

    public ItemEntity getItemByName(String name){
        return items.findByName(name);
    }
}
