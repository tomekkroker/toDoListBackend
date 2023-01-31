package com.todolist.todolist.utils;

public class BasicResponse {

    private Boolean success;
    private String message;

    public BasicResponse(Boolean success, String message, Object dto) {
        this.success = success;
        this.message = message;
    }

    public BasicResponse(boolean success, String task_deleted_successfully) {
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
}
