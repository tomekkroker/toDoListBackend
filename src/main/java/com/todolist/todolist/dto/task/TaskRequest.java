package com.todolist.todolist.dto.task;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import lombok.Builder;

@Builder
@ApiModel(description = "Klasa reprezentująca żądanie dodania nowego zadania.")
public class TaskRequest {

    @ApiModelProperty(notes = "Id")
    @NotNull
    private Integer id;

    @ApiModelProperty(notes = "Nazwa")
    @NotNull
    private String name;

    @ApiModelProperty(notes = "Priorytet")
    private String priority;

    @ApiModelProperty(notes = "Deadline")
    private LocalDate deadline;

    @ApiModelProperty(notes = "Opis")
    private String description;

    @ApiModelProperty(notes = "Id listy")
    private Integer listId;

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

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }
}
