package com.aptech.sem4eprojectbe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptech.sem4eprojectbe.common.model.IdModel;
import com.aptech.sem4eprojectbe.entity.UserEntity;
import com.aptech.sem4eprojectbe.repository.UserRepository;
 

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity insertUser(UserEntity user){
        return userRepository.save(user);
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findByDeletedIsFalse();
    }

    public Optional<UserEntity> getUserById(IdModel idModel){
        return userRepository.findById(idModel.getId());
    }

    public UserEntity updateUser(UserEntity user){
        return userRepository.findById(user.getId())
        .map(userItem -> {
            userItem.setFirstname(user.getFirstname());
            userItem.setLastname(user.getLastname());
            userItem.setAddress(user.getAddress());
            userItem.setDistrict(user.getDistrict());
            userItem.setCity(user.getCity());
            userItem.setRole(user.getRole());
            userItem.setPhone(user.getPhone());
            userItem.setEmail(user.getEmail());
            userItem.setPassword(user.getPassword());
            userItem.setDeleted(user.getDeleted());
            return userRepository.save(userItem);
        })
        .orElseGet(() -> {
            UserEntity newUser = new UserEntity();
            newUser.setFirstname(user.getFirstname());
            newUser.setLastname(user.getLastname());
            newUser.setAddress(user.getAddress());
            newUser.setDistrict(user.getDistrict());
            newUser.setCity(user.getCity());
            newUser.setRole(user.getRole());
            newUser.setPhone(user.getPhone());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setDeleted(user.getDeleted());
            return userRepository.save(newUser);
        });
    }

}
