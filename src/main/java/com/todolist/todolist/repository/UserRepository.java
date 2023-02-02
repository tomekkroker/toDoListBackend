package com.todolist.todolist.repository;


import com.todolist.todolist.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  UserEntity findByLogin(String login);

  boolean existsByLogin(String login);

}
