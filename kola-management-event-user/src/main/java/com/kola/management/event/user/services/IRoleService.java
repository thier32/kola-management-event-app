package com.kola.management.event.user.services;


import com.kola.management.event.user.dto.role.IRoleDto;
import com.kola.management.event.user.model.Role;
import com.kola.management.event.user.services.exceptions.RoleServiceException;

import java.util.List;

public interface IRoleService {

    Role createRole(Role role) throws RoleServiceException;

    Role updateRole(Role role, Long roleId)  throws RoleServiceException;

    Role updateRole(IRoleDto roleDto, Long roleId) throws RoleServiceException;

    Role updateRoleName(String nom, Long roleId)  throws RoleServiceException;

    Role updateRoleDescription(String description, Long roleId)  throws RoleServiceException;

    Role updateRolePermissions(String permissions, Long roleId)  throws RoleServiceException;

    boolean verifyRoleExists(Long roleId)  throws RoleServiceException;

    boolean verifyRoleExists(Role role);

    boolean verifyRoleExists(String nom, String description);

    Role findRoleByRoleId(Long roleId);

    List<Role> findRoleByName(String nom);

    List<Role> findRoleByPermissions(String permissions);

    /***
     * Verify whether a Document has the provided DocumentId
     *
     * @param roleId
     * @return Role
     * @throws RoleServiceException
     */
    public Role checkRoleExists(Long roleId) throws RoleServiceException;

    /**
     * Verify whether the mandatory params are provided
     *
     * @param role
     * @throws RoleServiceException
     */
    void checkMandatoryProperty(Role role) throws RoleServiceException;

}
