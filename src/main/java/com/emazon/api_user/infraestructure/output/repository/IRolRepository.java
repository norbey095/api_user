package com.emazon.api_user.infraestructure.output.repository;

import com.emazon.api_user.infraestructure.output.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<RolEntity, Integer> {

    RolEntity getRolByName(String name);
}
