package com.globant.micoservice.clienteservice.controller;

import com.globant.micoservice.clienteservice.model.Cuenta;

import com.globant.micoservice.clienteservice.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    CuentaService service;

    @GetMapping
    public ResponseEntity<List<Cuenta>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cuenta> findById(@PathVariable Long id){
        Optional<Cuenta> cliente = service.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cuenta> create(@Valid @RequestBody Cuenta cuenta){
        Cuenta cuentaDb = service.create(cuenta);
        return ResponseEntity.created(URI.create("/cuenta/".concat(cuentaDb.getId().toString())))
                .body(cuentaDb);
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Cuenta> update(@PathVariable Long id, @RequestBody Cuenta cuenta){
        Optional<Cuenta>cuentaOptional=service.update(cuenta,id);
        if (cuentaOptional.isPresent()){
            return ResponseEntity.created(URI.create("/cuenta/".concat(cuentaOptional.get().getId().toString())))
                    .body(cuentaOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Cuenta> cuenta = service.delete(id);
        if(cuenta.isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
