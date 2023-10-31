package com.example.demo_project.service;


import com.example.demo_project.model.dto.UserRegisterDTO;
import com.example.demo_project.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    boolean registerUser(UserRegisterDTO userRegisterDTO);

    void promoteToAdmin(Long userId);

    List<UserEntity> getAllUsers();

    void removeAdminRole(Long userId);
}
