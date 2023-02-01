package com.todolist.todolist.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class LocalTimeProviderImplementation extends LocalTimeProvider {
    @Value("${app.timeZone}")
    private String timezone;

    public LocalDateTime localDateTimeNow() {
        if (timezone == null) {
            return LocalDateTime.now();
        }
        return LocalDateTime.now(ZoneId.of(timezone));
    }
}
