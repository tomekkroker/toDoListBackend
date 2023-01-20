package com.todolist.todolist.utils;

public class BasicResponse {

    private Boolean success;
    private String message;
    private Object dto;

    public BasicResponse(Boolean success, String message, Object dto) {
        this.success = success;
        this.message = message;
        this.dto = dto;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }
}
