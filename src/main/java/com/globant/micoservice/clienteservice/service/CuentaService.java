package com.globant.micoservice.clienteservice.service;

import com.globant.micoservice.clienteservice.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaService {

    public List<Cuenta> findAll();

    public Optional<Cuenta> findById(Long id);

    public Cuenta create(Cuenta cuenta);

    public Optional<Cuenta> update(Cuenta cuenta, Long id);

    public Optional<Cuenta> delete(Long id);
}
