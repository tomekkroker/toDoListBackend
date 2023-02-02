package com.todolist.todolist.service;

import com.todolist.todolist.dto.user.UserRequest;
import com.todolist.todolist.dto.user.UserResponse;
import com.todolist.todolist.model.UserEntity;
import com.todolist.todolist.repository.UserRepository;
import com.todolist.todolist.utils.BadRequestException;
import com.todolist.todolist.utils.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new BadRequestException("Istnieje już użytkownik o takim loginie");
        }
        return UserResponse.fromEntity(userRepository.save(fromSimpleDto(request)));
    }

    @Transactional
    public UserEntity getUser(String login, String password) {
        UserEntity user = userRepository.findByLogin(login);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new NotFoundException("User", "login", login);
        }

    }

    public UserEntity fromSimpleDto(UserRequest dto) {
        return UserEntity.builder()
                .id(dto.getId())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .build();
    }
}
