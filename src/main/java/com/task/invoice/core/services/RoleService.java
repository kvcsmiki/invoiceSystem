package com.task.invoice.core.services;

import com.task.invoice.core.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAllRolesExceptAdmin();
    List<Role> getAllRoles();

    Role getRole(String name);

    List<Role> getRoles(List<String> names);
}
