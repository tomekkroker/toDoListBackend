package com.todolist.todolist.dto.todolist;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Klasa reprezentująca żądanie dodania nowej listy zadań.")

public class ListRequest {

    @ApiModelProperty(notes = "Id")
    @NotNull
    private Integer id;

    @ApiModelProperty(notes = "Nazwa")
    @NotNull
    private String name;

    @ApiModelProperty(notes = "Priorytet")
    private String priority;

    @ApiModelProperty(notes = "Login usera")
    private String userLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getUserLogin() {return userLogin; }

    public void setUserLogin(String userLogin) { this.userLogin = userLogin; }
}
