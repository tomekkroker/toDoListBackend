package com.todolist.todolist.controller;

import com.todolist.todolist.dto.user.UserRequest;
import com.todolist.todolist.dto.user.UserResponse;
import com.todolist.todolist.service.UserService;
import com.todolist.todolist.utils.BasicResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping()
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public ResponseEntity<UserResponse> getUser(String login, String password) {
        return ResponseEntity.ok(UserResponse.fromEntity(userService.getUser(login, password)));
    }

    @PostMapping
    public ResponseEntity<BasicResponse> createUser(@Valid @RequestBody UserRequest request) {
        var dto = userService.createUser(request);
        return ResponseEntity.created(UriBuilder.getUri("/register", dto.getLogin())).body(
                new BasicResponse(true, "Pomyślnie zarejestrowano użytkownika", dto)
        );
    }
}
