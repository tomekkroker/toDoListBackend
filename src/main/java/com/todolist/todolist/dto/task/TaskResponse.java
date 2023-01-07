package com.todolist.todolist.dto.task;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

@ApiModel(description = "Klasa reprezentująca odpowiedź zawierającą informacje o liście zadań.")

public class TaskResponse {

    @NotNull
    @ApiModelProperty(notes = "ID zadania")
    private Integer id;

    @NotNull
    @ApiModelProperty(notes = "Nazwa")
    private String name;

    @ApiModelProperty(notes = "Priorytet")
    private String priority;

    @ApiModelProperty(notes = "Deadline")
    private LocalDate deadline;

    @ApiModelProperty(notes = "Opis")
    private String description;

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

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
