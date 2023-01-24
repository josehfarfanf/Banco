package com.globant.micoservice.clienteservice.dao;

import com.globant.micoservice.clienteservice.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {

    @Query(value = "from Movimiento m where m.cuenta.cliente.id = :clienteId AND m.fecha BETWEEN :fromDate AND :toDate")
    public List<Movimiento> findBetweenDateAndUser(@Param("fromDate")Date fromDate, @Param("toDate")Date toDate, @Param("clienteId")Long clienteId);
}
