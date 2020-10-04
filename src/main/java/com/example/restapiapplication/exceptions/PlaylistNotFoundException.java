package com.example.restapiapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlaylistNotFoundException extends RuntimeException{

    public PlaylistNotFoundException(String message){
        super(message);
    }
}
