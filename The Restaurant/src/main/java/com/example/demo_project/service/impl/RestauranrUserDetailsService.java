package com.example.demo_project.service.impl;

import com.example.demo_project.model.entity.UserEntity;
import com.example.demo_project.model.entity.UserRoleEntity;
import com.example.demo_project.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public class RestauranrUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public RestauranrUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(RestauranrUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found!"));
    }
    private static UserDetails map(UserEntity userEntity) {
        return User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles().stream().map(RestauranrUserDetailsService::map).toList())
                .build();
    }
    private static GrantedAuthority map(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority(
                "ROLE_" + userRoleEntity.getRole().name()
        );
    }
}
