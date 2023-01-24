package com.globant.micoservice.clienteservice.controller;

import com.globant.micoservice.clienteservice.model.Cliente;
import com.globant.micoservice.clienteservice.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){
        Optional<Cliente> cliente = service.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente){
        Cliente clientDb = service.create(cliente);
        return ResponseEntity.created(URI.create("/cliente/".concat(clientDb.getId().toString())))
                .body(clientDb);
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente>clienteOptional=service.update(cliente,id);
        if (clienteOptional.isPresent()){
            return ResponseEntity.created(URI.create("/cliente/".concat(clienteOptional.get().getId().toString())))
                    .body(clienteOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Cliente> cliente = service.delete(id);
        if(cliente.isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
