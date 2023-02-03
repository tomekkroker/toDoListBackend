package com.todolist.todolist.repository;

import com.todolist.todolist.dto.task.TaskResponse;
import com.todolist.todolist.model.TaskEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    List<TaskResponse> findByListId(Integer id);
}
