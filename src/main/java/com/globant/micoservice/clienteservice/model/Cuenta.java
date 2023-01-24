package com.globant.micoservice.clienteservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String numero;

    @NotEmpty
    private String tipo;

    @NotNull
    private BigDecimal saldo;

    @NotNull
    private Boolean estado;

    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    @NotNull
    private Cliente cliente;

}
