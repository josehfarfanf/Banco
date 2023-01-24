package com.globant.micoservice.clienteservice.dao;

import com.globant.micoservice.clienteservice.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
}
