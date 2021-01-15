package com.maitech.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserModel implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String idUserModel;

    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Column(name = "gender", nullable = false, length = 6)
    private String gender;

    @Column(name = "email", unique = true, nullable = false, length = 150)
    private String email;

    @Column(name = "phone", unique = true, nullable = false, length = 25)
    private String phone;

    @Column(name = "username", unique = true, nullable = false, length = 25)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    private RoleModel roleModel;

    public UserModel(String email) {
        this.email = email;
    }
}
