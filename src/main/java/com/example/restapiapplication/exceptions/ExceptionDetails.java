package com.example.restapiapplication.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ExceptionDetails {

    private String message;
    private Date timeStamp;
}
