package com.globant.micoservice.clienteservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CuentaNotFoundException extends RuntimeException{

    public CuentaNotFoundException(String message){
        super(message.concat(" no existe"));
    }
}
