package com.todolist.todolist.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public abstract class LocalTimeProvider {
    public abstract LocalDateTime localDateTimeNow();
    public final LocalDateTime localDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    public final LocalDate localDateNow() {
        return localDateTimeNow().toLocalDate();
    }
    public final Date localDate() {
        return Date.from(localDateTimeNow().atZone(ZoneId.systemDefault()).toInstant());
    }
}
