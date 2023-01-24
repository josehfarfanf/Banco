package com.globant.micoservice.clienteservice.dao;

import com.globant.micoservice.clienteservice.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
}
