package com.maitech.dtos;

import com.maitech.models.RoleModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@ApiModel(description = "Role data")
@Data
@ToString
@NoArgsConstructor
public class RoleDto implements Serializable {

    @ApiModelProperty(notes = "ID of the role", name = "id", required = true, value = "5c165e469f5d91393c9dff3a")
    private String id;

    @ApiModelProperty(notes = "name of the role", name = "name", required = true, value = "COMPTABLE")
    private String name;

    @ApiModelProperty(notes = "code of the role", name = "code", required = true, value = "COMP")
    private String code;

    @ApiModelProperty(notes = "description of the role", name = "description", value = "role limité aux taches de comptabilité")
    private String description;

    public RoleDto(RoleModel roleModel) {
        this.id = roleModel.getIdRoleModel();
        this.name = roleModel.getName();
        this.code = roleModel.getCode();
        this.description = roleModel.getDescription();
    }

    public RoleModel toRoleModel() {
        RoleModel roleModel = new RoleModel();

        roleModel.setIdRoleModel(id);
        roleModel.setName(name);
        roleModel.setCode(code);
        roleModel.setDescription(description);

        return roleModel;
    }
}
