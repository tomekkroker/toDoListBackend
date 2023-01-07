package com.todolist.todolist.dto.todolist;


import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Klasa reprezentująca odpowiedź zawierającą informacje o liście list zadań.")
public class ToDoListResponse {

    @NotNull
    @ApiModelProperty(notes = "ID zadania")
    private Integer id;

    @NotNull
    @ApiModelProperty(notes = "Nazwa")
    private String name;

    @ApiModelProperty(notes = "Priorytet")
    private String priority;

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
}
