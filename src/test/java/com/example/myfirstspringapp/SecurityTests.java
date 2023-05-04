package com.example.myfirstspringapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class SecurityTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkPermitAll() throws Exception {
        mockMvc.perform(get("/items/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "user1")
    @Test
    public void checkOnlyAdminAsUser() throws Exception {
        mockMvc.perform(get("/items/manage/add"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "admin", roles={"ADMIN"})
    @Test
    public void checkOnlyAdminAsAdmin() throws Exception {
        mockMvc.perform(get("/items/manage/add"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
