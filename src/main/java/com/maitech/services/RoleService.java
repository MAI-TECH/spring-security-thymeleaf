package com.maitech.services;

import com.maitech.models.RoleModel;
import com.maitech.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Page<RoleModel> findRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public RoleModel findRoleById(String id) {
        return roleRepository.findById(id).orElse(null);
    }

    public RoleModel findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public RoleModel findRoleByCode(String code) {
        return roleRepository.findByCode(code);
    }

    public RoleModel saveRole(RoleModel roleModel) throws Exception {
        RoleModel role = new RoleModel();
        List<String> errorsList = new ArrayList<>();

        if (roleModel.getIdRoleModel() != null)
            role.setIdRoleModel(roleModel.getIdRoleModel());

        if (roleModel.getName().length() < 5)
            errorsList.add("Error : Role name should have a least 05 characters");
        role.setName(roleModel.getName());

        if (roleModel.getCode().length() < 3)
            errorsList.add("Error : Role code should have a least 03 characters");
        role.setCode(roleModel.getCode());

        if (roleModel.getDescription().length() < 10)
            errorsList.add("Error : Role description should have a least 10 characters");
        role.setDescription(roleModel.getDescription());

        if (!errorsList.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();

            for (String error : errorsList)
                errorMessage.append(error).append("\n");

            throw new Exception(errorMessage.toString());
        }

        return roleRepository.save(role);
    }

    public boolean deleteRoleById(String id) {
        RoleModel role = roleRepository.findById(id).orElse(null);

        if (role == null)
            return false;

        roleRepository.delete(role);

        return true;
    }

    public boolean deleteRoleByName(String name) {
        RoleModel role = roleRepository.findByName(name);

        if (role == null)
            return false;

        roleRepository.delete(role);

        return true;
    }

    public boolean deleteRoleByCode(String code) {
        RoleModel role = roleRepository.findByCode(code);

        if (role == null)
            return false;

        roleRepository.delete(role);

        return true;
    }
}
