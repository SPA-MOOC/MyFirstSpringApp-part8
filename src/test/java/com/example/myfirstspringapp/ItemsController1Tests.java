package com.example.myfirstspringapp;

import com.example.myfirstspringapp.repositories.db.ItemEntityRepository;
import com.example.myfirstspringapp.services.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
//@WebMvcTest(ItemsController.class)
@AutoConfigureMockMvc
@SpringBootTest
class ItemsController1Tests {

   @Autowired
    private MockMvc mockMvc;

    @Test
    public void testItemsPage() throws Exception {
        mockMvc.perform(get("/items/"))
                .andExpect(status().isOk())
                .andExpect(view().name("items"))
                .andExpect(content().string(
                        containsString("Items in store")));
    }
    @Test
    public void shouldReturnExpectedDataFromRepo() throws Exception {
        mockMvc
                .perform(get("/items/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Items in store")));
    }

    @Test
    public void categoryWithRequestParamTest() throws Exception {
        mockMvc.perform(get("/items/category").param("name", "dairy"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Category:dairy")));

    }

    @Test
    public void shouldReturnOneItemWithPathVariableTest() throws Exception {
        mockMvc.perform(get("/items/{id}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("item"))
                .andExpect(content().string(containsString("yoghurt")));

    }



}
