package com.aptech.sem4eprojectbe.controller;
 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aptech.sem4eprojectbe.common.model.IdModel;
import com.aptech.sem4eprojectbe.common.model.ResponseModel;
import com.aptech.sem4eprojectbe.entity.UserEntity;
import com.aptech.sem4eprojectbe.service.UserService;
 

@RestController
@RequestMapping("/api/v1")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/insert-user")
    public ResponseModel insertUser(@RequestBody UserEntity user) {
        return new ResponseModel("ok", "success", userService.insertUser(user));
    }

    @GetMapping("/users")
    public ResponseModel getAllUsers(){
        return new ResponseModel("ok", "success", userService.getAllUsers());
    }

    @GetMapping("/user")
    public ResponseModel getUserById(@RequestBody IdModel idModel){
        Optional<UserEntity> user = userService.getUserById(idModel);
        if(user.isPresent() && !user.get().getDeleted()){
            return new ResponseModel("ok", "success", user.get());
        } else {
            return new ResponseModel("fail", "Can not find id : " + idModel.getId(), null);
        }
    }

    @PutMapping("/update-user")
    public ResponseModel updateUser(@RequestBody UserEntity user){
        return new ResponseModel("ok", "success", userService.updateUser(user));
    }

    @DeleteMapping("/delete-user")
    public ResponseModel deleteUser(@RequestBody IdModel idModel){
        Optional<UserEntity> user = userService.getUserById(idModel);
        if(user.isPresent()){
            UserEntity deleteUser = user.get();
            deleteUser.setDeleted(true);
            userService.updateUser(deleteUser);
            return new ResponseModel("ok", "success", null);
        } else {
            return new ResponseModel("fail", "Can not find id : "+ idModel.getId(), null);
        }
    }
}
