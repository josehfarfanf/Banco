package com.globant.micoservice.clienteservice.dao;

import com.globant.micoservice.clienteservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
