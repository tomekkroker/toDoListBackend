package com.todolist.todolist.dto.auth;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Klasa reprezentująca żądanie zalogowania się do aplikacji")
public class LoginRequest {
    @ApiModelProperty(notes = "Login użytkownika")
    @NotNull
//    @Size(min = 1, max = 32)
    private String login;

    @ApiModelProperty(notes = "Hasło użytkownika")
    @NotNull
//    @Size(min = 1, max = 64)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
