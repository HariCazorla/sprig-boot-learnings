package com.shreeharibi.junitex.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NoCarsFoundException extends ResponseStatusException {
    public NoCarsFoundException(String message) {
        super(HttpStatus.NO_CONTENT, message);
    }

    @Override
    public HttpHeaders getResponseHeaders() {
        return super.getResponseHeaders();
    }
}
