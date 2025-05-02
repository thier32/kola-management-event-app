package com.kola.management.event.user.services.impl;


import com.kola.management.event.kernel.exception.KernelException;
import com.kola.management.event.kernel.services.BaseKernelService;
import com.kola.management.event.user.dto.role.IRoleDto;
import com.kola.management.event.user.dto.role.RoleDescriptionDto;
import com.kola.management.event.user.dto.role.RoleNameDto;
import com.kola.management.event.user.dto.role.RolePermissionsDto;
import com.kola.management.event.user.model.Role;
import com.kola.management.event.user.repository.RoleRepository;
import com.kola.management.event.user.services.IRoleService;
import com.kola.management.event.user.services.exceptions.RoleServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BaseKernelService<Role> implements IRoleService {

    @Override
    public Role createRole(Role role) throws RoleServiceException {
        checkMandatoryProperty(role);
        verifyRoleExists(role);

        try {
            role =  this.save(role);
        }catch (KernelException exception){
            throw new RoleServiceException(exception.getMessage());
        }
        return role;
    }

    @Override
    public Role updateRole(Role role, Long roleId) throws RoleServiceException {
        try {
            role = update(role, roleId);
        } catch (KernelException kernelException) {
            throw new RoleServiceException(kernelException.getMessage());
        }
        return role;
    }

    @Override
    public Role updateRole(IRoleDto roleDto, Long roleId) throws RoleServiceException {
        Role role;
        try {
            role = update(roleDto, roleId);
        } catch (KernelException kernelException) {
            throw new RoleServiceException(kernelException.getMessage());
        }
        return role;
    }

    @Override
    public Role updateRoleNom(String nom, Long roleId) throws RoleServiceException {
        RoleNameDto roleNameDto = new RoleNameDto(nom);
        return updateRole(roleNameDto, roleId);
    }

    @Override
    public Role updateRoleDescription(String description, Long roleId) throws RoleServiceException {
        RoleDescriptionDto roleDescriptionDto = new RoleDescriptionDto(description);
        return updateRole(roleDescriptionDto, roleId);
    }

    @Override
    public Role updateRolePermissions(String permissions, Long roleId) throws RoleServiceException {
        RolePermissionsDto rolePermissionsDto = new RolePermissionsDto(permissions);
        return updateRole(rolePermissionsDto, roleId);
    }


    @Override
    public boolean verifyRoleExists(Long roleId) {
        return findRoleByRoleId(roleId) != null;
    }

    @Override
    public boolean verifyRoleExists(Role role) {
        return ((RoleRepository) getDefaultRepository())
                .findRoleByNomAndDescription(role.getNom(), role.getDescription()) != null;
    }

    @Override
    public boolean verifyRoleExists(String nom, String description) {
        return ((RoleRepository) getDefaultRepository())
                .findRoleByNomAndDescription(nom, description) != null;
    }

    @Override
    public Role findRoleByRoleId(Long roleId) {
        return ((RoleRepository) getDefaultRepository()).findRoleByRoleId(roleId);
    }

    @Override
    public List<Role> findRoleByNom(String nom) {
        return ((RoleRepository) getDefaultRepository()).findRoleByNom(nom);
    }

    @Override
    public List<Role> findRoleByPermissions(String permissions) {
        return ((RoleRepository) getDefaultRepository()).findRoleByPermissions(permissions);
    }

    @Override
    public Role checkRoleExists(Long roleId) throws RoleServiceException {
        Role role = findRoleByRoleId(roleId);
        if (role == null) {
            throw new RoleServiceException("Role with ID " + roleId + " not found.");
        }
        return role;
    }

    @Override
    public void checkMandatoryProperty(Role role) throws RoleServiceException {
        if (role.getNom() == null || role.getNom().isEmpty()) {
            throw new RoleServiceException("Property 'nom' is mandatory");
        }
        if (role.getPermissions() == null || role.getPermissions().isEmpty()) {
            throw new RoleServiceException("Property 'permissions' is mandatory");
        }
    }
}
