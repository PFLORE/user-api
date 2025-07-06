package com.campany.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void registerUser_ShouldReturnCreated() throws Exception {
        String json = "{\n" +
                "    \"name\": \"Juan Rodriguez\",\n" +
                "    \"email\": \"juan@rodriguez.org\",\n" +
                "    \"password\": \"Hunter123\",\n" +
                "    \"phones\": [\n" +
                "        {\n" +
                "            \"number\": \"1234567\",\n" +
                "            \"citycode\": \"1\",\n" +
                "            \"contrycode\": \"57\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void registerUser_DuplicateEmail_ShouldReturnBadRequest() throws Exception {
        String json = "{\n" +
                "    \"name\": \"Juan Rodriguez\",\n" +
                "    \"email\": \"test@rodriguez.org\",\n" +
                "    \"password\": \"Hunter123\",\n" +
                "    \"phones\": [\n" +
                "        {\n" +
                "            \"number\": \"1234567\",\n" +
                "            \"citycode\": \"1\",\n" +
                "            \"contrycode\": \"57\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El correo ya registrado"));
    }

}