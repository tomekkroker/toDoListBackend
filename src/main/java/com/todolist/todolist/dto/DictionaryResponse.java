package com.todolist.todolist.dto;

import com.sun.istack.NotNull;
import com.todolist.todolist.model.TaskEntity;
import lombok.Builder;

@Builder
public class DictionaryResponse {

    private Integer id;

    @NotNull
    private String name;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static DictionaryResponse fromEntity(TaskEntity entity) {
        return  DictionaryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
