package com.todolist.todolist.dto.user;


import com.sun.istack.NotNull;
import com.todolist.todolist.model.UserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;


@ApiModel(description = "Klasa reprezentująca odpowiedź zawierającą informacje o użytkowniku.")
@Builder
public class UserResponse {

//    @ApiModelProperty(notes = "Id")
//    @NotNull
//    private Integer id;

    @ApiModelProperty(notes = "Login")
    @NotNull
    private String login;

//    @ApiModelProperty(notes = "Password")
//    private String password;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String priority) {
//        this.password = password;
//    }


    public static UserResponse fromEntity(UserEntity entity) {
        return builder()
            //.id(entity.getId())
            .login(entity.getLogin())
            //.password(entity.getPassword())
            .build();
    }

}
