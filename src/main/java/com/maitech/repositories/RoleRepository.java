package com.maitech.repositories;

import com.maitech.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, String> {
    RoleModel findByName(String name);

    RoleModel findByCode(String code);
}
