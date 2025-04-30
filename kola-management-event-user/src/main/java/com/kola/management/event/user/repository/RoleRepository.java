package com.kola.management.event.user.repository;

import com.kola.management.event.kernel.repository.BaseKernelRepository;
import com.kola.management.event.user.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends BaseKernelRepository<Role> {

    Role findRoleByRoleId(long roleId);

    List<Role> findRoleByNom(String nom);

    List<Role> findRoleByPermissions(String permissions);

    Role findRoleByNomAndDescription(String nom, String description);
}
