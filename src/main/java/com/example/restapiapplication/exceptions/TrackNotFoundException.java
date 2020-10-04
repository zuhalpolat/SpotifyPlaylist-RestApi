package com.example.restapiapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TrackNotFoundException extends RuntimeException{
    public TrackNotFoundException(String message){
        super(message);
    }
}
