package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.UserRegisterDTO;
import com.example.demo_project.model.entity.UserEntity;
import com.example.demo_project.model.entity.UserRoleEntity;
import com.example.demo_project.model.enums.UserRoleEnum;
import com.example.demo_project.repository.UserRepository;
import com.example.demo_project.repository.UserRoleRepository;
import com.example.demo_project.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean registerUser(UserRegisterDTO userRegisterDTO) {

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }
        Optional<UserEntity> existByEmail = userRepository.findByEmail(userRegisterDTO.getEmail());

        if (existByEmail.isPresent()) {
            return false;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userRegisterDTO.getFirstName());
        userEntity.setLastName(userRegisterDTO.getLastName());
        userEntity.setEmail(userRegisterDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(userEntity);
        return true;
}

    @Override
    public void promoteToAdmin(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);

        user.getRoles().add(adminRole);
        userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void removeAdminRole(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            // Проверяваме дали потребителят има администраторска роля преди да я премахнем
            List<UserRoleEntity> roles = user.getRoles();
            UserRoleEntity adminRole = roles.stream()
                    .filter(role -> role.getRole() == UserRoleEnum.ADMIN)
                    .findFirst()
                    .orElse(null);

            // Ако потребителят има администраторска роля, я премахваме
            if (adminRole != null && adminRole.getId().equals(userId)) {
                roles.remove(adminRole);
                userRepository.save(user);
            }
        }
    }
}