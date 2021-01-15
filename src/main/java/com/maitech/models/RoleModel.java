package com.maitech.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class RoleModel implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String idRoleModel = UUID.randomUUID().toString();

    @Column(name = "name",unique = true, nullable = false, length = 25)
    private String name;

    @Column(name = "code", unique = true, nullable = false, length = 10)
    private String code;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @OneToMany(mappedBy = "roleModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserModel> userModelList;

    public RoleModel(String code) {
        this.code = code;
    }
}
