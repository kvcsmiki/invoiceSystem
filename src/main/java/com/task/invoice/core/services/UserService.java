package com.task.invoice.core.services;

import com.task.invoice.core.dtos.RoleDto;
import com.task.invoice.core.dtos.UserDto;
import com.task.invoice.core.entities.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDto> login(String username, String password);

    Optional<UserDto> logout();

    boolean registerUser(String username, String password, String role);

    List<UserDto> getAllUsers();
    Optional<UserDto> getUser(String username);

    boolean saveRoles(String username, List<Role> roles);

    boolean deleteUser(String username);
}
