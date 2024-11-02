package com.tomcat.Loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class ResourceAlreadyAvailable extends RuntimeException {
    public ResourceAlreadyAvailable(String message) {
        super(message);
    }
}
