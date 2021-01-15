package com.maitech.repositories;

import com.maitech.models.RoleModel;
import com.maitech.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    UserModel findByUsername(String username);

    UserModel findByEmail(String email);

    UserModel findByPhone(String phone);

    Page<UserModel> findByGender(Pageable pageable, String gender);

    Page<UserModel> findByRoleModel(Pageable pageable, RoleModel roleModel);
}