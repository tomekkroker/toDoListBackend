package com.todolist.todolist.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    /**
     *
     * @param resource the type of the searched resource
     * @param fieldName field name by which, the type is searched for
     * @param value value of the field
     */
    public NotFoundException(String resource, String fieldName, Object value) {
        super(String.format("%s resource not found with %s: %s", resource, fieldName, value));
    }
}
