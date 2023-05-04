package com.example.myfirstspringapp;

import com.example.myfirstspringapp.entities.CategoryEntity;
import com.example.myfirstspringapp.entities.ItemEntity;
import com.example.myfirstspringapp.repositories.db.CategoryEntityRepository;
import com.example.myfirstspringapp.repositories.db.ItemEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class DataFromDBTests {
    @Autowired
    CategoryEntityRepository categoryRepositoryDB;

    @Autowired
    private ItemEntityRepository itemRepository;


    @Test
    public void dbTest(){
        assertThat(itemRepository).isNotNull();
        assertThat(categoryRepositoryDB).isNotNull();
    }

    @Test
    public void saveNewCatgory() {
        List<CategoryEntity> allCats=categoryRepositoryDB.findAll();
        int catSize=allCats.size();
        CategoryEntity category=new CategoryEntity();
        category.setName("sweets");
        categoryRepositoryDB.save(category);

        CategoryEntity catFound=categoryRepositoryDB.findByNameIgnoreCase("sweets");
        allCats=categoryRepositoryDB.findAll();
        assertThat(catFound.getName())
                .isEqualTo(category.getName());
        assertThat(allCats.size()).isEqualTo(catSize+1);
    }

    @Test
    public void saveNewItem() {
        CategoryEntity category=new CategoryEntity();
        category.setName("sweets");
        categoryRepositoryDB.save(category);
        ItemEntity item=new ItemEntity();
        item.setCategory(category);item.setName("candy");item.setPrice(1.11f);
        itemRepository.save(item);

        ItemEntity itemFound=itemRepository.findByName("candy");
        assertThat(itemFound.getName())
                .isEqualTo(item.getName());
    }

}
