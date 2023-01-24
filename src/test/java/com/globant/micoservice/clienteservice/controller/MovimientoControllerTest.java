package com.globant.micoservice.clienteservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.micoservice.clienteservice.model.Cuenta;
import com.globant.micoservice.clienteservice.model.Movimiento;
import com.globant.micoservice.clienteservice.service.Impl.MovimientoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MovimientoController.class)
class MovimientoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MovimientoServiceImpl movimientoService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        objectMapper=new ObjectMapper();
    }

    @Test
    void create() throws Exception {
        Cuenta cuenta=new Cuenta(1L,"001","AHORRO",new BigDecimal(100),true,null);
        Movimiento movimiento=new Movimiento(1L,new Date(),new BigDecimal(101),"DEBITO",cuenta);
        when(movimientoService.create(any())).thenReturn(movimiento);

        mvc.perform(post("/movimiento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movimiento)))
                .andExpect(status().isCreated());
    }

    @Test
    void findById() throws Exception {
        when(movimientoService.findById(any())).thenReturn(Optional.empty());

        ResultActions resultActions = mvc.perform(get("/movimiento/1"))
                .andExpect(status().isNotFound());
    }
}