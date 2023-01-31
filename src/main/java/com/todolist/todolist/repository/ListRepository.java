package com.todolist.todolist.repository;

import com.todolist.todolist.model.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<ListEntity, Integer> {

}
