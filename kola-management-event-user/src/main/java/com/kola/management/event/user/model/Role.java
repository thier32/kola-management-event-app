package com.kola.management.event.user.model;

import com.kola.management.event.kernel.model.BaseKernelModel;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseKernelModel {

    public static final String roleIdProp = "roleId";
    public static final String nomProp = "nom";
    public static final String descriptionProp = "description";
    public static final String permissionsProp = "permissions";

    private Long roleId;

    private String nom;

    private String description;

    private String permissions;
}
