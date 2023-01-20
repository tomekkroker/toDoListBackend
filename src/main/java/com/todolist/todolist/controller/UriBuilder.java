package com.todolist.todolist.controller;

import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UriBuilder {

    public static URI getUri(String path, Object value) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/" + path)
                .buildAndExpand(value)
                .toUri();
    }
}
