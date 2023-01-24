package com.globant.micoservice.clienteservice.service;

import com.globant.micoservice.clienteservice.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    public List<Cliente> findAll();

    public Optional<Cliente> findById(Long id);

    public Cliente create(Cliente cliente);

    public Optional<Cliente> update(Cliente cliente, Long id);

    public Optional<Cliente> delete(Long id);

}
