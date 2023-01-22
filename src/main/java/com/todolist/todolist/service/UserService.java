package com.todolist.todolist.service;

import com.todolist.todolist.dto.auth.RegisterRequest;
import com.todolist.todolist.exception.BadRequestException;
import com.todolist.todolist.model.UserEntity;
import com.todolist.todolist.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserEntity createAccount(RegisterRequest request) {
        if (userRepository.existsByUsernameIgnoreCase(request.getLogin())) {
            throw new BadRequestException("Login jest już używany");
        }
        return userRepository.save(fromRegisterDto(request));
    }



    public UserEntity fromRegisterDto(RegisterRequest dto) {
        return UserEntity.builder()
                .username(dto.getLogin())
                .password(passwordEncoder.encode(dto.getPassword()))
                        .build();
    }
}
