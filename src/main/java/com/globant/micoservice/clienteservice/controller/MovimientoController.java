package com.globant.micoservice.clienteservice.controller;

import com.globant.micoservice.clienteservice.model.Movimiento;
import com.globant.micoservice.clienteservice.service.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimiento")
public class MovimientoController {

    @Autowired
    MovimientoService service;

    @GetMapping
    public ResponseEntity<List<Movimiento>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movimiento> findById(@PathVariable Long id){
        Optional<Movimiento> movimientoOptional = service.findById(id);
        if(movimientoOptional.isPresent()){
            return ResponseEntity.ok(movimientoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Movimiento> create(@Valid @RequestBody Movimiento Movimiento){
        Movimiento movimientoDb = service.create(Movimiento);
        return ResponseEntity.created(URI.create("/movimiento/".concat(movimientoDb.getId().toString())))
                .body(movimientoDb);
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Movimiento> update(@PathVariable Long id, @RequestBody Movimiento Movimiento){
        Optional<Movimiento>movimientoOptional=service.update(Movimiento,id);
        if (movimientoOptional.isPresent()){
            return ResponseEntity.created(URI.create("/Movimiento/".concat(movimientoOptional.get().getId().toString())))
                    .body(movimientoOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Movimiento> Movimiento = service.delete(id);
        if(Movimiento.isPresent()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/consulta")
    public ResponseEntity<List<Movimiento>>findByFechasAndCliente(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
            @RequestParam Long clienteId
            ){
        List<Movimiento> movimientos=service.findByFechasAndCliente(fromDate,toDate,clienteId);
        if(movimientos.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(movimientos);
        }
    }
}
