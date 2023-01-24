package com.globant.micoservice.clienteservice.service;

import com.globant.micoservice.clienteservice.model.Movimiento;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovimientoService {

    public List<Movimiento> findAll();

    public Optional<Movimiento> findById(Long id);

    public Movimiento create(Movimiento movimiento);

    public Optional<Movimiento> update(Movimiento movimiento, Long id);

    public Optional<Movimiento> delete(Long id);

    public List<Movimiento> findByFechasAndCliente(Date fromDate, Date toDate, Long clienteId);
}
