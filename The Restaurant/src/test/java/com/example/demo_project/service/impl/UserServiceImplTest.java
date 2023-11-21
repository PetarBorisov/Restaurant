package com.example.demo_project.service.impl;

import com.example.demo_project.model.dto.UserRegisterDTO;
import com.example.demo_project.model.entity.UserEntity;

import com.example.demo_project.model.entity.UserRoleEntity;
import com.example.demo_project.model.enums.UserRoleEnum;
import com.example.demo_project.repository.UserRepository;
import com.example.demo_project.repository.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testRegisterUser_Success() {

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setEmail("test@example.com");
        userRegisterDTO.setPassword("password");
        userRegisterDTO.setConfirmPassword("password");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        boolean result = userService.registerUser(userRegisterDTO);


        assertTrue(result);
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(UserEntity.class));
    }

    @Test
    void testRegisterUser_PasswordMismatch() {

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setPassword("password");
        userRegisterDTO.setConfirmPassword("differentPassword");


        boolean result = userService.registerUser(userRegisterDTO);

        assertFalse(result);
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(UserEntity.class));
    }
    @Test
    void testPromoteToAdmin_Success() {
        long userId = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setId(1L);
        adminRole.setRole(UserRoleEnum.ADMIN);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        when(userRoleRepository.findByRole(UserRoleEnum.ADMIN)).thenReturn(adminRole);

        userService.promoteToAdmin(userId);

        verify(userRepository, times(1)).save(userEntity);
        assertTrue(userEntity.getRoles().stream().anyMatch(role -> role.getRole() == UserRoleEnum.ADMIN));
    }

    @Test
    void testPromoteToAdmin_UserNotFound() {
        long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.promoteToAdmin(userId));

        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void testGetAllUsers() {
        UserEntity userEntity = new UserEntity();
        when(userRepository.findAll()).thenReturn(Collections.singletonList(userEntity));

        assertEquals(1, userService.getAllUsers().size());

        verify(userRepository, times(1)).findAll();
    }


    @Test
    void testRemoveAdminRole_UserNotAdmin() {
        long userId = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);
        userEntity.setRoles(Collections.singletonList(userRole));

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        userService.removeAdminRole(userId);

        verify(userRepository, never()).save(userEntity);
    }

    @Test
    void testRemoveAdminRole_UserNotFound() {
        long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> userService.removeAdminRole(userId));

        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
