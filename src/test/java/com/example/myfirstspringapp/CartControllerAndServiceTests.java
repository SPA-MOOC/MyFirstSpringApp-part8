package com.example.myfirstspringapp;

import com.example.myfirstspringapp.entities.CategoryEntity;
import com.example.myfirstspringapp.entities.ItemEntity;
import com.example.myfirstspringapp.repositories.Cart;
import com.example.myfirstspringapp.services.StoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerAndServiceTests {
    @MockBean
    private StoreService service;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(value = "user1")
    @Test
    public void shouldReturnExpectedDataFromServiceNoItemsInCart() throws Exception {
        Cart cart=new Cart();
        when(service.getCart()).thenReturn(cart);
        mockMvc
                .perform(get("/cart/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("There is no items in cart")));
    }
    @WithMockUser(value = "user1")
    @Test
    public void shouldReturnExpectedDataFromServiceItemsInCart() throws Exception {
        Cart cart=new Cart();
        CategoryEntity categoryEntity=new CategoryEntity();categoryEntity.setName("bakery");
        ItemEntity item=new ItemEntity();item.setCategory(categoryEntity);item.setPrice(12.44f);
        item.setName("bread");
        cart.addItem(item);
        when(service.getCart()).thenReturn(cart);
        mockMvc
                .perform(get("/cart/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Items in cart")));
    }

}
