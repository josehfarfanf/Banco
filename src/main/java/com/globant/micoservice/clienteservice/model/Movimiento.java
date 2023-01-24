package com.globant.micoservice.clienteservice.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @NotNull
    private BigDecimal saldo;

    @NotNull
    private String tipo;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
}
