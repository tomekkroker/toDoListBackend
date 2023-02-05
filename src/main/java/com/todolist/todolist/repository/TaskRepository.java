package com.todolist.todolist.repository;

import com.todolist.todolist.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    void deleteAllByListId(Integer id);
}
