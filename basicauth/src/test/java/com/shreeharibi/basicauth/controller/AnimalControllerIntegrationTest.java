package com.shreeharibi.basicauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shreeharibi.basicauth.model.Animal;
import com.shreeharibi.basicauth.service.AnimalService;
import com.shreeharibi.basicauth.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AnimalController.class)
@Slf4j
class AnimalControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalService animalService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private AppUserService appUserService;

    @Test
    @WithMockUser(username = "admin")
    void getAllAnimals_ShouldReturnOk() throws Exception {
        //arrange
        when(animalService.findAllAnimals()).thenReturn(Collections.singletonList(Animal.builder().id(1L).name("cat").build()));
        //act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/animal/all"))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    void getAllAnimals_ShouldReturnUnAuthorized() throws Exception {
        //arrange
        when(animalService.findAllAnimals()).thenReturn(Collections.singletonList(Animal.builder().id(1L).name("cat").build()));
        //act
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/animal/all"))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    @WithMockUser(username = "shree")
    void saveAnimal_ShouldReturnOk() throws Exception {
        //arrange
        Animal cat = Animal.builder().name("cat").build();
        when(animalService.saveAnimal(cat)).thenReturn(cat);

        //act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/animal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cat)))
                .andExpect(status().isOk());
    }

    @Test
    void saveAnimal_ShouldReturnUnauthorized() throws Exception {
        //arrange
        Animal cat = Animal.builder().name("cat").build();
        when(animalService.saveAnimal(cat)).thenReturn(cat);

        //act
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/animal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cat)))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }
}