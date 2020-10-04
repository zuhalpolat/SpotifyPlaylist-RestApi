package com.example.restapiapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;
import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(PlaylistNotFoundException.class)
    public ResponseEntity<Object> PlaylistNotFoundException(PlaylistNotFoundException exception){
        ExceptionDetails details = new ExceptionDetails("Playlist Not Found!!", new Date());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<Object> TrackNotFoundException(TrackNotFoundException exception){
        ExceptionDetails details = new ExceptionDetails("Track Not Found!!", new Date());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> UserNotFoundException( UserNotFoundException exception){
        ExceptionDetails details = new ExceptionDetails("User Not Found!!", new Date());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> internalServerErrorException(InternalServerErrorException exception){
        ExceptionDetails details = new ExceptionDetails(exception.getMessage(), new Date());

        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotCreatedException.class)
    public ResponseEntity<Object> NotCreatedException(NotCreatedException exception){
        ExceptionDetails details = new ExceptionDetails("Couldn't created!!", new Date());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Object> invalidParameterException(InvalidParameterException exception){
        ExceptionDetails details = new ExceptionDetails(exception.getMessage(), new Date());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
