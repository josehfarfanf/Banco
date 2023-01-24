package com.globant.micoservice.clienteservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidTipoMovimientoException extends RuntimeException{

    public InvalidTipoMovimientoException(String message){
        super(message.concat(" no es un tipo de movimiento válido"));
    }
}
