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
        request.setPassword(encryptCaesarCipher(request.getPassword()));
        return UserResponse.fromEntity(userRepository.save(fromSimpleDto(request)));
    }

    @Transactional
    public UserEntity getUser(String login, String password) {
        UserEntity user = userRepository.findByLogin(login);
        if (decryptCaesarCipher(user.getPassword()).equals(password)) {
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

    public static String encryptCaesarCipher(String input) {
        int key = 14;
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                int charCode = c;
                if (Character.isUpperCase(c)) {
                    charCode = (charCode + key - 65) % 26 + 65;
                } else {
                    charCode = (charCode + key - 97) % 26 + 97;
                }
                encrypted.append((char) charCode);
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    public static String decryptCaesarCipher(String input) {
        int key = 14;
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                int charCode = (int) c;
                if (Character.isUpperCase(c)) {
                    charCode = (charCode - key - 65 + 26) % 26 + 65;
                } else {
                    charCode = (charCode - key - 97 + 26) % 26 + 97;
                }
                decrypted.append((char) charCode);
            } else {
                decrypted.append(c);
            }
        }
        return decrypted.toString();
    }

}
