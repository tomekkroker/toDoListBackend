package com.todolist.todolist.dto.user;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Klasa reprezentująca żądanie dodania nowego użytkownika.")

public class UserRequest {

    @ApiModelProperty(notes = "Id")
    @NotNull
    private Integer id;

    @ApiModelProperty(notes = "Login")
    @NotNull
    private String login;

    @ApiModelProperty(notes = "Password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
