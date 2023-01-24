package com.globant.micoservice.clienteservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Persona implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String identificacion;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String genero;

    @NotEmpty
    private String direccion;

    @NotEmpty
    private String telefono;

    @NotNull
    private Boolean estado;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date nacimiento;

}
