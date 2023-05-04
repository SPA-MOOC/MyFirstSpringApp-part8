package com.example.myfirstspringapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Operation not allowed")
public class OperationNotAllowedException extends RuntimeException {
        public OperationNotAllowedException(String msg){
            super(msg);
        }
    }


