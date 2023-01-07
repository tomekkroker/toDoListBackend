package com.todolist.todolist.dto.todolist;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Klasa reprezentująca żądanie dodania nowej listy zadań.")

public class ToDoListRequest {

    @ApiModelProperty(notes = "Nazwa")
    @NotNull
    private String name;

    @ApiModelProperty(notes = "Priorytet")
    private String priority;

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
