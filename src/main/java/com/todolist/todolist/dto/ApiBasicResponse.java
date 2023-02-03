package com.todolist.todolist.dto;

import javax.validation.constraints.NotNull;

public class ApiBasicResponse {
  @NotNull
  private Boolean success;
  @NotNull
  private String message;

  public ApiBasicResponse(Boolean success, String message) {
    this.success = success;
    this.message = message;
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
