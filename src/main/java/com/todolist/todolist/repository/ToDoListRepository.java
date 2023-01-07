package com.todolist.todolist.repository;

import com.todolist.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoListRepository extends JpaRepository<Task, Integer> {

}
