package com.maitech.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maitech.models.RoleModel;
import com.maitech.models.UserModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ApiModel(description = "User data")
@Data
@ToString
@NoArgsConstructor
public class UserDto implements Serializable {

    @ApiModelProperty(notes = "ID of the user", name = "id", required = true, value = "5c165e469f5d91393c9dff3a")
    private String id;

    @ApiModelProperty(notes = "FirstName of the user", name = "firstName", required = true, value = "john")
    private String firstName;

    @ApiModelProperty(notes = "LastName of the user", name = "lastName", required = true, value = "Dutchman")
    private String lastName;

    @ApiModelProperty(notes = "email of the user", name = "phone", required = true, value = "john@email.com")
    private String email;

    @ApiModelProperty(notes = "phone of the user", name = "phone", required = true, value = "237540125896")
    private String phone;

    @ApiModelProperty(notes = "Gender of the user", name = "gender", required = true, value = "MALE")
    private String gender;

    @ApiModelProperty(notes = "Username of the user", name = "username", required = true, value = "Aze123")
    private String username;

    @ApiModelProperty(notes = "Password of the user", name = "password", required = true, value = "az#|154df")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty(notes = "Role of the user", name = "role", required = true, value = "COMP")
    private String role;

    public UserDto(UserModel userModel) {
        this.id = userModel.getIdUserModel();
        this.firstName = userModel.getFirstName();
        this.lastName = userModel.getLastName();
        this.gender = userModel.getGender();
        this.email = userModel.getEmail();
        this.phone = userModel.getPhone();
        this.username = userModel.getUsername();
        this.password = userModel.getPassword();
        this.role = userModel.getRoleModel().getCode();
    }

    public UserModel toUserModel() {
        UserModel userModel = new UserModel();

        userModel.setIdUserModel(id);
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setGender(gender);
        userModel.setEmail(email);
        userModel.setPhone(phone);
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setRoleModel(new RoleModel(role));

        return userModel;
    }
}
