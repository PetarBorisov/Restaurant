package com.example.demo_project.repository;

import com.example.demo_project.model.entity.UserRoleEntity;
import com.example.demo_project.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findByRole(UserRoleEnum userRoleEnum);

}
