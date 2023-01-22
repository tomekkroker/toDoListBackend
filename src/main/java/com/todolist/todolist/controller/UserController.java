//package com.todolist.todolist.controller;
//
//import com.todolist.todolist.dto.auth.RegisterRequest;
//import io.swagger.annotations.Api;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriBuilder;
//
//@Api(tags = "Authorization of user access")
//@RestController
//@RequestMapping("/auth")
//public class UserController {
//
//    @PostMapping("/register")
//    @PreAuthorize("permitAll()")
//    public ResponseEntity<BasicResponse> register(@Valid @RequestBody RegisterRequest request) {
//        var dto = UserResponse.fromEntity(userService.createAccount(request));
//        return ResponseEntity.created(UriBuilder.getUri("/users/{username}", dto.getUsername()))
//                .body(new BasicResponse(
//                        true,
//                        "User added successfully",
//                        dto
//                ));
//    }
//}
