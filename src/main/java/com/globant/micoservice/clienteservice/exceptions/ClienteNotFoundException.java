package com.globant.micoservice.clienteservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends RuntimeException{

    public ClienteNotFoundException(String message){
        super(message.concat(" no existe"));
    }
}
