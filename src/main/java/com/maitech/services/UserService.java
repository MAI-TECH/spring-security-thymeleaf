package com.maitech.services;

import com.maitech.models.RoleModel;
import com.maitech.models.UserModel;
import com.maitech.repositories.RoleRepository;
import com.maitech.repositories.UserRepository;
import com.maitech.utils.RegexValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RegexValidation regexValidation;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Page<UserModel> findUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<UserModel> searchUserByRole(Pageable pageable, RoleModel roleModel) {
        return userRepository.findByRoleModel(pageable, roleModel);
    }

    public Page<UserModel> searchUserByGender(Pageable pageable, String gender) {
        return userRepository.findByGender(pageable, gender);
    }

    public UserModel findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserModel findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserModel saveUser(UserModel userModel) throws Exception {
        UserModel user = new UserModel();
        List<String> errorsList = new ArrayList<>();

        if (userModel.getIdUserModel() != null)
            user.setIdUserModel(userModel.getIdUserModel());

        if (userModel.getFirstName().length() < 2)
            errorsList.add("Error : User firstName should have a least 02 characters.");
        user.setFirstName(userModel.getFirstName());

        if (userModel.getLastName().length() < 2)
            errorsList.add("Error : User lastName should have a least 02 characters.");
        user.setLastName(userModel.getLastName());

        if (!userModel.getGender().equalsIgnoreCase("MALE") && !userModel.getGender().equalsIgnoreCase("FEMALE"))
            errorsList.add("Error : User gender not exist.");
        user.setGender(userModel.getGender());

        if (!regexValidation.isValidEmail(userModel.getEmail()))
            errorsList.add("Error : User email not correct.");
        user.setEmail(userModel.getEmail());

        if (!regexValidation.isValidPhone(userModel.getPhone()))
            errorsList.add("Error : User phone not correct.");
        user.setPhone(userModel.getPhone());

        if (!regexValidation.isValidUsername(userModel.getUsername()))
            errorsList.add("Username must contain at least one letter, at least one number, and be longer than six characters.");
        user.setUsername(userModel.getUsername());

        if (!regexValidation.isValidPassword(userModel.getPassword()))
            errorsList.add("Error : User password should have minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character");
        user.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));

        if (!userModel.getPassword().equals(userModel.getConfirmPassword()))
            errorsList.add("Error : User confirm Password is not the same as the User password.");

        if (roleRepository.findByCode(userModel.getRoleModel().getCode()) == null)
            errorsList.add("Error : User role should not correct");
        user.setRoleModel(roleRepository.findByCode(userModel.getRoleModel().getCode()));

        if (!errorsList.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();

            for (String error : errorsList)
                errorMessage.append(error).append("\n");

            throw new Exception(errorMessage.toString());
        }

        return userRepository.save(user);
    }

    public boolean deleteUserById(String id) {
        UserModel user = userRepository.findById(id).orElse(null);

        if (user == null)
            return false;

        userRepository.delete(user);

        return true;
    }

    public boolean deleteUserByUsername(String username) {
        UserModel user = userRepository.findByUsername(username);

        if (user == null)
            return false;

        userRepository.delete(user);

        return true;
    }

    /*
    public boolean uploadfile(String id, MultipartFile file) throws IOException {
        UserModel userModel = userRepository.findById(id).orElse(null);

        if (userModel == null)
            throw new NotFoundException("ERROR : User is not found");

        userModel.setAvatar(file.getBytes());
        userModel.setContentType(file.getContentType());

        userRepository.save(userModel);
        return true;
    } */
}
