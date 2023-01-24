package com.globant.micoservice.clienteservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String contrasena;

    @NotNull
    private Boolean estado;

    @ManyToOne()
    @JoinColumn(name = "persona_id")
    @NotNull
    @Valid
    private Persona persona;
}
