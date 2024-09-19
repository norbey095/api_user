package com.emazon.api_user.infraestructure.output.jpa.repository;

import com.emazon.api_user.infraestructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);
}
