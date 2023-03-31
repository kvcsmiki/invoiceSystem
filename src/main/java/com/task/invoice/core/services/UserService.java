package com.task.invoice.core.services;

import com.task.invoice.core.dtos.RoleDto;
import com.task.invoice.core.dtos.UserDto;
import com.task.invoice.core.entities.Role;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> login(String username, String password);

    Optional<UserDto> logout();

    void registerUser(String username, String password, RoleDto role);
}
