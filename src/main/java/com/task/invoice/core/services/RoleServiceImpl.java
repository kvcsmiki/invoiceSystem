package com.task.invoice.core.services;

import com.task.invoice.core.entities.Role;
import com.task.invoice.core.helpers.RoleHelper;
import com.task.invoice.core.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;


    @Override
    public List<Role> getAllRolesExceptAdmin() {
        return roleRepository.findAll().stream()
                .filter(role -> !role.getName().equals(RoleHelper.ADMIN.getValue()))
                .toList();
    }
    @Override
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> getRoles(List<String> names){
        List<Role> result = new ArrayList<>();
        names.stream().forEach(name -> result.add(roleRepository.findByName(name)));
        return result;
    }
}
